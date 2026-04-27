package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.GeneralBSH;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SupportPageAdapter implements PageAdapter<GeneralBSH> {


    public SupportPageAdapter() {
        
    }

    public PageIntro getPageIntro(Locale locale, GeneralBSH page) {
        return new PageIntro(page);
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof GeneralBSH;
    }
}
