package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.LocationLoader;
import com.visitscotland.brxm.dms.model.LocationObject;
import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.page.PageIntroAssembler;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class DestinationContentComponent extends PageContentComponent<Destination> {

    private static final Logger logger = LoggerFactory.getLogger(DestinationContentComponent.class);

    private final PageAssembler builder;
    private final LocationLoader locationLoader;
    private final PageIntroAssembler pageIntroAssembler;

    public DestinationContentComponent(){
        logger.debug("DestinationContentComponent initialized");
        this.builder = VsComponentManager.get(PageAssembler.class);
        this.locationLoader = VsComponentManager.get(LocationLoader.class);
        this.pageIntroAssembler = VsComponentManager.get(PageIntroAssembler.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        PageCompositionHelper pageConfig = new PageCompositionHelper(getBundle(), pageIntroAssembler, request);

        super.doBeforeRender(request, response, pageConfig);

        addAttributesToRequest(request, pageConfig);
    }

    void addAttributesToRequest(HstRequest request, PageCompositionHelper pageConfig) {
        Destination document = (Destination) request.getAttribute("document");
        LocationObject location = locationLoader.getLocation(document.getLocation(), Locale.UK);
        request.setModel("location", location);
        request.setModel("region", locationLoader.getRegion(location, Locale.UK));

        builder.addModules(request , pageConfig);
    }

}
