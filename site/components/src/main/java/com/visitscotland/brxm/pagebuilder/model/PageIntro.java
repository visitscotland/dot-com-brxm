package com.visitscotland.brxm.pagebuilder.model;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;

import java.util.ArrayList;
import java.util.List;

public class PageIntro extends Module<Page> {

    LinksModule<EnhancedLink> categorySection;

    public PageIntro(Page page) {
        setHippoBean(page);
    }

    public PageIntro(Page page, LinksModule<EnhancedLink> categorySection) {
        setHippoBean(page);
        this.categorySection = categorySection;
    }

    public LinksModule<EnhancedLink> getCategorySection() {
        return categorySection;
    }

    public void setCategorySection(LinksModule<EnhancedLink> categorySection) {
        this.categorySection = categorySection;
    }

}
