package com.visitscotland.brxm.hippobeans;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import java.util.Calendar;
import java.util.List;

@HippoEssentialsGenerated(internalName = "visitscotland:GeneralBSH")
@Node(jcrType = "visitscotland:GeneralBSH")
public class GeneralBSH extends Page {
    @HippoEssentialsGenerated(internalName = "visitscotland:theme")
    public String getTheme() {
        return getSingleProperty("visitscotland:theme");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:publishDate")
    public Calendar getPublishDate() {
        return getSingleProperty("visitscotland:publishDate");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:readingTime")
    public Long getReadingTime() {
        return getSingleProperty("visitscotland:readingTime");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:skill")
    public String getSkill() {
        return getSingleProperty("visitscotland:skill");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:sectors")
    public String[] getSectors() {
        return getMultipleProperty("visitscotland:sectors");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:regions")
    public String[] getRegions() {
        return getMultipleProperty("visitscotland:regions");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:links", allowModifications = false)
    public List<HippoBean> getLinks() {
        return getLinkedBeans("visitscotland:links", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:type")
    public String getType() {
        return getSingleProperty("visitscotland:type");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:topic")
    public String[] getTopic() {
        return getMultipleProperty("visitscotland:topic");
    }
}
