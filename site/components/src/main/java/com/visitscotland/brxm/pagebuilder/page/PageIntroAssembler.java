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

    private final List<PageAdapter<? extends Page>> adapters;

    public PageIntroAssembler(List<PageAdapter<? extends Page>> adapters) {
        logger.debug("Adapters found: {}", adapters.size());
        this.adapters = adapters;
    }

    @SuppressWarnings("unchecked")
    public <P extends Page> PageIntro from(Locale locale, P page) throws PageCompositionException {

        return adapters.stream()
                .filter(adaptor -> adaptor.supports(page))
                .findFirst()
                .map(a -> ((PageAdapter<P>) a).getPageIntro(locale, page))
                .orElseThrow(() -> new PageCompositionException("No adapter found for type: " + page.getClass()));
    }
}
