package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.EventsListing;
import com.visitscotland.brxm.model.event.EventsListingTab;

import java.util.ArrayList;
import java.util.List;

public class EventsLingsModule extends Module<EventsListing> {

    private List<EventsListingTab> eventsListings;

    public List<EventsListingTab> getEventsListings() {
        return eventsListings;
    }

    public void setEventsListings(List<EventsListingTab> eventsListings) {
        this.eventsListings = eventsListings;
    }

    public void addTab(EventsListingTab eventsListingTab) {
        if (eventsListings == null) {
            eventsListings = new ArrayList<>();
        }
        eventsListings.add(eventsListingTab);
    }
}
