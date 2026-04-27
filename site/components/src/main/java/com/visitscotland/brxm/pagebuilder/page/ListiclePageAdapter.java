package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Listicle;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ListiclePageAdapter implements PageAdapter<Listicle> {


    public ListiclePageAdapter() {
    }

    public PageIntro getPageIntro(Locale locale, Listicle page) {
        return new PageIntro(page);
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof Listicle;
    }
}
