package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

@HippoEssentialsGenerated(internalName = "visitscotland:IndustryEventBSH")
@Node(jcrType = "visitscotland:IndustryEventBSH")
public class IndustryEventBSH extends EventBSH {
    @HippoEssentialsGenerated(internalName = "visitscotland:sectors")
    public String[] getSectors() {
        return getMultipleProperty("visitscotland:sectors");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:regions")
    public String getRegions() {
        return getSingleProperty("visitscotland:regions");
    }
}
