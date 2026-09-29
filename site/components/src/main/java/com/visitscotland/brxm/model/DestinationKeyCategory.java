package com.visitscotland.brxm.model;

import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class DestinationKeyCategory {

    private SimpleEntry category;
    private HippoHtml copy;

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }

    public SimpleEntry getCategory() {
        return category;
    }

    public void setCategory(SimpleEntry category) {
        this.category = category;
    }
}
