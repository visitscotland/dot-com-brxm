package com.visitscotland.brxm.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brxm.beans.InstagramImage;
import com.visitscotland.brxm.cfg.VsComponentManager;
import com.visitscotland.brxm.components.content.factory.ImageFactory;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

@Component
public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    //TODO add message format for other languages
    //TODO the variable parameters is not in use!
    public static final String contentIssue(String message, Object... parameters) {
        String logMessage = "- [CONTENT] - " + message;
        return String.format(logMessage, parameters);
    }

    /**
     * Request a page and return the body as String
     *
     * @param url
     * @return null if status code not 200 or 300
     * @throws IOException
     * @deprecated Use requestUrl instead which is the non-static version of this method.
     */
    @Deprecated
    public static String request(String url) throws IOException {
        int responseCode = ((HttpURLConnection) new URL(url).openConnection()).getResponseCode();
        if (responseCode < 400) {
            if (responseCode >= 300){
                logger.warn("The request for {} has responded with the Status code {}", url, responseCode);
            }
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8))) {
                final StringBuilder sb = new StringBuilder();
                int cp;

                while ((cp = br.read()) != -1) {
                    sb.append((char) cp);
                }

                return sb.toString();
            }
        } else {
            logger.warn("The request for {} has responded with the Status code {}", url, responseCode);
        }
        return null;
    }

    public String requestUrl(String url) throws IOException {
        return CommonUtils.request(url);
    }

    /**
     * TODO this method returns the current open state and it could be affected by the cache, ask WEBOPS and move it to front end if needed
     * TODO move to DMSDataService once this method need is confirmed
     *
     * @deprecated  This method is not in use at the moment. See TODOs
     */
    @Deprecated
    public static String currentOpenStatus(String starTime, String endTime, Locale locale) {
        final String ITINERARY_BUNDLE = "itinerary";
        ResourceBundleService bundle = VsComponentManager.get(ResourceBundleService.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
        LocalTime starts = LocalTime.parse(starTime, formatter);
        LocalTime ends = LocalTime.parse(endTime, formatter);
        LocalTime currentTime = LocalTime.now(ZoneId.of("+1"));
        if (currentTime.isAfter(starts) && currentTime.isBefore(ends)) {
            if (currentTime.plusMinutes(30).isAfter(ends)) {
                return bundle.getResourceBundle(ITINERARY_BUNDLE, "stop.close.soon", locale);
            } else {
                return bundle.getResourceBundle(ITINERARY_BUNDLE, "stop.open", locale);
            }
        } else {
            return bundle.getResourceBundle(ITINERARY_BUNDLE, "stop.closed", locale);
        }
    }

    public String buildQueryString(Map<String, String> parameters, String encoding) {
        StringBuilder sb = new StringBuilder();
        try {
            if (parameters.size() > 0) {
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    if (entry.getKey() == null) {
                        continue;
                    }
                    if (sb.length() > 1) {
                        sb.append("&");
                    } else {
                        sb.append("?");
                    }
                    sb.append(entry.getKey());
                    if (!Contract.isEmpty(entry.getValue())) {
                        sb.append("=").append(URLEncoder.encode(entry.getValue(), encoding));
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return sb.toString();
    }
}
