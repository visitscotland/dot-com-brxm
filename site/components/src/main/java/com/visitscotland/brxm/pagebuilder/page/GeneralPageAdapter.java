package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.mapper.page.CategoryCardsMapper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class GeneralPageAdapter extends CommonPageAdapter<General> {

    private final CategoryCardsMapper categoryCardsMapper;

    public GeneralPageAdapter(CategoryCardsMapper categoryCardsMapper) {
        this.categoryCardsMapper = categoryCardsMapper;
    }

    @Override
    public PageIntro getPageIntro(Locale locale, General page) {
        PageIntro template = super.getPageIntro(locale, page);
        if (page.getCategoryLinks() != null){
            template.setCategorySection(categoryCardsMapper.getCategoryCards(locale, page.getCategoryLinks()));
        }

        return template;
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof General;
    }
}
