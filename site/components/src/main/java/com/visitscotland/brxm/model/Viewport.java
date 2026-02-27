package com.visitscotland.brxm.model;

public class Viewport {
    private final Coordinates low;   // SW
    private final Coordinates high;  // NE

    public Viewport(Coordinates low, Coordinates high) {
        this.low = low;
        this.high = high;
    }

    public Coordinates getLow() {
        return low;
    }

    public Coordinates getHigh() {
        return high;
    }
}
