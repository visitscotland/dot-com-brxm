package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PageIntroAssemblerTest {

    private static final Locale LOCALE = Locale.UK;

    @Test
    @DisplayName("The correct adapter is used to create a PageIntro for a supported page type")
    void returnsIntroFromSupportingAdapter() throws Exception {
        TestPage page = new TestPage();

        PageAdapter<General> unusedAdapter = spy(new GeneralPageAdapter(null));
        PageAdapter<TestPage> supportingAdapter = spy(new TestAdapter());

        PageIntroAssembler assembler = new PageIntroAssembler(List.of(supportingAdapter, unusedAdapter));

        PageIntro result = assembler.from(LOCALE, page);

        assertNotNull(result);
        assertEquals(page, result.getHippoBean());

        verify(supportingAdapter, times(1)).getPageIntro(LOCALE, page);
        verify(unusedAdapter, never()).getPageIntro(any(), any());
    }


    @Test
    @DisplayName("Throws PageCompositionException when no adapter supports the given page type")
    void throwsExceptionWhenNoAdapterSupportsPage() {
        General page = mock(General.class);

        PageIntroAssembler assembler = new PageIntroAssembler(List.of(new TestAdapter()));

        PageCompositionException exception = assertThrows(
                PageCompositionException.class,
                () -> assembler.from(LOCALE, page)
        );

        assertTrue(exception.getMessage().contains("No adapter found for type"));
    }

    @Test
    @DisplayName("uses the first matching adapter even if later adapters also support the page")
    void firstSupportingAdapterWins() throws Exception {
        TestPage page = new TestPage();

        PageAdapter<TestPage> firstAdapter = spy(new TestAdapter());
        PageAdapter<TestPage> secondAdapter = spy(new TestAdapter());

        PageIntroAssembler assembler =
                new PageIntroAssembler(List.of(firstAdapter, secondAdapter));

        PageIntro result = assembler.from(LOCALE, page);

        assertEquals(page, result.getHippoBean());

        verify(firstAdapter, times(1)).getPageIntro(LOCALE, page);
        verify(secondAdapter, never()).getPageIntro(any(), any());
    }

    private static class TestPage extends Page {}

    private static class TestAdapter implements PageAdapter<TestPage> {

        @Override
        public PageIntro getPageIntro(Locale locale, TestPage page) {
            return new PageIntro(page);
        }

        @Override
        public boolean supports(Page page) {
            return page instanceof TestPage;
        }
    }
}
