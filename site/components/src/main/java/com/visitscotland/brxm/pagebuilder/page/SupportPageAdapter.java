package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.GeneralBSH;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SupportPageAdapter implements PageAdapter<GeneralBSH> {

    private final Logger log = LoggerFactory.getLogger(SupportPageAdapter.class);


    @Override
    public PageIntro getPageIntro(PageCompositionHelper pageConfig) {
        log.warn("Page intro composition for GeneralBSH is not implemented yet.");
        return null;
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof GeneralBSH;
    }
}
