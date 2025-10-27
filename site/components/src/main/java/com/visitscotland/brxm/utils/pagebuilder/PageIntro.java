package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;

public class PageIntro {

    Page page;
    LinksModule<EnhancedLink> categoryCards;

    public PageIntro(General page, LinksModule<EnhancedLink> categoryCards) {
        this.page = page;
        this.categoryCards = categoryCards;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public LinksModule<EnhancedLink> getCategoryCards() {
        return categoryCards;
    }

    public void setCategoryCards(LinksModule<EnhancedLink> categoryCards) {
        this.categoryCards = categoryCards;
    }
}
