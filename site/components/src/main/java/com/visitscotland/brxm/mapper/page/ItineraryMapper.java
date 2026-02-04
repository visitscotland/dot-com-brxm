package com.visitscotland.brxm.mapper.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.EntryMapper;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.Coordinates;
import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.GoogleMapsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.*;

/**
 * mapper class for itineraries
 */
@Component
public class ItineraryMapper {

    private static final Logger logger = LoggerFactory.getLogger(ItineraryMapper.class);

    static final String BUNDLE_FILE = "itinerary";
    private static final String THEMES = "themes";
    private static final String AREAS = "areas";
    private static final String TRANSPORTS = "transports";
    private static final String DEFAULT_CTA_TEXT = "itinerary.default-cta";

    private final ResourceBundleService bundle;
    private final DMSDataService dmsData;
    private final ImageMapper imageMapper;
    private final DMSUtils utils;
    private final EntryMapper entryMapper;
    private final DocumentUtilsService documentUtils;
    private final LinkService linkService;
    private final GoogleMapsService googleMapsService;
    private final Logger contentLogger;
    private final StopMapper stopMapper;


    public ItineraryMapper(ResourceBundleService bundle, DMSDataService dmsData, ImageMapper imageMapper,
                           DMSUtils utils, DocumentUtilsService documentUtils, LinkService linkService,
                           GoogleMapsService googleMapsService, ContentLogger contentLogger,
                           EntryMapper entryMapper, StopMapper stopMapper) {
        this.bundle = bundle;
        this.dmsData = dmsData;
        this.imageMapper = imageMapper;
        this.utils = utils;
        this.documentUtils = documentUtils;
        this.linkService = linkService;
        this.googleMapsService = googleMapsService;
        this.contentLogger = contentLogger;
        this.entryMapper = entryMapper;
        this.stopMapper = stopMapper;
    }

    /**
     * Method for creating new format Itineraries
     * Collects the information about an itinerary and enhances the information in it
     */
    public ItineraryPage buildItinerary(final Itinerary itinerary, final Locale locale) {

        logger.debug("buildItinerary initialized");

        ItineraryPage page = new ItineraryPage(itinerary);

        page.setDays(documentUtils.getAllowedDocuments(itinerary, Day.class));

        // check if a user value has been supplied
        final boolean calculateDistance = (itinerary.getDistance() == null || itinerary.getDistance() == 0);
        final boolean noDays = page.getDays() == null || page.getDays().isEmpty();

        if (noDays) {
            contentLogger.info("The itinerary page {} does not have any modules published", itinerary.getPath());
            page.setDayCount(0);
        } else {
            page.setDayCount(page.getDays().size());
        }

        if (calculateDistance && !noDays) {
            page.setDistance(googleMapsService.calculateDistanceFromDays(page.getDays()));
        } else if (!calculateDistance) {
            page.setDistance(BigDecimal.valueOf(itinerary.getDistance()));
        } else {
            // default to 0 if we can't get distance from calculations or user value
            contentLogger.info("No distance value provided for itinerary page {} - defaulting to 0", itinerary.getPath());
            page.setDistance(BigDecimal.valueOf(0));
        }

        if(itinerary.getMapLink() == null || itinerary.getMapLink().getLink() == null) {
            contentLogger.info("An issue occurred while extracting Itinerary map link for {}", itinerary.getPath());
        } else {
            FlatLink ctaLink = linkService.createExternalLink(locale, itinerary.getMapLink().getLink(),
                    !Contract.isEmpty(itinerary.getMapLink().getLabel()) ? itinerary.getMapLink().getLabel() : bundle.getResourceBundle(BUNDLE_FILE, DEFAULT_CTA_TEXT, locale),
                    itinerary.getMapLink().getPath());
            ctaLink.setType(LinkType.EXTERNAL);
            page.setMapLink(ctaLink);
        }

        populateTransports(page, itinerary.getTransports(), locale);
        populateThemes(page, itinerary.getTheme(), locale);
        populateAreas(page, itinerary.getAreas(), locale);

        return page;
    }

    @Deprecated
    public boolean isStopBasedItinerary(final Itinerary itinerary) {
        List<BaseDocument> bean = documentUtils.getAllowedDocuments(itinerary, BaseDocument.class);
        for (BaseDocument b : bean) {
            if (b instanceof Day) {
                return ((Day) b).getStops() != null && !((Day) b).getStops().isEmpty();
            }
        }
        return false;
    }

