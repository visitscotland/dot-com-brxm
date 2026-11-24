package com.visitscotland.brxm.hippobeans;

import com.visitscotland.brxm.hippobeans.capabilities.BregConsent;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@HippoEssentialsGenerated(internalName = "visitscotland:Entry")
@Node(jcrType = "visitscotland:Entry")
public class Entry extends HippoCompound implements BregConsent {
    @HippoEssentialsGenerated(internalName = "visitscotland:key")
    public String getKey() {
        return getSingleProperty("visitscotland:key");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:value")
    public String getValue() {
        return getSingleProperty("visitscotland:value");
    }
}
