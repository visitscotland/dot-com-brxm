package com.visitscotland.brxm.model.navigation;

import com.visitscotland.brxm.model.Module;
import org.hippoecm.hst.content.annotations.PageModelIgnore;
import org.hippoecm.hst.content.beans.standard.HippoBean;

public abstract class NavigationWidget<W extends HippoBean> extends Module<W> {
    /**
     * This value has been duplicated so its value can be removed from the payload.
     *
     * The navigation widgets are requested by the page navigation menu which is cached. Since the HippoBean are
     * lazy loaded, any attempt to access to the bean after the original connection was closed will result on an exception
     */
    @PageModelIgnore
    private W hippoBean;

    @Override
    public W getHippoBean() {
        return hippoBean;
    }

    @Override
    public void setHippoBean(W hippoBean) {
        this.hippoBean = hippoBean;
    }
}
