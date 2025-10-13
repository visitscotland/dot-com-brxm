package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@HippoEssentialsGenerated(internalName = "visitscotland:EventsListing")
@Node(jcrType = "visitscotland:EventsListing")
public class EventsListing extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:trainingTitle")
    public String getTrainingTitle() {
        return getSingleProperty("visitscotland:trainingTitle");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:industryTitle")
    public String getIndustryTitle() {
        return getSingleProperty("visitscotland:industryTitle");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:tradeTitle")
    public String getTradeTitle() {
        return getSingleProperty("visitscotland:tradeTitle");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:trainingCopy")
    public HippoHtml getTrainingCopy() {
        return getHippoHtml("visitscotland:trainingCopy");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:industryCopy")
    public HippoHtml getIndustryCopy() {
        return getHippoHtml("visitscotland:industryCopy");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:tradeCopy")
    public HippoHtml getTradeCopy() {
        return getHippoHtml("visitscotland:tradeCopy");
    }
}
