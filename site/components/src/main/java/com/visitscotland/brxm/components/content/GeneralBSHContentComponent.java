package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.components.navigation.info.GeneralPageComponentInfo;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.hippobeans.GeneralBSH;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.HorizontalListLinksModule;
import com.visitscotland.brxm.obs.ComparisonMapper;
import com.visitscotland.brxm.utils.PageTemplateBuilder;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ParametersInfo(type = GeneralPageComponentInfo.class)
public class GeneralBSHContentComponent extends PageContentComponent<GeneralBSH> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralBSHContentComponent.class);

    private static final String ERROR_CODE = "errorCode";
    private static final String READ_DATA = "readData";
    private static final String COMPARISON_MODULE = "comparisonModule";


    private final PageTemplateBuilder builder;

    public GeneralBSHContentComponent(){
        logger.debug("GeneralBSHContentComponent initialized");
        this.builder = VsComponentManager.get(PageTemplateBuilder.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        addReadData(request);
        addPageStatusCode(request, response);

        builder.addModules(request);

        if (request.getRequestURI().endsWith("online-booking-system")){
            List<Module<?>> list = request.getModel("pageItems");
            list.add(new ComparisonMapper().map());
            request.setModel(COMPARISON_MODULE, true);
        }
    }

    @Override
    protected void addOTYML(HstRequest request) {
        GeneralBSH page = getDocument(request);
        if (!Contract.isEmpty(page.getLinks())) {
            HorizontalListLinksModule otyml = megalinkFactory.horizontalListLayout(page, request.getLocale());
            request.setModel(OTYML_BUNDLE, otyml);
        }
    }

    protected void addReadData(HstRequest request) {
        FlatBlog blog = blogFactory.getPageReadData(getDocument(request), request.getLocale());
        request.setModel(READ_DATA, blog);
    }

    //TODO move to PageContentComponent
    private void addPageStatusCode(HstRequest request, HstResponse response){
        GeneralPageComponentInfo pageInfo = getComponentParametersInfo(request);
        int pageStatus = Integer.parseInt(pageInfo.getStatus());
        response.setStatus(pageStatus);
        if (pageStatus >= 400) {
            request.setModel(ERROR_CODE, pageStatus);
        }
    }
}
