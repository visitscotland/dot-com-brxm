package com.visitscotland.brxm.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.hippobeans.ICentre;
import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.ICentreModule;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.DMSConstants;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.CMSProperties;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.Language;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import com.visitscotland.utils.DataServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vs.ase.dms.ProductTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class ICentreFactory {

    private static final Logger logger = LoggerFactory.getLogger(ICentreFactory.class);

    static final String BUNDLE_ID = "tourism.information";

    private final HippoUtilsService utils;
    private final DMSDataService dmsData;
    private final ResourceBundleService bundle;
    private final QuoteFactory quoteEmbedder;
    private final ImageFactory imageFactory;
    private final CMSProperties cmsProperties;
    private final SiteProperties siteProperties;
    private final HippoUtilsService hippoUtilsService;

    @Autowired
    public ICentreFactory(HippoUtilsService utils, DMSDataService dmsData, ResourceBundleService bundle, QuoteFactory quoteEmbedder,
                          ImageFactory imageFactory, CMSProperties cmsProperties, SiteProperties siteProperties,
                          HippoUtilsService hippoUtilsService) {
        this.utils = utils;
        this.dmsData = dmsData;
        this.bundle = bundle;
        this.quoteEmbedder = quoteEmbedder;
        this.imageFactory = imageFactory;
        this.cmsProperties = cmsProperties;
        this.siteProperties = siteProperties;
        this.hippoUtilsService = hippoUtilsService;
    }

    /**
     * Builds an iCentre module when there is enough data to build it or null when there is not.
     */
    public ICentreModule getModule(ICentre doc, Locale locale, String location) {
        logger.info("Creating iCentreModule for {}", doc.getPath());

        ICentreModule module = new ICentreModule();

        //Populate Title
        if (Contract.isEmpty(doc.getTitle())) {
            module.setTitle(bundle.getResourceBundle(BUNDLE_ID, "icentre.title.default", locale));
        } else {
            module.setTitle(doc.getTitle());
        }

        //Populate Image
        if (doc.getImage() != null) {
            module.setImage(imageFactory.createImage(doc.getImage(), module, locale));
        }

        //Quote
        if (doc.getQuote() != null) {
            module.setQuote(quoteEmbedder.getQuote(doc.getQuote(), module, locale));
            if (module.getImage() == null && module.getQuote().getLink() != null && module.getQuote().getLink().getImage() != null) {
                module.setImage(module.getQuote().getLink().getImage());
            }
        }

        //Default the Image if it hasn't been set
        if (module.getImage() == null) {
            try {
                Image defaultImage = utils.getDocumentFromNode(bundle.getResourceBundle(BUNDLE_ID, "icentre.image.default", locale));
                module.setImage(imageFactory.createImage(defaultImage, module, locale));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return module;
    }


    /**
     * Get the list of links for a location and a locale. When the location is not provided a link to the iCentres page
     * would be returned
     */
    private List<FlatLink> getLinks(String location, Locale locale) {
        if (!Contract.isEmpty(location)) {
            return getVicList(location, locale);
        } else {

            return getICentreLandingLink(locale);
        }
    }

    private List<FlatLink> getICentreLandingLink(Locale locale) {
        String url = hippoUtilsService.createUrlFromNode(siteProperties.getSiteICentre(), true);
        String text = bundle.getResourceBundle(BUNDLE_ID, "icentre.description.link.text", locale);

        return Collections.singletonList(new FlatLink(text, url, LinkType.INTERNAL));
    }

    /**
     * Queries the DMS and return a list of iCentre for the specific location
     * <p>
     * The location must be specified
     */
    private List<FlatLink> getVicList(String location, Locale locale) {
        if (Contract.isNull(location)) {
            return Collections.emptyList();
        }

        logger.info("Calulating list of iCentres for the location {} (locale {})", location, locale);
        List<FlatLink> vicList = new ArrayList<>();

        ProductSearchBuilder dmsQuery = VsComponentManager.get(ProductSearchBuilder.class).location(location)
                .productTypes(DMSConstants.TYPE_SERVICES).category(DMSConstants.CAT_ICENTRE)
                .sortBy(DMSConstants.SORT_ALPHA);

        //Retrieves the iCenters for a location
        JsonNode node = dmsData.legacyMapSearch(dmsQuery.buildDataMap(true));

        for (JsonNode child : node) {
            if (child.has(DMSConstants.MapSearch.PROPERTIES) &&
                    child.get(DMSConstants.MapSearch.PROPERTIES).has(DMSConstants.MapSearch.NAME) &&
                    child.get(DMSConstants.MapSearch.PROPERTIES).has(DMSConstants.MapSearch.ID)) {
                String label = child.get(DMSConstants.MapSearch.PROPERTIES).get(DMSConstants.MapSearch.NAME).asText();
                String id = child.get(DMSConstants.MapSearch.PROPERTIES).get(DMSConstants.MapSearch.ID).asText();
                String languagePath = Language.getLanguageForLocale(locale).getDmsPrefix();
                String url = cmsProperties.getDmsHost() + DataServiceUtils.getProductURL(label, id, ProductTypes.TOURIST_INFO.getUrlPath(), languagePath);
                vicList.add(new FlatLink(label, url, LinkType.INTERNAL));
            }
        }

        return vicList;
    }
}
