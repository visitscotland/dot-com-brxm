package com.visitscotland.brxm.comparator.model;

import com.visitscotland.brxm.model.Module;

import java.util.List;

public class ComparisonModule extends Module {

    List<Feature> features;
    List<Provider> providers;

    //TODO: TO BE RENAMED
    public List<Feature> getFunctions() {
        return features;
    }

    public void setFunctions(List<Feature> functions) {
        this.features = functions;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
