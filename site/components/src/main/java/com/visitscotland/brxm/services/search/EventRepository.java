package com.visitscotland.brxm.services.search;

import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.hippobeans.TrainingEventBSH;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.model.bsh.PaginatedResult;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventRepository extends BaseHstComponent {

    private final HstQueryService hstQueryService;

    public EventRepository(HstQueryService hstQueryService) {
        this.hstQueryService = hstQueryService;
    }

    public EventCardFactory eventCardFactory;

    public PaginatedResult<EventCard> findAllTrainingEvents() {
        HstQueryResult query = hstQueryService.query(TrainingEventBSH.class);

        PaginatedResult<EventCard> page = new PaginatedResult<>();
        page.setTotal(query.getTotalSize());
        page.setResults(new ArrayList<>());

        HippoBeanIterator i = query.getHippoBeans();

        while (i.hasNext()){
            EventBSH event = (EventBSH) i.nextHippoBean();
            page.getResults().add(eventCardFactory.createEventCard(event));
        }

        return page;
    }
}
