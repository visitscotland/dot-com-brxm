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
            "(?i)https://www\\.google\\.com/maps/place/[^/]*/@(-?\\d{1,3}\\.\\d{1,20}),(-?\\d{1,3}\\.\\d{1,20}),.*";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    /**
     * takes a list of google place urls, extracts the coordinates and returns a google directions url
     */
    public String getDirectionsUrl(final List<String> urls) {

        StringBuilder urlBuilder = new StringBuilder();
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
}
