package com.visitscotland.brxm.personalisation;

class VisitorContext {
    private final String country;

    VisitorContext(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    static class Builder {
        private String country;

        Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        VisitorContext build() {
            return new VisitorContext(country);
        }
    }
}