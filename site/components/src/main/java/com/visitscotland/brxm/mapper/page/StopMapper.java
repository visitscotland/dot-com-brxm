package com.visitscotland.brxm.mapper.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.hippobeans.DMSLink;
import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.hippobeans.ItineraryExternalLink;
import com.visitscotland.brxm.hippobeans.Stop;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.model.Coordinates;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.ItineraryStopModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.*;
import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.OPENING;

@Deprecated
@Component
public class StopMapper {

    static final String BUNDLE_FILE = "itinerary";

    private final ResourceBundleService bundle;
    private final DMSDataService dmsData;
    private final ImageMapper imageMapper;
    private final DMSUtils utils;
    private final LinkService linkService;
    private final Logger contentLogger;

    public StopMapper(ResourceBundleService bundle, DMSDataService dmsData, ImageMapper imageMapper, DMSUtils utils,
                      LinkService linkService, Logger contentLogger) {
        this.bundle = bundle;
        this.dmsData = dmsData;
        this.imageMapper = imageMapper;
        this.utils = utils;
        this.linkService = linkService;
        this.contentLogger = contentLogger;
    }

    /**
     * Transform a Stop document in a ItineraryStopModule and add extra information depending on the type
     */
    public ItineraryStopModule generateStop(Locale locale, Stop stop, Itinerary itinerary, Integer index){
        ItineraryStopModule module = initializeStop(stop);
        module.setIndex(index);

        if (stop.getImage() != null) {
            module.setImage(imageMapper.createImage(stop.getImage(), module, locale));
        }

        if (stop.getStopItem() instanceof DMSLink) {
            processDMSStop(locale, module, (DMSLink) stop.getStopItem());
        } else if (stop.getStopItem() instanceof ItineraryExternalLink) {
            processExternalStop(locale, module, (ItineraryExternalLink) stop.getStopItem());
        } else {
            contentLogger.warn("The product's id  was not provided for {}, Stop {}", itinerary.getName(), module.getIndex());
        }

        if (module.getImage() == null) {
            contentLogger.warn("An image could not be found for {}, Stop {}", itinerary.getName(), module.getIndex());
        }

        if (module.getSubTitle() == null) {
            contentLogger.warn("The stop {} does not have a subtitle. Itinerary {}", module.getIndex(), itinerary.getName());
        }

        return module;
    }

    /**
     * Creates a Stop from the stop Document type
     */
    private ItineraryStopModule initializeStop(Stop stop) {
        ItineraryStopModule module = new ItineraryStopModule();
        module.setHippoBean(stop);
        module.setTitle(stop.getTitle());
        module.setDescription(stop.getDescription());
        module.setSubTitle(stop.getSubtitle());

        if (stop.getStopTip()!=null){
            module.setTipsTitle(stop.getStopTip().getTitle());
            module.setTipsBody(stop.getStopTip().getCopy());
        }

        return module;
    }

    /**
     * Extracts all relevant information for an External link in order to enhance the stop
     */
    public void processExternalStop(Locale locale, ItineraryStopModule module, ItineraryExternalLink externalLink) {
        if (!Contract.isEmpty(externalLink.getTimeToExplore())) {
            module.setTimeToExplore(generateTimeToExplore(externalLink.getTimeToExplore(), locale));
        }

        if (externalLink.getExternalLink() != null) {
            FlatLink ctaLink = linkService.createExternalLink(locale, externalLink.getExternalLink().getLink(),
                    !externalLink.getExternalLink().getLabel().isEmpty()? externalLink.getExternalLink().getLabel(): bundle.getFindOutMoreAboutCta(module.getTitle(), locale),
                    externalLink.getPath());
            module.setCtaLink(ctaLink);
        }

        if (externalLink.getCoordinates() != null) {
            module.setCoordinates(new Coordinates(externalLink.getCoordinates().getLatitude(), externalLink.getCoordinates().getLongitude()));
        }
    }

    /**
     * Generates the text for time to explore
     */
    private String generateTimeToExplore(String visitDuration, Locale locale){
        return visitDuration + " " + bundle.getResourceBundle(BUNDLE_FILE, visitDuration.equals("1") ?"stop.hour": "stop.hours", locale);
    }

    /**
     * Extracts all relevant information from the Product Card in order to enhance the Stop
     */
    public void processDMSStop(Locale locale, ItineraryStopModule module, DMSLink dmsLink) {
        JsonNode product = dmsData.productCard(dmsLink.getProduct(), locale);

        if (product == null) {
            String message = String.format("The DMS product added to '%s' was not found, please review the DMS Product id field at: %s ", module.getTitle(), dmsLink.getPath());
            module.addErrorMessage(message);
            contentLogger.warn(message);

            return;
        }

        module.setCtaLink(linkService.createDmsLink(locale, dmsLink, product, bundle.getFindOutMoreAboutCta(module.getTitle(), locale)));
        module.setFacilities(utils.getKeyFacilities(product));

        if (module.getImage() == null && product.has(IMAGE)) {
            module.setImage(imageMapper.createImage(product, module, locale));
        }

        if (product.has(ADDRESS)) {
            JsonNode address = product.get(ADDRESS);
            module.setAddress(address);
            if (address.has(LOCATION) && Contract.isEmpty(module.getSubTitle())){
                module.setSubTitle(address.get(LOCATION).asText());
            }
        }

        if (product.has(LATITUDE) && product.has(LONGITUDE)) {
            module.setCoordinates(new Coordinates(product.get(LATITUDE).asDouble(), product.get(LONGITUDE).asDouble()));
        } else {
            String message = String.format("The DMS product added to '%s' does not have coordinates, please review the DMS Product id field at: %s ", module.getTitle(), dmsLink.getPath());
            module.addErrorMessage(message);
            contentLogger.error(message);
        }

        if (product.has(TIME_TO_EXPLORE)) {
            module.setTimeToExplore(generateTimeToExplore(product.get(TIME_TO_EXPLORE).asText(), locale));
        }

        if (product.has(PRICE) && product.get(PRICE).has(DISPLAY_PRICE)) {
            module.setPrice(product.get(PRICE).get(DISPLAY_PRICE).asText());
        }

        if (product.has(OPENING)){
            JsonNode opening = product.get(OPENING);
            module.setOpening(opening);
            module.setOpenLink(new FlatLink(bundle.getResourceBundle(BUNDLE_FILE, "stop.opening", locale),
                    module.getCtaLink().getLink() + "#opening", null));        }
    }
}
