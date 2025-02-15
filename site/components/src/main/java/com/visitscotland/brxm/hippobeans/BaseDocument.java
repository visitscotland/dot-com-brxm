package com.visitscotland.brxm.hippobeans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoMirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Node(jcrType = "visitscotland:basedocument")
public class BaseDocument extends HippoDocument {

    private static final Logger logger = LoggerFactory.getLogger(BaseDocument.class);

    protected <T extends HippoBean> T getOnlyChild(List<T> children) {
        if (children.isEmpty()) {
            return null;
        } else if (children.size() == 1) {
            return children.get(0);
        } else {
            logger.warn("This list in supposed to have only one child");
            return children.get(0);
        }
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    /**
     * There is an existing issue in BloomReach affecting only images where they are not correctly mapped. This method
     * works as a workaround to that issue
     */
    @SuppressWarnings("unchecked")
    protected <T extends HippoBean> List<T> getMedia(String childNodeName) {
        return (List<T>) getChildBeansByName(childNodeName, HippoBean.class).stream().map(hippoBean -> {
                // TODO: Replace block with the following line
                // (hippoBean instanceof HippoMirror ? ((HippoMirror) hippoBean).getReferencedBean() : hippoBean)
                    if (hippoBean instanceof HippoMirror) {
                        return ((HippoMirror) hippoBean).getReferencedBean();
                    }
                    return hippoBean;
                }
        ).collect(Collectors.toList());
    }

}
