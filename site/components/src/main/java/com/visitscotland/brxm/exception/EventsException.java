package com.visitscotland.brxm.exception;

abstract class EventsException extends BusinessSupportHubException {
    protected EventsException(final String message, final Throwable cause) {
        super(message, cause);
    }
}