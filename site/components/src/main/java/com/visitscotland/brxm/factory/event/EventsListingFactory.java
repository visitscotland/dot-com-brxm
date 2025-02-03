package com.visitscotland.brxm.factory.event;

import com.visitscotland.brxm.hippobeans.EventsListing;
import com.visitscotland.brxm.model.EventsLingsModule;
import com.visitscotland.brxm.model.event.EventFilter;
import com.visitscotland.brxm.model.event.EventValueOption;
import com.visitscotland.brxm.model.event.EventsListingTab;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventsListingFactory {

    private final static String BASE_ENDPOINT_TRAINING = "/api/bsh-events-listing/training";
    private final static String START_DATE = "startDate";

    public EventsLingsModule createModule(EventsListing document) {
        EventsLingsModule module = new EventsLingsModule();

        module.setHippoBean(document);
        module.addTab(addTrainingTab(document));

        return module;
    }

    /**
     *
     * @param document
     * @return
     */
    private EventsListingTab addTrainingTab(EventsListing document) {
        EventsListingTab tab = new EventsListingTab();

        tab.setTitle(document.getTrainingTitle());
        tab.setCopy(document.getTrainingCopy());
        tab.setBaseEndPoint(BASE_ENDPOINT_TRAINING);
        tab.setSortBy(buildSortBy(List.of("date", "price", "priceDesc")));
        tab.setFilters(buildTrainingFilters());


        return tab;
    }

    private List<EventValueOption> buildSortBy(List<String> options) {
        return options.stream().map(option -> new EventValueOption(option, "label"))
                .collect(Collectors.toList());
    }

    private List<EventFilter> buildTrainingFilters() {
        List<EventFilter> filters = new ArrayList<>();

        addDateFilters(filters);


//        List<EventFilter> filters = new ArrayList<>();
//        EventFilter filter = new EventFilter(START_DATE, "label", "date", "date", "date", "date", "date", "date", "date");
//        filters.add(filter);

        return filters;
    }

    private void addDateFilters(List<EventFilter> filters) {

        for (String dateField : Arrays.asList("startDate", "endDate")){
            EventFilter filter = new EventFilter();

            filters.add(filter);
        }
    }

}
