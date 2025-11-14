package com.visitscotland.brxm.comparator.model;

import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.Module;
import org.onehippo.forge.selection.hst.contentbean.ValueListItem;

import java.util.List;

public class ComparatorModule extends Module<DevModule> {

    private List<Feature> features;
    private List<Provider> providers;
    private String submitUrl;
    private List<ValueListItem> properties;

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

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public List<ValueListItem> getProperties() {
        return properties;
    }

    public void setProperties(List<ValueListItem> properties) {
        this.properties = properties;
    }
}
