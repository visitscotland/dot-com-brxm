package com.visitscotland.brmx.beans;

import com.visitscotland.brmx.beans.capabilities.Linkable;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

import java.util.List;

/** 
 * TODO: Beanwriter: Failed to create getter for node type: hippo:compound
 */
@HippoEssentialsGenerated(internalName = "visitscotland:SharedLink")
@Node(jcrType = "visitscotland:SharedLink")
public class SharedLink extends BaseDocument implements Linkable {
    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:teaser")
    public String getTeaser() {
        return getSingleProperty("visitscotland:teaser");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:image")
    public Image getImage() {
        return getLinkedBean("visitscotland:image", Image.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:linkTypes", allowModifications = false)
    public List<HippoBean> getLinkTypes() {
        return getChildBeansByName("visitscotland:linkTypes", HippoBean.class);
    }

    public HippoBean getLinkType() {
        return getOnlyChild(getLinkTypes());
    }
}
