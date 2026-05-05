package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.hippobeans.Page;
import org.springframework.stereotype.Component;

@Component
public class DestinationPageAdapter extends CommonPageAdapter<Destination>  {

    @Override
    public boolean supports(Page page) {
        return page instanceof Destination;
    }
}
