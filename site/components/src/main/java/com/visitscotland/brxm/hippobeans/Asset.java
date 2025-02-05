package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@HippoEssentialsGenerated(internalName = "visitscotland:Asset")
@Node(jcrType = "visitscotland:Asset")
public class Asset extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:asset")
    public HippoBean getAsset() {
        return getLinkedBean("visitscotland:asset", HippoBean.class);
    }
}
