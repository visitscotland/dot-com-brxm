package com.visitscotland.brxm.resource;

class ResourceQueryFailedException extends ResourceException {
    private static final String EXCEPTION_MESSAGE =
        "Attempt to query the requested resource bundle, %s, has failed";

    ResourceQueryFailedException(final String bundleName, final Throwable cause) {
        super(String.format(EXCEPTION_MESSAGE, bundleName), cause);
    }
}