package com.visitscotland.brxm.model;

public class Persona {

    private String country;
    private String[] interests;
    private String[] sectors;

    public String[] getSectors() {
        return sectors;
    }

    public void setSectors(final String[] sectors) {
        this.sectors = sectors;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(final String[] interests) {
        this.interests = interests;
    }
}
