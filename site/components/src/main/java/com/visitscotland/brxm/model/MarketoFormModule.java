package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.MarketoForm;
import com.visitscotland.brxm.model.form.MarketoConfiguration;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class MarketoFormModule extends Module<MarketoForm> {

    private String title;
    private String jsonUrl;
    private String noJavaScriptMessage;
    private HippoHtml copy;

    private MarketoConfiguration config;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJsonUrl() {
        return jsonUrl;
    }

    public void setJsonUrl(String jsonUrl) {
        this.jsonUrl = jsonUrl;
    }

    public String getNoJavaScriptMessage() {
        return noJavaScriptMessage;
    }

    public void setNoJavaScriptMessage(String noJavaScriptMessage) {
        this.noJavaScriptMessage = noJavaScriptMessage;
    }

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }

    public MarketoConfiguration getConfig() {
        return config;
    }

    public void setConfig(MarketoConfiguration config) {
        this.config = config;
    }
}
