package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.hippobeans.Page;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageIntroService {

    private static final Logger logger = LoggerFactory.getLogger(PageIntroService.class);

    private final List<PageAdapter<?>> adapters;

    public PageIntroService(List<PageAdapter<?>> adapters) {
        logger.debug("Adapters found: {}", adapters.size());
        this.adapters = adapters;
    }

    public PageIntro from(HstRequest request, Page page) {
        return adapters.stream()
                .filter(adaptor -> adaptor.supports(page))
                .findFirst()
                .map(a -> ((PageAdapter<Page>) a).getPageIntro(request, page))
                /** TODO: Transform to PageBuilderException after v3.0.0 is out */
                .orElseThrow(() -> new IllegalArgumentException("No adapter found for type: " + page.getClass()));
    }
}
