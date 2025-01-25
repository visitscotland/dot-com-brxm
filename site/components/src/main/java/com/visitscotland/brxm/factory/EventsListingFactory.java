package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.EventsListing;
import com.visitscotland.brxm.model.EventsLingsModule;
import org.springframework.stereotype.Component;

@Component
public class EventsListingFactory {

    public EventsLingsModule createModule(EventsListing document) {
        EventsLingsModule module = new EventsLingsModule();

        return module;
    }
}
