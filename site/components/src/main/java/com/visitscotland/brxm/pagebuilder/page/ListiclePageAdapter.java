package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Listicle;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ListiclePageAdapter implements PageAdapter {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(ListiclePageAdapter.class);

    @Override
    public Optional<PageIntro> getPageIntro(PageCompositionHelper pageConfig) {
        log.warn("Page intro composition for Listicle is not implemented yet.");

        return null;
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof Listicle;
    }
}
