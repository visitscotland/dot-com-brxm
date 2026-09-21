package com.visitscotland.brxm.model;

import java.math.BigDecimal;

public class IntraDayModule {

    private String title;
    private FlatLink routeUrl;
    private BigDecimal distance;

    public IntraDayModule(final String title, final FlatLink routeUrl, final BigDecimal distance) {
        this.title = title;
        this.routeUrl = routeUrl;
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FlatLink getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(final FlatLink routeUrl) {
        this.routeUrl = routeUrl;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

}
