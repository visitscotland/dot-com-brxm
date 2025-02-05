package com.visitscotland.brxm.event;

import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.hippobeans.Price;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
class PriceFormatterTest {
    private static final String EVENTS_RESOURCE_BUNDLE_KEY = "events-listings";
    private static final String EVENT_FREE_LABEL_KEY = "price.free";
    private static final String EVENT_VAT_LABEL_KEY = "price.vat";
    private static final String ISO_4217_UK_CURRENCY_CODE = "GBP";

    @Mock
    private ResourceBundleService resourceBundleService;

    @InjectMocks
    private PriceFormatter priceFormatter;

    @Test
    void formatPrice_ValidPriceNoVat_ExpectedFormat() {
        final String expected = "1470.32 GBP";
        final Price price = mock(Price.class);

        when(price.getAmount()).thenReturn(1470.32D);
        when(price.getCurrency()).thenReturn(ISO_4217_UK_CURRENCY_CODE);

        final String result = priceFormatter.format(price);

        Assertions.assertEquals(expected, result);

        verify(price, times(1)).getAmount();
        verify(price, times(1)).getCurrency();
    }

    @Test
    void formatPrice_ValidPriceWithVat_ExpectedFormat() {
        final String vatLabel = "+VAT";
        final String expected = "10.00 GBP " + vatLabel;
        final Price price = mock(Price.class);

        when(price.getAmount()).thenReturn(10.00D);
        when(price.getCurrency()).thenReturn(ISO_4217_UK_CURRENCY_CODE);
        when(price.getVat()).thenReturn(true);
        when(resourceBundleService.getResourceBundle(EVENTS_RESOURCE_BUNDLE_KEY, EVENT_VAT_LABEL_KEY, Locale.UK))
            .thenReturn(vatLabel);

        final String result = priceFormatter.format(price);

        Assertions.assertEquals(expected, result);

        verify(resourceBundleService, times(1))
            .getResourceBundle(eq(EVENTS_RESOURCE_BUNDLE_KEY), eq(EVENT_VAT_LABEL_KEY), any(Locale.class));
    }

    @Test
    void formatPrice_PriceNull_ExpectNull() {
        final String result = priceFormatter.format(null);
        final Price price = mock(Price.class);

        Assertions.assertNull(result);

        verify(price, never()).getAmount();
        verify(price, never()).getVat();
        verify(price, never()).getCurrency();
    }

    @Test
    void formatPrice_PriceZero_ExpectFree() {
        final String expected = "free";
        final Price price = mock(Price.class);

        when(price.getAmount()).thenReturn(0.00D);
        when(price.getCurrency()).thenReturn(ISO_4217_UK_CURRENCY_CODE);
        when(price.getVat()).thenReturn(false);
        when(resourceBundleService.getResourceBundle(EVENTS_RESOURCE_BUNDLE_KEY, EVENT_FREE_LABEL_KEY, Locale.UK))
            .thenReturn(expected);

        final String result = priceFormatter.format(price);

        Assertions.assertEquals(expected, result);

        verify(resourceBundleService, times(1))
            .getResourceBundle(eq(EVENTS_RESOURCE_BUNDLE_KEY), eq(EVENT_FREE_LABEL_KEY), any(Locale.class));
    }
}
