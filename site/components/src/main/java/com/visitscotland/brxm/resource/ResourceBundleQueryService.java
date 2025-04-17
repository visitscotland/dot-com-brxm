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

    Optional<String> getValueFor(final String bundleName,
                                 final Locale locale,
                                 final String itemKey,
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

    Map<String, String> getAllValuesFor(final String bundleName, final boolean isSiteBundle) {
        final String resolvedBundleName = resolveBundleName(bundleName, isSiteBundle);

        try{
            final ResourceBundle resourceBundle = resourceBundleRegistry.getBundle(resolvedBundleName);
            return resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);
        } catch (final MissingResourceException | ClassCastException exception) {
            throw new ResourceQueryFailedException(bundleName, exception);
        }
    }

    Map<String, String> getAllValuesFor(final String bundleName, final Locale locale, final boolean isSiteBundle) {
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