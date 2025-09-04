package com.visitscotland.brxm.exception;

public final class EventsSearchException extends EventsException {
    private static final String EXCEPTION_MESSAGE =
        "Failed to execute a search against Events, caused by %s";

    public EventsSearchException(final Throwable cause) {
        super(String.format(EXCEPTION_MESSAGE, cause.getMessage()), cause);
    }
}