package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DestinationPageAdapter implements PageAdapter<Destination> {


    public DestinationPageAdapter() {
    }

    public PageIntro getPageIntro(Locale locale, Destination page) {
        return new PageIntro(page);
    }

    @Override
    public boolean supports(Page page) {
        return page instanceof Destination;
    }
}
