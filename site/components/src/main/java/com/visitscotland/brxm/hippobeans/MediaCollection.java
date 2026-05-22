package com.visitscotland.brxm.hippobeans;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoMirror;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

import java.util.List;
import java.util.stream.Collectors;

@HippoEssentialsGenerated(internalName = "visitscotland:MediaCollection")
@Node(jcrType = "visitscotland:MediaCollection")
public class MediaCollection extends HippoCompound {

    @HippoEssentialsGenerated(internalName = "visitscotland:media", allowModifications = false)
    public List<HippoBean> getMedia() {
        return getMedia("visitscotland:media");
    }

    /**
     * There is an existing issue in BloomReach affecting only images where they are not correctly mapped. This method
     * works as a workaround to that issue
     */
    private List<HippoBean> getMedia(String childNodeName) {
        return getChildBeansByName(childNodeName, HippoBean.class).stream().map(this::getResolvedBean)
                .collect(Collectors.toList());
    }

    private HippoBean getResolvedBean(final HippoBean hippoBean) {
        if (hippoBean instanceof HippoMirror) {
            return ((HippoMirror) hippoBean).getReferencedBean();
        }
        return hippoBean;
    }
}
