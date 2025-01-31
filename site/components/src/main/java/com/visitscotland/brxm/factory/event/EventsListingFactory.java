package com.visitscotland.brxm.factory.event;

import com.visitscotland.brxm.hippobeans.EventsListing;
import com.visitscotland.brxm.model.EventsLingsModule;
import com.visitscotland.brxm.model.event.EventsListingTab;
import org.springframework.stereotype.Component;

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

    private EventsListingTab addTrainingTab(EventsListing document) {
        EventsListingTab tab = new EventsListingTab();

        tab.setTitle(document.getTrainingTitle());
        tab.setCopy(document.getTrainingCopy());
        tab.setBaseEndPoint(BASE_ENDPOINT_TRAINING);

        return tab;
    }
}
