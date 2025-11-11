package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import org.hippoecm.hst.core.component.HstRequest;
import org.springframework.stereotype.Component;

@Component
public class GeneralPageAdapter implements PageAdapter<General> {

    private final CategoryCardsMapper categoryCardsMapper;

    public GeneralPageAdapter(CategoryCardsMapper categoryCardsMapper) {
        this.categoryCardsMapper = categoryCardsMapper;
    }

    public PageIntro getPageIntro(HstRequest request, General page) {
        return new PageIntro(page, categoryCardsMapper.getCategoryCards(request, page.getCategoryLinks()));
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof General;
    }
}
