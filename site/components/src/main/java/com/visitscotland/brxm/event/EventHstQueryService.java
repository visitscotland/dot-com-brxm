package com.visitscotland.brxm.event;

import com.visitscotland.brxm.hippobeans.IndustryEventBSH;
import com.visitscotland.brxm.hippobeans.TrainingEventBSH;
import com.visitscotland.brxm.hippobeans.TravelTradeEventBSH;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.core.component.HstComponentException;
import org.springframework.stereotype.Component;

import javax.jcr.*;

@Component
class EventHstQueryService {

    private final SiteProperties siteProperties;

    protected EventHstQueryService(SiteProperties siteProperties) {
        this.siteProperties = siteProperties;
    }

    HstQueryResult queryTrainingEvents() {

        try {
            final HstQuery hstQuery = new EventHstQueryBuilder(TrainingEventBSH.class)
                    .addPagination(getPageSize()).sort()
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

    HstQueryResult queryIndustryEvents() {

        try {
            final HstQuery hstQuery = new EventHstQueryBuilder(IndustryEventBSH.class)
                    .addPagination(getPageSize()).sort()
                    .addDatesFilters()
                    .addPriceFilters().addLocationFilters()
                    .addSectorsFilters().addEventTypesFilters().addRegionsFilters()
                    .build();

            return hstQuery.execute();

        } catch (QueryException | RepositoryException e) {
            throw new HstComponentException(
                    "Exception occurred during creation or execution of HstQuery.", e);
        }
    }

    HstQueryResult queryTravelTradeEvents() {

        try {
            final HstQuery hstQuery = new EventHstQueryBuilder(TravelTradeEventBSH.class)
                    .addPagination(getPageSize()).sort()
                    .addDatesFilters()
                    .addPriceFilters().addLocationFilters()
                    .addInternationalFilters()
                    .build();

            return hstQuery.execute();

        } catch (QueryException | RepositoryException e) {
            throw new HstComponentException(
                    "Exception occurred during creation or execution of HstQuery.", e);
        }
    }

    private int getPageSize() {
        return siteProperties.getEventsListingsPageSize();
    }
}
