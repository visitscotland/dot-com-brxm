package com.visitscotland.brxm.resource;

public abstract class ResourceException extends RuntimeException {
    protected ResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}