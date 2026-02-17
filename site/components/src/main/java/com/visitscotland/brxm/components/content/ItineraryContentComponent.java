package com.visitscotland.brxm.components.content;


import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.mapper.page.ItineraryMapper;
import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.model.ItineraryPage;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.utils.Contract;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ItineraryContentComponent extends PageContentComponent<Itinerary> {

    private static final Logger logger = LoggerFactory.getLogger(ItineraryContentComponent.class);

    public static final String ITINERARY = "itinerary";
    public static final String PAGE_INTRO = "pageIntro";
    public static final String HAS_STOPS = "hasStops";

    private final ItineraryMapper itineraryMapper;
    private final PageAssembler builder;

    public ItineraryContentComponent() {
        logger.debug("ItineraryContentComponent initialized");

        this.itineraryMapper = VsComponentManager.get(ItineraryMapper.class);
        this.builder = VsComponentManager.get(PageAssembler.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        PageCompositionHelper pageConfig = new PageCompositionHelper(getBundle(), request);
        super.doBeforeRender(request, response, pageConfig);

        includeLabels(request);
        if (itineraryMapper.isStopBasedItinerary(getDocument(request))){
            pageConfig.addProperty(HAS_STOPS, true);
            buildStopBasedItinerary(request);
        } else {
            pageConfig.addProperty(HAS_STOPS, false);
            ItineraryPage itinerary = itineraryMapper.buildItinerary(getDocument(request), request.getLocale());
            request.setModel(PAGE_INTRO, itinerary);
            builder.addModules(request, pageConfig);
        }
    }

    private void buildStopBasedItinerary(HstRequest request) {
        ItineraryPage itineraryPage = itineraryMapper.buildStopBasedItinerary(getDocument(request), request.getLocale());

        request.setModel(PAGE_INTRO, itineraryPage);
        /*
         * Stop-based version of Itineraries is deprecated and the plan is to remove it from the codebase in the short
         * term. If they are still needed, the front-end should be updated to use the pageIntro object instead of the
         * itinerary object. Add after that the following line, including the itinerary object, can be removed.
         */
        //TODO: Read previous comment and remove the following line if needed
        request.setModel(ITINERARY, itineraryPage);

        if (!Contract.isEmpty(itineraryPage.getErrorMessages())) {
            setErrorMessages(request, itineraryPage.getErrorMessages());
        }
    }


    /**
     * Adds labels that are necessary for itineraries.
     *
     * @param request HstRequest Current Request
     */
    private void includeLabels(HstRequest request) {
        addAllLabels(request, "itinerary");
    }

}
