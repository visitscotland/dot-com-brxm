package com.visitscotland.brxm.beans.mapping;

import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class LongCopyModule extends Module {
    private HippoHtml copy;

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }
}
