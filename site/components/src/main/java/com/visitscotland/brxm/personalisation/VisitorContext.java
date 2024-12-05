package com.visitscotland.brxm.personalisation;

public class VisitorContext {
    private final String country;

    VisitorContext(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }
}