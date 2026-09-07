package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;

import java.math.BigDecimal;
import java.util.List;

public class ItineraryPage extends PageTemplate {

    private BigDecimal distance;
    private List<Entry> transports;
    private List<Entry> areas;
    private List<Entry> seasons;
    private List<String> locations;
    private String iframeMap;
    private Entry theme;
    private List<Day> days;
    private Integer dayCount;
    private FlatLink mapLink;

    public ItineraryPage(Page page) {
        super(page);
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Integer getDayCount() { return dayCount; }

    public void setDayCount(Integer dayCount) { this.dayCount = dayCount; }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public List<Entry> getTransports() {
        return transports;
    }

    public void setTransports(List<Entry> transports) {
        this.transports = transports;
    }

    public List<Entry> getAreas() {
        return areas;
    }

    public void setAreas(List<Entry> areas) {
        this.areas = areas;
    }

    public List<Entry> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Entry> seasons) {
        this.seasons = seasons;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getIframeMap() {
        return iframeMap;
    }

    public void setIframeMap(String iframeMap) {
        this.iframeMap = iframeMap;
    }

    public Entry getTheme() {
        return theme;
    }

    public void setTheme(Entry theme) {
        this.theme = theme;
    }

    public FlatLink getMapLink() { return mapLink; }

    public void setMapLink(FlatLink mapLink) { this.mapLink = mapLink; }
}
