package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@HippoEssentialsGenerated(internalName = "visitscotland:SplitBlock")
@Node(jcrType = "visitscotland:SplitBlock")
public class SplitBlock extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:leftReference")
    public HippoBean getLeftReference() {
        return getLinkedBean("visitscotland:leftReference", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:rightReference")
    public HippoBean getRightReference() {
        return getLinkedBean("visitscotland:rightReference", HippoBean.class);
    }
}
