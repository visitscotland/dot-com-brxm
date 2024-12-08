package com.visitscotland.brxm.page;

import org.hippoecm.hst.core.component.HstRequest;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface PageConfigurer extends Configurable {
    PageConfigurer forRequest(HstRequest request);
    <T> PageConfigurer addModel(String modelName, Supplier<T> model);
    <T> PageConfigurer addModuleIf(String moduleName, Supplier<T> module, Predicate<T> filter);
}