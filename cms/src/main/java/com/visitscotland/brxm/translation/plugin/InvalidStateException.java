package com.visitscotland.brxm.translation.plugin;

public class InvalidStateException extends IllegalArgumentException {

    public InvalidStateException() {
    }

    public InvalidStateException(String s) {
        super(s);
    }

    public InvalidStateException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidStateException(Throwable throwable) {
        super(throwable);
    }
}
