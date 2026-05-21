package com.visitscotland.brxm.hippobeans;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

import java.util.List;

@HippoEssentialsGenerated(internalName = "visitscotland:MediaCollection")
@Node(jcrType = "visitscotland:MediaCollection")
public class MediaCollection extends HippoCompound {

    @HippoEssentialsGenerated(internalName = "visitscotland:media", allowModifications = false)
    public List<HippoBean> getMedia() {
        return getMedia("visitscotland:media");
    }
}
