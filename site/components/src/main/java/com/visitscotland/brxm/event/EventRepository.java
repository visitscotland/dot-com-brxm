package com.visitscotland.brxm.event;

import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.model.bsh.PaginatedResult;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventRepository extends BaseHstComponent {

    private final EventHstQueryService hstQueryService;
    private final EventCardFactory eventCardFactory;
    private final SiteProperties siteProperties;


    protected EventRepository(EventHstQueryService hstQueryService, EventCardFactory eventCardFactory,
                           SiteProperties siteProperties) {
        this.hstQueryService = hstQueryService;
        this.eventCardFactory = eventCardFactory;
        this.siteProperties = siteProperties;
    }

    PaginatedResult<EventCard> findTrainingEvents() {
        HstQueryResult query = hstQueryService.queryTrainingEvents();
        return convertToPaginatedResults(query);
    }

    PaginatedResult<EventCard> findIndustryEvents() {
        HstQueryResult query = hstQueryService.queryIndustryEvents();
        return convertToPaginatedResults(query);
    }

    PaginatedResult<EventCard> findTravelTradeEvents() {
        HstQueryResult query = hstQueryService.queryTravelTradeEvents();
        return convertToPaginatedResults(query);
    }

    private PaginatedResult<EventCard> convertToPaginatedResults(HstQueryResult query) {
        PaginatedResult<EventCard> page = new PaginatedResult<>();
        page.setTotal(query.getTotalSize());
        page.setPageSize(siteProperties.getEventsListingsPageSize());
        page.setResults(new ArrayList<>());

        HippoBeanIterator iterator = query.getHippoBeans();

        while (iterator.hasNext()){
            EventBSH event = (EventBSH) iterator.nextHippoBean();
            page.getResults().add(eventCardFactory.createEventCard(event));
        }

        return page;
    }
}
