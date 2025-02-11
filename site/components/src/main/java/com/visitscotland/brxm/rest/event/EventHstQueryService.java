package com.visitscotland.brxm.rest.event;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.springframework.stereotype.Component;

import javax.jcr.*;

@Component
public class EventHstQueryService {

    public <T extends HippoBean> HstQueryResult trainingEventsQuery(Class<T> documentType) {

        try {
            HstQuery hstQuery = new EventHstQueryBuilder(documentType)
                    .addPagination().sort()
                    .addDatesFilters()
                    .addPriceFilters().addLocationFilters()
                    .addSectorsFilters().addTopicsFilters()
                    .build();

            return hstQuery.execute();

        } catch (QueryException | RepositoryException e) {
            throw new HstComponentException(
                    "Exception occurred during creation or execution of HstQuery.", e);
        }
    }
}
