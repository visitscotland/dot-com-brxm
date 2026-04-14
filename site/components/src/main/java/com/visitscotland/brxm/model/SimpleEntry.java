package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Entry;

//TODO convert to record in Java 17
public class SimpleEntry {
    private String key;
    private String value;

    public SimpleEntry(Entry entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
