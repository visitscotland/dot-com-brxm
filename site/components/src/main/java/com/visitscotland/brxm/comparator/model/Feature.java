package com.visitscotland.brxm.comparator.model;

import com.visitscotland.brxm.hippobeans.ComparatorFeature;

public class Feature {
    private String id;
    private String name;
    private String description;
    private String group;
    private String groupDescription;

    public Feature (ComparatorFeature feature, String groupDescription) {
        this.id = feature.getId();
        this.name = feature.getName();
        this.description = feature.getDescription();
        this.group = feature.getCategory();
        this.groupDescription = groupDescription;
    }

    public Feature (String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGroup() {
        return group;
    }

    public String getGroupDescription() {
        return groupDescription;
    }
}
