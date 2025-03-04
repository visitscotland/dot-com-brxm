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

public class EventHstQueryBuilder {

    private static final Logger logger = LoggerFactory.getLogger(EventHstQueryBuilder.class);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    //TODO: Move to models?
    public static final String ONLINE = "visitscotland:online";
    public static final String VENUE = "visitscotland:venue";
    public static final String START_DATE = "visitscotland:startDate";
    public static final String END_DATE = "visitscotland:endDate";
    public static final String AMOUNT = "visitscotland:price/visitscotland:amount";
    public static final String SECTORS = "visitscotland:sectors";
    public static final String REGION = "visitscotland:region";
    public static final String TYPES = "visitscotland:types";
    public static final String TOPICS = "visitscotland:topics";
    public static final String DEADLINE = "visitscotland:deadline";
    public static final String INTERNATIONAL = "visitscotland:international";

    private final HstQueryBuilder hstQuerybuilder;

    //TODO: Create property for this or should we get the channel mount point for this?
    private static final String EVENTS_LOCATION = "/content/documents/bsh/";

    //This map will prevent parameter for being included twice
    private final Map<String, Constraint> constraints;

    @SuppressWarnings("unchecked")
    public EventHstQueryBuilder(Class<? extends HippoBean> documentType) throws RepositoryException {
        this.hstQuerybuilder = HstQueryBuilder.create(getBaseNode()).ofTypes(documentType);
        constraints = new HashMap<>();
    }

    /**
     * Add pagination limits and pagination offset if needed
     */
    public EventHstQueryBuilder addPagination(int pageSize) {
        hstQuerybuilder.limit(pageSize);
        if (getQueryParameters().containsKey(PAGE_PARAM)) {
            int pageIndex = Integer.parseInt(getQueryParameters().get(PAGE_PARAM)[0]);
            hstQuerybuilder.offset((pageIndex - 1) * pageSize);
        }

        return this;
    }

    /**
     * Add sorting if needed
     */
    public EventHstQueryBuilder sort() {
        if (getQueryParameters().containsKey(SORT_BY_PARAM)) {
            String sortBy = getQueryParameters().get(SORT_BY_PARAM)[0];

            switch (sortBy) {
                case DATE:
                    hstQuerybuilder.orderByAscending(START_DATE);
                    break;
                case PRICE:
                    hstQuerybuilder.orderByAscending(AMOUNT);
                    break;
                case PRICE_DESC:
                    hstQuerybuilder.orderByDescending(AMOUNT);
                    break;
                case REGISTRATION:
                    hstQuerybuilder.orderByAscending(DEADLINE);
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
    public EventHstQueryBuilder addPriceFilters() {
        if (getQueryParameters().containsKey(FREE_PARAM)) {
            constraints.put(FREE_PARAM, constraint(AMOUNT).equalTo(0));
        }

        return this;
    }

    /**
     * Add filters related to the location: online, in-person
     */
    public EventHstQueryBuilder addLocationFilters() {
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
    public EventHstQueryBuilder addInternationalFilters() {
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
    public EventHstQueryBuilder addSectorsFilters() {
        addConstraintFromList(EventSearchParameters.SECTOR_PARAM, SECTORS);
        return this;
    }

    /**
     * Add filters related to the topic if needed
     */
    public EventHstQueryBuilder addTopicsFilters() {
        addConstraintFromList(EventSearchParameters.TOPIC_PARAM, TOPICS);
        return this;
    }

    /**
     * Add filters related to the region if needed
     */
    public EventHstQueryBuilder addRegionsFilters() {
        addConstraintFromList(EventSearchParameters.REGION_PARAM, REGION);
        return this;
    }

    /**
     * Add filters related to the event type if needed
     */
    public EventHstQueryBuilder addEventTypesFilters() {
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
    public EventHstQueryBuilder addDatesFilters() {

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
    Map<String, String[]> getQueryParameters() {
        return RequestContextProvider.get().getServletRequest().getParameterMap();
    }

    public HstQuery build() {
        return hstQuerybuilder.where(and(constraints.values().toArray(Constraint[]::new))).build();
    }
}
