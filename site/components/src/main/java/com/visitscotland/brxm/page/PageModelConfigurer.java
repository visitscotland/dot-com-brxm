package com.visitscotland.brxm.page;

import org.hippoecm.hst.core.component.HstRequest;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PageModelConfigurer implements PageConfigurer {
    private HstRequest request;
    private final Map<String, Object> models;

    public PageModelConfigurer() {
        this.models = new HashMap<>();
    }

    @Override
    public PageConfigurer forRequest(HstRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public <T> PageConfigurer addModel(String modelName, Supplier<T> model) {
        this.models.put(modelName, model.get());
        return this;
    }

    @Override
    public <T> PageConfigurer addModuleIf(String moduleName, Supplier<T> module, Predicate<T> filter) {
        if(filter.test(module.get())) {
            this.models.put(moduleName, module.get());
        }

        return this;
    }

    @Override
    public void configure() {
        this.models.forEach((key, value) -> request.setModel(key, value));
    }
}