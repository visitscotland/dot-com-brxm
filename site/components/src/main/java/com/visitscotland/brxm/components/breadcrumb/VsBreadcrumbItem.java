package com.visitscotland.brxm.components.breadcrumb;

import org.hippoecm.hst.core.linking.HstLink;
import org.onehippo.forge.breadcrumb.om.BreadcrumbItem;

public class VsBreadcrumbItem extends BreadcrumbItem {

    private String plainLink;

    public VsBreadcrumbItem(HstLink link, String title, String plainLink) {
        super(link, title);
        this.plainLink = plainLink;
    }

    public String getPlainLink() {
        return plainLink;
    }
}
