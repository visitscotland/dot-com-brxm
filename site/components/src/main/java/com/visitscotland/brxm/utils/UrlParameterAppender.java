package com.visitscotland.brxm.utils;

public final class UrlParameterAppender {
    private UrlParameterAppender() { }

    public static String addParameter(String url) {
        return url.contains("?") ? "&" : "?";
    }
}