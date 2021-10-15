package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class Properties {

    private static final Logger logger = LoggerFactory.getLogger(Properties.class.getName());

    static final String DEFAULT_ID = "config.cms";

    static final String INSTAGRAM_API = "instagram.api";
    static final String INSTAGRAM_ACCESS_TOKEN ="instagram.accesstoken";
    static final String INSTAGRAM_APP_ID ="instagram.app-id";
    static final String INSTAGRAM_URL ="instagram.post-url";
    static final String HELPDESK_EMAIL = "helpdesk-email";
    static final String IKNOW_COMMUNITY_URL = "iknow-community.url";
    static final String IKNOW_COMMUNITY_TAGGED_DISCUSSION = "iknow-community.tagged-discussion";

    //Environment
    static final String USE_RELATIVE_URLS = "links.use-relative-urls";
    static final String INTERNAL_SITES = "links.internal-sites";
    static final String CMS_BASE_PATH = "links.cms-base-path-url";
    static final String CONVERT_TO_RELATIVE = "links.convert-to-relative";

    // DMS Properties
    static final String DMS_DATA_HOST = "dms-data.private-url";
    static final String DMS_DATA_PUBLIC_HOST = "dms-data.public-url";
    static final String DMS_DATA_ENCODING = "dms-data.encoding";
    static final String DMS_DATA_API_KEY = "dms-data.api-key";
    static final String DMS_DATA_TIMEOUT = "dms-data.timeout";
    static final String DMS_DATA_TRIES = "dms-data.tries";
    static final String DMS_DATA_SLEEP_TIME = "dms-data.sleep-time";
    static final String DMS_HOST = "links.vs-dms-products.url";
    static final String DMS_MAP_DEFAULT_DISTANCE = "dms.default-distance";

    private final ResourceBundleService bundle;

    private final HippoUtilsService utils;

    public Properties(ResourceBundleService bundle, HippoUtilsService utils){
        this.bundle = bundle;
        this.utils = utils;
    }

    public String getInstagramApi() {
        return readString(INSTAGRAM_API);
    }

    public String getInstagramURL() {
        return readString(INSTAGRAM_URL);
    }

    public String getInstagramToken() {
        String accessCode = readString(INSTAGRAM_ACCESS_TOKEN);
        if (Contract.isEmpty(accessCode)){
            return readString(INSTAGRAM_APP_ID);
        } else {
            return readString(INSTAGRAM_APP_ID) +"|"+accessCode;
        }
    }

    public String getHelpdeskEmail() {
        return readString(HELPDESK_EMAIL);
    }

    public boolean isRelativeURLs(){
        return readBoolean(USE_RELATIVE_URLS);
    }

    public String getDmsHost() {
        if (isRelativeURLs()){
            return "";
        } else {
            return readString(DMS_HOST);
        }
    }

    public String getCmsBasePath() {
        if (isRelativeURLs()){
            return "";
        } else {
            return readString(CMS_BASE_PATH);
        }
    }

    public String getConvertToRelative() {
        if (isRelativeURLs()){
            return readString(CONVERT_TO_RELATIVE);
        } else {
            return "";
        }
    }

    public String getDmsDataHost() {
        return readString(DMS_DATA_HOST);
    }

    public String getDmsDataPublicHost() {
        return readString(DMS_DATA_PUBLIC_HOST);
    }

    public Double getDmsMapDefaultDistance() {
        return readDouble(DMS_MAP_DEFAULT_DISTANCE);
    }

    public String getDmsToken() {
        return readString(DMS_DATA_API_KEY);
    }

    public Integer getDmsTimeout() {
        return readInteger(DMS_DATA_TIMEOUT);
    }

    public Integer getDmsTries() {
        return readInteger(DMS_DATA_TRIES);
    }

    public Integer getDmsWaitTime() {
        return readInteger(DMS_DATA_SLEEP_TIME);
    }

    public String getIknowCommunityUrl() {
        return readString(IKNOW_COMMUNITY_URL);
    }

    public String getIknowCommunityTaggedDiscussion() {
        return readString(IKNOW_COMMUNITY_TAGGED_DISCUSSION);
    }

    public List<String> getInternalSites() {
        String sites = readString(INTERNAL_SITES);
        if (!Contract.isEmpty(sites)){
            //TODO: Java 10 -> toUnmodifiableList()
            //TODO: Java 11 -> Predicate.not(String::isEmpty)
            return Arrays.stream(sites.trim().split("\\s*,\\s*")).filter(((Predicate<String>) String::isEmpty).negate()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    public Charset getDmsEncoding() {
        String value = getProperty(DMS_DATA_ENCODING);
        try{
            if (!Contract.isEmpty(value)) {
                return Charset.forName(value);
            }
        } catch (Exception e){
            logger.warn("{} is not a valid value for the property {}", value, DMS_DATA_ENCODING);
        }
        return StandardCharsets.UTF_8;
    }

    public String readString(String key){
        String value = getProperty(key);
        if (value != null){
            return value;
        } else {
            return "";
        }
    }

    public int readInteger(String key){
        String value = getProperty(key);

        try {
            if (value != null){
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException nfe){
            logger.error("The property value of the property {} cannot be casted to Integer. '{}' is not allowed.", key,value);
        }
        return 0;
    }

    public double readDouble(String key){
        String value = getProperty(key);

        try {
            if (value != null){
                return Double.parseDouble(value);
            }
        } catch (NumberFormatException nfe){
            logger.error("The property value of the property {} cannot be casted to Double. '{}' is not allowed.", key,value);
        }
        return 0;
    }

    public boolean readBoolean(String key){
        return Boolean.parseBoolean(getProperty(key));
    }

    /**
     * Calculates the properties document defined in the environment (i.e. /hst:visitscotland/hst:hosts/dev-localhost/localhost/)
     * and when none is defined returns the default one.
     *
     * @return Resource Bundle id for the configuration
     */
    private String getEnvironmentProperties(){
        if (utils.getResolvedMount(null) != null) {
            String bundleId = utils.getResolvedMount(null).getProperty("visitscotland:cmsProperties");
            if (bundleId != null){
                return bundleId;
            }
        }

        return DEFAULT_ID;
    }

    private String getProperty(String key){
        String bundleId = getEnvironmentProperties();
        String value = bundle.getResourceBundle(bundleId, key, Locale.UK);

        if (Contract.isEmpty(value)){
            logger.info("The property {} hasn't been set in the resourceBundle {}", key, bundleId);
        } else if (value.startsWith("$")){
            return getEnvironmentVariable(value.substring(1));
        } else {
            return value;
        }

        return null;
    }

    String getEnvironmentVariable(String name){
        try {
            return System.getenv(name);
        } catch (RuntimeException e){
            return null;
        }
    }
}
