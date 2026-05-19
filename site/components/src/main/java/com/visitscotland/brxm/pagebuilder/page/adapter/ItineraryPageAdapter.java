package com.visitscotland.brxm.pagebuilder.page.adapter;

import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;
import com.visitscotland.brxm.pagebuilder.page.PageTemplateInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItineraryPageAdapter implements PageAdapter {

    private final Logger log = LoggerFactory.getLogger(ItineraryPageAdapter.class);

    private final PageTemplateInitializer pageTemplateInitializer;

    public ItineraryPageAdapter(PageTemplateInitializer pageTemplateInitializer) {
        this.pageTemplateInitializer = pageTemplateInitializer;
    }

    @Override
    public Optional<PageTemplate> getPageIntro(PageCompositionHelper pageConfig) {
        try {
            return Optional.of(pageTemplateInitializer.getPageTemplate(pageConfig));
        } catch (PageCompositionException e) {
            log.error("Error while composing page intro for General page: {}" , e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof Itinerary;
    }
}
