package com.visitscotland.brxm.pagebuilder.model;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;

public class PageIntro extends Module<Page> {

    LinksModule<EnhancedLink> categorySection;
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
}
