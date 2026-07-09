package com.visitscotland.brxm.model;

import java.math.BigDecimal;

public class IntraDayModule {

    private String routeUrl;
    private BigDecimal distance;

    public IntraDayModule(final String routeUrl, final BigDecimal distance) {
        this.routeUrl = routeUrl;
        this.distance = distance;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(final String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
