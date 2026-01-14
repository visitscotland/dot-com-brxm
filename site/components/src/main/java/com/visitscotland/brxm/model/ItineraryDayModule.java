package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Day;
import org.hippoecm.hst.content.beans.standard.HippoHtml;


public class ItineraryDayModule extends Module<Day> {

    private String title;
    private HippoHtml introduction;
    private String[] transports;
    private FlatLink mapLink;
    private FlatLink ctaLink;
    // image - check with design requirements

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
}
