package com.visitscotland.brxm.obs.model;

import java.util.List;

public class SpreadSheetRequest {

    List<Provider> providers;
    List<Function> functions;

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}
