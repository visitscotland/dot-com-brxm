package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.mapper.page.CategoryCardsMapper;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class GeneralPageAdapter implements PageAdapter<General> {

    private final CategoryCardsMapper categoryCardsMapper;

    public GeneralPageAdapter(CategoryCardsMapper categoryCardsMapper) {
        this.categoryCardsMapper = categoryCardsMapper;
    }

    public PageIntro getPageIntro(Locale locale, General page) {
        LinksModule<EnhancedLink> categorySection = null;
        if (page.getCategoryLinks() != null){
            categorySection = categoryCardsMapper.getCategoryCards(locale, page.getCategoryLinks());
        }

        return new PageIntro(page, categorySection);
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof General;
    }
}
