package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;

import java.util.Locale;

public abstract class CommonPageAdapter<P extends Page> implements PageAdapter<P> {

    @Override
    public PageIntro getPageIntro(Locale locale, P page) {
        return new PageIntro(page);
    }
}
