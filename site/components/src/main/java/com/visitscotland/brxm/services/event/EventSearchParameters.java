package com.visitscotland.brxm.services.event;

public interface EventSearchParameters {

    //TODO: Move constants to the Controller
    String BASE_ENDPOINT_TRAINING = "/api/bsh-events-listing/training";
    String BASE_ENDPOINT_INDUSTRY = "/api/bsh-events-listing/industry";
    String BASE_ENDPOINT_TRADE = "/api/bsh-events-listing/travel";

    String START_DATE = "start-date";
    String END_DATE = "end-date";

    String FREE = "free";
    String ONLINE = "online";
    String IN_PERSON = "in-person";
    String NATIONAL = "national";
    String INTERNATIONAL = "international";

    String SECTOR = "sector";
    String TOPIC = "topic";
    String REGION = "region";
    String EVENT_TYPE = "event-type";

    String DATE = "date";
    String REGISTRATION = "registration";
    String PRICE = "price";
    String PRICE_DESC = "price-desc";
}
