package com.visitscotland.brxm.components.content;


import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.factory.ItineraryFactory;
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

    private ItineraryFactory itineraryFactory;
    private final PageAssembler builder;

    public ItineraryContentComponent() {
        logger.debug("ItineraryContentComponent initialized");

        itineraryFactory = VsComponentManager.get(ItineraryFactory.class);
        this.builder = VsComponentManager.get(PageAssembler.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        PageCompositionHelper pageConfig = new PageCompositionHelper(getBundle(), request);
        super.doBeforeRender(request, response, pageConfig);

        includeLabels(request);

        if (itineraryFactory.isStopBasedItinerary(getDocument(request))){
            buildStopBasedItinerary(request);
        } else {
            ItineraryPage itinerary = itineraryFactory.buildItinerary(getDocument(request), request.getLocale());
            request.setModel("pageIntro", itinerary);
            builder.addModules(request, pageConfig);
        }
    }

    private void buildStopBasedItinerary(HstRequest request) {
        ItineraryPage itineraryPage = itineraryFactory.buildStopBasedItinerary(getDocument(request), request.getLocale());
        request.setModel(ITINERARY, itineraryPage);

        if (!Contract.isEmpty(itineraryPage.getErrorMessages())) {
            setErrorMessages(request, itineraryPage.getErrorMessages());
        }

        addProductSearchBuilder(request);
    }

    // This is only in use in Freemarker to inject product search
    @Deprecated (forRemoval = true)  // TODO: Remove method after VS-343 is completed
    // TODO: Remove method after VS-343 is completed
    public void addProductSearchBuilder(HstRequest request) {
        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        try {
            TemplateHashModel psb = (TemplateHashModel) staticModels.get(ProductSearchBuilder.class.getCanonicalName());
            request.setModel("ProductSearchBuilder", psb);
        } catch (TemplateModelException e) {
            logger.error("Product Search Builder is not available for the Page", e);
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
