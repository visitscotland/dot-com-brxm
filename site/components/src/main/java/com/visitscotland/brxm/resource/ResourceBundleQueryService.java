package com.visitscotland.brxm.resource;

import com.visitscotland.brxm.utils.SiteProperties;

import org.springframework.stereotype.Component;

import org.hippoecm.hst.resourcebundle.ResourceBundleRegistry;

import javax.annotation.Nonnull;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Optional;
import java.util.Objects;
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
    Optional<String> getValueFor(final @Nonnull String bundleName,
                                 final @Nonnull String itemKey,
                                 final boolean isSiteBundle) {
        Objects.requireNonNull(bundleName, "bundleName cannot be null");
        Objects.requireNonNull(itemKey, "itemKey cannot be null");

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
    Optional<String> getValueFor(final @Nonnull String bundleName,
                                 final @Nonnull String itemKey,
                                 final @Nonnull Locale locale,
                                 final boolean isSiteBundle) {
        Objects.requireNonNull(bundleName, "bundleName cannot be null");
        Objects.requireNonNull(itemKey, "itemKey cannot be null");
        Objects.requireNonNull(locale, "locale cannot be null");

        final String resolvedBundleName = resolveBundleName(bundleName, isSiteBundle);

        try{
            final ResourceBundle resourceBundle = resourceBundleRegistry.getBundle(resolvedBundleName, locale);
            return resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, itemKey);
        } catch (final MissingResourceException | ClassCastException exception) {
            throw new ResourceQueryFailedException(bundleName, exception);
        }
    }

    /**
     * Retrieves all key-value pairs from the specified resource bundle as a {@code Map}.
     * <p>
     * The method resolves the actual bundle name based on the provided {@code bundleName} and
     * whether it is a site bundle or not. It then attempts to load the resource bundle without specifying a locale
     * and extracts all values as a map.
     * </p>
     *
     * @param bundleName the base name of the resource bundle to retrieve; must not be {@code null}
     * @param isSiteBundle flag indicating whether the bundle is a site bundle, affecting bundle name resolution
     * @return a map containing all key-value pairs from the resolved resource bundle
     * @throws NullPointerException if {@code bundleName} is {@code null}
     * @throws ResourceQueryFailedException if the resource bundle cannot be found, loaded, or cast properly
     */
    Map<String, String> getAllValuesFor(final @Nonnull String bundleName, final boolean isSiteBundle) {
        Objects.requireNonNull(bundleName, "bundleName cannot be null");

        final String resolvedBundleName = resolveBundleName(bundleName, isSiteBundle);

        try{
            final ResourceBundle resourceBundle = resourceBundleRegistry.getBundle(resolvedBundleName);
            return resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);
        } catch (final MissingResourceException | ClassCastException exception) {
            throw new ResourceQueryFailedException(bundleName, exception);
        }
    }

    /**
     * Retrieves all key-value pairs from the specified resource bundle as a {@code Map}.
     * <p>
     * The method resolves the actual bundle name based on the provided {@code bundleName} and
     * whether it is a site bundle or not. It then attempts to load the resource bundle for the
     * given {@code locale} and extracts all values as a map.
     * </p>
     *
     * @param bundleName the base name of the resource bundle to retrieve; must not be {@code null}
     * @param locale the locale for which a resource bundle is desired; must not be {@code null}
     * @param isSiteBundle flag indicating whether the bundle is a site bundle, affecting bundle name resolution
     * @return a map containing all key-value pairs from the resolved resource bundle
     * @throws NullPointerException if {@code bundleName} or {@code locale} is {@code null}
     * @throws ResourceQueryFailedException if the resource bundle cannot be found, loaded, or cast properly
     */
    Map<String, String> getAllValuesFor(final @Nonnull String bundleName,
                                        final @Nonnull Locale locale,
                                        final boolean isSiteBundle) {
        Objects.requireNonNull(bundleName, "bundleName cannot be null");
        Objects.requireNonNull(locale, "locale cannot be null");

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