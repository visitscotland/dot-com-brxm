package com.visitscotland.brxm.mapper.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.factory.hippo.ValueList;
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


        ItineraryPage page = new ItineraryPage(itinerary);

        page.setDays(documentUtils.getAllowedDocuments(itinerary, Day.class));

        // check if a user value has been supplied
        final boolean calculateDistance = (itinerary.getDistance() == null || itinerary.getDistance() == 0);
        final boolean noDays = page.getDays() == null || page.getDays().isEmpty();

        if (noDays) {
            contentLogger.warn("The itinerary page {} does not have any modules published", itinerary.getPath());
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
            page.setDistance(BigDecimal.valueOf(0));
        }

        page.setSubHeading(itinerary.getSubheading());
        populateTransports(page, itinerary.getTransports());
        populateThemes(page, itinerary.getTheme());
        populateAreas(page, itinerary.getAreas());

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

        populateTransports(page, itinerary.getTransports());
        populateThemes(page, itinerary.getTheme());
        populateAreas(page, itinerary.getAreas());

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
                    !externalLink.getExternalLink().getLabel().isEmpty() ? externalLink.getExternalLink().getLabel() : bundle.getFindOutMoreAboutCta(module.getTitle(), locale),
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
            String message = String.format("The DMS product added to '%s' was not found, please review the DMS Product id field at: %s ", module.getTitle(), dmsLink.getPath());
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
            String message = String.format("The DMS product added to '%s' does not have coordinates, please review the DMS Product id field at: %s ", module.getTitle(), dmsLink.getPath());
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

    private void populateTransports(ItineraryPage page, String[] transports) {
        page.setTransports(valueListToEntryList(transports, ValueList.VS_ITINERARY_TRANSPORT));
    }

    private void populateThemes(ItineraryPage page, String theme) {
        page.setTheme(entryMapper.getEntry(theme, ValueList.VS_ITINERARY_THEMES));
    }

    private void populateAreas(ItineraryPage page, String[] areas) {
        page.setAreas(valueListToEntryList(areas, ValueList.VS_ITINERARY_AREAS));
    }

    private List<com.visitscotland.brxm.model.megalinks.Entry> valueListToEntryList(final String[] items, final ValueList valueList) {
        if (items != null) {
            List<Entry> entries = new ArrayList<>(items.length);
            for (String item : items) {
                entries.add(entryMapper.getEntry(item, valueList));
            }
            return entries;
        }
        return Collections.emptyList();
    }

    @Deprecated
    private String getProductSearchUrl(Locale locale, String productType, Double latitude, Double longitude) {
        return ProductSearchBuilder.newInstance().locale(locale).productTypes(productType).proximity(5.)
                .coordinates(latitude, longitude).build();
    }
}
