package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.ListicleFactory;
import com.visitscotland.brxm.hippobeans.Listicle;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.page.PageIntroAssembler;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListicleContentComponent extends PageContentComponent<Listicle> {

    private static final Logger logger = LoggerFactory.getLogger(ListicleContentComponent.class);

    public static final String LISTICLE_ITEMS = "items";
    private static final String BUNDLE_ID = "listicle";


    private ListicleFactory factory;
    private final PageIntroAssembler pageIntroAssembler;

    public ListicleContentComponent(){
        logger.debug("ListicleContentComponent initialized");
        this.factory = VsComponentManager.get(ListicleFactory.class);
        this.pageIntroAssembler = VsComponentManager.get(PageIntroAssembler.class);
    }



    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        PageCompositionHelper pageConfig = new PageCompositionHelper(getBundle(), pageIntroAssembler, request);

        super.doBeforeRender(request, response, pageConfig);

        pageConfig.addAllSiteLabels(BUNDLE_ID);
        request.setModel(LISTICLE_ITEMS, factory.generateItems(request.getLocale(), getDocument(request)));
    }

}
