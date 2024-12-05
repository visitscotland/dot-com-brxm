package com.visitscotland.brxm.personalisation;

class VisitorContext {
    private final String country;

    public VisitorContext(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return "VisitorContext{" +
                "country='" + country + '\'' +
                '}';
    }
}