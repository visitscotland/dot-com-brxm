package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.GeneralBSH;
import com.visitscotland.brxm.hippobeans.Page;
import org.springframework.stereotype.Component;

@Component
public class SupportPageAdapter extends CommonPageAdapter<GeneralBSH> {

    @Override
    public boolean supports(Page page) {
        return page instanceof GeneralBSH;
    }
}
