package com.visitscotland.brxm.formatter;

import org.hippoecm.hst.content.beans.standard.HippoBean;

@FunctionalInterface
public interface HippoBeanFormatter<H extends HippoBean, R> {
    R format(H hippoBean);
}