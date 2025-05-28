package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import java.util.Calendar;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@HippoEssentialsGenerated(internalName = "visitscotland:EventBSH")
@Node(jcrType = "visitscotland:EventBSH")
public class EventBSH extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:organiser")
    public String getOrganiser() {
        return getSingleProperty("visitscotland:organiser");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:online")
    public Boolean getOnline() {
        return getSingleProperty("visitscotland:online");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:venue")
    public String getVenue() {
        return getSingleProperty("visitscotland:venue");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:startDate")
    public Calendar getStartDate() {
        return getSingleProperty("visitscotland:startDate");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:endDate")
    public Calendar getEndDate() {
        return getSingleProperty("visitscotland:endDate");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:startTime")
    public String getStartTime() {
        return getSingleProperty("visitscotland:startTime");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:endTime")
    public String getEndTime() {
        return getSingleProperty("visitscotland:endTime");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:summary")
    public HippoHtml getSummary() {
        return getHippoHtml("visitscotland:summary");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:ctaLink")
    public ExternalLink getCtaLink() {
        return getBean("visitscotland:ctaLink", ExternalLink.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:price")
    public Double getPrice() {
        return getSingleProperty("visitscotland:price");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:currency")
    public String getCurrency() {
        return getSingleProperty("visitscotland:currency");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:vat")
    public Boolean getVat() {
        return getSingleProperty("visitscotland:vat");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:featured")
    public Boolean getFeatured() {
        return getSingleProperty("visitscotland:featured");
    }
}
