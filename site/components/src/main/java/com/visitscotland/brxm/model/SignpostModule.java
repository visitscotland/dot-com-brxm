package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.CTABanner;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

//TODO to be renamed to Spotlight module
public class SignpostModule extends Module<CTABanner> {

    private String title;
    private boolean nested;
    private HippoHtml copy;
    private FlatLink cta;
    private FlatImage image;

    public boolean isNested() {
        return nested;
    }

    public void setNested(boolean nested) {
        this.nested = nested;
    }

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

    public FlatLink getCta() {
        return cta;
    }

    public void setCta(FlatLink cta) {
        this.cta = cta;
    }

    public FlatImage getImage() {
        return image;
    }

    public void setImage(FlatImage image) {
        this.image = image;
    }
}
