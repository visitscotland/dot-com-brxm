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

    @HippoEssentialsGenerated(internalName = "visitscotland:region")
    public String getRegion() {
        return getSingleProperty("visitscotland:region");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:types")
    public String[] getTypes() {
        return getMultipleProperty("visitscotland:types");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:featured")
    public Boolean getFeatured() {
        return getSingleProperty("visitscotland:featured");
    }
}
