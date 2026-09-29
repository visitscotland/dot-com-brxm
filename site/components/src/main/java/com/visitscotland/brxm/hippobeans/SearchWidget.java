package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import java.util.Calendar;

@HippoEssentialsGenerated(internalName = "visitscotland:SearchWidget")
@Node(jcrType = "visitscotland:SearchWidget")
public class SearchWidget extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:type")
    public String getType() {
        return getSingleProperty("visitscotland:type");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:placeholder")
    public String getPlaceholder() {
        return getSingleProperty("visitscotland:placeholder");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:ctaLabel")
    public String getCtaLabel() {
        return getSingleProperty("visitscotland:ctaLabel");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:diff")
    public String getDiff() {
        return getSingleProperty("visitscotland:diff");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationDeadline")
    public Calendar getTranslationDeadline() {
        return getSingleProperty("visitscotland:translationDeadline");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationFlag")
    public Boolean getTranslationFlag() {
        return getSingleProperty("visitscotland:translationFlag");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationPriority")
    public String getTranslationPriority() {
        return getSingleProperty("visitscotland:translationPriority");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:copy")
    public HippoHtml getCopy() {
        return getHippoHtml("visitscotland:copy");
    }
}
