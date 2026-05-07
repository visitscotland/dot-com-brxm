package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.mapper.page.CategoryCardsMapper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GeneralPageAdapter implements PageAdapter {

    private static final Logger log = LoggerFactory.getLogger(GeneralPageAdapter.class);

    private final CategoryCardsMapper categoryCardsMapper;
    private final PageTemplateInitializer pageTemplateInitializer;

    public GeneralPageAdapter(CategoryCardsMapper categoryCardsMapper, PageTemplateInitializer pageTemplateInitializer) {
        this.categoryCardsMapper = categoryCardsMapper;
        this.pageTemplateInitializer = pageTemplateInitializer;
    }

    @Override
    public Optional<PageIntro> getPageIntro(PageCompositionHelper pageConfig)  {

        try {
            PageIntro template = pageTemplateInitializer.getPageIntro(pageConfig);
            General page = pageConfig.getPage();

            if (page.getCategoryLinks() != null) {
                template.setCategorySection(
                        categoryCardsMapper.getCategoryCards(pageConfig.getLocale(), page.getCategoryLinks()));
            }

            return Optional.of(template);
        } catch (PageCompositionException e) {
            log.error("Error while composing page intro for General page: {}" , e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof General;
    }
}
