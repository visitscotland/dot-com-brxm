package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.hippobeans.SplitBlock;
import org.hippoecm.hst.content.beans.standard.HippoBean;

public class SplitBlockModule extends Module<SplitBlock> {
    private HippoBean leftReference;
    private HippoBean rightReference;

    public SplitBlockModule(HippoBean leftReference, HippoBean rightReference) {
        this.leftReference = leftReference;
        this.rightReference = rightReference;
    }

    public static SplitBlockModule of(final BaseDocument baseDocument) {
        final SplitBlock splitBlock = (SplitBlock) baseDocument;
        return new SplitBlockModule(splitBlock.getLeftReference(), splitBlock.getRightReference());
    }
}