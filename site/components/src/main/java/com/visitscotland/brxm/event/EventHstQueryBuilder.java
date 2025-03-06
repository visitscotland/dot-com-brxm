package com.visitscotland.brxm.event;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.builder.Constraint;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.repository.util.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

import static com.visitscotland.brxm.event.EventSearchParameters.*;
import static org.hippoecm.hst.content.beans.query.builder.ConstraintBuilder.*;

class EventHstQueryBuilder {

    private static final Logger logger = LoggerFactory.getLogger(EventHstQueryBuilder.class);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    //TODO: Move to models?
    private static final String ONLINE = "visitscotland:online";
    private static final String VENUE = "visitscotland:venue";
    private static final String START_DATE = "visitscotland:startDate";
    private static final String END_DATE = "visitscotland:endDate";
    private static final String AMOUNT = "visitscotland:price/visitscotland:amount";
    private static final String SECTORS = "visitscotland:sectors";
    private static final String REGION = "visitscotland:region";
    private static final String TYPES = "visitscotland:types";
    private static final String TOPICS = "visitscotland:topics";
    private static final String DEADLINE = "visitscotland:deadline";
    private static final String INTERNATIONAL = "visitscotland:international";

    private final HstQueryBuilder builder;

    //TODO: Create property for this or should we get the channel mount point for this?
    private static final String EVENTS_LOCATION = "/content/documents/bsh/";

    //This map will prevent parameter for being included twice
    private final Map<String, Constraint> constraints;

    @SuppressWarnings("unchecked")
    public EventHstQueryBuilder(Class<? extends HippoBean> documentType) throws RepositoryException {
        this.builder = HstQueryBuilder.create(getBaseNode()).ofTypes(documentType);
        constraints = new HashMap<>();
    }

    /**
     * Add pagination limits and pagination offset if needed
     */
    EventHstQueryBuilder addPagination(int pageSize) {
        builder.limit(pageSize);
        if (getQueryParameters().containsKey(PAGE_PARAM)) {
            int pageIndex = Integer.parseInt(getQueryParameters().get(PAGE_PARAM)[0]);
            builder.offset((pageIndex - 1) * pageSize);
        }

        return this;
    }

    /**
     * Add sorting if needed
     */
    EventHstQueryBuilder sort() {
        if (getQueryParameters().containsKey(SORT_BY_PARAM)) {
            String sortBy = getQueryParameters().get(SORT_BY_PARAM)[0];

            switch (sortBy) {
                case DATE:
                    builder.orderByAscending(START_DATE);
                    break;
                case PRICE:
                    builder.orderByAscending(AMOUNT);
                    break;
                case PRICE_DESC:
                    builder.orderByDescending(AMOUNT);
                    break;
                case REGISTRATION:
                    builder.orderByAscending(DEADLINE);
                    break;
                default:
                    logger.warn("The sort by parameter {} is not valid", sortBy);
            }
        }

        return this;
    }

    //-------------------------- [ FILTERS ] ------------------------

    /**
     * Add price filter if the query parameter is provided
     */
    EventHstQueryBuilder addPriceFilters() {
        if (getQueryParameters().containsKey(FREE_PARAM)) {
            constraints.put(FREE_PARAM, constraint(AMOUNT).equalTo(0));
        }

        return this;
    }

    /**
     * Add filters related to the location: online, in-person
     */
    EventHstQueryBuilder addLocationFilters() {
        if (getQueryParameters().containsKey(EventSearchParameters.ONLINE_PARAM)) {
            constraints.put(EventSearchParameters.ONLINE_PARAM, constraint(ONLINE).equalTo(true));
        }
        if (getQueryParameters().containsKey(IN_PERSON_PARAM)) {
            constraints.put(IN_PERSON_PARAM, constraint(VENUE).notLike(""));
        }
        return this;
    }

