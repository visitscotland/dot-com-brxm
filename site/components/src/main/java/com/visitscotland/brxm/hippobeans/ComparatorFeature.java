package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

@HippoEssentialsGenerated(internalName = "visitscotland:ComparatorFeature")
@Node(jcrType = "visitscotland:ComparatorFeature")
public class ComparatorFeature extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:id")
    public String getId() {
        return getSingleProperty("visitscotland:id");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:description")
    public String getDescription() {
        return getSingleProperty("visitscotland:description");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:category")
    public String getCategory() {
        return getSingleProperty("visitscotland:category");
    }
}
