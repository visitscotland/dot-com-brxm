package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;

import java.util.Locale;

public interface PageAdapter<P extends Page> {

    PageIntro getPageIntro(Locale locale, P page);

    boolean supports(Page page);
}
