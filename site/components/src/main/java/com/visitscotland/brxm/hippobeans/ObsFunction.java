package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@HippoEssentialsGenerated(internalName = "visitscotland:ObsFunction")
@Node(jcrType = "visitscotland:ObsFunction")
public class ObsFunction extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:description")
    public String getDescription() {
        return getSingleProperty("visitscotland:description");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:category")
    public String[] getCategory() {
        return getMultipleProperty("visitscotland:category");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:featured")
    public Boolean getFeatured() {
        return getSingleProperty("visitscotland:featured");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:id")
    public String getId() {
        return getSingleProperty("visitscotland:id");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:content")
    public HippoHtml getContent() {
        return getHippoHtml("visitscotland:content");
    }
}
