package com.visitscotland.brxm.factory.event;

import com.visitscotland.brxm.hippobeans.EventsListing;
import com.visitscotland.brxm.model.EventsLingsModule;
import com.visitscotland.brxm.model.event.EventFilter;
import com.visitscotland.brxm.model.event.EventValueOption;
import com.visitscotland.brxm.model.event.EventsListingTab;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.HippoUtilsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventsListingFactory {

    private final static String SECTORS_VALUE_LIST = "bsh-sectors";
    private final static String TRAINING_TOPICS_VALUE_LIST = "bsh-training-topics";
    private final static String INDUSTRY_EVENTS_TYPES_VALUE_LIST = "bsh-industry-event-types";
    private final static String REGIONS_VALUE_LIST = "bsh-regions";

    //TODO: Move constants to the Controller
    private final static String BASE_ENDPOINT_TRAINING = "/api/bsh-events-listing/training";
    private final static String BASE_ENDPOINT_INDUSTRY = "/api/bsh-events-listing/industry";
    private final static String BASE_ENDPOINT_TRADE = "/api/bsh-events-listing/travel";

    private final static String START_DATE = "startDate";
    private final static String END_DATE = "endDate";

    private final static String FREE = "free";
    private final static String ONLINE = "online";
    private final static String IN_PERSON = "inPerson";
    private final static String NATIONAL = "national";
    private final static String INTERNATIONAL = "international";

    private final static int DATE_GROUP = 0;
    private final static int BOOLEAN_GROUP = 1;

    private final HippoUtilsService hippoUtilsService;
    private final ResourceBundleService bundle;

    public EventsListingFactory(HippoUtilsService hippoUtilsService, ResourceBundleService bundle) {
        this.hippoUtilsService = hippoUtilsService;
        this.bundle = bundle;
    }

    public EventsLingsModule createModule(EventsListing document) {
        EventsLingsModule module = new EventsLingsModule();

        module.setHippoBean(document);
        module.addTab(addTrainingTab(document));
        module.addTab(addIndustryTab(document));
        module.addTab(addTradeTab(document));

        return module;
    }

    private EventsListingTab addTrainingTab(EventsListing document) {
        EventsListingTab tab = new EventsListingTab();

        tab.setTitle(document.getTrainingTitle());
        tab.setCopy(document.getTrainingCopy());
        tab.setBaseEndPoint(BASE_ENDPOINT_TRAINING);
        tab.setSortBy(buildSortBy(List.of("date", "price", "priceDesc")));
        tab.setFilters(buildTrainingFilters());

        return tab;
    }

    private List<EventFilter> buildTrainingFilters() {
        List<EventFilter> filters = new ArrayList<>();

        filters.add(buildDateField(START_DATE));
        filters.add(buildDateField(END_DATE));

        filters.add(buildBooleanField(FREE));
        filters.add(buildBooleanField(ONLINE));
        filters.add(buildBooleanField(IN_PERSON));

        filters.add(buildMultiselectField("topic", TRAINING_TOPICS_VALUE_LIST, 2));
        filters.add(buildMultiselectField("sector", SECTORS_VALUE_LIST, 3));

        return filters;
    }

    private EventsListingTab addIndustryTab(EventsListing document) {
        EventsListingTab tab = new EventsListingTab();

        tab.setTitle(document.getIndustryTitle());
        tab.setCopy(document.getIndustryCopy());
        tab.setBaseEndPoint(BASE_ENDPOINT_INDUSTRY);
        tab.setSortBy(buildSortBy(List.of("date", "price", "priceDesc")));
        tab.setFilters(buildIndustryFilters());

        return tab;
    }

    private List<EventFilter> buildIndustryFilters() {
        List<EventFilter> filters = new ArrayList<>();

        filters.add(buildDateField(START_DATE));
        filters.add(buildDateField(END_DATE));

        filters.add(buildBooleanField(FREE));
        filters.add(buildBooleanField(ONLINE));

        filters.add(buildMultiselectField("event-type", INDUSTRY_EVENTS_TYPES_VALUE_LIST, 2));
        filters.add(buildMultiselectField("sector", SECTORS_VALUE_LIST, 3));
        filters.add(buildMultiselectField("region", REGIONS_VALUE_LIST, 4));

        return filters;
    }

    private EventsListingTab addTradeTab(EventsListing document) {
        EventsListingTab tab = new EventsListingTab();

        tab.setTitle(document.getTradeTitle());
        tab.setCopy(document.getTradeCopy());
        tab.setBaseEndPoint(BASE_ENDPOINT_TRADE);
        tab.setSortBy(buildSortBy(List.of("date", "registration")));
        tab.setFilters(buildTradeFilters());

        return tab;
    }

    private List<EventFilter> buildTradeFilters() {
        List<EventFilter> filters = new ArrayList<>();

        filters.add(buildDateField(START_DATE));
        filters.add(buildDateField(END_DATE));

        filters.add(buildBooleanField(FREE));
        filters.add(buildBooleanField(ONLINE));
        filters.add(buildBooleanField(NATIONAL));
        filters.add(buildBooleanField(INTERNATIONAL));

        filters.add(buildMultiselectField("sector", SECTORS_VALUE_LIST, 2));

        return filters;
    }

    private List<EventValueOption> buildSortBy(List<String> options) {
        return options.stream().map(option -> new EventValueOption(option, "label-"+option))
                .collect(Collectors.toList());
    }

    /**
     * Create an EventFilter for a date field
     */
    private EventFilter buildDateField(String key) {
        return new EventFilter(key,"date-label-"+key, EventFilter.Type.DATE, DATE_GROUP);
    }

    /**
     * Create an EventFilter for a checkbox field
     */
    private EventFilter buildBooleanField(String key) {
        return new EventFilter(key, "boolean-label-"+key, EventFilter.Type.BOOLEAN, BOOLEAN_GROUP);
    }

    /**
     * Create an EventFilter from a Value List
     */
    private EventFilter buildMultiselectField(String key, String valueList, int group) {
        EventFilter filter = new EventFilter(key, "multi-label-"+key, EventFilter.Type.MULTI_SELECT, group);

        List<EventValueOption> values = hippoUtilsService.getValueMap(valueList).entrySet().stream()
                .map(e -> new EventValueOption(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        filter.setValues(values);

        return filter;
    }

}
