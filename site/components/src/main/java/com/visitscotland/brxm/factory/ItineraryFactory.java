package com.visitscotland.brxm.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.Coordinates;
import com.visitscotland.brxm.services.CommonUtilsService;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.Properties;
import com.visitscotland.utils.Contract;
import com.visitscotland.utils.CoordinateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;

import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.*;

@Component
public class ItineraryFactory {

    private static final Logger logger = LoggerFactory.getLogger(ItineraryFactory.class);

    static final String BUNDLE_FILE = "itinerary";

    private final ResourceBundleService bundle;
    private final DMSDataService dmsData;
    private final ImageFactory imageFactory;
    private final DMSUtils utils;
    private final DocumentUtilsService documentUtils;
    private final Properties properties;


    public ItineraryFactory(ResourceBundleService bundle, DMSDataService dmsData, ImageFactory imageFactory, DMSUtils utils, DocumentUtilsService documentUtils, Properties properties) {
        this.bundle = bundle;
        this.dmsData = dmsData;
        this.imageFactory = imageFactory;
        this.utils = utils;
        this.documentUtils = documentUtils;
        this.properties = properties;
    }

    /**
     * Collects the information about an itinerary and enhances the information in it
     */
    public ItineraryPage buildItinerary (Itinerary itinerary, Locale locale){
        final boolean calculateDistance = (itinerary.getDistance() == null || itinerary.getDistance() == 0);

        ItineraryPage page = new ItineraryPage();
        ItineraryStopModule firstStop = null;
        ItineraryStopModule lastStop = null;
        BigDecimal totalDistance = BigDecimal.ZERO;
        Coordinates prevCoordinates = null;
        int index = 1;

        page.setDocument(itinerary);
        page.setDays(documentUtils.getAllowedDocuments(itinerary, Day.class));

        for (Day day : page.getDays()) {
            for (Stop stop : day.getStops()) {
                ItineraryStopModule module = generateStop(locale, stop, itinerary, index++);

                lastStop = module;
                if (firstStop == null) {
                    firstStop = lastStop;
                }

                if (calculateDistance && module.getCoordinates() != null) {
                    totalDistance = totalDistance.add(getDistanceStops(prevCoordinates, module.getCoordinates()));
                    prevCoordinates = module.getCoordinates();
                }

                page.addStop(module);
            }
        }

        page.setDistance(calculateDistance ? totalDistance :BigDecimal.valueOf(itinerary.getDistance()));

        populateFirstAndLastStopTexts(page, firstStop, lastStop);

        return page;
    }

    /**
     * Populates the first stop text and the last stop text depending on whether they have been set or not in the
     * Itinerary document
     */
    private void populateFirstAndLastStopTexts(ItineraryPage page, ItineraryStopModule first, ItineraryStopModule last){
        Itinerary itinerary = page.getDocument();

        if (Contract.isEmpty(itinerary.getStart()) && first != null) {
            page.setFirstStopLocation(first.getSubTitle());
        } else {
            page.setFirstStopLocation(itinerary.getStart());
        }

        if(Contract.isEmpty(itinerary.getFinish()) && last != null) {
            page.setLastStopLocation(last.getSubTitle());
        } else {
            page.setLastStopLocation(itinerary.getFinish());
        }
    }

    /**
     * Method to calculate the distance between stops
     */
    private BigDecimal getDistanceStops(Coordinates previous, Coordinates current) {
        if (previous == null || current == null){
            return BigDecimal.ZERO;
        } else {
            return CoordinateUtils.haversineDistance(
                    BigDecimal.valueOf(previous.getLatitude()), BigDecimal.valueOf(previous.getLongitude()),
                    BigDecimal.valueOf(current.getLatitude()), BigDecimal.valueOf(current.getLongitude()),
                    true, "#,###,##0.0");
        }
    }

    /**
     * Transform an Stop document in a ItineraryStopModule and add extra information depending on the type
     */
    public ItineraryStopModule generateStop(Locale locale, Stop stop, Itinerary itinerary, Integer index){
        ItineraryStopModule module = initializeStop(stop);
        module.setIndex(index);

        if (stop.getImage() != null) {
            module.setImage(imageFactory.createImage(stop.getImage(), module, locale));
        }

        if (stop.getStopItem() instanceof DMSLink) {
            processDMSStop(locale, itinerary, module, (DMSLink) stop.getStopItem());
        } else if (stop.getStopItem() instanceof ItineraryExternalLink) {
            processExternalStop(locale, module, (ItineraryExternalLink) stop.getStopItem());
        } else if (logger.isWarnEnabled()) {
            logger.warn(CommonUtilsService.contentIssue("The product's id  was not provided for %s, Stop %s", itinerary.getName(), module.getIndex()));
        }

        if (module.getImage() == null && logger.isWarnEnabled()) {
            logger.warn(CommonUtilsService.contentIssue("An image could not be found for %s, Stop %s", itinerary.getName(), module.getIndex()));
        }

        if (module.getSubTitle() == null && logger.isWarnEnabled()) {
            logger.warn(CommonUtilsService.contentIssue("The stop %s does not have a subtitle. Itinerary %s", module.getIndex(), itinerary.getName()));
        }

        return module;
    }

    /**
     * Creates an Stop from the stop Document type
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
     * Generates the text for time to explore
     */
    private String generateTimeToExplore(String visitDuration, Locale locale){
        return visitDuration + " " + bundle.getResourceBundle(BUNDLE_FILE, visitDuration.equals("1") ?"stop.hour": "stop.hours", locale);
    }

    /**
     * Extracts all relevant information for an External link in order to enhance the stop
     */
    public void processExternalStop(Locale locale, ItineraryStopModule module, ItineraryExternalLink externalLink) {
        if (!Contract.isEmpty(externalLink.getTimeToExplore())) {
            module.setTimeToExplore(generateTimeToExplore(externalLink.getTimeToExplore(), locale));
        }

        if (externalLink.getExternalLink() != null) {
            FlatLink ctaLink = new FlatLink(bundle.getCtaLabel(externalLink.getExternalLink().getLabel(), locale),
                    externalLink.getExternalLink().getLink(), LinkType.EXTERNAL);
            module.setCtaLink(ctaLink);
        }

        if (externalLink.getCoordinates() != null) {
            module.setCoordinates(new Coordinates(externalLink.getCoordinates().getLatitude(), externalLink.getCoordinates().getLongitude()));
        }
    }

    /**
     * Extracts all relevant information from the Product Card in order to enhance the Stop
     */
    public void processDMSStop(Locale locale, Itinerary itinerary, ItineraryStopModule module, DMSLink dmsLink) {
        JsonNode product = dmsData.productCard(dmsLink.getProduct(), locale);

        if (product == null) {
            module.addErrorMessage("The product id does not match in the DMS");
            if (logger.isWarnEnabled()) {
                logger.warn(CommonUtilsService.contentIssue("The product id does not match in the DMS for %s, Stop %s", itinerary.getName(), module.getIndex()));
            }
            return;
        }

        module.setCtaLink(new FlatLink(bundle.getCtaLabel(dmsLink.getLabel(), locale), product.get(URL).asText(), LinkType.INTERNAL));
        module.setFacilities(utils.getKeyFacilities(product));

        if (module.getImage() == null && product.has(IMAGE)) {
            module.setImage(imageFactory.createImage(product, module));
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
                    properties.getDmsHost() + module.getCtaLink().getLink() + "#opening", null));
        }

    }


}
