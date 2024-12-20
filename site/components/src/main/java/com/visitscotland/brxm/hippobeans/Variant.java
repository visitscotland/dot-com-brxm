package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@HippoEssentialsGenerated(internalName = "visitscotland:Variant")
@Node(jcrType = "visitscotland:Variant")
public class Variant extends HippoCompound {
    public static final String VARIANT_JCR_TYPE = "visitscotland:Variant";

    @HippoEssentialsGenerated(internalName = "visitscotland:country")
    public String getCountry() {
        return getSingleProperty("visitscotland:country");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:isVariant")
    public Boolean getIsVariant() {
        return getSingleProperty("visitscotland:isVariant");
    }
}
