package com.visitscotland.brxm.obs.model;

import com.visitscotland.brxm.hippobeans.ObsProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

//TODO: Convert to record
public class Provider {
    private String name;
    private String url;
    private String[] functions;
    private String[] contract;
    private String contractLength;
    private String description;


    public Provider(ObsProvider hippoBean) {
        this.name = hippoBean.getName();
        this.url = hippoBean.getUrl();
        this.functions = hippoBean.getFunctions();
        this.contract = hippoBean.getContract();
        this.contractLength = hippoBean.getContractLegth();
        this.description = hippoBean.getDescription().getContent();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String[] getFunctions() {
        return functions;
    }

    public String[] getContract() {
        return contract;
    }

    public String getContractLength() {
        return contractLength;
    }

    public String getDescription() {
        return description;
    }
}
