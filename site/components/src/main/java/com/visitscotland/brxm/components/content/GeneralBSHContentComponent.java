package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.components.navigation.info.GeneralPageComponentInfo;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.hippobeans.GeneralBSH;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.model.megalinks.HorizontalListLinksModule;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ParametersInfo(type = GeneralPageComponentInfo.class)
public class GeneralBSHContentComponent extends PageContentComponent<GeneralBSH> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralBSHContentComponent.class);

    private static final String ERROR_CODE = "errorCode";
    private static final String READ_DATA = "readData";


    private final PageAssembler builder;

    public GeneralBSHContentComponent(){
        logger.debug("GeneralBSHContentComponent initialized");
        this.builder = VsComponentManager.get(PageAssembler.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        PageCompositionHelper pageConfig = new PageCompositionHelper(getBundle(), request);

        super.doBeforeRender(request, response, pageConfig);

        addReadData(request);
        addPageStatusCode(request, response);

        builder.addModules(request, new PageCompositionHelper(getBundle(), request));
    }

    @Override
    protected void addOTYML(HstRequest request) {
        GeneralBSH page = getDocument(request);
        if (!Contract.isEmpty(page.getLinks())) {
            HorizontalListLinksModule otyml = megalinkMapper.horizontalListLayout(page, request.getLocale());
            request.setModel(OTYML_BUNDLE, otyml);
        }
    }

    protected void addReadData(HstRequest request) {
        FlatBlog blog = blogFactory.getPageReadData(getDocument(request), request.getLocale());
        request.setModel(READ_DATA, blog);
    }

    //TODO mode to PageContentComponent
    private void addPageStatusCode(HstRequest request, HstResponse response){
        GeneralPageComponentInfo pageInfo = getComponentParametersInfo(request);
        int pageStatus = Integer.parseInt(pageInfo.getStatus());
        response.setStatus(pageStatus);
        if (pageStatus >= 400) {
            request.setModel(ERROR_CODE, pageStatus);
        }
    }
}
