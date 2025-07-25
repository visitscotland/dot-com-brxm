package com.visitscotland.brxm.comparator.model;

import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.Module;

import java.util.List;

public class ComparatorModule extends Module<DevModule> {

    List<Feature> features;
    List<Provider> providers;

    public ComparatorModule(DevModule document) {
        setHippoBean(document);
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> functions) {
        this.features = functions;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
