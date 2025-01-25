package com.visitscotland.brxm.model;

import org.hippoecm.hst.content.beans.standard.HippoHtml;


public class EventsListingTab {

    private String title;
    private HippoHtml copy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }
}
