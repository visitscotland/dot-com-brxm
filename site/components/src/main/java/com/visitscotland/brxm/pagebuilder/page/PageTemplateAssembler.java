package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;
import com.visitscotland.brxm.pagebuilder.page.adapter.PageAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageTemplateAssembler {

    private static final Logger logger = LoggerFactory.getLogger(PageTemplateAssembler.class);

    private final List<PageAdapter> adapters;

    public PageTemplateAssembler(List<PageAdapter> adapters) {
        logger.debug("Adapters found: {}", adapters.size());
        this.adapters = adapters;
    }


    public PageTemplate from(PageCompositionHelper pageCompositionHelper) throws PageCompositionException {
        Page page = pageCompositionHelper.getPage();

        PageAdapter adapter = adapters.stream()
                .filter(a -> a.supports(page))
                .findFirst()
                .orElseThrow(() -> new PageCompositionException("No adapter found for type: " + page.getClass()));

        return adapter.getPageIntro(pageCompositionHelper)
                .orElseThrow(() -> new PageCompositionException("An error occurred while composing the pageIntro for " + page.getPath()));
    }

}
