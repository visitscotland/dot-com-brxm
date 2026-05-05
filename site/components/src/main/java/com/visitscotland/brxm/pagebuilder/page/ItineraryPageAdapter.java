package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.hippobeans.Page;
import org.springframework.stereotype.Component;

@Component
public class ItineraryPageAdapter extends CommonPageAdapter<Itinerary> {


    @Override
    public boolean supports(Page page) {
        return page instanceof Itinerary;
    }
}
