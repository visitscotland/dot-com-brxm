package com.visitscotland.brxm.resource;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceBundleValueExtractorTest {
    private static final String UNKNOWN_KEY = "unknown";
    private static final String KNOWN_KEY = "known";

    @Mock private ResourceBundle resourceBundle;

    private ResourceBundleValueExtractor resourceBundleValueExtractor;

    @BeforeEach
    void setUp() {
        this.resourceBundleValueExtractor = new ResourceBundleValueExtractor();
    }

    @Test
    void When_ExtractValueFromResourceBundle_With_ValidResourceBundleAndUnknownKey_OptionalOfEmpty() {
        when(resourceBundle.containsKey(UNKNOWN_KEY)).thenReturn(false);

        var actual = resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, UNKNOWN_KEY);

        Assertions.assertTrue(actual.isEmpty());
        verify(resourceBundle).containsKey(eq(UNKNOWN_KEY));
    }

    @Test
    void When_ExtractValueFromResourceBundle_With_ValidResourceBundleAndKnownKey_OptionalOfResult() {
        final var bundleValue = "Hello world!";

        when(resourceBundle.containsKey(KNOWN_KEY)).thenReturn(true);
        when(resourceBundle.getString(KNOWN_KEY)).thenReturn(bundleValue);

        var actual = resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, KNOWN_KEY);

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(bundleValue, actual.get());
        verify(resourceBundle).getString(eq(KNOWN_KEY));
    }

    @Test
    void When_ExtractValueFromResourceBundle_With_ResourceBundleValueDoesNotExist_ThrowsMissingResourceException() {
        when(resourceBundle.containsKey(UNKNOWN_KEY)).thenReturn(true);
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(MissingResourceException.class);

        Assertions.assertThrows(MissingResourceException.class,
            () -> resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, UNKNOWN_KEY));

        verify(resourceBundle).getString(eq(UNKNOWN_KEY));
    }

    @Test
    void When_ExtractValueFromResourceBundle_With_ResourceBundleContainsValueOtherThanString_ThrowsClassCastException() {
        when(resourceBundle.containsKey(UNKNOWN_KEY)).thenReturn(true);
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(ClassCastException.class);

        Assertions.assertThrows(ClassCastException.class,
            () -> resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, UNKNOWN_KEY));

        verify(resourceBundle).getString(eq(UNKNOWN_KEY));
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsList_With_ResourceBundleAsListWithNoEntries_EmptyList() {
        when(resourceBundle.keySet()).thenReturn(Set.of());

        var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle);

        Assertions.assertTrue(actual.isEmpty());
        verify(resourceBundle).keySet();
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsList_With_ResourceBundleWithEntries_Expect_ListOfValuesAsList() {
        final var context = Map.of(
            "item.one", "I am this first value",
            "item.two", "I am the second value",
            "item.three", "I am the third value"
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle);

        context.forEach((key, value) -> {
            Assertions.assertTrue(actual.contains(value));
            verify(resourceBundle).getString(key);
        });

        verify(resourceBundle).keySet();
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsList_With_ResourceBundleAsListWithBlankEntries_Expect_ListNotToIncludeBlanks() {
        final var value = "  ";

        when(resourceBundle.keySet()).thenReturn(Set.of(KNOWN_KEY));
        when(resourceBundle.getString(KNOWN_KEY)).thenReturn(value);

        var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle);

        Assertions.assertTrue(actual.isEmpty());
        verify(resourceBundle).getString(eq(KNOWN_KEY));
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsList_With_ResourceBundleValueDoesNotExist_Expect_ThrowsMissingResourceException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(MissingResourceException.class);

        Assertions.assertThrows(MissingResourceException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle));

        verify(resourceBundle).getString(eq(UNKNOWN_KEY));
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsList_With_ResourceBundleContainsValueOtherThanString_Expect_ThrowsClassCastException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(ClassCastException.class);

        Assertions.assertThrows(ClassCastException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle));

        verify(resourceBundle).getString(eq(UNKNOWN_KEY));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void When_ExtractValuesFromResourceBundleAsList_With_NullResourceBundle_Expect_ThrowsNullPointerException() {
        final var message = "resourceBundle cannot be null";
        final var exception = Assertions.assertThrows(NullPointerException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(null));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsMap_With_ValidResourceBundle_Expect_MapOfBundleEntries() {
        final Map<String, String> context = Map.of(
            "item.one", "I am this first value",
            "item.two", "I am the second value"
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);

        Assertions.assertEquals(context, actual);
        verify(resourceBundle).keySet();
        actual.keySet().forEach(key -> verify(resourceBundle).getString(eq(key)));
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsMap_With_ResourceBundleValueDoesNotExist_Expect_ThrowsMissingResourceException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(MissingResourceException.class);

        Assertions.assertThrows(MissingResourceException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle));

        verify(resourceBundle).getString(eq(UNKNOWN_KEY));
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsMap_With_ResourceBundleContainsValueOtherThanString_Expect_ThrowsClassCastException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(ClassCastException.class);

        Assertions.assertThrows(ClassCastException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle));

        verify(resourceBundle).getString(eq(UNKNOWN_KEY));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void When_ExtractValuesFromResourceBundleAsMap_With_NullResourceBundle_Expect_ThrowsNullPointerException() {
        final var message = "resourceBundle cannot be null";
        final var exception = Assertions.assertThrows(NullPointerException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(null));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    void When_ExtractValuesFromResourceBundleAsMap_With_ValidResourceBundle_Expect_MapCannotBeModified() {
        final Map<String, String> context = Map.of(
            "item.one", "I am this first value",
            "item.two", "I am the second value"
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);

        Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.remove("item.one"));
    }
}