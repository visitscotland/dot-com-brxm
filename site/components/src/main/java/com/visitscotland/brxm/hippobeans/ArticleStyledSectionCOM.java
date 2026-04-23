package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

@HippoEssentialsGenerated(internalName = "visitscotland:ArticleStyledSectionCOM")
@Node(jcrType = "visitscotland:ArticleStyledSectionCOM")
public class ArticleStyledSectionCOM extends ArticleSection {

    @HippoEssentialsGenerated(internalName = "visitscotland:heading")
    public String getHeading() {
        return getSingleProperty("visitscotland:heading");
    }
}
