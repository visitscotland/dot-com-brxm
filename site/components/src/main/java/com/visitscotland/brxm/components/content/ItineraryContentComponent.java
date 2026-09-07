package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.mapper.page.ItineraryMapper;
import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.model.ItineraryPage;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.page.PageTemplateAssembler;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ItineraryContentComponent extends PageContentComponent<Itinerary> {

    private static final Logger logger = LoggerFactory.getLogger(ItineraryContentComponent.class);

    public static final String ITINERARY = "itinerary";
    public static final String ITINERARY_BUNDLE = "itinerary";
    public static final String PAGE_INTRO = "pageIntro";
    public static final String HAS_STOPS = "hasStops";

    private final ItineraryMapper itineraryMapper;
    private final PageAssembler builder;
    private final PageTemplateAssembler pageTemplateAssembler;

    public ItineraryContentComponent() {
        logger.debug("ItineraryContentComponent initialized");

        this.itineraryMapper = VsComponentManager.get(ItineraryMapper.class);
        this.builder = VsComponentManager.get(PageAssembler.class);
        this.pageTemplateAssembler = VsComponentManager.get(PageTemplateAssembler.class);
    }

    @Override
    public PageCompositionHelper createPageCompositionHelper(HstRequest request) {
        return new PageCompositionHelper(getBundle(), pageTemplateAssembler, request);
    }

    @Override
    public void addPageAttributes(PageCompositionHelper pageConfig) {
        HstRequest request = pageConfig.getRequest();
        includeLabels(pageConfig);
        if (itineraryMapper.isStopBasedItinerary(getDocument(request))){
            logger.warn("Stop based itineraries are no longer in use." + getDocument(request));
            // TODO - will be removed at a later date
            pageConfig.addProperty(HAS_STOPS, true);
        } else {
            pageConfig.addProperty(HAS_STOPS, false);
            ItineraryPage itinerary = itineraryMapper.buildItinerary(getDocument(request), request.getLocale());
            request.setModel(PAGE_INTRO, itinerary);
            builder.addModules(request, pageConfig);
        }
    }

    /**
     * Adds labels that are necessary for itineraries.
     */
    private void includeLabels(PageCompositionHelper pageConfig) {
        pageConfig.addAllSiteLabels( ITINERARY_BUNDLE);
    }

}
