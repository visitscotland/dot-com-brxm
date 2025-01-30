package com.visitscotland.brxm.model.bsh;

import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.Module;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class EventCard extends Module<EventBSH> {

    private String title;
    private HippoHtml summary;
    private String dates;
    private String times;
    private String price;
    private String location;
    private String organizer;
    private String contact ;
    private String registrationDeadline;
    private FlatLink cta;

    public EventCard() {
    }

    public EventCard(EventBSH document) {
        this.title = document.getTitle();
        this.summary = document.getSummary();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HippoHtml getSummary() {
        return summary;
    }

    public void setSummary(HippoHtml summary) {
        this.summary = summary;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(String registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public FlatLink getCta() {
        return cta;
    }

    public void setCta(FlatLink cta) {
        this.cta = cta;
    }
}
