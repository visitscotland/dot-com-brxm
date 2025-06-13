package com.visitscotland.brxm.obs.model;

import com.visitscotland.brxm.hippobeans.ObsProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;

//TODO: Convert to record
public class Provider {
    private String name;
    private String url;
    private String[] functions;
    private String[] contract;
    private String contractLength;

    public Provider(ObsProvider hippoBean) {
        this.name = hippoBean.getName();
        this.url = hippoBean.getUrl();
        this.functions = hippoBean.getFunctions();
        this.contract = hippoBean.getContract();
        this.contractLength = hippoBean.getContractLegth();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getFunctions() {
        return functions;
    }

    public void setFunctions(String[] functions) {
        this.functions = functions;
    }

    public String[] getContract() {
        return contract;
    }

    public void setContract(String[] contract) {
        this.contract = contract;
    }

    public String getContractLength() {
        return contractLength;
    }

    public void setContractLength(String contractLength) {
        this.contractLength = contractLength;
    }
}
