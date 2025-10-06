package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@HippoEssentialsGenerated(internalName = "visitscotland:ComparatorSystem")
@Node(jcrType = "visitscotland:ComparatorSystem")
public class ComparatorSystem extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:name")
    public String getName() {
        return getSingleProperty("visitscotland:name");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:url")
    public String getUrl() {
        return getSingleProperty("visitscotland:url");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:features")
    public String[] getFeatures() {
        return getMultipleProperty("visitscotland:features");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:description")
    public HippoHtml getDescription() {
        return getHippoHtml("visitscotland:description");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:referral")
    public String getReferral() {
        return getSingleProperty("visitscotland:referral");
    }
}
