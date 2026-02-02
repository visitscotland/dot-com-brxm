package com.visitscotland.brxm.factory.hippo;

/**
 * Value list defined at META-INF/hst-assembly/overrides/valueListManager.xml
 */
public enum ValueList {
    VS_ITINERARY_TRANSPORT("vs-itinerary-transports"),
    VS_ITINERARY_THEMES("vs-itinerary-themes"),
    VS_ITINERARY_AREAS("vs-itinerary-areas");

    private final String value;

    ValueList(String value) {
        this.value = value;
    }

    public String id(){
        return value;
    }
}
