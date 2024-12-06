package com.visitscotland.brxm.module;

import com.visitscotland.brxm.model.Module;
import org.hippoecm.hst.core.component.HstRequest;

import java.util.Collection;
import java.util.function.Supplier;

public interface HstRequestConfigurer extends Configurable {
    HstRequestConfigurer forRequest(HstRequest request);
    <T extends Module<?>> HstRequestConfigurer registerModule(Supplier<T> supplier);
    <T extends Module<?>> HstRequestConfigurer registerModules(Collection<T> modules);
}