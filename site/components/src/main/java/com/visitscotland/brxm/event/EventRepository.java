package com.visitscotland.brxm.event;

import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.model.bsh.PaginatedResult;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventRepository extends BaseHstComponent {

    //TODO: Create from configuration
    static final int PAGE_SIZE = 10;

    private final EventHstQueryService hstQueryService;
    private final EventCardFactory eventCardFactory;

    public EventRepository(EventHstQueryService hstQueryService, EventCardFactory eventCardFactory) {
        this.hstQueryService = hstQueryService;
        this.eventCardFactory = eventCardFactory;
    }

    public PaginatedResult<EventCard> findTrainingEvents() {
        HstQueryResult query = hstQueryService.queryTrainingEvents();
        return convertToPaginatedResults(query);
    }

    public PaginatedResult<EventCard> findIndustryEvents() {
        HstQueryResult query = hstQueryService.queryIndustryEvents();
        return convertToPaginatedResults(query);
    }

    public PaginatedResult<EventCard> findTravelTradeEvents() {
        HstQueryResult query = hstQueryService.queryTravelTradeEvents();
        return convertToPaginatedResults(query);
    }

    private PaginatedResult<EventCard> convertToPaginatedResults(HstQueryResult query) {
        PaginatedResult<EventCard> page = new PaginatedResult<>();
        page.setTotal(query.getTotalSize());
        page.setPageSize(PAGE_SIZE);
        page.setResults(new ArrayList<>());

        HippoBeanIterator iterator = query.getHippoBeans();

        while (iterator.hasNext()){
            EventBSH event = (EventBSH) iterator.nextHippoBean();
            page.getResults().add(eventCardFactory.createEventCard(event));
        }

        return page;
    }
}
