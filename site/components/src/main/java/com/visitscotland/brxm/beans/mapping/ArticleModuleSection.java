package com.visitscotland.brxm.beans.mapping;

import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class ArticleModuleSection {
    private HippoHtml copy;
    private FlatImage image;
    private FlatQuote quote;

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }

    public FlatImage getImage() {
        return image;
    }

    public void setImage(FlatImage image) {
        this.image = image;
    }

    public FlatQuote getQuote() {
        return quote;
    }

    public void setQuote(FlatQuote quote) {
        this.quote = quote;
    }
}
