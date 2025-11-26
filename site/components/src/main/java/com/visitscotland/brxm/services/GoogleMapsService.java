package com.visitscotland.brxm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * poc helper class for using the google maps place and directions urls
 */
@Component
public class GoogleMapsService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleMapsService.class);

    private static final String DIRECTIONS_URL = "https://www.google.com/maps/dir";

    // regex to extract coordinates from url using @ coordinates
    private static final String URL_REGEX =
            "(?i)https://www\\.google\\.com/maps/place/[^/]*/@(-?\\d{1,3}\\.\\d{1,20}),(-?\\d{1,3}\\.\\d{1,10}),.*";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public String getDirectionsUrl(final List<String> urls) {

        StringBuilder sb1 = new StringBuilder();
        if (urls.isEmpty()) {
            return null;
        }
        try {
            for (final String url : urls) {
                final Matcher urlMatcher = URL_PATTERN.matcher(url);
                if (urlMatcher.matches()) {
                    sb1.append("/").append(urlMatcher.group(1)).append(",").append(urlMatcher.group(2));
                } else {
                    // bad url provided
                    logger.error("url format not recognized. url: {}", url);
                }
            }
            if (sb1.length() > 1) {
                sb1.insert(0, DIRECTIONS_URL);
                sb1.append("/");
            } else {
                return null;
            }
        } catch (NullPointerException exception) { // improve exception handling
            logger.error("An error occurred: ", exception);
        }
        return sb1.toString();
    }
}
