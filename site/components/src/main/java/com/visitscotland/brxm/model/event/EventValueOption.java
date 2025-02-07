package com.visitscotland.brxm.model.event;

public class EventValueOption {

    private String label;
    private String key;

    public EventValueOption() {
    }

    public EventValueOption(String key, String label) {
        this.label = label;
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
