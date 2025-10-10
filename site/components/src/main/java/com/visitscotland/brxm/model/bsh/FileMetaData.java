package com.visitscotland.brxm.model.bsh;

// TODO: Convert to a record after upgrading to Java 17
public class FileMetaData {
    private final String link;
    private final long contentLength;
    private final String mimeType;

    public FileMetaData(String link, long contentLength, String mimeType) {
        this.link = link;
        this.contentLength = contentLength;
        this.mimeType = mimeType;
    }

    public String getLink() {
        return link;
    }

    public long getContentLength() {
        return contentLength;
    }

    public String getMimeType() {
        return mimeType;
    }
}
