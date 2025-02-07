package com.visitscotland.brxm.services.event;

import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.hippobeans.TrainingEventBSH;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.model.bsh.PaginatedResult;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventRepository extends BaseHstComponent {

    private final HstQueryService hstQueryService;
    private final EventCardFactory eventCardFactory;

    public EventRepository(HstQueryService hstQueryService, EventCardFactory eventCardFactory) {
        this.hstQueryService = hstQueryService;
        this.eventCardFactory = eventCardFactory;
    }

    public PaginatedResult<EventCard> findAllTrainingEvents() {
        HstQueryResult query = hstQueryService.query(TrainingEventBSH.class);

        PaginatedResult<EventCard> page = new PaginatedResult<>();
        page.setTotal(query.getTotalSize());
        //TODO: The value of Page size should come from properties
        page.setPageSize(QueryBuilder.PAGE_SIZE);
        page.setResults(new ArrayList<>());

        HippoBeanIterator i = query.getHippoBeans();

        while (i.hasNext()){
            EventBSH event = (EventBSH) i.nextHippoBean();
            page.getResults().add(eventCardFactory.createEventCard(event));
        }

        return page;
    }
}
