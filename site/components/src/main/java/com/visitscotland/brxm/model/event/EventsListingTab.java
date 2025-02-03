package com.visitscotland.brxm.model.event;

import org.hippoecm.hst.content.beans.standard.HippoHtml;

import java.util.List;
import java.util.Map;


public class EventsListingTab {

    private String title;
    private HippoHtml copy;
    private String baseEndPoint;
    private List<EventValueOption> sortBy;
    private List<EventFilter> filters;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }

    public String getBaseEndPoint() {
        return baseEndPoint;
    }

    public void setBaseEndPoint(String baseEndPoint) {
        this.baseEndPoint = baseEndPoint;
    }

    public List<EventValueOption> getSortBy() {
        return sortBy;
    }

    public void setSortBy(List<EventValueOption> sortBy) {
        this.sortBy = sortBy;
    }

    public List<EventFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<EventFilter> filters) {
        this.filters = filters;
    }
}
