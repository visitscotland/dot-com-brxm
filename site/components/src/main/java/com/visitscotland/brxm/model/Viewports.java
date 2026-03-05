package com.visitscotland.brxm.model;

public class Viewports {
    private double minLat = Double.POSITIVE_INFINITY;
    private double maxLat = Double.NEGATIVE_INFINITY;
    private double minLng = Double.POSITIVE_INFINITY;
    private double maxLng = Double.NEGATIVE_INFINITY;

    public void update(double lat, double lng) {
        minLat = Math.min(minLat, lat);
        maxLat = Math.max(maxLat, lat);
        minLng = Math.min(minLng, lng);
        maxLng = Math.max(maxLng, lng);
    }

    public boolean hasValidValues() {
        return Double.isFinite(minLat) && Double.isFinite(maxLat) && Double.isFinite(minLng) && Double.isFinite(maxLng);
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLng() {
        return minLng;
    }

    public void setMinLng(double minLng) {
        this.minLng = minLng;
    }

    public double getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(double maxLng) {
        this.maxLng = maxLng;
    }
}
