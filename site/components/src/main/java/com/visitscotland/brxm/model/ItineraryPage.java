package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItineraryPage extends PageTemplate {

    private BigDecimal distance;
    private List<Entry> transports;
    private List<Entry> areas;
    private List<Entry> seasons;
    private List<String> locations;
    private String iframeMap;
    private Entry theme;
    // Properties to be removed from the delivery API
    private String firstStopLocation;
    private String lastStopLocation;
    private List<Day> days;
    private Integer dayCount;
    private Map<String, ItineraryStopModule> stops;
    private String lastStopNearbyEat;
    private String lastStopNearbyStay;
    private FlatLink mapLink;

    public ItineraryPage(Page page) {
        super(page);
    }

    public String getFirstStopLocation() {
        return firstStopLocation;
    }

    public void setFirstStopLocation(String firstStopLocation) {
        this.firstStopLocation = firstStopLocation;
    }

    public String getLastStopLocation() {
        return lastStopLocation;
    }

    public void setLastStopLocation(String lastStopLocation) {
        this.lastStopLocation = lastStopLocation;
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

    public Map<String, ItineraryStopModule> getStops() {
        return stops;
    }

    public void setStops(Map<String, ItineraryStopModule> stops) {
        this.stops = stops;
    }

    public void addStop(ItineraryStopModule module){
        if (stops == null){
            stops = new HashMap<>();
        }
        stops.put(module.getIdentifier(), module);
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

    public String getLastStopNearbyEat() {
        return lastStopNearbyEat;
    }

    public void setLastStopNearbyEat(String lastStopNearbyEat) {
        this.lastStopNearbyEat = lastStopNearbyEat;
    }

    public String getLastStopNearbyStay() {
        return lastStopNearbyStay;
    }

    public void setLastStopNearbyStay(String lastStopNearbyStay) {
        this.lastStopNearbyStay = lastStopNearbyStay;
    }

    public FlatLink getMapLink() { return mapLink; }

    public void setMapLink(FlatLink mapLink) { this.mapLink = mapLink; }
}
