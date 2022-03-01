package com.visitscotland.brxm.services;

import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

@Component
public class CommonUtilsService {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtilsService.class);

    /**
     * Request a page and return the body as String
     *
     * @param url URL
     * @return null if status code not 200 or 300
     * @throws IOException
     */
    public String requestUrl(String url) throws IOException {
        int responseCode = openConnection(url).getResponseCode();
        if (responseCode < 400) {
            if (logger.isWarnEnabled() && responseCode >= 300){
                logger.warn("The request for {} has responded with the Status code {}", protectUrl(url), responseCode);
            }
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8))) {
                final StringBuilder sb = new StringBuilder();
                int cp;

                while ((cp = br.read()) != -1) {
                    sb.append((char) cp);
                }

                return sb.toString();
            }
        } else if (logger.isWarnEnabled()) {
            logger.warn("The request for {} has responded with the Status code {}", protectUrl(url), responseCode);
        }
        return null;
    }

    /**
     * Remove the access token from Instagram urls, to avoid it being logged in error messages
     *
     * TODO Verify if the token can be sent in the headers instead of part of the URL
     *
     */
    private String protectUrl(String url){
        if (url.contains("accessToken")){
            String base = url.substring(url.indexOf("accessToken")+"accessToken=".length());
            return base + "[HIDDEN KEY]";
        }
        return url;
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

    /**
     * Calculates the Size of the External document if the document
     *
     */
    @Cacheable (value="dmsProducts")
    public String getExternalDocumentSize(String link, Locale locale) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#.#", dfs);
        try {
            URLConnection con = openConnection(link);
            String type = con.getContentType();
            if (type.contains("pdf")) {
                double bytes = con.getContentLength();

                return "PDF " + decimalFormat.format(bytes / 1024 / 1024) + "MB";
            }
        } catch (IOException e) {
            logger.error("The URL {} is not valid", link, e);
        }
        return null;
    }

    /**
     * This method allow us to test this class
     */
    public HttpURLConnection openConnection(String url) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
    }
}