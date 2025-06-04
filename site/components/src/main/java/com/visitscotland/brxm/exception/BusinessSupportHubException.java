package com.visitscotland.brxm.exception;

abstract class BusinessSupportHubException extends RuntimeException {
    protected BusinessSupportHubException(final String message, final Throwable cause) {
        super(message, cause);
    }
}