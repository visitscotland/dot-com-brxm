package com.visitscotland.brxm.translation;

abstract class TranslationException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Translation failed, details: %s";

    protected TranslationException(final String message) {
        super(String.format(EXCEPTION_MESSAGE, message));
    }
}