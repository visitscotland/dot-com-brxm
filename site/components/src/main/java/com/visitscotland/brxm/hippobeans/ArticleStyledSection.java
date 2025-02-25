package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

/**
 * TODO: Beanwriter: Failed to create getter for node type: hippo:compound
 */
@HippoEssentialsGenerated(internalName = "visitscotland:ArticleStyledSection")
@Node(jcrType = "visitscotland:ArticleStyledSection")
public class ArticleStyledSection extends ArticleSection {
    public static final String HEADING = "visitscotland:heading";

    @HippoEssentialsGenerated(internalName = "visitscotland:heading")
    public String getHeading() {
        return getSingleProperty("visitscotland:heading");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:copy")
    public HippoHtml getCopy() {
        return getHippoHtml("visitscotland:copy");
    }
}
