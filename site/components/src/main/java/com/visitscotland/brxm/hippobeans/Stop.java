package com.visitscotland.brxm.hippobeans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import java.util.List;
import java.util.Calendar;

@HippoEssentialsGenerated(internalName = "visitscotland:Stop")
@Node(jcrType = "visitscotland:Stop")
public class Stop extends BaseDocument {
    public static final String PRODUCTS = "visitscotland:products";

    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:description")
    public HippoHtml getDescription() {
        return getHippoHtml("visitscotland:description");
    }

    public List<Object> getProducts() {
        return getChildBeansByName("visitscotland:product");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:product", allowModifications = false)
    public List<HippoBean> getStop() {
        return getChildBeansByName("visitscotland:product", HippoBean.class);
    }

    public HippoBean getStopItem() {
        return getOnlyChild(getStop());
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:media", allowModifications = false)
    public Image getImage() {
        return getLinkedBean("visitscotland:media", Image.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:media", allowModifications = false)
    public List<HippoBean> getMedia() {
        return getMedia("visitscotland:media");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:subtitle")
    public String getSubtitle() {
        return getSingleProperty("visitscotland:subtitle");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:diff")
    public String getDiff() {
        return getSingleProperty("visitscotland:diff");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationFlag")
    public Boolean getTranslationFlag() {
        return getSingleProperty("visitscotland:translationFlag");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translation")
    public String getTranslation() {
        return getSingleProperty("visitscotland:translation");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:StopTip")
    public StopTip getStopTip() {
        return getBean("visitscotland:StopTip", StopTip.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationPriority")
    public String getTranslationPriority() {
        return getSingleProperty("visitscotland:translationPriority");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationDeadline")
    public Calendar getTranslationDeadline() {
        return getSingleProperty("visitscotland:translationDeadline");
    }

    @HippoEssentialsGenerated(internalName = "hippotaxonomy:keys")
    public String[] getKeys() {
        return getMultipleProperty("hippotaxonomy:keys");
    }
}
