package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.PageLinksSectionCompound;
import com.visitscotland.brxm.mapper.page.CategoryCardsMapper;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;
import com.visitscotland.brxm.pagebuilder.page.adapter.GeneralPageAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeneralPageAdapterTest {

    private static final Locale LOCALE = Locale.UK;

    private CategoryCardsMapper categoryCardsMapper;
    private PageTemplateInitializer pageTemplateInitializer;
    private GeneralPageAdapter adapter;
    private PageCompositionHelper pageConfig;

    @BeforeEach
    void setUp() {
        categoryCardsMapper = mock(CategoryCardsMapper.class);
        pageTemplateInitializer = mock(PageTemplateInitializer.class);
        adapter = new GeneralPageAdapter(categoryCardsMapper, pageTemplateInitializer);
        pageConfig = mock(PageCompositionHelper.class);
    }

    @Test
    @DisplayName("Includes category section when page has category links")
    void includesCategorySectionWhenCategoryLinksPresent() throws PageCompositionException {
        General page = mock(General.class);

        when(pageConfig.getPage()).thenReturn(page);
        when(pageConfig.getLocale()).thenReturn(LOCALE);
        when(page.getCategoryLinks()).thenReturn(mock(PageLinksSectionCompound.class));
        when(categoryCardsMapper.getCategoryCards(eq(LOCALE), any()))
                .thenReturn(new LinksModule<>());
        when(pageTemplateInitializer.getPageTemplate(pageConfig)).thenReturn(new PageTemplate(page));


        PageTemplate result = adapter.getPageIntro(pageConfig).orElseThrow();

        assertSame(page, result.getHippoBean());
        assertNotNull(result.getCategorySection());
    }

    @Test
    @DisplayName("Omits category section and does not call mapper when page has no category links")
    void omitsCategorySectionWhenCategoryLinksAreNull() throws PageCompositionException {
        General page = mock(General.class);

        when(pageConfig.getPage()).thenReturn(page);
        when(pageTemplateInitializer.getPageTemplate(pageConfig)).thenReturn(new PageTemplate(page));

        PageTemplate result = adapter.getPageIntro(pageConfig).orElseThrow();

        assertSame(page, result.getHippoBean());
        assertNull(result.getCategorySection());

        verifyNoInteractions(categoryCardsMapper);
    }
}
