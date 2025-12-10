package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class PageIntroAssembler {

    private static final Logger logger = LoggerFactory.getLogger(PageIntroAssembler.class);

    // TODO: Review the following comment and test:
    /*
    Change List<PageAdapter<Page>> to List<PageAdapter<?>> for correct generic type compatibility.

    Due to Java's generic type invariance, PageAdapter<General> (which extends Page) is not a subtype of List<PageAdapter<Page>>. This causes a type mismatch during Spring's dependency injection.

    With only one implementation (GeneralPageAdapter implements PageAdapter<General>), Spring may still inject it at runtime due to type erasure, but:

    It generates unchecked assignment warnings at compile time
    Line 30 (a.getPageIntro(locale, page)) becomes type-unsafe if a different Page subtype is passed that the adapter doesn't support
    Calling supports() filters by runtime behavior, not static type guarantees

    Use List<PageAdapter<?>> to allow Spring to correctly inject all PageAdapter implementations while preserving type safety through the method's generic constraint <P extends Page>.
     */
    private final List<PageAdapter<Page>> adapters;

    public PageIntroAssembler(List<PageAdapter<Page>> adapters) {
        logger.debug("Adapters found: {}", adapters.size());
        this.adapters = adapters;
    }

    public <P extends Page> PageIntro from(Locale locale, P page) throws PageCompositionException {

        return adapters.stream()
                .filter(adaptor -> adaptor.supports(page))
                .findFirst()
                .map(a -> a.getPageIntro(locale, page))
                .orElseThrow(() -> new PageCompositionException("No adapter found for type: " + page.getClass()));
    }
}