    /**
     * Build an old format itinerary
     */
    @Deprecated
    public ItineraryPage buildStopBasedItinerary(final Itinerary itinerary, final Locale locale) {

        logger.debug("buildStopBasedItinerary initialized");

        final boolean calculateDistance = (itinerary.getDistance() == null || itinerary.getDistance() == 0);

        ItineraryPage page = new ItineraryPage(itinerary);
        ItineraryStopModule firstStop = null;
        ItineraryStopModule lastStop = null;
        BigDecimal totalDistance = BigDecimal.ZERO;
        Coordinates prevCoordinates = null;
        int index = 1;

        page.setDays(documentUtils.getAllowedDocuments(itinerary, Day.class));

        if (page.getDays() == null || page.getDays().isEmpty()) {

            contentLogger.warn("The itinerary page {} does not have any modules published", itinerary.getPath());

        } else {
            for (Day day : page.getDays()) {
                for (Stop stop : day.getStops()) {
                    if (page.getStops() != null && page.getStops().containsKey(stop.getIdentifier())) {
                        String message = String.format("Duplicate stop '%s' found on itinerary '%s', please review the document %s at: %s ", stop.getTitle(), itinerary.getTitle(), itinerary.getDisplayName(), itinerary.getPath());
                        contentLogger.error(message);
                        page.addErrorMessage(message);
                        continue;
                    }

                    ItineraryStopModule module = stopMapper.generateStop(locale, stop, itinerary, index++);

                    if (module.getCoordinates() == null) {
                        contentLogger.error("The Itinerary {} located at {} has a stop without coordinates," +
                                " the stop affected is {} located at {}", itinerary.getName(), itinerary.getPath(), stop.getName(), stop.getPath());
                    }

                    lastStop = module;
                    if (firstStop == null) {
                        firstStop = lastStop;
                    }

                    if (calculateDistance && module.getCoordinates() != null) {
                        totalDistance = totalDistance.add(googleMapsService.getDistanceStops(prevCoordinates, module.getCoordinates()));
                        prevCoordinates = module.getCoordinates();
                    }

                    page.addStop(module);
                }
            }
            page.setDistance(calculateDistance ? totalDistance.setScale(0, RoundingMode.HALF_UP) : BigDecimal.valueOf(itinerary.getDistance()));
            populateFirstAndLastStopTexts(page, firstStop, lastStop);
        }

        populateTransports(page, itinerary.getTransports(), locale);
        populateThemes(page, itinerary.getTheme(), locale);
        populateAreas(page, itinerary.getAreas(), locale);

        return page;
    }

    /**
     * Populates the first stop text and the last stop text depending on whether they have been set or not in the
     * Itinerary document
     */
    @Deprecated
    private void populateFirstAndLastStopTexts(ItineraryPage page, ItineraryStopModule first, ItineraryStopModule last) {
        Itinerary itinerary = (Itinerary) page.getHippoBean();

        if (Contract.isEmpty(itinerary.getStart()) && first != null) {
            page.setFirstStopLocation(first.getSubTitle());
        } else {
            page.setFirstStopLocation(itinerary.getStart());
        }

        if (Contract.isEmpty(itinerary.getFinish()) && last != null) {
            page.setLastStopLocation(last.getSubTitle());
        } else {
            page.setLastStopLocation(itinerary.getFinish());
        }
    }

    /**
     * Transform a Stop document in a ItineraryStopModule and add extra information depending on the type
     */
    @Deprecated
    public ItineraryStopModule generateStop(final Locale locale, final Stop stop, final Itinerary itinerary, final Integer index) {
        ItineraryStopModule module = initializeStop(stop);
        module.setIndex(index);

        if (stop.getImage() != null) {
            module.setImage(imageMapper.createImage(stop.getImage(), module, locale));
        }

        if (stop.getStopItem() instanceof DMSLink) {
            processDMSStop(locale, module, (DMSLink) stop.getStopItem());
        } else if (stop.getStopItem() instanceof ItineraryExternalLink) {
            processExternalStop(locale, module, (ItineraryExternalLink) stop.getStopItem());
        } else if (logger.isWarnEnabled()) {
            contentLogger.warn("The product's id  was not provided for {}, Stop {}", itinerary.getName(), module.getIndex());
        }

        if (module.getImage() == null && logger.isWarnEnabled()) {
            contentLogger.warn("An image could not be found for {}, Stop {}", itinerary.getName(), module.getIndex());
        }

        if (module.getSubTitle() == null && logger.isWarnEnabled()) {
            contentLogger.warn("The stop {} does not have a subtitle. Itinerary {}", module.getIndex(), itinerary.getName());
        }

        return module;
    }

    /**
     * Creates a Stop from the stop Document type
     */
    @Deprecated
    private ItineraryStopModule initializeStop(final Stop stop) {
        ItineraryStopModule module = new ItineraryStopModule();
        module.setHippoBean(stop);
        module.setTitle(stop.getTitle());
        module.setDescription(stop.getDescription());
        module.setSubTitle(stop.getSubtitle());

        if (stop.getStopTip() != null) {
            module.setTipsTitle(stop.getStopTip().getTitle());
            module.setTipsBody(stop.getStopTip().getCopy());
        }

        return module;
    }

    /**
     * Generates the text for time to explore
     */
    @Deprecated
    private String generateTimeToExplore(final String visitDuration, final Locale locale) {
        return visitDuration + " " + bundle.getResourceBundle(BUNDLE_FILE, visitDuration.equals("1") ? "stop.hour" : "stop.hours", locale);
    }

