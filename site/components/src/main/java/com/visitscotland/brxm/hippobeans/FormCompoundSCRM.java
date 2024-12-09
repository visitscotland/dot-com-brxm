package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@HippoEssentialsGenerated(internalName = "visitscotland:FormCompoundSCRM")
@Node(jcrType = "visitscotland:FormCompoundSCRM")
public class FormCompoundSCRM extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:jsonURL")
    public String getJsonURL() {
        return getSingleProperty("visitscotland:jsonURL");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:url")
    public String getUrl() {
        return getSingleProperty("visitscotland:url");
    }
}
