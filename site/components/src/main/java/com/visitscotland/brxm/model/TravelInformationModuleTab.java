package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.TravelInformationTab;

import java.util.List;

public class TravelInformationModuleTab extends Module<TravelInformationTab>  {

    private String title;
    private TravelInformationTabInterface travelInformationContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TravelInformationTabInterface getTravelInformationContent() {
        return travelInformationContent;
    }

    public void setTravelInformationContent(TravelInformationTabInterface travelInformationContent) {
        this.travelInformationContent = travelInformationContent;
    }
}
