package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@HippoEssentialsGenerated(internalName = "visitscotland:ObsProvider")
@Node(jcrType = "visitscotland:ObsProvider")
public class ObsProvider extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "visitscotland:name")
    public String getName() {
        return getSingleProperty("visitscotland:name");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:url")
    public String getUrl() {
        return getSingleProperty("visitscotland:url");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:functions")
    public String[] getFunctions() {
        return getMultipleProperty("visitscotland:functions");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:contract")
    public String[] getContract() {
        return getMultipleProperty("visitscotland:contract");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:contractLegth")
    public String getContractLegth() {
        return getSingleProperty("visitscotland:contractLegth");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:description")
    public HippoHtml getDescription() {
        return getHippoHtml("visitscotland:description");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:cta")
    public String getCta() {
        return getSingleProperty("visitscotland:cta");
    }
}
