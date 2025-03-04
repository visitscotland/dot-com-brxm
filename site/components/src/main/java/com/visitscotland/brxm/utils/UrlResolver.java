package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.components.navigation.MenuItem;

import java.util.Objects;
import java.util.Optional;

public final class UrlResolver {
    private static final String HASHTAG_URL_FALLBACK = "#";
    private static final String PAGE_NOT_FOUND = "pagenotfound";
    private UrlResolver() { }

    public static String getUrl(final MenuItem menuItem) {
        return Optional
            .ofNullable(menuItem)
            .map(UrlResolver::extractLink)
            .orElse(HASHTAG_URL_FALLBACK);
    }

    private static String extractLink(final MenuItem menuItem) {
        if (Objects.nonNull(menuItem.getPage())) {
            return menuItem.getPlainLink();
        } else if (Objects.nonNull(menuItem.getHstLink())) {
            return PAGE_NOT_FOUND;
        } else if (Objects.nonNull(menuItem.getExternalLink())) {
            return menuItem.getExternalLink().replace("\"", "");
        }

        return HASHTAG_URL_FALLBACK;
    }
}