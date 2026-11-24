package com.visitscotland.brxm.pagebuilder.page.adapter;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;

import java.util.Optional;

public interface PageAdapter {

    Optional<PageTemplate> getPageIntro(PageCompositionHelper pageConfig);

    boolean supports(Page page);
}
