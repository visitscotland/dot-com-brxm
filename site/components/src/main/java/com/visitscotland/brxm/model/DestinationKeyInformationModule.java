package com.visitscotland.brxm.model;

import java.util.List;

public class DestinationKeyInformationModule extends Module{
    private String title;
    private List<DestinationKeyCategory> highlights;
    private FlatLink cta;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DestinationKeyCategory> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<DestinationKeyCategory> highlights) {
        this.highlights = highlights;
    }

    public FlatLink getCta() {
        return cta;
    }

    public void setCta(FlatLink cta) {
        this.cta = cta;
    }
}
