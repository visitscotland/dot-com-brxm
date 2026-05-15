package com.visitscotland.brxm.pagebuilder.model;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.HorizontalListLinksModule;
import com.visitscotland.brxm.model.megalinks.LinksModule;

public class PageIntro extends Module<Page> {

    private LinksModule<EnhancedLink> categorySection;
    private SignpostModule newsletter;
    private FlatBlog author;
    private FlatImage heroImage;
    private EnhancedLink video;
    private HorizontalListLinksModule otyml;

    public PageIntro(Page page) {
        setHippoBean(page);
    }

    public LinksModule<EnhancedLink> getCategorySection() {
        return categorySection;
    }

    public void setCategorySection(LinksModule<EnhancedLink> categorySection) {
        this.categorySection = categorySection;
    }

    public FlatBlog getAuthor() {
        return author;
    }

    public void setAuthor(FlatBlog author) {
        this.author = author;
    }

    public SignpostModule getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(SignpostModule newsletter) {
        this.newsletter = newsletter;
    }

    public FlatImage getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(FlatImage heroImage) {
        this.heroImage = heroImage;
    }

    public EnhancedLink getVideo() {
        return video;
    }

    public void setVideo(EnhancedLink video) {
        this.video = video;
    }

    public HorizontalListLinksModule getOtyml() {
        return otyml;
    }

    public void setOtyml(HorizontalListLinksModule otyml) {
        this.otyml = otyml;
    }
}
