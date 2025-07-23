package com.visitscotland.brxm.comparator.model;

import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.Module;

import java.util.List;

public class ShortListModule extends Module<DevModule> {

    List<Provider> providers;
    List<Feature> functions;

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Feature> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Feature> functions) {
        this.functions = functions;
    }
}
