package com.visitscotland.brxm.personalisation;

class VisitorContext {
    private final String country;

    VisitorContext(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }
}