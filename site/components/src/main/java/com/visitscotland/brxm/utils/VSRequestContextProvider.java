package com.visitscotland.brxm.utils;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.springframework.stereotype.Component;

import javax.jcr.Node;

@Component
@NonTestable(NonTestable.Cause.BRIDGE)
public class VSRequestContextProvider {

    public HstRequestContext getRequestContext() {
        return RequestContextProvider.get();
    }

    public HstLink getHstLink(Node node){
        return getRequestContext().getHstLinkCreator().create(node, getRequestContext());
    }

    public String getUrl(Node node){
        final boolean FULLY_QUALIFIED = false;
        return getHstLink(node).toUrlForm(getRequestContext(), FULLY_QUALIFIED);
    }
}
