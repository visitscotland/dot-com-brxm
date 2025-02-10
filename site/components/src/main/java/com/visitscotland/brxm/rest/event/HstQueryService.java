package com.visitscotland.brxm.rest.event;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.springframework.stereotype.Component;

import javax.jcr.*;

@Component
public class HstQueryService {

    //TODO: Create CMS configuration property
    private static final String EVENTS_LOCATION = "/content/documents/bsh/sandbox/events";

    public <T extends HippoBean> HstQueryResult  query(Class<T> documentType) {

        try {
            final Node node = RequestContextProvider.get().getSession().getNode(EVENTS_LOCATION);

            HstQuery hstQuery = new QueryBuilder(documentType)
                    .addPagination().sort()
                    .addDatesFilters()
                    .addPriceFilters().addLocationFilters()
                    .addSectorsFilters().addTopicsFilters()
                    .build();

            // execute the query
            return hstQuery.execute();

        } catch (QueryException | RepositoryException e) {
            throw new HstComponentException (
                    "Exception occurred during creation or execution of HstQuery.", e);
        }
    }
}
