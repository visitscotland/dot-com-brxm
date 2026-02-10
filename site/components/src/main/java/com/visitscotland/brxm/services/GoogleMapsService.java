package com.visitscotland.brxm.services;

import com.google.common.collect.ImmutableMap;
import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.model.Coordinates;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.ItineraryPage;
import com.visitscotland.utils.CoordinateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * service class for using the google maps place and directions urls
 */
@Component
public class GoogleMapsService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleMapsService.class);

    private static final String DIRECTIONS_URL = "https://www.google.com/maps/dir";

    // regex to extract coordinates from url using @ coordinates
    private static final String URL_REGEX =
            "(?i)https://www\\.google\\.com/maps/place/[^/]*/@(-?\\d{1,3}\\.\\d{1,20}),(-?\\d{1,3}\\.\\d{1,20}),\\d{1,2}z/data=.*";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private static final ImmutableMap<String, String> LANGUAGES_MAP = ImmutableMap.<String, String>builder()
            .put("es-ES", "es")
            .put("it-IT", "it")
            .put("de-DE", "de")
            .put("fr-FR", "fr")
            .put("nl-NL", "nl")
            .build();

    /**
     * calculates the total distance across days from the coordinates contained in the map
     * url for each day
     *
     * returns a distance of 0 if any issues occur to prevent erroneous values
     */
    public BigDecimal calculateDistanceFromDays(final List<Day> days) {

        BigDecimal distance = new BigDecimal(0);
        TreeMap<Integer, Coordinates> coordinatesMap = new TreeMap<>();
        int dayCount = 0;

        for (final Day day : days) {
            final String mapUrl = day.getMapLink().getLink();
            if (mapUrl == null) {
                logger.warn("No map Url provided for day {}", day.getTitle());
                return new BigDecimal(0);
            }
            final Matcher matcher = URL_PATTERN.matcher(mapUrl);

            if (matcher.matches()) {
                coordinatesMap.put(dayCount++, new Coordinates(Double.valueOf(matcher.group(1)),Double.valueOf(matcher.group(2))));
            } else {
                logger.warn("Could not extract coordinates from map Url {}", mapUrl);
                return new BigDecimal(0);
            }
        }

        Coordinates previous = null;
        for (final Coordinates current : coordinatesMap.values()) {
            if (previous == null) {
                previous = current;
                continue;
            }
            distance = distance.add(getDistanceStops(previous, current));
            previous = current;
        }
        return distance;
    }

    /**
     * Method to calculate the distance between coordinates
     */
    @Deprecated
    public BigDecimal getDistanceStops(final Coordinates previous, final Coordinates current) {
        if (previous == null || current == null){
            return BigDecimal.ZERO;
        } else {
            return CoordinateUtils.haversineDistance(
                    BigDecimal.valueOf(previous.getLatitude()), BigDecimal.valueOf(previous.getLongitude()),
                    BigDecimal.valueOf(current.getLatitude()), BigDecimal.valueOf(current.getLongitude()),
                    true, "#,###,##0.0");
        }
    }

    public void localizeUrl(FlatLink link, Locale locale) {
        if (locale == null || link == null || link.getLink() == null) {
            logger.warn("Null locale or link provided.");
            return;
        }
        if (LANGUAGES_MAP.containsKey(locale.toLanguageTag())) {
            link.setLink(link.getLink() + "&hl=" + LANGUAGES_MAP.get(locale.toLanguageTag()));
        } else {
            logger.warn("Unable to apply language parameter to url {} for locale {}.  Default (en) will be used.", link.getLink(), locale.toLanguageTag());
        }
    }

    /*private boolean hasParameters(final String link) {
        return link.contains("?") || link.matches(".*[a-zA-Z0-9]+=.*");
    }*/

    /**
     * takes a list of google place urls, extracts the coordinates and returns a google directions url
     */
    public String getDirectionsUrl(final List<String> urls) {

        final StringBuilder urlBuilder = new StringBuilder();
        if (urls == null || urls.isEmpty()) {
            return null;
        }

        for (final String url : urls) {
            if (url == null) {
                logger.warn("Null URL encountered, skipping...");
                continue;
            }
            final Matcher urlMatcher = URL_PATTERN.matcher(url);
            if (urlMatcher.matches()) {
                urlBuilder.append("/").append(urlMatcher.group(1)).append(",").append(urlMatcher.group(2));
            } else {
                logger.warn("url format not recognized. url: {}", url);
            }
        }
        if (urlBuilder.length() > 0) {
            urlBuilder.insert(0, DIRECTIONS_URL);
            urlBuilder.append("/");
            return urlBuilder.toString();
        } else {
            return null;
        }
    }

    /**
     * extracts a map of direction urls from a list of coordinates
     *
     * @param coordinatesList
     * @return urlBuilder.toString()
     */
    public String getDirectionsUrlFromCoordinates(List<Coordinates> coordinatesList) {

        final StringBuilder urlBuilder = new StringBuilder();
        if (coordinatesList == null || coordinatesList.isEmpty()) {
            return null;
        }
        for (final Coordinates coordinates : coordinatesList) {
            if (coordinates == null ) {
                logger.warn("Null URL encountered, skipping...");
                continue;
            }
            if (coordinates.getLatitude() == null || coordinates.getLongitude() == null ) {
                logger.warn("Invalid or empty coordinates...");
                continue;
            }

            urlBuilder.append("/").append(coordinates.getLatitude().toString()).append(",").append(coordinates.getLongitude().toString());

        }
        if (urlBuilder.length() > 0) {
            urlBuilder.insert(0, DIRECTIONS_URL);
            urlBuilder.append("/");
            return urlBuilder.toString();
        } else {
            return null;
        }

    }

    /**
     * creates a route url from coordinates extracted from the place urls of days in an itinerary
     * *
     * returns null if unable to extract any coordinates for a day to prevent erroneous route maps
     * @param itineraryPage
     * @return directionsByDayMap
     */
    public String getDirectionsUrlFromItinerary(ItineraryPage itineraryPage) {

        final StringBuilder urlBuilder = new StringBuilder();
        if (itineraryPage == null ) {
            return null;
        }

        TreeMap<Integer, Coordinates> coordinatesMap = new TreeMap<>();

        int dayCount = 0;
        for (Day dayModule : itineraryPage.getDays()) {
            final String url = dayModule.getMapLink().getLink();
            if (url == null) {
                logger.warn("Null url. Skipping...");
                return null;
            }
            Matcher urlMatcher = URL_PATTERN.matcher(url);
            if (urlMatcher.matches()) {
                coordinatesMap.put(dayCount++, new Coordinates(Double.valueOf(urlMatcher.group(1)),Double.valueOf(urlMatcher.group(2))));
            } else {
                logger.warn("Url {} did is not a valid google place url.", url);
                return null;
            }
        }

        for (Coordinates coordinates : coordinatesMap.values()) {
            if (coordinates == null || coordinates.getLatitude() == null || coordinates.getLongitude() == null) {
                logger.warn("Null coordinates encountered, skipping...");
                continue;
            }

            urlBuilder.append("/").append(coordinates.getLatitude().toString()).append(",").append(coordinates.getLongitude().toString());
        }
        if (urlBuilder.length() > 0) {
            urlBuilder.insert(0, DIRECTIONS_URL);
            urlBuilder.append("/");
            return urlBuilder.toString();
        } else {
            return null;
        }
    }
}