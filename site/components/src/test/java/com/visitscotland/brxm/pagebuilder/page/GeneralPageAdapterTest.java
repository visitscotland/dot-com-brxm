package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.hippobeans.PageLinksSectionCompound;
import com.visitscotland.brxm.mapper.page.CategoryCardsMapper;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
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

    private final CategoryCardsMapper categoryCardsMapper = mock(CategoryCardsMapper.class);
    private final GeneralPageAdapter adapter = new GeneralPageAdapter(categoryCardsMapper);

    @Test
    @DisplayName("Includes category section when page has category links")
    void includesCategorySectionWhenCategoryLinksPresent() {
        General page = mock(General.class);

        when(page.getCategoryLinks()).thenReturn(mock(PageLinksSectionCompound.class));
        when(categoryCardsMapper.getCategoryCards(eq(LOCALE), any()))
                .thenReturn(new LinksModule<>());

        PageIntro result = adapter.getPageIntro(LOCALE, page);

        assertSame(page, result.getHippoBean());
        assertNotNull(result.getCategorySection());
    }

    @Test
    @DisplayName("Omits category section and does not call mapper when page has no category links")
    void omitsCategorySectionWhenCategoryLinksAreNull() {
        General page = mock(General.class);

        PageIntro result = adapter.getPageIntro(LOCALE, page);

        assertSame(page, result.getHippoBean());
        assertNull(result.getCategorySection());

        verifyNoInteractions(categoryCardsMapper);
    }
}
