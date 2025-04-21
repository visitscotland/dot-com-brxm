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
    void extractValueFromResourceBundle_ValidResourceBundleAndUnknownKey_OptionalOfEmpty() {
        when(resourceBundle.containsKey(UNKNOWN_KEY)).thenReturn(false);

        final var actual = resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, UNKNOWN_KEY);

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void extractValueFromResourceBundle_ValidResourceBundleAndKnownKey_OptionalOfResult() {
        final var bundleValue = "Hello world!";

        when(resourceBundle.containsKey(KNOWN_KEY)).thenReturn(true);
        when(resourceBundle.getString(KNOWN_KEY)).thenReturn(bundleValue);

        final var actual = resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, KNOWN_KEY);

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(bundleValue, actual.get());
    }

    @Test
    void extractValueFromResourceBundle_ResourceBundleDoesNotExist_ThrowsMissingResourceException() {
        when(resourceBundle.containsKey(UNKNOWN_KEY)).thenReturn(true);
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(MissingResourceException.class);

        Assertions.assertThrowsExactly(MissingResourceException.class,
            () -> resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, UNKNOWN_KEY));
    }

    @Test
    void extractValueFromResourceBundle_ResourceBundleContainsValuesOtherThanStrings_ThrowsClassCastException() {
        when(resourceBundle.containsKey(UNKNOWN_KEY)).thenReturn(true);
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(ClassCastException.class);

        Assertions.assertThrowsExactly(ClassCastException.class,
            () -> resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, UNKNOWN_KEY));
    }

    @Test
    void extractValuesFromResourceBundleAsList_ResourceBundleWithNoEntries_EmptyList() {
        when(resourceBundle.keySet()).thenReturn(Set.of());

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle);

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void extractValuesFromResourceBundleAsList_ValidResourceBundle_Expect_ValuesAsList() {
        final var context = Map.of(
            "item.one", "I am this first value",
            "item.two", "I am the second value",
            "item.three", "I am the third value"
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle);

        context.forEach((key, value) ->
            Assertions.assertTrue(actual.contains(value)));
    }

    @Test
    void extractValuesFromResourceBundleAsList_ResourceBundleContainsBlankValues_ListNotToIncludeBlankValues() {
        final var value = "  ";

        when(resourceBundle.keySet()).thenReturn(Set.of(KNOWN_KEY));
        when(resourceBundle.getString(KNOWN_KEY)).thenReturn(value);

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle);

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void extractValuesFromResourceBundleAsList_ResourceBundleValueDoesNotExist_ThrowsMissingResourceException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(MissingResourceException.class);

        Assertions.assertThrowsExactly(MissingResourceException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle));
    }

    @Test
    void extractValuesFromResourceBundleAsList_ResourceBundleContainsValuesOtherThanStrings_ThrowsClassCastException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(ClassCastException.class);

        Assertions.assertThrowsExactly(ClassCastException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle));
    }

    @Test
    void extractValuesFromResourceBundleAsList_ValidResourceBundle_ReturnedListIsImmutable() {
        final var context = Map.of(
            "item.one", "I am this first value",
            "item.two", "I am the second value",
            "item.three", "I am the third value"
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(resourceBundle);

        Assertions.assertThrows(UnsupportedOperationException.class,
            () -> actual.remove(0));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void extractValuesFromResourceBundleAsList_NullResourceBundle_ThrowsNullPointerException() {
        final var message = "resourceBundle cannot be null";
        final var exception = Assertions.assertThrows(NullPointerException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsList(null));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    void extractValuesFromResourceBundleAsMap_ValidResourceBundle_MapOfBundleEntries() {
        final Map<String, String> context = Map.of(
            "item.one", "I am this first value",
            "item.two", "I am the second value"
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);

        Assertions.assertEquals(context, actual);
    }

    @Test
    void extractValuesFromResourceBundleAsMap_ResourceBundleIncludesBlankValues_MapNotToIncludeBlankValues() {
        final Map<String, String> context = Map.of(
            "item.one", "",
            "item.two", "   "
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        final var actual = resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle);

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void extractValuesFromResourceBundleAsMap_ResourceBundleValueDoesNotExist_Expect_ThrowsMissingResourceException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(MissingResourceException.class);

        Assertions.assertThrowsExactly(MissingResourceException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle));
    }

    @Test
    void extractValuesFromResourceBundleAsMap_ResourceBundleContainsValuesOtherThanStrings_ThrowsClassCastException() {
        when(resourceBundle.keySet()).thenReturn(Set.of(UNKNOWN_KEY));
        when(resourceBundle.getString(UNKNOWN_KEY)).thenThrow(ClassCastException.class);

        Assertions.assertThrowsExactly(ClassCastException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(resourceBundle));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void extractValuesFromResourceBundleAsMap_NullResourceBundle_ThrowsNullPointerException() {
        final var message = "resourceBundle cannot be null";
        final var exception = Assertions.assertThrowsExactly(NullPointerException.class,
            () -> resourceBundleValueExtractor.extractValuesFromResourceBundleAsMap(null));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    void extractValuesFromResourceBundleAsMap_ValidResourceBundle_ReturnedMapIsImmutable() {
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