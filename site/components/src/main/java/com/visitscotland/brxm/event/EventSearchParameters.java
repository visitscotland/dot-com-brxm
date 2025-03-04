package com.visitscotland.brxm.event;

public interface EventSearchParameters {


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
