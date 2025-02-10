package com.visitscotland.brxm.services.event;

import com.visitscotland.brxm.factory.BannerFactory;
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

import static com.visitscotland.brxm.services.event.EventSearchParameters.*;
import static org.hippoecm.hst.content.beans.query.builder.ConstraintBuilder.*;

public class QueryBuilder {

    private static final Logger logger = LoggerFactory.getLogger(BannerFactory.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //TODO: Move to EventBSH?
    public static final String ONLINE = "visitscotland:online";
    public static final String VENUE = "visitscotland:venue";
    public static final String START_DATE = "visitscotland:startDate";
    public static final String END_DATE = "visitscotland:endDate";
    public static final String AMOUNT = "visitscotland:amount";
    //TODO: Move to IndustyEventBSH?
    public static final String SECTORS = "visitscotland:sectors";
    public static final String REGION = "visitscotland:region";
    public static final String TYPES = "visitscotland:types";
    //TODO: Move to TrainingEventBSH?
    public static final String TOPICS = "visitscotland:topics";
    //TODO: Move to TravelTradeEventBSH?
    public static final String DEADLINE = "visitscotland:deadline";
    public static final String INTERNATIONAL = "visitscotland:international";

    private final HstQueryBuilder builder;

    //TODO: Create from configuration
    public static int PAGE_SIZE = 10;
    //TODO: Create property for this or should we get the channel mount point for this?
    public static final String EVENTS_LOCATION = "/content/documents/bsh/sandbox/events";

    //This map will prevent parameter for being included twice
    private final Map<String, Constraint> constraints;

    public QueryBuilder(Class<? extends HippoBean>... documentTypes) throws RepositoryException {
        this.builder = HstQueryBuilder.create(getBaseNode()).ofTypes(documentTypes);
        constraints = new HashMap<>();
    }

    /**
     * Add pagination limits and pagination offset if needed
     */
    public void addPagination(){
        builder.limit(PAGE_SIZE);
        if (getQueryParameters().containsKey(PAGE)) {
            int pageIndex = Integer.parseInt(getQueryParameters().get(PAGE)[0]) ;
            builder.offset((pageIndex - 1 )* PAGE_SIZE);
        }
    }

    /**
     * Add sorting if needed
     */
    public void sort(){
        if (getQueryParameters().containsKey(SORT_BY)) {
            String sortBy = getQueryParameters().get(SORT_BY)[0];

            switch (sortBy){
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
                    logger.warn("The sort by parameter {} is not valid", sortBy);;
            }
        }
    }

    //-------------------------- [ FILTERS ] ------------------------

    /**
     * Add price filter if the query parameter is provided
     */
    public void price() {
        if (getQueryParameters().containsKey(FREE)) {
            constraints.put(FREE, constraint(AMOUNT).equalTo(0));
        }
    }

    /**
     * Add filters related to the location: online, in-person, national, international
     */
    public void location() {
        if (getQueryParameters().containsKey(EventSearchParameters.ONLINE)) {
            constraints.put(EventSearchParameters.ONLINE, constraint(ONLINE).equalTo(true));
        }
        if (getQueryParameters().containsKey(IN_PERSON)) {
            constraints.put(IN_PERSON, constraint(VENUE).notLike(""));
        }
        if (getQueryParameters().containsKey(NATIONAL)) {
            constraints.put(NATIONAL, constraint(INTERNATIONAL).equalTo(false));
        }
        if (getQueryParameters().containsKey(EventSearchParameters.INTERNATIONAL)) {
            constraints.put(EventSearchParameters.INTERNATIONAL, constraint(INTERNATIONAL).equalTo(true));
        }
    }

    /**
     * Add filters related to the sector if needed
     */
    public void sector() {
        addConstraintFromList(EventSearchParameters.SECTOR, SECTORS);
    }

    /**
     * Add filters related to the topic if needed
     */
    public void topic() {
        addConstraintFromList(EventSearchParameters.TOPIC, TOPICS);
    }

    /**
     * Add filters related to the region if needed
     */
    public void region() {
        addConstraintFromList(EventSearchParameters.REGION, REGION);
    }

    /**
     * Add filters related to the event type if needed
     */
    public void eventType() {
        addConstraintFromList(EventSearchParameters.EVENT_TYPE, TYPES);
    }

    /**
     * Add a constraint to the query builder based on the parameter and field
     */
    public void addConstraintFromList(String param, String field) {
        if (getQueryParameters().containsKey(param)) {
            List<Constraint> orConstraints = new ArrayList<>();
            for (String sector : getQueryParameters().get(param)) {
                orConstraints.add(constraint(field).equalTo(sector));
            }
            constraints.put(param, or(orConstraints.toArray(Constraint[]::new)));
        }
    }

    /**
     * Add date filters if the query parameters are provided
     */
    public void dates(){
        if (getQueryParameters().containsKey(EventSearchParameters.START_DATE) || getQueryParameters().containsKey(EventSearchParameters.END_DATE)) {
            Calendar startDate = getStartDate();
            Calendar endDate = getEndDate();
            constraints.put(EventSearchParameters.START_DATE, or(
                    constraint(END_DATE).greaterOrEqualThan(endDate, DateTools.Resolution.DAY),
                    constraint(START_DATE)
                            .greaterOrEqualThan(startDate, DateTools.Resolution.DAY)));
            constraints.put(EventSearchParameters.END_DATE,
                    constraint(START_DATE)
                            .greaterOrEqualThan(endDate, DateTools.Resolution.DAY));
        }
    }

    /**
     * Calculate the start date from the query parameter
     */
    private Calendar getStartDate(){
        return getDateFromParameter(EventSearchParameters.START_DATE).orElse(Calendar.getInstance());
    }

    /**
     * Calculate the end date from the query parameter
     */
    private Calendar getEndDate(){
        return getDateFromParameter(EventSearchParameters.END_DATE).orElseGet(() -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2999);
            return calendar;
        });
    }

    /**
     * Parse a date from a query parameter to a Calendar
     */
    private Optional<Calendar> getDateFromParameter(String parameter){
        if (getQueryParameters().containsKey(parameter)) {
            try {
                Date date = sdf.parse(getQueryParameters().get(parameter)[0]);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                return Optional.of(calendar);
            } catch (ParseException | IndexOutOfBoundsException e){
                logger.warn("Could not parse date from parameter: " + parameter);
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

    public HstQuery build(){
        return builder.where(and(constraints.values().toArray(Constraint[]::new))).build();
    }

    Map<String, Constraint> getConstraints() {
        return constraints;
    }
}
