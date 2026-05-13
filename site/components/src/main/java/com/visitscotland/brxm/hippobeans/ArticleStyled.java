package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

import java.util.List;

@HippoEssentialsGenerated(internalName = "visitscotland:ArticleStyled")
@Node(jcrType = "visitscotland:ArticleStyled")
public class ArticleStyled extends Article {
    @HippoEssentialsGenerated(internalName = "visitscotland:theme")
    public String getTheme() {
        return getSingleProperty("visitscotland:theme");
    }
}
