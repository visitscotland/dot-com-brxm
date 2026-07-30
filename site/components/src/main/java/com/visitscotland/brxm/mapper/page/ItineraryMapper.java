package com.visitscotland.brxm.mapper.page;

import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.EntryMapper;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.mapper.module.TransportMapper;
import com.visitscotland.brxm.model.*;
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
    private static final String SEASONS = "seasons";
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
    private final TransportMapper transportMapper;


    public ItineraryMapper(ResourceBundleService bundle, DMSDataService dmsData, ImageMapper imageMapper,
                           DMSUtils utils, DocumentUtilsService documentUtils, LinkService linkService,
                           GoogleMapsService googleMapsService, ContentLogger contentLogger,
                           EntryMapper entryMapper, TransportMapper transportMapper) {
        this.bundle = bundle;
        this.dmsData = dmsData;
        this.imageMapper = imageMapper;
        this.utils = utils;
        this.documentUtils = documentUtils;
        this.linkService = linkService;
        this.googleMapsService = googleMapsService;
        this.contentLogger = contentLogger;
        this.entryMapper = entryMapper;
        this.transportMapper = transportMapper;
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
                    !Contract.isEmpty(itinerary.getMapLink().getLabel())
                            ? itinerary.getMapLink().getLabel()
                            : bundle.getResourceBundle(BUNDLE_FILE, DEFAULT_CTA_TEXT, locale),
                    itinerary.getMapLink().getPath());
            if (!Locale.UK.equals(locale)) {
                googleMapsService.localizeUrl(ctaLink, locale);
            }
            ctaLink.setType(LinkType.EXTERNAL);
            page.setMapLink(ctaLink);
        }

        page.setIframeMap(itinerary.getEmbeddedMap());
        populateTransports(page, itinerary.getTransports(), locale);
        populateThemes(page, itinerary.getTheme(), locale);
        populateAreas(page, itinerary.getAreas(), locale);
        populateSeasons(page, itinerary.getSeasons(), locale);
        populateLocations(page, itinerary.getLocations());

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

    private void populateTransports(ItineraryPage page, final String[] transports, final Locale locale) {
        page.setTransports(transportMapper.getTransports(transports, locale));
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

    private void populateAreas(ItineraryPage page, final String[] areas, final Locale locale) {
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

    private void populateSeasons(ItineraryPage page, final String[] seasons, final Locale locale) {
        List<Entry> seasonsToAdd = new ArrayList<>();
        if (seasons == null) {
            page.setSeasons(seasonsToAdd);
        } else {
            for (final String season : seasons) {
                if (season != null && bundle.existsResourceBundleKey(SEASONS, season, locale)) {
                    seasonsToAdd.add(new Entry(season, bundle.getResourceBundle(SEASONS, season, locale)));
                } else {
                    contentLogger.warn("No key/value pair for season {}", season);
                }
            }
            page.setSeasons(seasonsToAdd);
        }
    }

    private void populateLocations(final ItineraryPage page, final String[] locations) {
        List<String> locationsToAdd = new ArrayList<>();
        if (locations == null) {
            page.setLocations(locationsToAdd);
        } else {
            for (final String location : locations) {
                if (location == null || location.isEmpty()) {
                    contentLogger.warn("Null location provided.");
                } else {
                    locationsToAdd.add(location);
                }
            }
            page.setLocations(locationsToAdd);
        }
    }
}
