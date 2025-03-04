package com.visitscotland.brxm.utils;

public final class JsonEscaper {
    private JsonEscaper() { }

    public static String escape(String original, boolean isJsonObject) {
        String escaped = original.replace("'", "\\'");
        escaped = isJsonObject ? escapeJsonObject(escaped) : escapeQuotes(escaped);

        return escaped;
    }

    private static String escapeJsonObject(final String json) {
        return json.replace("\"", "'");
    }

    private static String escapeQuotes(final String json) {
        return json.replace("\"", "&quot;");
    }
}