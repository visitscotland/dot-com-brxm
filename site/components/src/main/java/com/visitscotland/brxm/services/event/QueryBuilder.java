package com.visitscotland.brxm.services.event;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.builder.Constraint;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.visitscotland.brxm.services.event.EventSearchParameters.*;
import static org.hippoecm.hst.content.beans.query.builder.ConstraintBuilder.and;
import static org.hippoecm.hst.content.beans.query.builder.ConstraintBuilder.constraint;

public class QueryBuilder {

    private static final String EVENTS_LOCATION = "/content/documents/bsh/sandbox/events";

    private final HstQueryBuilder builder;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    //This map will prevent parameter for being included twice
    private final Map<String, Constraint> constraints;


    public QueryBuilder(Class<? extends HippoBean>... documentTypes) throws RepositoryException {
        this.builder = HstQueryBuilder.create(getBaseNode()).ofTypes(documentTypes);
        constraints = new HashMap<>();
    }

    private void buildFromRequest(){
        builder
    }

    public void isFree(){
        if (getQueryParameters().containsKey(FREE)) {
            constraints.put(FREE, constraint("visitscotland:amount").equalTo(0));
        }
    }

    public void dates(){
        Calendar start = Calendar.getInstance();
        if (getQueryParameters().containsKey(START_DATE)) {

            constraints.put(FREE, constraint("visitscotland:amount").equalTo(0));
        }

        if (getQueryParameters().containsKey(END_DATE)) {
            constraints.put(FREE, constraint("visitscotland:amount").equalTo(0));
        }
    }

    private Calendar getStartDate(){
        if (getQueryParameters().containsKey(START_DATE)) {
            try {
                String date = sdf.parse(getQueryParameters().containsKey(START_DATE));
            } catch (ParseException e){
                //TODO logger
            }
        }
        return Calendar.getInstance();
    }

    private Node getBaseNode() throws RepositoryException {
        return RequestContextProvider.get().getSession().getNode(EVENTS_LOCATION);
    }

    private Map<String, String[]> getQueryParameters() {
        return RequestContextProvider.get().getServletRequest().getParameterMap();
    }
}
