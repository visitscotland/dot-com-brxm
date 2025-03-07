package com.visitscotland.brxm.event;

import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.hippobeans.Price;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.math.BigDecimal;

import java.util.Objects;
import java.util.Locale;

@Component
class PriceFormatter {
    private static final String EVENTS_RESOURCE_BUNDLE_KEY = "events-listings";

    private final ResourceBundleService resourceBundleService;

    protected PriceFormatter(ResourceBundleService resourceBundleService) {
        this.resourceBundleService = resourceBundleService;
    }

    <P extends Price> String format(final P price) {
        if (Objects.isNull(price)) {
            return null;
        }

        final String currency = price.getCurrency();
        final BigDecimal amount = BigDecimal.valueOf(price.getAmount())
            .setScale(2, RoundingMode.UNNECESSARY);

        if (amount.compareTo(BigDecimal.ZERO) == 0){
            return resourceBundleService.getResourceBundle(EVENTS_RESOURCE_BUNDLE_KEY, "price.free", Locale.UK);
        } else if (price.getVat()) {
            return formatPriceWithVat(amount, currency);
        } else {
            return amount + " " + currency;
        }
    }

    private String formatPriceWithVat(BigDecimal amount, String currency) {
        final String vatLabel = resourceBundleService.getResourceBundle(EVENTS_RESOURCE_BUNDLE_KEY, "price.vat", Locale.UK);
        return amount + " " + currency + " " + vatLabel;
    }
}