package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.hippobeans.Page;
import org.hippoecm.hst.core.component.HstRequest;

public interface PageAdapter<P extends Page> {

    PageIntro getPageIntro(HstRequest request, P page);

    boolean supports(Page page);
}
