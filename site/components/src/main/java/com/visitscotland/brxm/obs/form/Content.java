package com.visitscotland.brxm.obs.form;

public class Content {
    String heading = "Online Booking System Comparison";
    String successHeading = "Calculating...";
    String successContent = "You will receive the results shortly";
    String noJs = "<p><strong>You need JavaScript enabled to be able to display our newsletter subscription form.</strong></p> <p>You can enable this in your browser settings. You can also ask to subscribe by sending an email to <a href='mailto:info@visitscotland.com'>info@visitscotland.com</a>.</p>";
    String submit = "Calculate";

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSuccessHeading() {
        return successHeading;
    }

    public void setSuccessHeading(String successHeading) {
        this.successHeading = successHeading;
    }

    public String getSuccessContent() {
        return successContent;
    }

    public void setSuccessContent(String successContent) {
        this.successContent = successContent;
    }

    public String getNoJs() {
        return noJs;
    }

    public void setNoJs(String noJs) {
        this.noJs = noJs;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
}
