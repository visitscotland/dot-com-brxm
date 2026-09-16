package com.visitscotland.brxm.hippobeans;

import com.visitscotland.brxm.model.Module;

public class SimpleDevModule extends Module<DevModule> {

    private String id;
    private String[] footerContributions;
    private String[] headerContributions;

    public SimpleDevModule(DevModule hippoBean) {
        setHippoBean(hippoBean);
    }

    public SimpleDevModule(DevModule hippoBean, String id) {
        this(hippoBean);
        setId(id);
        setHeaderContributions(hippoBean.getHeadContributions());
        setFooterContributions(hippoBean.getHeadContributions());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getFooterContributions() {
        return footerContributions;
    }

    public void setFooterContributions(String[] footerContributions) {
        this.footerContributions = footerContributions;
    }

    public String[] getHeaderContributions() {
        return headerContributions;
    }

    public void setHeaderContributions(String[] headerContributions) {
        this.headerContributions = headerContributions;
    }
}
