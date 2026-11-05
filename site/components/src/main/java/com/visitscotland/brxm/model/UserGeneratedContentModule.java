package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Stackla;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class UserGeneratedContentModule extends Module<Stackla> {

    private String title;
    private HippoHtml copy;
    private String storystreamId;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }

    public String getStorystreamId() {
        return storystreamId;
    }

    public void setStorystreamId(String storystreamId) {
        this.storystreamId = storystreamId;
    }

    public String getTitle() {
        return title;
    }

    public HippoHtml getCopy() {
        return copy;
    }
}
