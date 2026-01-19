package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import org.hippoecm.hst.core.component.HstRequest;
import org.springframework.stereotype.Component;

@Component
public class GeneralPageAdapter implements PageAdapter<General> {

    private final CategoryCardsMapper categoryCardsMapper;

    public GeneralPageAdapter(CategoryCardsMapper categoryCardsMapper) {
        this.categoryCardsMapper = categoryCardsMapper;
    }

    public PageIntro getPageIntro(HstRequest request, General page) {
        LinksModule<EnhancedLink> categorySection = null;
        if (page.getCategoryLinks() != null){
            categorySection = categoryCardsMapper.getCategoryCards(request, page.getCategoryLinks());
        }

        return new PageIntro(page, categorySection);
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof General;
    }
}
