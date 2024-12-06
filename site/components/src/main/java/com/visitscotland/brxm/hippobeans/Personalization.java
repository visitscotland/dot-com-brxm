package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@HippoEssentialsGenerated(internalName = "visitscotland:Personalization")
@Node(jcrType = "visitscotland:Personalization")
public class Personalization extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:module")
    public HippoBean getModule() {
        return getLinkedBean("visitscotland:module", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:country")
    public String getCountry() {
        return getSingleProperty("visitscotland:country");
    }
}
