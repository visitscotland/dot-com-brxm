package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@HippoEssentialsGenerated(internalName = "visitscotland:Price")
@Node(jcrType = "visitscotland:Price")
public class Price extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:currency")
    public String getCurrency() {
        return getSingleProperty("visitscotland:currency");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:vat")
    public Boolean getVat() {
        return getSingleProperty("visitscotland:vat");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:amount")
    public Double getAmount() {
        return getSingleProperty("visitscotland:amount");
    }
}
