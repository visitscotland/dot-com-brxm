package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.Day;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class ItineraryDayModule extends Module<Day> {

    private String title;
    private HippoHtml introduction;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HippoHtml getIntroduction() {
        return introduction;
    }

    public void setIntroduction(HippoHtml introduction) {
        this.introduction = introduction;
    }
}
