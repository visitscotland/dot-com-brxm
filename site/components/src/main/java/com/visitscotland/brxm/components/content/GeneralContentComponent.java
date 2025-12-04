package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.components.navigation.info.GeneralPageComponentInfo;
import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.utils.pagebuilder.PageIntroAssembler;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ParametersInfo(type = GeneralPageComponentInfo.class)
public class GeneralContentComponent extends PageContentComponent<Destination> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralContentComponent.class);

    public static final String SIMPLE = "Simple";
    public static final String STANDARD = "Standard";
    public static final String TOP_LEVEL = "Top-Level";
    public static final String INSPIRATION = "Inspiration";
    static final String ERROR_CODE = "errorCode";

    private final PageAssembler builder;
    private PageIntroAssembler pageIntroAssembler;

    public GeneralContentComponent(){
        logger.debug("GeneralContentComponent initialized");
        this.builder = VsComponentManager.get(PageAssembler.class);
        this.pageIntroAssembler = VsComponentManager.get(PageIntroAssembler.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        GeneralPageComponentInfo pageInfo = getComponentParametersInfo(request);
        int pageStatus = Integer.parseInt(pageInfo.getStatus());
        response.setStatus(pageStatus);
        if (pageStatus >= 400) {
            request.setModel(ERROR_CODE, pageStatus);
        }
        addNavigationCards(request);
        builder.addModules(request);
    }

    private void addNavigationCards(HstRequest request){
        //TODO: This model can be renamed to pageIntro after FreeMarker is retired
        request.setModel("pageIntroData", pageIntroAssembler.from(request, request.getModel("document")));
    }
}
