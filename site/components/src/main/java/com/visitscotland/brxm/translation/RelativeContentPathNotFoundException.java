package com.visitscotland.brxm.translation;

final class RelativeContentPathNotFoundException extends TranslationException {
    private static final String EXCEPTION_MESSAGE =
        "the relative content path could not be extracted from the resolved sitemap item";

    RelativeContentPathNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }
}