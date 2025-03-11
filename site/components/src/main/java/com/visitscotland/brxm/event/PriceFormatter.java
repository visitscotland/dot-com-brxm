package com.visitscotland.brxm.event;

import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.services.ResourceBundleService;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.math.BigDecimal;

import java.util.Objects;
import java.util.Locale;

@Component
public class PriceFormatter {
    private static final String EVENTS_RESOURCE_BUNDLE_KEY = "events-listings";

    private final ResourceBundleService resourceBundleService;

    protected PriceFormatter(ResourceBundleService resourceBundleService) {
        this.resourceBundleService = resourceBundleService;
    }

    <E extends EventBSH> String format(final E eventBsh) {
        if (Objects.isNull(eventBsh) | Objects.isNull(eventBsh.getPrice())) {
            return null;
        }

        final String currency = eventBsh.getCurrency();
        final BigDecimal amount = BigDecimal.valueOf(eventBsh.getPrice())
            .setScale(2, RoundingMode.UNNECESSARY);

        if (amount.compareTo(BigDecimal.ZERO) == 0){
            return resourceBundleService.getResourceBundle(EVENTS_RESOURCE_BUNDLE_KEY, "price.free", Locale.UK);
        } else if (eventBsh.getVat()) {
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