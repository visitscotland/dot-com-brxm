package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import java.util.List;


public class ItineraryDayModule extends Module<Day> {

    private String title;
    private HippoHtml introduction;
    private String[] transports;
    private FlatLink mapLink;
    private FlatLink ctaLink;
    private FlatImage image;
    private EnhancedLink video;
    private MediaSection mediaSection;
    private IntraDayModule intraDayModule;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HippoHtml getIntroduction() {
        return introduction;
    }

    public void setIntroduction(HippoHtml introduction) {
        this.introduction = introduction;
    }

    public String[] getTransports() {
        return transports;
    }

    public void setTransports(String[] transports) {
        this.transports = transports;
    }

    public FlatLink getMapLink() {
        return mapLink;
    }

    public void setMapLink(FlatLink mapLink) {
        this.mapLink = mapLink;
    }

    public FlatLink getCtaLink() {
        return ctaLink;
    }

    public void setCtaLink(FlatLink ctaLink) {
        this.ctaLink = ctaLink;
    }

    public MediaSection getMediaSection() {
        return mediaSection;
    }

    public void setMediaSection(MediaSection mediaSection) {
        this.mediaSection = mediaSection;
    }

    public IntraDayModule getIntraDayModule() {
        return intraDayModule;
    }

    public void setIntraDayModule(IntraDayModule intraDayModule) {
        this.intraDayModule = intraDayModule;

    }

    public FlatImage getImage() {
        return image;
    }

    public void setImage(FlatImage image) {
        this.image = image;
    }

    public EnhancedLink getVideo() {
        return video;
    }

    public void setVideo(EnhancedLink video) {
        this.video = video;
    }
}
