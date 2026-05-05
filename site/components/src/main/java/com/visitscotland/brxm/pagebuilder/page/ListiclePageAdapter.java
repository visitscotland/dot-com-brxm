package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Listicle;
import com.visitscotland.brxm.hippobeans.Page;
import org.springframework.stereotype.Component;

@Component
public class ListiclePageAdapter extends CommonPageAdapter<Listicle> {

    @Override
    public boolean supports(Page page) {
        return page instanceof Listicle;
    }
}
