package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import org.hippoecm.hst.content.beans.standard.HippoBean;

public interface ModuleMapper<H extends HippoBean, M extends Module<H>> {

    void include(H document, PageCompositionHelper page);

}
