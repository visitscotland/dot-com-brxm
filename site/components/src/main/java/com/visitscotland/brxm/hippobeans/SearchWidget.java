package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@HippoEssentialsGenerated(internalName = "visitscotland:SearchWidget")
@Node(jcrType = "visitscotland:SearchWidget")
public class SearchWidget extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:type")
    public String getType() {
        return getSingleProperty("visitscotland:type");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:placeholder")
    public String getPlaceholder() {
        return getSingleProperty("visitscotland:placeholder");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:buttonText")
    public String getButtonText() {
        return getSingleProperty("visitscotland:buttonText");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:description")
    public HippoHtml getDescription() {
        return getHippoHtml("visitscotland:description");
    }
}
