package com.visitscotland.brxm.model;

public class DownloadLink extends FlatLink {

    private String teaser;
    private String publishedDate;
    private String size;
    private String extension;

    public DownloadLink(FlatLink flatLink) {
        super (flatLink.getLabel(), flatLink.getLink(), flatLink.getType());
        setType(LinkType.DOWNLOAD);
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }
}
