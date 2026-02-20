package com.visitscotland.brxm.model.favourites;

/**
 * Favourites card object used to send limited page info to favourites page
 */
public class FavouritesCard {

    private String uuid;
    private String title;
    private String introduction;
    private String url;
    private String image;

    public FavouritesCard(final String uuid, final String title, final String introduction, final String url, final String image) {
        this.uuid = uuid;
        this.title = title;
        this.introduction = introduction;
        this.url = url;
        this.image = image;
    }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
