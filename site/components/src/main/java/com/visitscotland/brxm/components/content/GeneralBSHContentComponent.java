package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.components.navigation.info.GeneralPageComponentInfo;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.hippobeans.GeneralBSH;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.model.megalinks.HorizontalListLinksModule;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.page.PageTemplateAssembler;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ParametersInfo(type = GeneralPageComponentInfo.class)
public class GeneralBSHContentComponent extends PageContentComponent<GeneralBSH> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralBSHContentComponent.class);

    private static final String READ_DATA = "readData";

    private final PageAssembler builder;
    private final PageTemplateAssembler pageTemplateAssembler;

    public GeneralBSHContentComponent(){
        logger.debug("GeneralBSHContentComponent initialized");
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
        addReadData(request);
        builder.addModules(request, pageConfig);
    }

    @Override
    protected void addOTYML(HstRequest request, PageCompositionHelper pageConfig) {
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
}
