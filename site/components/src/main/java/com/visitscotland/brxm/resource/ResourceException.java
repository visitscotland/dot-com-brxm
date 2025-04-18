package com.visitscotland.brxm.resource;

/**
 * Base exception class for all resource-related errors in the application.
 * <p>
 * This exception serves as the superclass for more specific resource exceptions,
 * providing a consistent way to represent errors encountered during resource handling.
 * </p>
 */
public abstract class ResourceException extends RuntimeException {
    protected ResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}