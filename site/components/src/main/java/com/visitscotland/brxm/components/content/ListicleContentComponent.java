package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.ListicleFactory;
import com.visitscotland.brxm.hippobeans.Listicle;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListicleContentComponent extends PageContentComponent<Listicle> {

    private static final Logger logger = LoggerFactory.getLogger(ListicleContentComponent.class);

    public static final String LISTICLE_ITEMS = "items";
    private static final String BUNDLE_ID = "listicle";
    private static final String ALLOW_FAVOURITE = "allowFavourite";

    private ListicleFactory factory;

    public ListicleContentComponent(){
        logger.debug("ListicleContentComponent initialized");

        factory = VsComponentManager.get(ListicleFactory.class);
    }



    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        PageCompositionHelper pageConfig = new PageCompositionHelper(getBundle(), request);

        super.doBeforeRender(request, response, pageConfig);
        pageConfig.addProperty(ALLOW_FAVOURITE, true);

        addLabels(request);

        request.setModel(LISTICLE_ITEMS, factory.generateItems(request.getLocale(), getDocument(request)));
    }

    private void addLabels(HstRequest request){
        addAllLabels(request, BUNDLE_ID);
    }

}
