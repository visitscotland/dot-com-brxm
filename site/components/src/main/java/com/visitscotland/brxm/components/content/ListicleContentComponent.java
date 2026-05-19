package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.ListicleFactory;
import com.visitscotland.brxm.hippobeans.Listicle;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.page.PageTemplateAssembler;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListicleContentComponent extends PageContentComponent<Listicle> {

    private static final Logger logger = LoggerFactory.getLogger(ListicleContentComponent.class);

    public static final String LISTICLE_ITEMS = "items";
    private static final String BUNDLE_ID = "listicle";


    private ListicleFactory factory;
    private final PageTemplateAssembler pageTemplateAssembler;

    public ListicleContentComponent(){
        logger.debug("ListicleContentComponent initialized");
        this.factory = VsComponentManager.get(ListicleFactory.class);
        this.pageTemplateAssembler = VsComponentManager.get(PageTemplateAssembler.class);
    }

    @Override
    public PageCompositionHelper createPageCompositionHelper(HstRequest request) {
        return new PageCompositionHelper(getBundle(), pageTemplateAssembler, request);
    }

    @Override
    public void addPageAttributes(PageCompositionHelper pageConfig) {
        HstRequest request = pageConfig.getRequest();

        pageConfig.addAllSiteLabels(BUNDLE_ID);
        request.setModel(LISTICLE_ITEMS, factory.generateItems(request.getLocale(), getDocument(request)));
    }

}
