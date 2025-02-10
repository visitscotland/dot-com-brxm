package com.visitscotland.brxm.rest.event;

import com.visitscotland.brxm.event.PriceFormatter;
import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.hippobeans.TravelTradeEventBSH;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Component
public class EventCardFactory {

    private static final String BUNDLE = "events-listings";
    private static final SimpleDateFormat dayMonthFormat = new SimpleDateFormat("dd MMM");
    private static final SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd MMM, yyyy");
    private static final Locale LOCALE = Locale.UK;

    private final ResourceBundleService bundle;
    private final PriceFormatter priceFormatter;
    private final ContentLogger contentLogger;

    public EventCardFactory (ResourceBundleService bundle,
                             PriceFormatter priceFormatter,
                             ContentLogger contentLogger) {
        this.bundle = bundle;
        this.priceFormatter = priceFormatter;
        this.contentLogger = contentLogger;
    }

    public EventCard createEventCard(EventBSH document) {
        EventCard card = new EventCard(document);

        card.setDates(formatDates(document, card));
        card.setTimes(formatTimes(document));
        card.setLocation(formatLocation(document, card));
        card.setOrganizer(valueOrNull(card.getOrganizer()));
        card.setPrice(priceFormatter.format(document.getPrice()));
        card.setCta(formatCTA(document));

        if (document instanceof TravelTradeEventBSH) {
            setTravelTradeFields((TravelTradeEventBSH) document, card);
        }

        return card;
    }

    private String valueOrNull(String value) {
        return Contract.isEmpty(value) ? null : value;
    }

    private FlatLink formatCTA(EventBSH document){
        FlatLink link = new FlatLink();

        link.setLabel(bundle.getCtaLabel(document.getCtaLink().getLabel(), LOCALE));
        link.setLink(document.getCtaLink().getLink());
        link.setType(LinkType.EXTERNAL);

        return link;
    }

    private void setTravelTradeFields(TravelTradeEventBSH document, EventCard card) {
        card.setContact(document.getContentType());
        card.setRegistrationDeadline(fullDateFormat.format(document.getDeadline()));
    }

    private String formatDates(EventBSH document, EventCard card){
        if (document.getStartDate() == null){
            contentLogger.error("The start Date of {} is not valid", document.getPath());
            card.addErrorMessage("The start date of this event is not valid");
            return null;
        }

        if (document.getEndDate() == null || document.getStartDate().equals(document.getEndDate())) {
            return fullDateFormat.format(document.getStartDate().getTime());
        } else {
            String toDate = fullDateFormat.format(document.getEndDate().getTime());
            String fromDate;
            if (document.getStartDate().get(Calendar.YEAR) != document.getEndDate().get(Calendar.YEAR)) {
                fromDate = fullDateFormat.format(document.getStartDate().getTime());
            } else if (document.getStartDate().get(Calendar.MONTH) != document.getEndDate().get(Calendar.MONTH)) {
                fromDate = dayMonthFormat.format(document.getStartDate().getTime());
            } else {
                fromDate = String.format("%02d", document.getStartDate().get(Calendar.DAY_OF_MONTH));
            }
            return fromDate + " - " + toDate;
        }
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

        return (times == null) ? bundle.getResourceBundle(BUNDLE, "time.empty", LOCALE) :  times;
    }

    private String formatLocation(EventBSH document, EventCard card) {
        String location = null;

        if (!Contract.isEmpty(document.getVenue())){
            location = document.getVenue();
        }

        if (document.getOnline()){
            String online = bundle.getResourceBundle(BUNDLE, "location.online", LOCALE);

            if (location == null) {
                location = online;
            } else {
                location += " " + bundle.getResourceBundle(BUNDLE, "location.separator", LOCALE) + " " + online;
            }
        }

        if (location == null) {
            card.addErrorMessage("The location of this event is not valid");
            contentLogger.warn("The location for the event {} is not valid", document.getPath());
        }

        return location;
    }
}
