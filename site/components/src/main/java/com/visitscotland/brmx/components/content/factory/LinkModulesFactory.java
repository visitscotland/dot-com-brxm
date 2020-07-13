package com.visitscotland.brmx.components.content.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brmx.beans.*;
import com.visitscotland.brmx.beans.capabilities.Linkable;
import com.visitscotland.brmx.beans.dms.LocationObject;
import com.visitscotland.brmx.beans.mapping.Coordinates;
import com.visitscotland.brmx.beans.mapping.FlatImage;
import com.visitscotland.brmx.beans.mapping.FlatLink;
import com.visitscotland.brmx.beans.mapping.megalinks.*;
import com.visitscotland.brmx.dms.LocationLoader;
import com.visitscotland.brmx.dms.ProductSearchBuilder;
import com.visitscotland.brmx.utils.CommonUtils;
import com.visitscotland.brmx.utils.HippoUtilsService;
import com.visitscotland.brmx.utils.Properties;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class LinkModulesFactory {

    private static final Logger logger = LoggerFactory.getLogger(LinkModulesFactory.class);

    public final static int MAX_ITEMS = 6;

    private final static String URL = "url";
    private final static String IMAGE = "images";

    final HippoUtilsService utils;
    final ProductSearchBuilder psBuilder;

    public LinkModulesFactory() {
        this(new HippoUtilsService(), new ProductSearchBuilder());
    }

    public LinkModulesFactory(HippoUtilsService utils, ProductSearchBuilder psb) {
        this.utils = utils;
        this.psBuilder = psb;
    }

    public AbstractLayout getMegalinkModule(Megalinks doc, Locale locale){
        if (!Boolean.TRUE.equals(doc.getListLayout()) && doc.getSingleImageModule() != null){
            return singleImageLayout(doc, locale);
        } else if (Boolean.TRUE.equals(doc.getListLayout()) || doc.getMegalinkItems().size() > MAX_ITEMS){
            return list(doc, locale);
        } else {
            return featuredLayout(doc, locale);
        }
    }

    public SingleImageLayout singleImageLayout(Megalinks doc, Locale locale){
        SingleImageLayout sil = new SingleImageLayout ();
        sil.setTitle(doc.getTitle());
        sil.setIntroduction(doc.getIntroduction());

        sil.setInnerTitle(doc.getSingleImageModule().getTitle());
        sil.setInnerIntroduction(doc.getSingleImageModule().getIntroduction());
        sil.setImage(createFlatImage(doc.getSingleImageModule().getImage(),locale));
        sil.setFullWidth(doc.getSingleImageModule().getFullWidth());
        sil.setLinks(convertoToFlatLinks(doc.getMegalinkItems(), locale));
        sil.setMegalinkItem(doc);

        //TODO cta?
        //sil.setCta(doc.getCta());

        return sil;
    }

    /**
     * Converts a Megalinks document into a FeaturedLayout Object for easier the consumption of the data on the front
     * end.
     *
     * The number of featured items marked on the document might change depending on the following rules:
     * <ul>
     * <li>Megalinks with 2 or less featured items won't have featured items</li>
     * <li>Megalinks with 3 items might have up to one featured items</li>
     * <li>Megalinks with 4 or more items will have between 1 and 2 featured items</li>
     * <li>When the number of featured items exceed what is expected, only the first items will remain as Featured</li>
     * <li>When the number of featured items is inferion to the expected, the first non-featured items will be promoted
     * to featured items</li>
     * </ul>
     *
     * @param doc Megalink document to be converted.
     * @param locale Locale of the request
     *
     * @return FeaturedLayout containing the relevant information from the Megalinks document
     */
    public FeaturedLayout featuredLayout(Megalinks doc, Locale locale){
        FeaturedLayout fl = new FeaturedLayout();
        fl.setTitle(doc.getTitle());
        fl.setIntroduction(doc.getIntroduction());
        fl.setTeaserVisible(doc.getTeaserVisible());
        fl.setMegalinkItem(doc);
        // TODO cta?
        //l.setCta();

        fl.setLinks(convertToEnhancedLinks(doc.getMegalinkItems(), locale));

        //There is no featured items when the amount of items is inferior to 3
        if (fl.getLinks().size() > 2) {
            //For 3 links the maximum of 1 featured item.  From 4 on the maximum is 2 featured items.
            fl.setFeaturedLinks(fl.getLinks().stream()
                    .filter(link -> link.isFeatured())
                    .limit(fl.getLinks().size() == 3 ? 1 : 2)
                    .collect(Collectors.toCollection(ArrayList::new)));

            //When there is more than 3 items and no featured item the first item is promoted as featured.
            if (fl.getFeaturedLinks().size() == 0 && fl.getLinks().size() > 3){
                fl.getFeaturedLinks().add(fl.getLinks().get(0));
            }

            //Links added to the Featured list MUST be removed from the original list
            fl.getLinks().removeAll(fl.getFeaturedLinks());
        } else {
            fl.setFeaturedLinks(Collections.EMPTY_LIST);
        }

        return fl;
    }

    public ListLayout list(Megalinks doc, Locale locale){
        ListLayout ll = new ListLayout();
        ll.setTitle(doc.getTitle());
        ll.setIntroduction(doc.getIntroduction());
        ll.setTeaserVisible(doc.getTeaserVisible());
        ll.setLinks(convertToEnhancedLinks(doc.getMegalinkItems(), locale));
        ll.setMegalinkItem(doc);
        // TODO cta?
        //l.setCta();

        return ll;
    }

    List<FlatLink> convertoToFlatLinks(List<MegalinkItem> items, Locale locale){
        List<FlatLink> links = new ArrayList<>();
        for (MegalinkItem item : items){
            if (item.getLink() == null) {
                CommonUtils.contentIssue("The module %s contains a link without any reference", item.getPath());
            } else if (item.getLink() instanceof Page){
                links.add(new FlatLink(((Page) item.getLink()).getTitle(), utils.createUrl(item.getLink())));
            } else if (item.getLink() instanceof SharedLink) {
                JsonNode node = getNodeFromSharedLink((SharedLink) item.getLink(), locale);
                links.add(new FlatLink(((SharedLink) item.getLink()).getTitle(), getPlainLink(item.getLink(), locale, node)));
            } else {
                CommonUtils.contentIssue("The module %s is pointing to a document of type %s which cannot be rendered as a page", item.getPath(), item.getLink().getClass().getSimpleName());
            }
        }
        return links;
    }

    List<EnhancedLink> convertToEnhancedLinks(List<MegalinkItem> items, Locale locale){
        List<EnhancedLink> links = new ArrayList<>();
        for (MegalinkItem item : items){

            if (item.getLink() == null) {
                CommonUtils.contentIssue("The module %s contains a link without any reference", item.getPath());
            } else if (item.getLink() instanceof Linkable){
                EnhancedLink link = new EnhancedLink();
                FlatImage image = null;
                link.setTeaser(((Linkable) item.getLink()).getTeaser());
                link.setLabel(((Linkable) item.getLink()).getTitle());
                link.setFeatured(item.getFeature());
                if (((Linkable) item.getLink()).getImage() != null){
                    image = createFlatImage(((Linkable) item.getLink()).getImage(),locale);
                }

                if (item.getLink() instanceof Page) {
                    link.setLink(utils.createUrl(item.getLink()));
                } else if (item.getLink() instanceof SharedLink) {
                    JsonNode product = getNodeFromSharedLink((SharedLink) item.getLink(), locale);
                    if (image == null && product != null && product.has(IMAGE)){
                        image = new FlatImage(product);
                    }
                    //TODO do the conversion in getplainLInk
                    link.setLink(getPlainLink(((SharedLink)item.getLink()).getLinkType(), locale, product));
                } else {
                    //TODO log.error
                }

                if (image == null){
                    //TODO log content issue
                } else {
                    link.setImage(image);
                }

                links.add(link);
            } else {
                CommonUtils.contentIssue("The module %s is pointing to a document of type %s which cannot be rendered as a page", item.getPath(), item.getLink().getClass().getSimpleName());
            }
        }
        return links;
    }

    private JsonNode getNodeFromSharedLink(SharedLink link, Locale locale){
        if (link.getLinkType() instanceof DMSLink){
            try {
                return CommonUtils.getProduct(((DMSLink) link.getLinkType()).getProduct(), locale);
            } catch (IOException e) {
                //TODO: log
                e.printStackTrace();
            }
        }
        return null;
    }

    private FlatImage createFlatImage(Image img, Locale locale) {
        FlatImage flatImage = new FlatImage(img, locale);
        LocationObject locationObject = getLocation(img.getLocation(), locale);
        if (locationObject!=null) {
            flatImage.setCoordinates(new Coordinates(locationObject.getLatitude(), locationObject.getLongitude()));
        }

        return flatImage;
    }

    private String getPlainLink(HippoBean link, Locale locale, JsonNode product){
        String plainLink = null;
        if (link instanceof DMSLink){
            if (product == null) {//((DMSLink) link).getDmsData(locale)
                //TODO: Log content error
            } else {
                plainLink = Properties.VS_DMS_SERVICE + product.get(URL).asText();
            }
        } else if (link instanceof ExternalLink) {
            plainLink = ((ExternalLink) link).getLink();
        } else if (link instanceof ProductsSearch){
            plainLink = psBuilder.fromHippoBean((ProductsSearch)link).build();
        } else {
            //TODO: log error
        }
        return plainLink;

    }

    LocationObject getLocation(String location, Locale locale){
        return LocationLoader.getLocation(location, locale);
    }


}
