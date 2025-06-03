package com.visitscotland.brxm.factory.hippo;

/**
 * Value list defined at META-INF/hst-assembly/overrides/valueListManager.xml
 */
public enum ValueList {
    VS_ITINERARY_TRANSPORT("vs-itinerary-transports"),
    VS_ITINERARY_THEME("vs-itinerary-theme"),
    VS_ITINERARY_AREA("vs-itinerary-area");

    private final String value;

    ValueList(String value) {
        this.value = value;
    }

    public String id(){
        return value;
    }
}
