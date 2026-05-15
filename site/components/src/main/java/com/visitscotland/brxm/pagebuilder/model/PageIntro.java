package com.visitscotland.brxm.pagebuilder.model;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import org.jetbrains.annotations.NotNull;

public class PageIntro extends Module<Page> {

    LinksModule<EnhancedLink> categorySection;
    SignpostModule newsletter;
    FlatBlog author;

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
}
