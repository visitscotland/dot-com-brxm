package com.visitscotland.brxm.hippobeans;

import com.visitscotland.brxm.hippobeans.capabilities.RegionalEvent;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

@HippoEssentialsGenerated(internalName = "visitscotland:TrainingEventBSH")
@Node(jcrType = "visitscotland:TrainingEventBSH")
public class TrainingEventBSH extends EventBSH implements RegionalEvent {
    @HippoEssentialsGenerated(internalName = "visitscotland:sectors")
    public String[] getSectors() {
        return getMultipleProperty("visitscotland:sectors");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:topics")
    public String[] getTopics() {
        return getMultipleProperty("visitscotland:topics");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:price")
    public Price getPrice() {
        return getBean("visitscotland:price", Price.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:region")
    public String getRegion() {
        return getSingleProperty("visitscotland:region");
    }
}
