package com.visitscotland.brxm.model;

import java.util.List;

public class TravelInformationTransportModule implements TravelInformationTabInterface {

    private final String type = "transport";
    private String title;
    private List<TravelInformationTransportRowModule> practicalInformationContent;

    @Override
    public String getType() {
        return type;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TravelInformationTransportRowModule> getPracticalInformationContent() {
        return practicalInformationContent;
    }

    public void setPracticalInformationContent(List<TravelInformationTransportRowModule> practicalInformationContent) {
        this.practicalInformationContent = practicalInformationContent;
    }
}
