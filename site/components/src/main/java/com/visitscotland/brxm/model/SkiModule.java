package com.visitscotland.brxm.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.hippobeans.SkiCentre;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import java.util.List;

public class SkiModule extends Module<SkiCentre> {

    private String title;
    private HippoHtml introduction;
    private String pisteMap;

    private String feedURL;

    private String phone;
    private FlatLink website;
    private JsonNode address;
    private String addressLine;
    private FlatLink openingLink;
    private EnhancedLink cmsPage;

    private Integer timeout;

    private List<JsonNode> socialChannels;
    private String[] socialChannelURLs;


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

    public String getPisteMap() {
        return pisteMap;
    }

    public void setPisteMap(String pisteMap) {
        this.pisteMap = pisteMap;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFeedURL() {
        return feedURL;
    }

    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }

    public JsonNode getAddress() {
        return address;
    }

    public void setAddress(JsonNode address) {
        this.address = address;
    }

    public FlatLink getOpeningLink() {
        return openingLink;
    }

    public void setOpeningLink(FlatLink openingLink) {
        this.openingLink = openingLink;
    }

    public FlatLink getCmsPage() {
        return cmsPage;
    }

    public void setCmsPage(EnhancedLink cmsPage) {
        this.cmsPage = cmsPage;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public FlatLink getWebsite() {
        return website;
    }

    public void setWebsite(FlatLink website) {
        this.website = website;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public List<JsonNode> getSocialChannels() {
        return socialChannels;
    }

    public void setSocialChannels(List<JsonNode> socialChannels) {
        this.socialChannels = socialChannels;
    }

    public String[] getSocialChannelURLs() {
        return socialChannelURLs;
    }

    public void setSocialChannelURLs(String[] socialChannelURLs) {
        this.socialChannelURLs = socialChannelURLs;
    }
}
