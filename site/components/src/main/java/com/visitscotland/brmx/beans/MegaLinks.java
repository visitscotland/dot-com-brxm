package com.visitscotland.brmx.beans;

import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import java.util.List;

/** 
 * TODO: Beanwriter: Failed to create getter for node type: hippo:compound
 */
@HippoEssentialsGenerated(internalName = "visitscotland:MegaLinks")
@Node(jcrType = "visitscotland:MegaLinks")
public class MegaLinks extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:list")
    public Boolean getList() {
        return getSingleProperty("visitscotland:list");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:hideTeaser")
    public Boolean getHideTeaser() {
        return getSingleProperty("visitscotland:hideTeaser");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:introduction")
    public HippoHtml getIntroduction() {
        return getHippoHtml("visitscotland:introduction");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:singleImageLinks", allowModifications = false)
    public List<HippoCompound> getSingleImageLinks() {
        return getChildBeansByName("visitscotland:singleImageLinks", HippoCompound.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:megaLinkItems", allowModifications = false)
    public List<HippoCompound> getMegaLinkItems() {
        return getChildBeansByName("visitscotland:megaLinkItems", HippoCompound.class);
    }
}
