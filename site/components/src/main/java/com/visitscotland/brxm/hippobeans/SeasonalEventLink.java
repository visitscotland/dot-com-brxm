package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

/** 
 * TODO: Beanwriter: Failed to create getter for node type: hippo:compound
 */
@HippoEssentialsGenerated(internalName = "visitscotland:SeasonalEventLink")
@Node(jcrType = "visitscotland:SeasonalEventLink")
public class SeasonalEventLink extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:location")
    public String getLocation() {
        return getSingleProperty("visitscotland:location");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:dates")
    public String getDates() {
        return getSingleProperty("visitscotland:dates");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:tags")
    public String[] getTags() {
        return getMultipleProperty("visitscotland:tags");
    }
}
