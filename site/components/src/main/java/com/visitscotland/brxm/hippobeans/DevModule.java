package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

/** 
 * TODO: Beanwriter: Failed to create getter for node type: selection:listitem
 */
@HippoEssentialsGenerated(internalName = "visitscotland:DevModule")
@Node(jcrType = "visitscotland:DevModule")
public class DevModule extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:html")
    public String getHtml() {
        return getSingleProperty("visitscotland:html");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:notes")
    public String getNotes() {
        return getSingleProperty("visitscotland:notes");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:headContributions")
    public String[] getHeadContributions() {
        return getMultipleProperty("visitscotland:headContributions");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:footerContributions")
    public String[] getFooterContributions() {
        return getMultipleProperty("visitscotland:footerContributions");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:freemarkerId")
    public String getFreemarkerId() {
        return getSingleProperty("visitscotland:freemarkerId");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:bespoken")
    public String getBespoken() {
        return getSingleProperty("visitscotland:bespoken");
    }
}
