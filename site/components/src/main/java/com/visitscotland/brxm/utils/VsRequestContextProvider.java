package com.visitscotland.brxm.utils;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.springframework.stereotype.Component;

import javax.jcr.Node;

@Component
@NonTestable(NonTestable.Cause.BRIDGE)
public class VsRequestContextProvider {

    public HstRequestContext getRequestContext() {
        return RequestContextProvider.get();
    }

    public HstLink getHstLink(Node node){
        if (node != null) {
            return getRequestContext().getHstLinkCreator().create(node, getRequestContext());
        }
        return null;
    }

    public String getUrl(Node node){
        final boolean FULLY_QUALIFIED = false;
        HstLink link = getHstLink(node);
        if (link == null) {
            return null;
        }
        return link.toUrlForm(getRequestContext(), FULLY_QUALIFIED);
    }
}
