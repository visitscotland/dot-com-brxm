package com.visitscotland.brxm.resource;

import com.visitscotland.brxm.utils.SiteProperties;

import org.springframework.stereotype.Component;

import org.hippoecm.hst.resourcebundle.ResourceBundleRegistry;

import com.visitscotland.utils.Contract;

import javax.annotation.Nonnull;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Optional;
import java.util.Locale;
import java.util.Map;

@Component
class ResourceBundleQueryService {
    private final ResourceBundleValueExtractor resourceBundleValueExtractor;
    private final ResourceBundleRegistry resourceBundleRegistry;
    private final SiteProperties siteProperties;

    protected ResourceBundleQueryService(final ResourceBundleRegistry resourceBundleRegistry,
                                         final ResourceBundleValueExtractor resourceBundleValueExtractor,
                                         final SiteProperties properties) {
        this.resourceBundleValueExtractor = resourceBundleValueExtractor;
        this.resourceBundleRegistry = resourceBundleRegistry;
        this.siteProperties = properties;
    }

    /**
     * Retrieves the value associated with a specific key from a resource bundle.
     *
     * <p>If either {@code bundleName} or {@code itemKey} is null, this method returns an empty {@code Optional}.
     * Otherwise, it resolves the bundle name based on whether the bundle is site-specific, attempts to load the resource bundle,
     * and extracts the value for the given key.
     *
     * @param bundleName the base name of the resource bundle; may not be null
     * @param itemKey the key whose associated value is to be retrieved; may not be null
     * @param isSiteBundle a flag indicating whether the bundle is site-specific, which affects
     * how the bundle name is resolved
     * @return an {@code Optional} containing the value associated with {@code itemKey} if found;
     * otherwise, an empty {@code Optional}
     * @throws ResourceQueryFailedException if the resource bundle cannot be found or if the contents
     * cannot be cast to strings, wrapping the original exception
     */
    Optional<String> getValueFor(final String bundleName, final String itemKey, final boolean isSiteBundle) {
        if (Contract.anyNull(bundleName, itemKey)) {
            return Optional.empty();
        }

        final String resolvedBundleName = resolveBundleName(bundleName, isSiteBundle);

        try{
            final ResourceBundle resourceBundle = resourceBundleRegistry.getBundle(resolvedBundleName);
            return resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, itemKey);
        } catch (final MissingResourceException | ClassCastException exception) {
            throw new ResourceQueryFailedException(bundleName, exception);
        }
    }

    /**
     * Retrieves the value associated with a specific key from a resource bundle for a given locale.
     *
     * <p>If any of the parameters {@code bundleName}, {@code itemKey}, or {@code locale} are null,
     * this method returns an empty {@code Optional}. Otherwise, it resolves the bundle name based on
     * whether the bundle is site-specific, attempts to load the resource bundle for the specified locale,
     * and extracts the value for the given key.
     *
     * @param bundleName the base name of the resource bundle; may not be null
     * @param locale the locale for which the resource bundle should be loaded; may not be null
     * @param itemKey the key whose associated value is to be retrieved; may not be null
     * @param isSiteBundle a flag indicating whether the bundle is site-specific, which affects
     * how the bundle name is resolved
     * @return an {@code Optional} containing the value associated with {@code itemKey} if found;
     * otherwise, an empty {@code Optional}
     * @throws ResourceQueryFailedException if the resource bundle cannot be found or if the contents
     * cannot be cast to strings, wrapping the original exception
     */
    Optional<String> getValueFor(final String bundleName,
                                 final String itemKey,
                                 final Locale locale,
                                 final boolean isSiteBundle) {
        if (Contract.anyNull(bundleName, itemKey, locale)) {
            return Optional.empty();
        }

        final String resolvedBundleName = resolveBundleName(bundleName, isSiteBundle);

        try{
            final ResourceBundle resourceBundle = resourceBundleRegistry.getBundle(resolvedBundleName, locale);
            return resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, itemKey);
        } catch (final MissingResourceException | ClassCastException exception) {
            throw new ResourceQueryFailedException(bundleName, exception);
        }
    }

    /**
     * Retrieves all key-value pairs from a resource bundle as a map.
     *
     * <p>This method resolves the actual bundle name based on the provided base bundle name
     * and whether it is a site-specific bundle. It then attempts to load the resource bundle
     * from the registry and extract all its values into a {@code Map<String, String>}.
     *
     * @param bundleName the base name of the resource bundle to retrieve; must not be null
     * @param isSiteBundle a flag indicating whether the bundle is site-specific, which affects
     * how the bundle name is resolved
     * @return a map containing all keys and their corresponding string values from the resolved resource bundle
     * @throws ResourceQueryFailedException if the resource bundle cannot be found or if the contents
     * cannot be cast to strings, wrapping the original exception
     */
    Map<String, String> getAllValuesFor(final @Nonnull String bundleName, final boolean isSiteBundle) {
        final String resolvedBundleName = resolveBundleName(bundleName, isSiteBundle);

        try{
            final ResourceBundle resourceBundle = resourceBundleRegistry.getBundle(resolvedBundleName);
            return resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);
        } catch (final MissingResourceException | ClassCastException exception) {
            throw new ResourceQueryFailedException(bundleName, exception);
        }
    }

    /**
     * Retrieves all key-value pairs from a resource bundle for a specific locale as a map.
     *
     * <p>This method resolves the actual bundle name based on the provided base bundle name
     * and whether it is a site-specific bundle. It then attempts to load the resource bundle
     * for the given locale from the registry and extract all its values into a {@code Map<String, String>}.
     *
     * @param bundleName the base name of the resource bundle to retrieve; must not be null
     * @param locale the locale for which the resource bundle should be loaded
     * @param isSiteBundle a flag indicating whether the bundle is site-specific, which affects
     * how the bundle name is resolved
     * @return a map containing all keys and their corresponding string values from the resolved resource bundle for the specified locale
     * @throws ResourceQueryFailedException if the resource bundle cannot be found or if the contents
     * cannot be cast to strings, wrapping the original exception
     */
    Map<String, String> getAllValuesFor(final @Nonnull String bundleName, final Locale locale, final boolean isSiteBundle) {
        final String resolvedBundleName = resolveBundleName(bundleName, isSiteBundle);

        try {
            final ResourceBundle resourceBundle = resourceBundleRegistry.getBundle(resolvedBundleName, locale);
            return resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);
        } catch (final MissingResourceException | ClassCastException exception) {
            throw new ResourceQueryFailedException(bundleName, exception);
        }
    }

    private String resolveBundleName(final @Nonnull String bundleName, final boolean isSiteBundle) {
        return isSiteBundle ? getSiteBundleName(bundleName) : bundleName;
    }

    private String getSiteBundleName(final @Nonnull String bundleName) {
        return siteProperties.getSiteId() + "." + bundleName;
    }
}