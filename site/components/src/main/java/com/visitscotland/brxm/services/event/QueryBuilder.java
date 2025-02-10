package com.visitscotland.brxm.services.event;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.builder.Constraint;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.repository.util.DateTools;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

import static com.visitscotland.brxm.services.event.EventSearchParameters.*;
import static org.hippoecm.hst.content.beans.query.builder.ConstraintBuilder.*;

public class QueryBuilder {

    private static final String EVENTS_LOCATION = "/content/documents/bsh/sandbox/events";


    private final HstQueryBuilder builder;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //TODO: Create from configuration
    public static int PAGE_SIZE = 10;


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
                    builder.orderByAscending("visitscotland:startDate");
                    break;
                case PRICE:
                    builder.orderByAscending("visitscotland:amount");
                    break;
                case PRICE_DESC:
                    builder.orderByDescending("visitscotland:amount");
                    break;
                case REGISTRATION:
                    builder.orderByAscending("visitscotland:registrationDate");
                    break;
                default:
                    //TODO logger
            }
        }
    }

    //-------------------------- [ FILTERS ] ------------------------

    /**
     * Add price filter if the query parameter is provided
     */
    public void price() {
        if (getQueryParameters().containsKey(FREE)) {
            constraints.put(FREE, constraint("visitscotland:amount").equalTo(0));
        }
    }

    /**
     * Add filters related to the location: online, in-person, national, international
     */
    public void location() {
        if (getQueryParameters().containsKey(ONLINE)) {
            constraints.put(ONLINE, constraint("visitscotland:online").equalTo(true));
        }
        if (getQueryParameters().containsKey(IN_PERSON)) {
            constraints.put(IN_PERSON, constraint("visitscotland:venue").notLike(""));
        }
        if (getQueryParameters().containsKey(NATIONAL)) {
            constraints.put(NATIONAL, constraint("visitscotland:international").equalTo(false));
        }
        if (getQueryParameters().containsKey(INTERNATIONAL)) {
            constraints.put(INTERNATIONAL, constraint("visitscotland:international").equalTo(true));
        }
    }

    /**
     * Add filters related to the sector if needed
     */
    public void sector() {
        addConstraintFromList(SECTOR, "visitscotland:sector");
    }

    /**
     * Add filters related to the topic if needed
     */
    public void topic() {
        addConstraintFromList(TOPIC, "visitscotland:topic");
    }

    /**
     * Add filters related to the region if needed
     */
    public void region() {
        addConstraintFromList(REGION, "visitscotland:region");
    }

    /**
     * Add filters related to the event type if needed
     */
    public void eventType() {
        addConstraintFromList(EVENT_TYPE, "visitscotland:evenType");
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
        if (getQueryParameters().containsKey(START_DATE) || getQueryParameters().containsKey(END_DATE)) {
            Calendar startDate = getStartDate();
            Calendar endDate = getEndDate();
            constraints.put(START_DATE, or(
                    constraint("visitscotland:endDate").greaterOrEqualThan(endDate, DateTools.Resolution.DAY),
                    constraint("visitscotland:startDate")
                            .greaterOrEqualThan(startDate, DateTools.Resolution.DAY)));
            constraints.put(END_DATE,
                    constraint("visitscotland:startDate")
                            .greaterOrEqualThan(endDate, DateTools.Resolution.DAY));
        }
    }

    /**
     * Calculate the start date from the query parameter
     */
    private Calendar getStartDate(){
        return getDateFromParameter(START_DATE).orElse(Calendar.getInstance());
    }

    /**
     * Calculate the end date from the query parameter
     */
    private Calendar getEndDate(){
        return getDateFromParameter(END_DATE).orElseGet(() -> {
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
                //TODO logger
            }
        }
        return Optional.empty();
    }

    /**
     * get the base node for the query
     */
    private Node getBaseNode() throws RepositoryException {
        return RequestContextProvider.get().getSession().getNode(EVENTS_LOCATION);
    }

    /**
     * Get the query parameters from the request
     */
    private Map<String, String[]> getQueryParameters() {
        return RequestContextProvider.get().getServletRequest().getParameterMap();
    }
}
