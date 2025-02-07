package com.visitscotland.brxm.model.event;

import java.util.List;

public class EventFilter {

    public enum Type {
        BOOLEAN, DATE, MULTISELECT, DROPDOWN
    }

    private String key;
    private String label;
    private Type type;
    private Integer group;
    private List<EventValueOption> values;

    public EventFilter() {

    }

    public EventFilter(String key, String label, Type type, Integer group) {
        this.key = key;
        this.label = label;
        this.type = type;
        this.group = group;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public List<EventValueOption> getValues() {
        return values;
    }

    public void setValues(List<EventValueOption> values) {
        this.values = values;
    }
}
