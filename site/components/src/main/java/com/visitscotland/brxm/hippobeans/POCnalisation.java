package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@HippoEssentialsGenerated(internalName = "visitscotland:POCnalisation")
@Node(jcrType = "visitscotland:POCnalisation")
public class POCnalisation extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:country")
    public String getCountry() {
        return getSingleProperty("visitscotland:country");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:sectors")
    public String[] getSectors() {
        return getMultipleProperty("visitscotland:sectors");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:isVariant")
    public Boolean getIsVariant() {
        return getSingleProperty("visitscotland:isVariant");
    }
}
