package com.visitscotland.brxm.model;

import com.visitscotland.brxm.model.megalinks.EnhancedLink;

public class AssetLink extends EnhancedLink {

    private String mimeType;
    private long size;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
