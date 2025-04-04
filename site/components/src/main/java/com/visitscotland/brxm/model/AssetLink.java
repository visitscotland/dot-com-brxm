package com.visitscotland.brxm.model;

public class AssetLink extends FlatLink {

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
