package com.visitscotland.brxm.factory.event;

import com.visitscotland.brxm.hippobeans.EventsListing;
import com.visitscotland.brxm.model.EventsLingsModule;
import com.visitscotland.brxm.model.event.EventFilter;
import com.visitscotland.brxm.model.event.EventValueOption;
import com.visitscotland.brxm.model.event.EventsListingTab;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.CMSProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.visitscotland.brxm.event.EventSearchParameters.*;

@Component
public class EventsListingFactory {

    //Endpoints
    private final static String BASE_ENDPOINT_TRAINING = "/api/bsh/events-search/training";
    private final static String BASE_ENDPOINT_INDUSTRY = "/api/bsh/events-search/industry";
    private final static String BASE_ENDPOINT_TRADE = "/api/bsh/events-search/travel-trade";

    // Value Lists
    private final static String SECTORS_VALUE_LIST = "bsh-sectors";
    private final static String TRAINING_TOPICS_VALUE_LIST = "bsh-training-topics";
    private final static String INDUSTRY_EVENTS_TYPES_VALUE_LIST = "bsh-industry-event-types";
    private final static String REGIONS_VALUE_LIST = "bsh-regions";

    // Resource Bundle keys
    private final static String BUNDLE = "events-listings";
    private final static String START_DATE_LABEL = "date.start-date";
    private final static String END_DATE_LABEL = "date.end-date";
    private final static String FREE_LABEL = "price.free";
    private final static String ONLINE_LABEL = "location.online";
    private final static String IN_PERSON_LABEL = "location.in-person";
    private final static String NATIONAL_LABEL = "location.national";
    private final static String INTERNATIONAL_LABEL = "location.international";
    private final static String SECTOR_LABEL = "sector";
    private final static String TOPIC_LABEL = "training-topic";
    private final static String REGION_LABEL = "region";
    private final static String EVENT_TYPE_LABEL = "event-type";

    // Grouping indices
    private final static int DATE_GROUP = 0;
    private final static int BOOLEAN_GROUP = 1;

    private final HippoUtilsService hippoUtilsService;
    private final ResourceBundleService bundle;
    private final CMSProperties cmsProperties;


    public EventsListingFactory(HippoUtilsService hippoUtilsService, ResourceBundleService bundle,
            CMSProperties cmsProperties) {
        this.hippoUtilsService = hippoUtilsService;
        this.bundle = bundle;
        this.cmsProperties = cmsProperties;
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
        tab.setBaseEndPoint(cmsProperties.getBasePath() + BASE_ENDPOINT_TRAINING);
        tab.setSortBy(buildSortBy(List.of(DATE, PRICE, PRICE_DESC)));
        tab.setFilters(buildTrainingFilters());

        return tab;
    }

    private List<EventFilter> buildTrainingFilters() {
        List<EventFilter> filters = new ArrayList<>();

        addDateFields(filters);
        addBooleanFields(filters, true, false);
        filters.add(buildMultiselectField(TOPIC_PARAM, TOPIC_LABEL, TRAINING_TOPICS_VALUE_LIST, 2));
        filters.add(buildMultiselectField(SECTOR_PARAM, SECTOR_LABEL, SECTORS_VALUE_LIST, 3));

        return filters;
    }

    private EventsListingTab addIndustryTab(EventsListing document) {
        EventsListingTab tab = new EventsListingTab();

        tab.setTitle(document.getIndustryTitle());
        tab.setCopy(document.getIndustryCopy());
        tab.setBaseEndPoint(cmsProperties.getBasePath() + BASE_ENDPOINT_INDUSTRY);
        tab.setSortBy(buildSortBy(List.of(DATE, PRICE, PRICE_DESC)));
        tab.setFilters(buildIndustryFilters());

        return tab;
    }

    private List<EventFilter> buildIndustryFilters() {
        List<EventFilter> filters = new ArrayList<>();

        addDateFields(filters);
        addBooleanFields(filters, false, false);
        filters.add(buildMultiselectField(EVENT_TYPE_PARAM, EVENT_TYPE_LABEL, INDUSTRY_EVENTS_TYPES_VALUE_LIST, 2));
        filters.add(buildMultiselectField(SECTOR_PARAM, SECTOR_LABEL, SECTORS_VALUE_LIST, 3));
        filters.add(buildMultiselectField(REGION_PARAM, REGION_LABEL, REGIONS_VALUE_LIST, 4));

        return filters;
    }

    private EventsListingTab addTradeTab(EventsListing document) {
        EventsListingTab tab = new EventsListingTab();

        tab.setTitle(document.getTradeTitle());
        tab.setCopy(document.getTradeCopy());
        tab.setBaseEndPoint(cmsProperties.getBasePath() + BASE_ENDPOINT_TRADE);
        tab.setSortBy(buildSortBy(List.of(DATE, REGISTRATION)));
        tab.setFilters(buildTradeFilters());

        return tab;
    }

    private List<EventFilter> buildTradeFilters() {
        List<EventFilter> filters = new ArrayList<>();

        addDateFields(filters);
        addBooleanFields(filters, false, true);

        return filters;
    }

    private void addDateFields(List<EventFilter> filters) {
        filters.add(buildDateField(START_DATE_PARAM, START_DATE_LABEL));
        filters.add(buildDateField(END_DATE_PARAM, END_DATE_LABEL));
    }

    private void addBooleanFields(List<EventFilter> filters, boolean inPerson, boolean national) {

        filters.add(buildBooleanField(FREE_PARAM, FREE_LABEL));
        filters.add(buildBooleanField(ONLINE_PARAM, ONLINE_LABEL));

        if (inPerson) {
            filters.add(buildBooleanField(IN_PERSON_PARAM, IN_PERSON_LABEL));
        }
        if (national) {
            filters.add(buildBooleanField(NATIONAL_PARAM, NATIONAL_LABEL));
            filters.add(buildBooleanField(INTERNATIONAL_PARAM, INTERNATIONAL_LABEL));
        }
    }

    private List<EventValueOption> buildSortBy(List<String> options) {
        return options.stream().map(option -> new EventValueOption(option, getLabel("sort." + option)))
                .collect(Collectors.toList());
    }

    /**
     * Create an EventFilter for a date field
     */
    private EventFilter buildDateField(String key, String labelKey) {
        return new EventFilter(key, getLabel(labelKey), EventFilter.Type.DATE, DATE_GROUP);
    }

    /**
     * Create an EventFilter for a checkbox field
     */
    private EventFilter buildBooleanField(String key, String labelKey) {
        return new EventFilter(key, getLabel(labelKey), EventFilter.Type.BOOLEAN, BOOLEAN_GROUP);
    }

    /**
     * Create an EventFilter from a Value List
     */
    private EventFilter buildMultiselectField(String key, String labelKey, String valueList, int group) {
        EventFilter filter = new EventFilter(key, getLabel(labelKey), EventFilter.Type.MULTISELECT, group);

        List<EventValueOption> values = hippoUtilsService.getValueMap(valueList).entrySet().stream()
                .map(e -> new EventValueOption(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        filter.setValues(values);

        return filter;
    }

    private String getLabel(String key) {
        //TODO: Should we use RequestContextProvider.get().getPreferredLocale() ?
        return bundle.getResourceBundle(BUNDLE, key, Locale.UK);
    }
}
