package com.visitscotland.brxm.obs.model;

import com.visitscotland.brxm.hippobeans.ObsFunction;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class Function {

    public String description;
    public String[] category;
    public Boolean featured;
    public String id;
    public HippoHtml content;

    public Function(ObsFunction obsFunction) {
        this.description = obsFunction.getDescription();
        this.category = obsFunction.getCategory();
        this.featured = obsFunction.getFeatured();
        this.id = obsFunction.getId();
        this.content = obsFunction.getContent();
    }

    public Function(String key, String value) {
        this.id = key;
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public String[] getCategory() {
        return category;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public String getId() {
        return id;
    }

    public HippoHtml getContent() {
        return content;
    }
}
