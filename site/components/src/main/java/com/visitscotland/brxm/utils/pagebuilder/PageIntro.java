package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;

public class PageIntro extends Module<Page> {

    LinksModule<EnhancedLink> categoryCards;

    public PageIntro(Page page, LinksModule<EnhancedLink> categoryCards) {
        setHippoBean(page);
        this.categoryCards = categoryCards;
    }

    public LinksModule<EnhancedLink> getCategoryCards() {
        return categoryCards;
    }

    public void setCategoryCards(LinksModule<EnhancedLink> categoryCards) {
        this.categoryCards = categoryCards;
    }
}
