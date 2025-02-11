package com.visitscotland.brxm.rest.event;

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

    //TODO: Create from configuration
    static final int PAGE_SIZE = 10;

    private final HstQueryService hstQueryService;
    private final EventCardFactory eventCardFactory;

    public EventRepository(HstQueryService hstQueryService, EventCardFactory eventCardFactory) {
        this.hstQueryService = hstQueryService;
        this.eventCardFactory = eventCardFactory;
    }

    public PaginatedResult<EventCard> findTrainingEvents() {
        HstQueryResult query = hstQueryService.query(TrainingEventBSH.class);

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
