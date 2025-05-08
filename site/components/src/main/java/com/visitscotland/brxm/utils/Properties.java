package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Optional;

public abstract class Properties {

    private static final Logger logger = LoggerFactory.getLogger(Properties.class.getName());
    private static final Locale DEFAULT_LOCALE = Locale.UK;

    private final ResourceBundleService bundle;
    private final HippoUtilsService utils;

    protected Properties(ResourceBundleService bundle, HippoUtilsService utils){
        this.bundle = bundle;
        this.utils = utils;
    }

    abstract String getDefaultConfig();

    abstract String getOverrideProperty();

    public String readString(String key){
        return readString(key, DEFAULT_LOCALE);
    }

    public String readString(String key, Locale locale){
        return getProperty(key, locale).orElseGet(() ->
                getProperty(key).orElseGet(() -> {
                    logIssueWithProperty(key);
                    return "";
                })
        );
    }

    public Optional<String> readOptionalString(String key){
        return getProperty(key);
    }


    public int readInteger(String key){
        Optional<String> value = getProperty(key);

        if (value.isPresent()) {
            try {
                return Integer.parseInt(value.get());
            } catch (NumberFormatException nfe) {
                logger.error("The property value of the property {} cannot be cast to Integer. '{}' is not allowed.", key, value);
            }
        } else {
            logIssueWithProperty(key);
        }
        return 0;
    }

    public double readDouble(String key){
        Optional<String> value = getProperty(key);

        if (value.isPresent()){
            try {
                return Double.parseDouble(value.get());
            } catch (NumberFormatException nfe){
                logger.error("The property value of the property {} cannot be cast to Double. '{}' is not allowed.", key,value);
            }
        } else {
            logIssueWithProperty(key);
        }
        return 0;
    }

    public boolean readBoolean(String key){
        return Boolean.parseBoolean(getProperty(key).orElseGet(() -> {
            logIssueWithProperty(key);
            return "false";
        }));
    }

    /**
     * Calculates the properties document defined in the environment (i.e. /hst:visitscotland/hst:hosts/dev-localhost/localhost/)
     * and when none is defined returns the default one.
     *
     * @return Resource Bundle id for the configuration
     */
    private String getEnvironmentProperties(){
        final Mount mount = utils.getResolvedMount(null);
        if (mount  != null) {
            String bundleId = mount.getProperty(getOverrideProperty());
            if (bundleId != null){
                return bundleId;
            } else if (mount.getParent() != null){
                //Other languages and data endpoints are mounted as subsites in the configuration
                bundleId = mount.getParent().getProperty(getOverrideProperty());

                if (bundleId != null){
                    return bundleId;
                }
            }
        }

        return getDefaultConfig();
    }

    private void logIssueWithProperty (String key) {
        logger.info("The property {} hasn't been set in the resourceBundle", key);
    }

    //TODO Reduce visibility to protected after DS-XXX
    public Optional<String> getProperty(String key){
        return getProperty(key, Locale.UK);
    }

    //TODO Reduce visibility to protected after DS-XXX
    public  Optional<String> getProperty(String key, String locale){
        return getProperty(key, Locale.forLanguageTag(locale));
    }

    //TODO Reduce visibility to protected after DS-XXX
    public Optional<String> getProperty(String key, Locale locale){
        String bundleId = getEnvironmentProperties();
        String value = readValueFromResourceBundle(key, locale, bundleId);

        if (Contract.isEmpty(value)) {
            return Optional.empty();
        } else if (value.startsWith("$")){
            value = getEnvironmentVariable(value.substring(1));
        } else if (value.startsWith("%")){
            value = getSystemProperty(value.substring(1));
        }

        return Optional.of(value);
    }


    /**
     * Reads a value from a Hippo resource bundle using the specified key, locale, and bundle ID.
     * Attempts to fall back to the default locale if the key is not found for the provided locale.
     * If the site-specific properties are undefined, it defaults to general properties.
     *
     * @param key       the property key to retrieve
     * @param locale    the locale to use for lookup
     * @param bundleId  the identifier of the resource bundle
     * @return the resolved property value, or null if not found
     */
    private String readValueFromResourceBundle(String key, Locale locale, String bundleId){

        boolean defaultConfig = bundleId.equals(getDefaultConfig());
        boolean englishLocale = Locale.UK.equals(locale);

        String value = bundle.getResourceBundle(bundleId, key, locale, !defaultConfig || !englishLocale);

        if (Contract.isEmpty(value)) {

            if (!englishLocale) {
                value = bundle.getResourceBundle(bundleId, key, Locale.UK, !defaultConfig );
            }
            if (Contract.isEmpty(value) && !defaultConfig) {
                value = bundle.getResourceBundle(getDefaultConfig(), key,locale, !englishLocale);
                if (Contract.isEmpty(value) && !englishLocale){
                    value = bundle.getResourceBundle(getDefaultConfig(), key,Locale.UK, false);
                }
            }
        }

        return value;
    }

    String getEnvironmentVariable(String name){
        try {
            return System.getenv(name);
        } catch (RuntimeException e){
            return null;
        }
    }

    String getSystemProperty(String name){
        return System.getProperty(name, "");
    }
}
