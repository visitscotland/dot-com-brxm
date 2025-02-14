package com.visitscotland.brxm.rest.event;

import com.visitscotland.brxm.formatter.EventFlatLinkFormatter;
import com.visitscotland.brxm.hippobeans.TravelTradeEventBSH;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.event.PriceFormatter;
import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.utils.ContentLogger;

import com.visitscotland.utils.Contract;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Objects;
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
    private final EventFlatLinkFormatter eventFlatlinkFormatter;

    public EventCardFactory(ResourceBundleService bundle,
                            PriceFormatter priceFormatter,
                            ContentLogger contentLogger,
                            EventFlatLinkFormatter eventFlatlinkFormatter) {
        this.bundle = bundle;
        this.priceFormatter = priceFormatter;
        this.contentLogger = contentLogger;
        this.eventFlatlinkFormatter = eventFlatlinkFormatter;
    }

    public EventCard createEventCard(EventBSH document) {
        EventCard card = new EventCard(document);

        card.setDates(formatDates(document, card));
        card.setTimes(formatTimes(document));
        card.setLocation(formatLocation(document, card));
        card.setOrganizer(valueOrNull(card.getOrganizer()));
        card.setPrice(priceFormatter.format(document.getPrice()));
        card.setCta(eventFlatlinkFormatter.format(document));

        if (document instanceof TravelTradeEventBSH) {
            setTravelTradeFields((TravelTradeEventBSH) document, card);
        }

        return card;
    }

    private String valueOrNull(String value) {
        return Contract.isEmpty(value) ? null : value;
    }

    private void setTravelTradeFields(TravelTradeEventBSH document, EventCard card) {
        final Calendar deadlineCalendar = document.getDeadline();

        if(Objects.isNull(deadlineCalendar)) {
            card.setRegistrationDeadline(null);
        } else {
            card.setRegistrationDeadline(fullDateFormat.format(deadlineCalendar.getTime()));
        }

        card.setContact(document.getContentType());
    }

    private String formatDates(EventBSH document, EventCard card) {
        final Calendar startDate = document.getStartDate();
        final Calendar endDate = document.getEndDate();

        if (Objects.isNull(startDate)) {
            contentLogger.error("The start Date of {} is not valid", document.getPath());
            card.addErrorMessage("The start date of this event is not valid");
            return null;
        }

        if (Objects.isNull(endDate) || startDate.equals(endDate)) {
            return fullDateFormat.format(startDate.getTime());
        } else {
            String toDate = fullDateFormat.format(endDate.getTime());
            String fromDate;
            if (startDate.get(Calendar.YEAR) != endDate.get(Calendar.YEAR)) {
                fromDate = fullDateFormat.format(startDate.getTime());
            } else if (startDate.get(Calendar.MONTH) != endDate.get(Calendar.MONTH)) {
                fromDate = dayMonthFormat.format(startDate.getTime());
            } else {
                fromDate = String.format("%02d", startDate.get(Calendar.DAY_OF_MONTH));
            }
            return fromDate + " - " + toDate;
        }
    }

    private String formatTimes(EventBSH document) {
        String times = null;
        if (!Contract.isEmpty(document.getStartTime())) {
            times = document.getStartTime();
        }
        if (!Contract.isEmpty(document.getEndTime())) {
            if (times == null) {
                times = document.getEndTime();
            } else {
                times += " - " + document.getEndTime();
            }
        }

        if (document instanceof TravelTradeEventBSH && times != null) {
            times += " " + ((TravelTradeEventBSH) document).getTimezone();
        }

        return (times == null) ? bundle.getResourceBundle(BUNDLE, "time.empty", LOCALE) : times;
    }

    private String formatLocation(EventBSH document, EventCard card) {
        String location = null;

        if (!Contract.isEmpty(document.getVenue())) {
            location = document.getVenue();
        }

        if (document.getOnline()) {
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
