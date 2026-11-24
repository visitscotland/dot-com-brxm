package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.LocationLoader;
import com.visitscotland.brxm.dms.model.LocationObject;
import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.page.PageTemplateAssembler;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class DestinationContentComponent extends PageContentComponent<Destination> {

    private static final Logger logger = LoggerFactory.getLogger(DestinationContentComponent.class);

    private final PageAssembler builder;
    private final LocationLoader locationLoader;
    private final PageTemplateAssembler pageTemplateAssembler;

    public DestinationContentComponent(){
        logger.debug("DestinationContentComponent initialized");
        this.builder = VsComponentManager.get(PageAssembler.class);
        this.locationLoader = VsComponentManager.get(LocationLoader.class);
        this.pageTemplateAssembler = VsComponentManager.get(PageTemplateAssembler.class);
    }

    @Override
    public PageCompositionHelper createPageCompositionHelper(HstRequest request) {
        return new PageCompositionHelper(getBundle(), pageTemplateAssembler, request);
    }

    @Override
    public void addPageAttributes(PageCompositionHelper pageConfig) {
        addAttributesToRequest(pageConfig.getRequest(), pageConfig);
    }

    void addAttributesToRequest(HstRequest request, PageCompositionHelper pageConfig) {
        Destination document = (Destination) request.getAttribute("document");
        LocationObject location = locationLoader.getLocation(document.getLocation(), Locale.UK);
        request.setModel("location", location);
        request.setModel("region", locationLoader.getRegion(location, Locale.UK));

        builder.addModules(request , pageConfig);
    }

}
