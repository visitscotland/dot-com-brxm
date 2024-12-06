package com.visitscotland.brxm.personalisation;

public class CountryNotPresentException extends PersonalisationException {
    private static final String EXCEPTION_MESSAGE = "The country is not present";

    public CountryNotPresentException() {
        super(EXCEPTION_MESSAGE);
    }
}