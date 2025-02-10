package com.visitscotland.brxm.rest.event;

public interface EventSearchParameters {

    //TODO: These constants are redundant to EventsListingFactory
    String BASE_ENDPOINT_TRAINING = "/api/bsh-events-listing/training";
    String BASE_ENDPOINT_INDUSTRY = "/api/bsh-events-listing/industry";
    String BASE_ENDPOINT_TRADE = "/api/bsh-events-listing/travel";

    String PAGE_PARAM = "page";

    String START_DATE_PARAM = "start-date";
    String END_DATE_PARAM = "end-date";

    String FREE_PARAM = "free";
    String ONLINE_PARAM = "online";
    String IN_PERSON_PARAM = "in-person";
    String NATIONAL_PARAM = "national";
    String INTERNATIONAL_PARAM = "international";

    String SECTOR_PARAM = "sector";
    String TOPIC_PARAM = "topic";
    String REGION_PARAM = "region";
    String EVENT_TYPE_PARAM = "event-type";

    //Sorting
    String SORT_BY_PARAM = "sort";
    String DATE = "date";
    String REGISTRATION = "registration";
    String PRICE = "price";
    String PRICE_DESC = "price-desc";
}
