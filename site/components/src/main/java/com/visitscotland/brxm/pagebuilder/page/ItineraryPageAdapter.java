package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ItineraryPageAdapter implements PageAdapter<Itinerary> {


    public ItineraryPageAdapter() {

    }

    public PageIntro getPageIntro(Locale locale, Itinerary page) {
        return new PageIntro(page);
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof Itinerary;
    }
}
