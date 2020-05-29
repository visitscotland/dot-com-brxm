package com.visitscotland.brmx.utils;

import com.visitscotland.brmx.beans.Image;
import com.visitscotland.brmx.beans.MegaLinkItem;
import com.visitscotland.brmx.beans.MegaLinks;
import com.visitscotland.brmx.beans.Page;
import com.visitscotland.brmx.beans.dms.LocationObject;
import com.visitscotland.brmx.beans.mapping.Coordinates;
import com.visitscotland.brmx.beans.mapping.FlatImage;
import com.visitscotland.brmx.beans.mapping.FlatLink;
import com.visitscotland.brmx.beans.mapping.megalinks.*;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class LinkModulesFactory {

    private static final Logger logger = LoggerFactory.getLogger(LinkModulesFactory.class);

    final static int MAX_ITEMS = 6;

    public AbstractLayout getMegalinkModule(MegaLinks doc, Locale locale){
        if (!doc.getList() && doc.getSingleImageModule() != null){
            return singleImageLayout(doc, locale);
        } else if (doc.getList() || doc.getMegaLinkItems().size() > MAX_ITEMS){
            return list(doc, locale);
        } else {
            return featuredLayout(doc, locale);
        }
    }

    public SingleImageLayout singleImageLayout(MegaLinks doc, Locale locale){
        SingleImageLayout sil = new SingleImageLayout ();
        sil.setTitle(doc.getTitle());
        sil.setIntroduction(doc.getIntroduction());

        sil.setInnerTitle(doc.getSingleImageModule().getTitle());
        sil.setInnerIntroduction(doc.getSingleImageModule().getIntroduction());
        sil.setImage(createFlatImage(doc.getSingleImageModule().getImage(),locale));
        sil.setFullWidth(doc.getSingleImageModule().getFullWidth());
        sil.setLinks(convertoToFlatLinks(doc.getMegaLinkItems()));
        sil.setMegaLinkItem(doc);

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
    public FeaturedLayout featuredLayout(MegaLinks doc, Locale locale){
        FeaturedLayout fl = new FeaturedLayout();
        fl.setTitle(doc.getTitle());
        fl.setIntroduction(doc.getIntroduction());
        fl.setHideTeaser(doc.getHideTeaser());
        fl.setMegaLinkItem(doc);
        // TODO cta?
        //l.setCta();

        fl.setLinks(convertToEnhancedLinks(doc.getMegaLinkItems(), locale));

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

    public ListLayout list(MegaLinks doc, Locale locale){
        ListLayout ll = new ListLayout();
        ll.setTitle(doc.getTitle());
        ll.setIntroduction(doc.getIntroduction());
        ll.setHideTeaser(doc.getHideTeaser());
        ll.setLinks(convertToEnhancedLinks(doc.getMegaLinkItems(), locale));
        ll.setMegaLinkItem(doc);
        // TODO cta?
        //l.setCta();

        return ll;
    }

    List<FlatLink> convertoToFlatLinks(List<MegaLinkItem> items){
        List<FlatLink> links = new ArrayList<>();
        for (MegaLinkItem item : items){
            if (item.getLink() == null) {
                CommonUtils.contentIssue("The module %s contains a link without any reference", item.getPath());
            } else if (item.getLink() instanceof Page){
                links.add(new FlatLink(((Page) item.getLink()).getTitle(), createUrl(item.getLink())));
            } else {
                CommonUtils.contentIssue("The module %s is pointing to a document of type %s which cannot be rendered as a page", item.getPath(), item.getLink().getClass().getSimpleName());
            }
        }
        return links;
    }

    List<EnhancedLink> convertToEnhancedLinks(List<MegaLinkItem> items, Locale locale){
        List<EnhancedLink> links = new ArrayList<>();
        for (MegaLinkItem item : items){

            if (item.getLink() == null) {
                CommonUtils.contentIssue("The module %s contains a link without any reference", item.getPath());
            } else if (item.getLink() instanceof Page){
                EnhancedLink link = new EnhancedLink();
                link.setTeaser(((Page) item.getLink()).getTeaser());
                link.setLabel(((Page) item.getLink()).getTitle());
                link.setLink(createUrl(item.getLink()));
                link.setImage(createFlatImage(((Page) item.getLink()).getHeroImage(),locale));
                link.setFeatured(item.getFeature());

                links.add(link);
            } else {
                CommonUtils.contentIssue("The module %s is pointing to a document of type %s which cannot be rendered as a page", item.getPath(), item.getLink().getClass().getSimpleName());
            }
        }
        return links;
    }

    private FlatImage createFlatImage(Image img, Locale locale) {
        FlatImage flatImage = new FlatImage(img, locale);
        LocationObject locationObject = getLocation(img.getLocation(), locale);
        if (locationObject!=null) {
            flatImage.setCoordinates(new Coordinates(locationObject.getLatitude(), locationObject.getLongitude()));
        }

        return flatImage;
    }

    /**
     *
     * @param page
     * @return
     */
    String createUrl(HippoBean page){
        return HippoUtils.createUrl(page);
    }

    LocationObject getLocation(String location, Locale locale){
        return LocationLoader.getLocation(location, locale);
    }


}
