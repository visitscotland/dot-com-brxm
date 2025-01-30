package com.visitscotland.brxm.services.search;

import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.hippobeans.TravelTradeEventBSH;
import com.visitscotland.brxm.hippobeans.capabilities.RegionalEvent;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class EventCardFactory {

    private final ResourceBundleService bundle;

    @Autowired
    public EventCardFactory (ResourceBundleService bundle) {
        this.bundle = bundle;
    }

    public EventCard createEventCard(EventBSH document) {
        EventCard card = new EventCard(document);

        card.setDates(formatDates(document));
        card.setTimes(formatTimes(document));
        card.setLocation(formatLocation(document, card));
        card.setPrice(formatPrice(document));

        return card;
    }

    private String formatDates(EventBSH document){
        if (document.getStartDate() != null){

        }
        return "";
    }

    private String formatTimes(EventBSH document) {
        String times = null;
        if (!Contract.isEmpty(document.getStartTime())){
            times = document.getStartTime();
        }
        if (!Contract.isEmpty(document.getEndTime())) {
            if (times == null){
                times = document.getEndTime();
            } else {
                times += " - " + document.getEndTime();
            }
        }

        if (document instanceof TravelTradeEventBSH && times != null){
            times += " " + ((TravelTradeEventBSH) document).getTimezone();
        }

        // TODO: Create labels
        return (times == null) ? "To be confirmed" :  times;
    }

    private String formatLocation(EventBSH document, EventCard card) {
        String location = null;

        if (!Contract.isEmpty(document.getVenue())){
            location = document.getVenue();
        } else if (document.getOnline()){
            // TODO: label
            location = "Online";

        } else if (document instanceof RegionalEvent) {
            location = ((RegionalEvent) document).getRegion();
        }

        if (location == null) {
            card.addErrorMessage("The location of this event is not valid");
            //TODO Content logger;
//            contentLogger.warn("The location for the event {} is not valid", document.getPath());
        }

        return location;
    }

    // TDD - Phase 1 - REFACTOR tomorrow proceed
    private String formatPrice(final EventBSH document) {
        final BigDecimal price = BigDecimal
                .valueOf(document.getPrice().getPrice());

        return String.format(
            "%s %s",
            price.setScale(2, RoundingMode.UNNECESSARY),
            document.getPrice().getCurrency()
        );
    }
}
