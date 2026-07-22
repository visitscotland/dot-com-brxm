package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;
import com.visitscotland.brxm.pagebuilder.page.adapter.GeneralPageAdapter;
import com.visitscotland.brxm.pagebuilder.page.adapter.PageAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class pageTemplateAssemblerTest {

    private static final Locale LOCALE = Locale.UK;

    private PageCompositionHelper mockPageConfig(Page page) throws PageCompositionException {
        PageCompositionHelper pageConfig = mock(PageCompositionHelper.class);
        when(pageConfig.getPage()).thenReturn(page);

        return pageConfig;
    }

    @Test
    @DisplayName("The correct adapter is used to create a PageTemplate for a supported page type")
    void returnsIntroFromSupportingAdapter() throws Exception {
        TestPage page = new TestPage();

        PageAdapter unusedAdapter = spy(new GeneralPageAdapter(null, null));
        PageAdapter supportingAdapter = spy(new TestAdapter());

        PageTemplateAssembler assembler = new PageTemplateAssembler(List.of(supportingAdapter, unusedAdapter));
        PageCompositionHelper pageConfig = mockPageConfig(page);

        PageTemplate result = assembler.from(pageConfig);

        assertNotNull(result);
        assertEquals(page, result.getHippoBean());

        verify(supportingAdapter, times(1)).getPageIntro(pageConfig);
        verify(unusedAdapter, never()).getPageIntro(any());
    }


    @Test
    @DisplayName("Throws PageCompositionException when no adapter supports the given page type")
    void throwsExceptionWhenNoAdapterSupportsPage() {
        PageTemplateAssembler assembler = new PageTemplateAssembler(List.of(new TestAdapter()));

        PageCompositionException exception = assertThrows(
                PageCompositionException.class,
                () -> assembler.from(mockPageConfig(mock(General.class)))
        );

        assertTrue(exception.getMessage().contains("No adapter found for type"));
    }

    @Test
    @DisplayName("uses the first matching adapter even if later adapters also support the page")
    void firstSupportingAdapterWins() throws Exception {
        TestPage page = new TestPage();

        PageAdapter firstAdapter = spy(new TestAdapter());
        PageAdapter secondAdapter = spy(new TestAdapter());

        PageTemplateAssembler assembler =
                new PageTemplateAssembler(List.of(firstAdapter, secondAdapter));

        PageCompositionHelper pageConfig = mockPageConfig(page);

        PageTemplate result = assembler.from(pageConfig);

        assertEquals(page, result.getHippoBean());

        verify(firstAdapter, times(1)).getPageIntro(pageConfig);
        verify(secondAdapter, never()).getPageIntro(any());
    }

    private static class TestPage extends Page {}

    private static class TestAdapter implements PageAdapter {

        @Override
        public Optional<PageTemplate> getPageIntro(PageCompositionHelper pageConfig) {
            try {
                return Optional.of(new PageTemplate(pageConfig.getPage()));
            } catch (PageCompositionException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean supports(Page page) {
            return page instanceof TestPage;
        }
    }
}
