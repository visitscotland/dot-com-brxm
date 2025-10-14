package com.visitscotland.brxm.model.megalinks;

import java.util.Objects;

public class Entry {

    private String key;
    private String displayName;

    public Entry(String key, String displayName) {
        this.key = key;
        this.displayName = displayName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key) &&
               Objects.equals(displayName, entry.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, displayName);
    }

    @Override
    public String toString() {
        return "Entry{" +
               "key='" + key + '\'' +
               ", displayName='" + displayName + '\'' +
               '}';
    }
}
