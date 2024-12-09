package com.visitscotland.brxm.page;

import org.hippoecm.hst.core.component.HstRequest;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Implementation of the {@link PageConfigurer} interface to manage and configure models for a web page.
 * <p>
 * This class is responsible for associating models with a given {@link HstRequest} and conditionally adding models
 * based on filters. The configured models are set in the {@link HstRequest} during the configuration process.
 * </p>
 */
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
    public <T> PageConfigurer addModelIf(String modelName, Supplier<T> model, Predicate<T> filter) {
        if(filter.test(model.get())) {
            this.models.put(modelName, model.get());
        }

        return this;
    }

    @Override
    public void configure() {
        this.models.forEach((key, value) -> request.setModel(key, value));
    }
}