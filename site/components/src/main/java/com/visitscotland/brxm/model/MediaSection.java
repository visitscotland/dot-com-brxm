package com.visitscotland.brxm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MediaSection {

    private String type;
    private List<Object> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getItems() {
        if (items != null && items.size() > 1) {
            return items;
        } else {
            return null;
        }
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public FlatImage getImage() {
        if (items != null && items.size() == 1 && items.get(0) instanceof FlatImage) {
            return (FlatImage) items.get(0);
        }
        return null;
    }

    public EnhancedLink getVideo() {
        if (items != null && items.size() == 1 && items.get(0) instanceof EnhancedLink) {
            return (EnhancedLink) items.get(0);
        }
        return null;
    }
}
