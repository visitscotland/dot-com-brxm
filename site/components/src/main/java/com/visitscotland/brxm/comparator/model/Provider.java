package com.visitscotland.brxm.comparator.model;

import com.visitscotland.brxm.hippobeans.ComparatorSystem;

public class Provider {
    private String name;
    private String url;
    private String[] features;
    private String description;


    public Provider(ComparatorSystem hippoBean) {
        this.name = hippoBean.getName();
        this.url = hippoBean.getUrl();
        this.features = hippoBean.getFeatures();

        this.description = hippoBean.getDescription().getContent();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    // TODO: TO BE RENAMED
    public String[] getFunctions() {
        return features;
    }

    public String getDescription() {
        return description;
    }
}
