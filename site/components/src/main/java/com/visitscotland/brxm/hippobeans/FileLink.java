package com.visitscotland.brxm.hippobeans;

import com.visitscotland.brxm.hippobeans.capabilities.UrlLink;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@HippoEssentialsGenerated(internalName = "visitscotland:FileLink")
@Node(jcrType = "visitscotland:FileLink")
public class FileLink extends HippoCompound implements UrlLink {

    @HippoEssentialsGenerated(internalName = "visitscotland:link")
    public String getLink() {
        return getSingleProperty("visitscotland:link");
    }
}
