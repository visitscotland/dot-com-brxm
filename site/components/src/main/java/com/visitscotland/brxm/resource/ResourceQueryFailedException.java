package com.visitscotland.brxm.resource;

public class ResourceQueryFailedException extends ResourceException {
    private static final String EXCEPTION_MESSAGE =
        "Attempt to query the requested resource bundle, %s, has failed";

    public ResourceQueryFailedException(final String bundleName, final Throwable cause) {
        super(String.format(bundleName), cause);
    }
}