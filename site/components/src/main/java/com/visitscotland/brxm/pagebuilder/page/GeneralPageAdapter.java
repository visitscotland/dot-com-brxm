package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.mapper.page.CategoryCardsMapper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

@Component
public class GeneralPageAdapter implements PageAdapter<General> {

    private final CategoryCardsMapper categoryCardsMapper;
    private final PageTemplateInitializer pageTemplateInitializer;

    public GeneralPageAdapter(CategoryCardsMapper categoryCardsMapper, PageTemplateInitializer pageTemplateInitializer) {
        this.categoryCardsMapper = categoryCardsMapper;
        this.pageTemplateInitializer = pageTemplateInitializer;
    }

    @Override
    public PageIntro getPageIntro(PageCompositionHelper pageConfig)  {

        try {
            General page = pageConfig.getPage();
            PageIntro template = pageTemplateInitializer.getPageIntro(pageConfig);

            if (page.getCategoryLinks() != null) {
                template.setCategorySection(
                        categoryCardsMapper.getCategoryCards(pageConfig.getLocale(), page.getCategoryLinks()));
            }

            return template;
        } catch (PageCompositionException e) {
            //TODO:
//            logger.error("Error while composing page intro for General page with id: " + pageConfig.getPage().getId(), e);
            return null;
        }
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof General;
    }
}
