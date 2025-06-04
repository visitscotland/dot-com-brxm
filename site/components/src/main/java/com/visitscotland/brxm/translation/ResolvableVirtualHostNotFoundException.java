package com.visitscotland.brxm.translation;

final class ResolvableVirtualHostNotFoundException extends TranslationException {
    private static final String EXCEPTION_MESSAGE =
        "failed to get the resolved virtual host from the request";

    ResolvableVirtualHostNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }
}