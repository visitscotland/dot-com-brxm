package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@HippoEssentialsGenerated(internalName = "visitscotland:FeaturedEventBSH")
@Node(jcrType = "visitscotland:FeaturedEventBSH")
public class FeaturedEventBSH extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:featuredEvent")
    public HippoBean getFeaturedEvent() {
        return getLinkedBean("visitscotland:featuredEvent", HippoBean.class);
    }
}
