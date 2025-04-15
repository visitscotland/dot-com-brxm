package com.visitscotland.brxm.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.ResourceBundle;
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
    void When_extractValueFromResourceBundle_With_ValidResourceBundleAndUnknownKey_OptionalOfEmpty() {
        when(resourceBundle.containsKey(UNKNOWN_KEY)).thenReturn(false);

        var actual = resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, UNKNOWN_KEY);

        Assertions.assertTrue(actual.isEmpty());
        verify(resourceBundle).containsKey(eq(UNKNOWN_KEY));
    }

    @Test
    void When_extractValueFromResourceBundle_With_ValidResourceBundleAndKnownKey_OptionalOfResult() {
        final var bundleValue = "Hello world!";

        when(resourceBundle.containsKey(KNOWN_KEY)).thenReturn(true);
        when(resourceBundle.getString(KNOWN_KEY)).thenReturn(bundleValue);

        var actual = resourceBundleValueExtractor.extractValueFromResourceBundle(resourceBundle, KNOWN_KEY);

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(bundleValue, actual.get());
        verify(resourceBundle).getString(eq(KNOWN_KEY));
    }

    @Test
    void When_extractValuesFromResourceBundle_With_ResourceBundleWithNoEntries_EmptyList() {
        when(resourceBundle.keySet()).thenReturn(Set.of());

        var actual = resourceBundleValueExtractor.extractValuesFromResourceBundle(resourceBundle);

        Assertions.assertTrue(actual.isEmpty());
        verify(resourceBundle).keySet();
    }

    @Test
    void When_extractValuesFromResourceBundle_With_ResourceBundleWithEntries_Expect_ListOfValues() {
        final var context = Map.of(
            "item.one", "I am this first value",
            "item.two", "I am the second value",
            "item.three", "I am the third value"
        );

        when(resourceBundle.keySet()).thenReturn(context.keySet());
        context.forEach((key, value) -> when(resourceBundle.getString(key)).thenReturn(value));

        var actual = resourceBundleValueExtractor.extractValuesFromResourceBundle(resourceBundle);

        Assertions.assertFalse(actual.isEmpty());

        context.forEach((key, value) -> {
            Assertions.assertTrue(actual.contains(value));
            verify(resourceBundle).getString(key);
        });
    }

    @Test
    void When_extractValuesFromResourceBundle_With_ResourceBundleWithBlankEntries_Expect_ListNotToIncludeBlanks() {
        final var value = "  ";

        when(resourceBundle.keySet()).thenReturn(Set.of(KNOWN_KEY));
        when(resourceBundle.getString(KNOWN_KEY)).thenReturn(value);

        var actual = resourceBundleValueExtractor.extractValuesFromResourceBundle(resourceBundle);

        Assertions.assertTrue(actual.isEmpty());
        verify(resourceBundle).getString(eq(KNOWN_KEY));
    }
}