package com.visitscotland.brxm.resource;

/**
 * Exception thrown when a query to access or extract values from a resource bundle fails.
 * <p>
 * This exception is typically thrown in scenarios such as:
 * <ul>
 *   <li>The requested resource bundle cannot be found.</li>
 *   <li>The resource bundle exists but data extraction fails.</li>
 *   <li>Type conversion of values from the resource bundle fails.</li>
 * </ul>
 * </p>
 */
public class ResourceQueryFailedException extends ResourceException {
    private static final String EXCEPTION_MESSAGE =
        "Attempt to query the requested resource bundle, %s, has failed";

    ResourceQueryFailedException(final String bundleName, final Throwable cause) {
        super(String.format(EXCEPTION_MESSAGE, bundleName), cause);
    }
}
