package com.visitscotland.brxm.comparator.model;

import com.visitscotland.brxm.hippobeans.ComparatorSystem;

public class Provider {

    private String name;
    private String url;
    private String[] features;
    private String description;
    private String contact;

    public Provider(ComparatorSystem hippoBean) {
        this.name = hippoBean.getName();
        this.url = hippoBean.getUrl();
        this.features = hippoBean.getFeatures();
        this.description = hippoBean.getDescription().getContent();
        this.contact = hippoBean.getReferral();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String[] getFeatures() {
        return features;
    }

    public String getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }
}
