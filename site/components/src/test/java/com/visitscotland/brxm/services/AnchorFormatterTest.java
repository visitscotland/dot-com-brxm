package com.visitscotland.brxm.services;

import com.visitscotland.brxm.utils.AnchorFormatter;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.function.Supplier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnchorFormatterTest {
    @Mock
    private Supplier<String> fallbackSupplier;
    private AnchorFormatter anchorFormatter;

    @BeforeEach
    void setUp() {
        this.anchorFormatter = new AnchorFormatter();
    }

    @Test
    void testGetAnchorOrFallback_ReturnsAnchor_WhenAnchorIsValid() {
        final String anchor = "validAnchor";

        final String result = anchorFormatter.getAnchorOrFallback(anchor, () -> "fallbackString");

        Assertions.assertEquals("validAnchor", result, "Should return the provided anchor if valid.");
    }

    @Test
    void testGetAnchorOrFallback_UsesFallback_WhenAnchorIsNull() {
        final String fallback = "fallbackstring";

        when(fallbackSupplier.get()).thenReturn(fallback);

        final String result = anchorFormatter.getAnchorOrFallback(null, fallbackSupplier);

        Assertions.assertEquals("fallbackstring", result);
        verify(fallbackSupplier, times(1)).get();
    }

    @Test
    void testGetAnchorOrFallback_UsesFallback_WhenAnchorIsBlank() {
        final String fallback = "fallbackstring";

        when(fallbackSupplier.get()).thenReturn(fallback);

        final String result = anchorFormatter.getAnchorOrFallback("   ", fallbackSupplier);

        Assertions.assertEquals("fallbackstring", result);
        verify(fallbackSupplier, times(1)).get();
    }

    @Test
    void testGetAnchorOrFallback_FormatsFallbackCorrectly() {
        final String fallback = "Fallback String with !@#Symbols ?";

        when(fallbackSupplier.get()).thenReturn(fallback);

        final String result = anchorFormatter.getAnchorOrFallback(null, fallbackSupplier);

        Assertions.assertEquals("fallback-string-with-symbols", result);
    }
}