package com.visitscotland.brxm.pagebuilder.page.adapter;

import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DestinationPageAdapter implements PageAdapter {

    private final Logger log = LoggerFactory.getLogger(DestinationPageAdapter.class);

    @Override
    public Optional<PageIntro> getPageIntro(PageCompositionHelper pageConfig) {
        log.warn("Page intro composition for Destination is not implemented yet.");
        return null;
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof Destination;
    }
}
