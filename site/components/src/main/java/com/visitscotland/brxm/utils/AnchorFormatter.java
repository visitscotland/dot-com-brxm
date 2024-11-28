package com.visitscotland.brxm.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

@Component
public final class AnchorFormatter {
    // No Argument Constructor
    public AnchorFormatter() { }

    public String getAnchorOrFallback(String anchor, Supplier<String> fallback) {
        if(Objects.isNull(anchor) || StringUtils.isBlank(anchor)) {
            return formatString(fallback.get());
        }

        return anchor;
    }

    private String formatString(String string) {
        return string
            .toLowerCase()
            .replaceAll("[^a-z0-9]+", "-")
            .replaceAll("-+$", "")
            .toLowerCase();
    }
}