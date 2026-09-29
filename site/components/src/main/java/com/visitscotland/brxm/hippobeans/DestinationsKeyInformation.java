package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

import java.util.List;


@HippoEssentialsGenerated(internalName = "visitscotland:DestinationsKeyInformation")
@Node(jcrType = "visitscotland:DestinationsKeyInformation")
public class DestinationsKeyInformation extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:DestinationsKeyCategories")
    public List<DestinationsKeyCategories> getHighlights() {
        return getChildBeansByName("visitscotland:highlights",
                DestinationsKeyCategories.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:ctaLink")
    public List<HippoCompound> getCtaLink() {
        return getChildBeansByName("visitscotland:ctaLink",
                HippoCompound.class);
    }

    public HippoCompound getCtaItem() {
        return getOnlyChild(getCtaLink());
    }

    protected <T> T getOnlyChild(List<T> children) {
        if (children.size() == 0) {
            return null;
        } else if (children.size() == 1) {
            return children.get(0);
        } else {
            return children.get(0);
        }
    }
}