    /**
     * Add filters related to the location: national, international
     */
    EventHstQueryBuilder addInternationalFilters() {
        if (getQueryParameters().containsKey(NATIONAL_PARAM)) {
            constraints.put(NATIONAL_PARAM, constraint(INTERNATIONAL).equalTo(false));
        }
        if (getQueryParameters().containsKey(EventSearchParameters.INTERNATIONAL_PARAM)) {
            constraints.put(EventSearchParameters.INTERNATIONAL_PARAM, constraint(INTERNATIONAL).equalTo(true));
        }
        return this;
    }


    /**
     * Add filters related to the sector if needed
     */
    EventHstQueryBuilder addSectorsFilters() {
        addConstraintFromList(EventSearchParameters.SECTOR_PARAM, SECTORS);
        return this;
    }

    /**
     * Add filters related to the topic if needed
     */
    EventHstQueryBuilder addTopicsFilters() {
        addConstraintFromList(EventSearchParameters.TOPIC_PARAM, TOPICS);
        return this;
    }

    /**
     * Add filters related to the region if needed
     */
    EventHstQueryBuilder addRegionsFilters() {
        addConstraintFromList(EventSearchParameters.REGION_PARAM, REGION);
        return this;
    }

    /**
     * Add filters related to the event type if needed
     */
    EventHstQueryBuilder addEventTypesFilters() {
        addConstraintFromList(EventSearchParameters.EVENT_TYPE_PARAM, TYPES);
        return this;
    }

    /**
     * Add a constraint to the query builder based on the parameter and field
     */
    private void addConstraintFromList(String param, String field) {
        if (getQueryParameters().containsKey(param)) {
            List<Constraint> orConstraints = new ArrayList<>();
            for (String sector : getQueryParameters().get(param)) {
                orConstraints.add(constraint(field).equalTo(sector));
            }
            constraints.put(param, or(orConstraints.toArray(Constraint[]::new)));
        }
    }

    /**
     * Add date filters if the query parameters are provided, otherwise it only allow events in the future
     */
    EventHstQueryBuilder addDatesFilters() {

        Calendar startDate = getStartDate();
        constraints.put(EventSearchParameters.START_DATE_PARAM, or(
                constraint(END_DATE).greaterOrEqualThan(startDate, DateTools.Resolution.DAY),
                constraint(START_DATE).greaterOrEqualThan(startDate, DateTools.Resolution.DAY)));

        if (getQueryParameters().containsKey(EventSearchParameters.END_DATE_PARAM)) {
            Calendar endDate = getEndDate();
            constraints.put(EventSearchParameters.END_DATE_PARAM,
                    constraint(START_DATE).lessOrEqualThan(endDate, DateTools.Resolution.DAY));
        }

        return this;
    }

    /**
     * Calculate the start date from the query parameter
     */
    private Calendar getStartDate() {
        return getDateFromParameter(EventSearchParameters.START_DATE_PARAM).orElse(Calendar.getInstance());
    }

    /**
     * Calculate the end date from the query parameter
     */
    private Calendar getEndDate() {
        return getDateFromParameter(EventSearchParameters.END_DATE_PARAM).orElseGet(() -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2999);
            return calendar;
        });
    }

    /**
     * Parse a date from a query parameter to a Calendar
     */
    private Optional<Calendar> getDateFromParameter(String parameter) {
        if (getQueryParameters().containsKey(parameter)) {
            try {
                Date date = SIMPLE_DATE_FORMAT.parse(getQueryParameters().get(parameter)[0]);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                return Optional.of(calendar);
            } catch (ParseException | IndexOutOfBoundsException e) {
                logger.warn("Could not parse date from parameter: {}", parameter);
            }
        }
        return Optional.empty();
    }

    /**
     * get the base node for the query
     */
    Node getBaseNode() throws RepositoryException {
        return RequestContextProvider.get().getSession().getNode(EVENTS_LOCATION);
    }

    /**
     * Get the query parameters from the request
     */
    private Map<String, String[]> getQueryParameters() {
        return RequestContextProvider.get().getServletRequest().getParameterMap();
    }

    HstQuery build() {
        return builder.where(and(constraints.values().toArray(Constraint[]::new))).build();
    }
}
