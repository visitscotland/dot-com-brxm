package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.LongCopy;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class LongCopyModule extends Module<LongCopy> {
    private HippoHtml copy;

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }
}
