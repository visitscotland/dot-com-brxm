package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

@HippoEssentialsGenerated(internalName = "visitscotland:ArticleStyledBSH")
@Node(jcrType = "visitscotland:ArticleStyledBSH")
public class ArticleStyledBSH extends Article {
    @HippoEssentialsGenerated(internalName = "visitscotland:nested")
    public Boolean getNested() {
        return getSingleProperty("visitscotland:nested");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:theme")
    public String getTheme() {
        return getSingleProperty("visitscotland:theme");
    }
}
