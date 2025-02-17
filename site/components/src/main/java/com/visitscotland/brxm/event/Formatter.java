package com.visitscotland.brxm.event;

import org.hippoecm.hst.content.beans.standard.HippoBean;

@FunctionalInterface
interface Formatter<H extends HippoBean, R> {
    R format(H hippoBean);
}