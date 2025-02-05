package com.visitscotland.brxm.exception;

public abstract class ValidationException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE =
        "An exception occurred while validating, more details: %s";

    public ValidationException(String message) {
        super(String.format(EXCEPTION_MESSAGE, message));
    }
}