package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@HippoEssentialsGenerated(internalName = "visitscotland:DestinationsKeyCategories")
@Node(jcrType = "visitscotland:DestinationsKeyCategories")
public class DestinationsKeyCategories extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:category")
    public String getCategory() {
        return getSingleProperty("visitscotland:category");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:copy")
    public HippoHtml getCopy() {
        return getHippoHtml("visitscotland:copy");
    }
}
