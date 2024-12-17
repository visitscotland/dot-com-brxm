package com.visitscotland.brxm.personalisation;

class CountryValidator implements Validator<String> {
    @Override
    public boolean isValid(String country) {
        return country.matches("^[A-Z]{2}$");
    }
}