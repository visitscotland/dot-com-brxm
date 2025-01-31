package com.visitscotland.brxm.event;

import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.hippobeans.Price;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.math.BigDecimal;

import java.util.Objects;
import java.util.Locale;

@Component
public class PriceFormatter {
    private static final String EVENTS_RESOURCE_BUNDLE_KEY = "events-listings";

    private final ResourceBundleService resourceBundleService;

    public PriceFormatter(ResourceBundleService resourceBundleService) {
        this.resourceBundleService = resourceBundleService;
    }

    public <P extends Price> String format(final P price) {
        if (Objects.isNull(price)) {
            return null;
        }

        final String currency = price.getCurrency();
        final BigDecimal amount = BigDecimal.valueOf(price.getPrice())
            .setScale(2, RoundingMode.UNNECESSARY);

        if (price.getVat()) {
            return formatPriceWithVat(amount, currency);
        }

        return amount.compareTo(BigDecimal.ZERO) == 0
            ? resourceBundleService.getResourceBundle(EVENTS_RESOURCE_BUNDLE_KEY, "price.free", Locale.UK)
            : amount + " " + currency;
    }

    private String formatPriceWithVat(BigDecimal amount, String currency) {
        final String vatLabel = resourceBundleService.getResourceBundle(EVENTS_RESOURCE_BUNDLE_KEY, "price.vat", Locale.UK);
        return amount + " " + currency + " " + vatLabel;
    }
}