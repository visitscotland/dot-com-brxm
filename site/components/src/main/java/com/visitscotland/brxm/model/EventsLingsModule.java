package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.EventsListing;
import com.visitscotland.brxm.model.event.EventsListingTab;

import java.util.ArrayList;
import java.util.List;

public class EventsLingsModule extends Module<EventsListing> {

    private final List<EventsListingTab> eventsListings = new ArrayList<>();
}
