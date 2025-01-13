package com.visitscotland.brxm.model;

import org.hippoecm.hst.content.beans.standard.HippoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Module<H extends HippoBean> {
    private H hippoBean;
    private String anchor;
    private List<String> errorMessages;
    private String marketoId;
    private Map<String, String> labels;

    public String getType() {
        return getClass().getSimpleName();
    }

    public H getHippoBean() {
        return hippoBean;
    }

    public void setHippoBean(H hippoBean) {
        this.hippoBean = hippoBean;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String message){
        if (errorMessages == null){
            errorMessages = new ArrayList<>();
        }
        errorMessages.add(message);
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Optional<Map<String, String>> getLabels() {
        return Optional.ofNullable(labels);
    }

    public void setMarketoId(String marketoId) {
        this.marketoId = marketoId;
    }
}