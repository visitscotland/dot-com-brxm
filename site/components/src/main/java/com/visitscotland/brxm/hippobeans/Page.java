package com.visitscotland.brxm.hippobeans;

import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.hippobeans.capabilities.TranslationParent;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import java.util.Calendar;
import com.visitscotland.brxm.hippobeans.Blog;

@HippoEssentialsGenerated(internalName = "visitscotland:Page")
@Node(jcrType = "visitscotland:Page")
public class Page extends BaseDocument implements TranslationParent, Linkable {
    public static final String PRIMARY_TYPE = "visitscotland:Page";

    @HippoEssentialsGenerated(internalName = "visitscotland:seoTitle")
    public String getSeoTitle() {
        return getSingleProperty("visitscotland:seoTitle");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:breadcrumb")
    public String getBreadcrumb() {
        return getSingleProperty("visitscotland:breadcrumb");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:heroImage")
    public Image getHeroImage() {
        return getLinkedBean("visitscotland:heroImage", Image.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:introduction")
    public HippoHtml getIntroduction() {
        return getHippoHtml("visitscotland:introduction");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:title")
    public String getTitle() {
        return getSingleProperty("visitscotland:title");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:seoNoIndex")
    public Boolean getSeoNoIndex() {
        return getSingleProperty("visitscotland:seoNoIndex");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:seoDescription")
    public String getSeoDescription() {
        return getSingleProperty("visitscotland:seoDescription");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:teaser")
    public String getTeaser() {
        return getSingleProperty("visitscotland:teaser");
    }

    public Image getImage() {
        return getHeroImage();
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:diff")
    public String getDiff() {
        return getSingleProperty("visitscotland:diff");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationFlag")
    public Boolean getTranslationFlag() {
        return getSingleProperty("visitscotland:translationFlag");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:otherThings")
    public OTYML getOtherThings() {
        return getBean("visitscotland:otherThings", OTYML.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translation")
    public String getTranslation() {
        return getSingleProperty("visitscotland:translation");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationPriority")
    public String getTranslationPriority() {
        return getSingleProperty("visitscotland:translationPriority");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:hideNewsletter")
    public Boolean getHideNewsletter() {
        return getSingleProperty("visitscotland:hideNewsletter");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:heroVideo")
    public VideoLink getHeroVideo() {
        return getBean("visitscotland:heroVideo", VideoLink.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationDeadline")
    public Calendar getTranslationDeadline() {
        return getSingleProperty("visitscotland:translationDeadline");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:blog")
    public Blog getBlog() {
        return getBean("visitscotland:blog", Blog.class);
    }
}
