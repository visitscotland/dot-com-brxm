package com.visitscotland.brxm.obs.model;

import com.visitscotland.brxm.model.Module;

import java.util.List;

public class ComparisonModule extends Module {
    List<Function> functions;
    List<Provider> providers;
    List<ContractFee> fees;



    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<ContractFee> getFees() {
        return fees;
    }

    public void setFees(List<ContractFee> fees) {
        this.fees = fees;
    }
}