    /**
     * Extracts all relevant information for an External link in order to enhance the stop
     */
    @Deprecated
    public void processExternalStop(Locale locale, ItineraryStopModule module, ItineraryExternalLink externalLink) {
        if (!Contract.isEmpty(externalLink.getTimeToExplore())) {
            module.setTimeToExplore(generateTimeToExplore(externalLink.getTimeToExplore(), locale));
        }

        if (externalLink.getExternalLink() != null) {
            FlatLink ctaLink = linkService.createExternalLink(locale, externalLink.getExternalLink().getLink(),
                    !externalLink.getExternalLink().getLabel().isEmpty() ? externalLink.getExternalLink().getLabel()
                    : bundle.getFindOutMoreAboutCta(module.getTitle(), locale),
                    externalLink.getPath());
            module.setCtaLink(ctaLink);
        }

        if (externalLink.getCoordinates() != null) {
            module.setCoordinates(new Coordinates(externalLink.getCoordinates().getLatitude(), externalLink.getCoordinates().getLongitude()));
        }
    }

    /**
     * Extracts all relevant information from the Product Card in order to enhance the Stop
     */
    @Deprecated
    public void processDMSStop(Locale locale, ItineraryStopModule module, DMSLink dmsLink) {
        JsonNode product = dmsData.productCard(dmsLink.getProduct(), locale);

        if (product == null) {
            String message = String.format("The DMS product added to '%s' was not found, please review the DMS Product id field at: %s ",
                    module.getTitle(), dmsLink.getPath());
            module.addErrorMessage(message);
            if (logger.isWarnEnabled()) {
                contentLogger.warn(message);
            }
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
            if (address.has(LOCATION) && Contract.isEmpty(module.getSubTitle())) {
                module.setSubTitle(address.get(LOCATION).asText());
            }
        }

        if (product.has(LATITUDE) && product.has(LONGITUDE)) {
            module.setCoordinates(new Coordinates(product.get(LATITUDE).asDouble(), product.get(LONGITUDE).asDouble()));
        } else {
            String message = String.format("The DMS product added to '%s' does not have coordinates, please review the DMS Product id field at: %s ",
                    module.getTitle(), dmsLink.getPath());
            module.addErrorMessage(message);
            if (logger.isWarnEnabled()) {
                contentLogger.error(message);
            }
        }

        if (product.has(TIME_TO_EXPLORE)) {
            module.setTimeToExplore(generateTimeToExplore(product.get(TIME_TO_EXPLORE).asText(), locale));
        }

        if (product.has(PRICE) && product.get(PRICE).has(DISPLAY_PRICE)) {
            module.setPrice(product.get(PRICE).get(DISPLAY_PRICE).asText());
        }

        if (product.has(OPENING)) {
            JsonNode opening = product.get(OPENING);
            module.setOpening(opening);
            module.setOpenLink(new FlatLink(bundle.getResourceBundle(BUNDLE_FILE, "stop.opening", locale),
                    module.getCtaLink().getLink() + "#opening", null));
        }
    }

    private void populateTransports(ItineraryPage page, final String[] transports, final Locale locale) {
        List<Entry> transportsToAdd = new ArrayList<>();
        if (transports == null) {
            page.setTransports(transportsToAdd);
        } else {
            for (final String transport : transports) {
                if (transport != null && bundle.existsResourceBundleKey(TRANSPORTS, transport, locale)) {
                    transportsToAdd.add(new Entry(transport, bundle.getResourceBundle(TRANSPORTS, transport, locale)));
                } else {
                    contentLogger.warn("No key/value pair for transport type {}", transport);
                }
            }
            page.setTransports(transportsToAdd);
        }
    }

    private void populateThemes(ItineraryPage page, final String theme, final Locale locale) {
        if (theme == null ) {
            contentLogger.warn("No theme provided for page.");
            return;
        }
        final String translatedTheme = bundle.getResourceBundle(THEMES, theme, locale);
        if (translatedTheme == null || translatedTheme.isEmpty() ) {
            contentLogger.warn("No theme found for {} for locale {}", theme, locale.getDisplayCountry());
        } else {
            page.setTheme(new Entry(theme, translatedTheme));
        }
    }

    private void populateAreas(ItineraryPage page, String[] areas, Locale locale) {
        List<Entry> areasToAdd = new ArrayList<>();
        if (areas == null) {
            page.setAreas(areasToAdd);
        } else {
            for (final String area : areas) {
                if (area != null && bundle.existsResourceBundleKey(AREAS, area, locale)) {
                    areasToAdd.add(new Entry(area, bundle.getResourceBundle(AREAS, area, locale)));
                } else {
                    contentLogger.warn("No key/value pair for area {}", area);
                }
            }
            page.setAreas(areasToAdd);
        }
    }
}
