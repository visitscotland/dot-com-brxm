package com.visitscotland.brxm.services;

import com.google.common.collect.ImmutableMap;
import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.model.Coordinates;
import com.visitscotland.brxm.model.FlatLink;
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
 *
 * TODO - rework this class to make it a bit more efficient
 */
@Component
public class GoogleMapsService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleMapsService.class);

    private static final String DIRECTIONS_URL = "https://www.google.com/maps/dir";
    private static final String FWD_SLASH = "/";

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

    /**
     * generate intraday url from location urls
     * @param previousUrl
     * @param nextUrl
     * @return
     */
    public String getDirectionsUrlForIntraDay(final String previousUrl, final String nextUrl) {

        final StringBuilder urlBuilder = new StringBuilder();

        if (previousUrl != null && !previousUrl.isEmpty() && nextUrl != null && !nextUrl.isEmpty()) {
            Matcher urlMatcher = URL_PATTERN.matcher(previousUrl);
            if (urlMatcher.matches()) {
                urlBuilder.append(FWD_SLASH).append(urlMatcher.group(1)).append(",").append(urlMatcher.group(2));
            } else {
                logger.info("Could not extract coordinates from previous url");
                urlBuilder.setLength(0);
                return urlBuilder.toString();
            }
            urlMatcher = URL_PATTERN.matcher(nextUrl);
            if (urlMatcher.matches()) {
                urlBuilder.append(FWD_SLASH).append(urlMatcher.group(1)).append(",").append(urlMatcher.group(2));
            } else {
                logger.info("Could not extract coordinates from next url");
                urlBuilder.setLength(0);
            }

            if (urlBuilder.length() > 0) {
                urlBuilder.insert(0, DIRECTIONS_URL);
                urlBuilder.append(FWD_SLASH);
            } else {
                logger.warn("Failed to build url...");
            }
        }

        return urlBuilder.toString();
    }

    public BigDecimal getDistanceFromUrls (final String previousUrl, final String nextUrl) {

        Coordinates previous = new Coordinates();
        Coordinates next = new Coordinates();

       try {
           if (previousUrl != null && !previousUrl.isEmpty() && nextUrl != null && !nextUrl.isEmpty()) {
               Matcher urlMatcher = URL_PATTERN.matcher(previousUrl);
               if (urlMatcher.matches()) {
                   previous.setLatitude(Double.valueOf(urlMatcher.group(1)));
                   previous.setLongitude(Double.valueOf(urlMatcher.group(2)));
               } else {
                   logger.info("Could not extract coordinates from previous url");
                   return BigDecimal.ZERO;

               }
               urlMatcher = URL_PATTERN.matcher(nextUrl);
               if (urlMatcher.matches()) {
                   next.setLatitude(Double.valueOf(urlMatcher.group(1)));
                   next.setLongitude(Double.valueOf(urlMatcher.group(2)));
               } else {
                   logger.info("Could not extract coordinates from next url");
                   return BigDecimal.ZERO;
               }
           }
        } catch (NumberFormatException e ) {
           logger.warn("A number format exception occurred whilst handling coordinates: ", e);
           return BigDecimal.ZERO;
       }
        return getDistanceStops(previous, next);

    }
}