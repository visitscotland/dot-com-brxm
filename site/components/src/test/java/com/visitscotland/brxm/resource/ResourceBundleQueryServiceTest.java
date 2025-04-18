package com.visitscotland.brxm.resource;

import com.visitscotland.brxm.utils.SiteProperties;

import org.hippoecm.hst.resourcebundle.ResourceBundleRegistry;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.Objects;
import java.util.Locale;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceBundleQueryServiceTest {
    private static final String SITE_ID = "site-id";
    private static final Locale UNITED_KINGDOM = Locale.UK;
    private static final String BUNDLE_NAME = "bundle.name";
    private static final String SITE_BUNDLE_NAME = SITE_ID + "." + BUNDLE_NAME;
    private static final String ITEM_KEY = "item.key";
    private static final boolean IS_NOT_SITE_BUNDLE = false;
    private static final boolean IS_SITE_BUNDLE = true;

    @Mock private ResourceBundleRegistry registry;
    @Mock private ResourceBundleValueExtractor valueExtractor;
    @Mock private SiteProperties siteProperties;
    @InjectMocks private ResourceBundleQueryService resourceBundleQueryService;

    @Test
    void getValueFor_ValidBundleNameItemKeyAndNotSiteBundle_OptionalOfValue() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);
        final var expected = "See more...";

        when(registry.getBundle(BUNDLE_NAME)).thenReturn(resourceBundle);
        when(valueExtractor.extractValueFromResourceBundle(resourceBundle, ITEM_KEY)).thenReturn(Optional.of(expected));

        var actual = resourceBundleQueryService.getValueFor(BUNDLE_NAME, ITEM_KEY, false);

        Assertions.assertAll(
            () -> Assertions.assertTrue(actual.isPresent()),
            () -> Assertions.assertEquals(expected, actual.orElseThrow())
        );

        verify(registry).getBundle(eq(BUNDLE_NAME));
        verify(valueExtractor).extractValueFromResourceBundle(eq(resourceBundle), eq(ITEM_KEY));
    }

    @Test
    void getValueFor_ValidBundleNameItemKeyLocaleAndNotSiteBundle_OptionalOfValue() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);
        final var expected = "Share";

        when(registry.getBundle(BUNDLE_NAME, UNITED_KINGDOM)).thenReturn(resourceBundle);
        when(valueExtractor.extractValueFromResourceBundle(resourceBundle, ITEM_KEY)).thenReturn(Optional.of(expected));

        var actual = resourceBundleQueryService
            .getValueFor(BUNDLE_NAME, ITEM_KEY, UNITED_KINGDOM, false);

        Assertions.assertAll(
            () -> Assertions.assertTrue(actual.isPresent()),
            () -> Assertions.assertEquals(expected, actual.orElseThrow())
        );
        verify(registry).getBundle(eq(BUNDLE_NAME), eq(UNITED_KINGDOM));
    }

    @Test
    void getValueFor_ValidBundleNameItemKeyAndIsSiteBundle_OptionalOfValue() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);
        final var expected = "Close Menu";

        when(siteProperties.getSiteId()).thenReturn(SITE_ID);
        when(registry.getBundle(SITE_BUNDLE_NAME)).thenReturn(resourceBundle);
        when(valueExtractor.extractValueFromResourceBundle(resourceBundle, ITEM_KEY)).thenReturn(Optional.of(expected));

        var actual = resourceBundleQueryService
            .getValueFor(BUNDLE_NAME, ITEM_KEY, true);

        Assertions.assertAll(
            () -> Assertions.assertTrue(actual.isPresent()),
            () -> Assertions.assertEquals(expected, actual.orElseThrow())
        );

        verify(siteProperties).getSiteId();
        verify(registry).getBundle(eq(SITE_BUNDLE_NAME));
    }

    @ParameterizedTest
    @MethodSource("nullParameterCombinationsForGetValueForWithLocale")
    void getValueFor_NullParameterCombinationsWithLocales_ThrowsNullPointerException(final String bundleName,
                                                                                     final String itemKey,
                                                                                     final Locale locale) {
        final var actual = Assertions.assertThrowsExactly(NullPointerException.class,
            () -> resourceBundleQueryService.getValueFor(bundleName, itemKey, locale, false));

        final var exceptionMessage = actual.getMessage();

        if(Objects.isNull(bundleName)) {
            Assertions.assertEquals("bundleName cannot be null", exceptionMessage);
        } else if(Objects.isNull(itemKey)) {
            Assertions.assertEquals("itemKey cannot be null", exceptionMessage);
        } else {
            Assertions.assertEquals("locale cannot be null", exceptionMessage);
        }
    }

    @ParameterizedTest
    @MethodSource("nullParameterCombinationsForGetValueForWithoutLocales")
    void getValueFor_NullParameterCombinationsWithoutLocales_ThrowsNullPointerException(final String bundleName,
                                                                                        final String itemKey) {
        final var actual = Assertions.assertThrowsExactly(NullPointerException.class,
            () -> resourceBundleQueryService.getValueFor(bundleName, itemKey, false));

        final var exceptionMessage = actual.getMessage();

        if(Objects.isNull(bundleName)) {
            Assertions.assertEquals("bundleName cannot be null", exceptionMessage);
        } else {
            Assertions.assertEquals("itemKey cannot be null", exceptionMessage);
        }
    }

    @Test
    void getValueFor_ThrowsResourceQueryFailedException_WhenGetBundleThrowsClassCastException() {
        when(registry.getBundle(BUNDLE_NAME, UNITED_KINGDOM)).thenThrow(ClassCastException.class);

        Assertions.assertThrowsExactly(ResourceQueryFailedException.class, () ->
            resourceBundleQueryService.getValueFor(BUNDLE_NAME, ITEM_KEY, UNITED_KINGDOM, false));
        verify(registry).getBundle(eq(BUNDLE_NAME), eq(UNITED_KINGDOM));
    }

    @Test
    void getAllValuesFor_ReturnsBundleValuesAsMap_WhenValidResourceBundleNameProvidedAndIsNotSiteBundle() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);
        final var expected = getMapTestData();

        when(registry.getBundle(BUNDLE_NAME)).thenReturn(resourceBundle);
        when(valueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle)).thenReturn(expected);

        final var actual = resourceBundleQueryService.getAllValuesFor(BUNDLE_NAME, IS_NOT_SITE_BUNDLE);

        Assertions.assertEquals(expected, actual);
        verify(registry).getBundle(eq(BUNDLE_NAME));
        verify(valueExtractor).extractValuesFromResourceBundleAsMap(eq(resourceBundle));
    }

    @Test
    void getAllValuesFor_ReturnsBundleValuesAsMap_WhenValidBundleNameProvidedAndIsSiteBundleFalse() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);
        final var expected = getMapTestData();

        when(siteProperties.getSiteId()).thenReturn(SITE_ID);
        when(registry.getBundle(SITE_BUNDLE_NAME)).thenReturn(resourceBundle);
        when(valueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle)).thenReturn(expected);

        final var actual = resourceBundleQueryService.getAllValuesFor(BUNDLE_NAME, IS_SITE_BUNDLE);

        Assertions.assertEquals(expected, actual);
        verify(siteProperties).getSiteId();
        verify(registry).getBundle(eq(SITE_BUNDLE_NAME));
    }

    @Test
    void getAllValuesFor_ReturnsBundleValuesAsMap_WhenValidBundleNameProvidedAndLocaleAndIsSiteBundleFalse() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);
        final var expected = getMapTestData();

        when(siteProperties.getSiteId()).thenReturn(SITE_ID);
        when(registry.getBundle(SITE_BUNDLE_NAME, UNITED_KINGDOM)).thenReturn(resourceBundle);
        when(valueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle)).thenReturn(expected);

        final var actual = resourceBundleQueryService.getAllValuesFor(BUNDLE_NAME, UNITED_KINGDOM, IS_SITE_BUNDLE);

        Assertions.assertEquals(expected, actual);
        verify(siteProperties).getSiteId();
        verify(registry).getBundle(eq(SITE_BUNDLE_NAME), eq(UNITED_KINGDOM));
    }

    @Test
    void getAllValuesFor_ReturnsBundleValuesAsMap_IfValidBundleNameAndLocaleAndSiteBundleFlagIsFalse() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);
        final var expected = getMapTestData();

        when(registry.getBundle(BUNDLE_NAME, UNITED_KINGDOM)).thenReturn(resourceBundle);
        when(valueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle)).thenReturn(expected);

        final var actual = resourceBundleQueryService.getAllValuesFor(BUNDLE_NAME, UNITED_KINGDOM, IS_NOT_SITE_BUNDLE);

        Assertions.assertEquals(expected, actual);
        verify(registry).getBundle(eq(BUNDLE_NAME), eq(UNITED_KINGDOM));
        verify(valueExtractor).extractValuesFromResourceBundleAsMap(eq(resourceBundle));
    }

    @Test
    void getAllValuesFor_ThrowsResourceQueryFailedException_WhenExtractValuesFromResourceBundleAsMapThrowsMissingResourceException() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);

        when(registry.getBundle(BUNDLE_NAME)).thenReturn(resourceBundle);
        when(valueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle))
            .thenThrow(MissingResourceException.class);

        Assertions.assertThrowsExactly(ResourceQueryFailedException.class, () ->
            resourceBundleQueryService.getAllValuesFor(BUNDLE_NAME, IS_NOT_SITE_BUNDLE));

        verify(valueExtractor).extractValuesFromResourceBundleAsMap(eq(resourceBundle));
    }

    @Test
    void getAllValuesFor_ThrowsResourceQueryFailedException_WhenExtractValuesFromResourceBundleAsMapThrowsClassCastException() {
        final var resourceBundle = Mockito.mock(ResourceBundle.class);

        when(registry.getBundle(BUNDLE_NAME)).thenReturn(resourceBundle);
        when(valueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle))
            .thenThrow(ClassCastException.class);

        Assertions.assertThrowsExactly(ResourceQueryFailedException.class, () ->
            resourceBundleQueryService.getAllValuesFor(BUNDLE_NAME, IS_NOT_SITE_BUNDLE));

        verify(valueExtractor).extractValuesFromResourceBundleAsMap(eq(resourceBundle));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void getAllValuesFor_ThrowsNullPointerException_IfBundleNameIsNull() {
        Assertions.assertThrowsExactly(NullPointerException.class,
            () -> resourceBundleQueryService.getAllValuesFor(null, IS_SITE_BUNDLE));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void getAllValuesFor_ThrowsNullPointerException_IfLocaleIsNull() {
        Assertions.assertThrowsExactly(NullPointerException.class,
            () -> resourceBundleQueryService.getAllValuesFor(BUNDLE_NAME, null, IS_SITE_BUNDLE));
    }

    private Map<String, String> getMapTestData() {
        return Map.of(
            "item.one", "This is the first item",
            "item.two", "This is the second item",
            "item.three", "This is the third item",
            "item.four", "This is the fourth item",
            "item.five", "This is the fifth item"
        );
    }

    private static Stream<Arguments> nullParameterCombinationsForGetValueForWithoutLocales() {
        return Stream.of(
            Arguments.of(null, ITEM_KEY),
            Arguments.of(BUNDLE_NAME, null)
        );
    }

    private static Stream<Arguments> nullParameterCombinationsForGetValueForWithLocale() {
        return Stream.of(
            Arguments.of(null, ITEM_KEY, UNITED_KINGDOM),
            Arguments.of(BUNDLE_NAME, null, UNITED_KINGDOM),
            Arguments.of(BUNDLE_NAME, ITEM_KEY, null)
        );
    }
}