package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

@HippoEssentialsGenerated(internalName = "visitscotland:AssetLink")
@Node(jcrType = "visitscotland:AssetLink")
public class AssetLink extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:Asset")
    public Asset getAsset() {
        return getBean("visitscotland:Asset", Asset.class);
    }
}
