package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.TravelInformation;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import java.util.List;

public class TravelInformationModule extends Module<TravelInformation> {

    private String title;
    private HippoHtml copy;
    private List<TravelInformationTabInterface> practicalInformation;

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

    public List<TravelInformationTabInterface> getPracticalInformation() {
        return practicalInformation;
    }

    public void setPracticalInformation(List<TravelInformationTabInterface> practicalInformation) {
        this.practicalInformation = practicalInformation;
    }
}
