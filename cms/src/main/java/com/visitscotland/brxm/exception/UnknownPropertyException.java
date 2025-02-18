package com.visitscotland.brxm.exception;

public class UnknownPropertyException extends ValidationException {
    private static final String EXCEPTION_MESSAGE =
        "The validator attempted to access a JCR property '%s' which was not found within the Node";

    public UnknownPropertyException(String property) {
        super(String.format(EXCEPTION_MESSAGE, property));
    }
}
