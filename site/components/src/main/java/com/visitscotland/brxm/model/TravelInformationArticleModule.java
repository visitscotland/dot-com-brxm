package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.TravelInformationArticle;
import com.visitscotland.brxm.hippobeans.TravelInformationTransportRow;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class TravelInformationArticleModule extends Module<TravelInformationArticle> implements TravelInformationTabInterface {

    private final String type = "article";
    private String title;
    private HippoHtml practicalInformationContent;

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

    public HippoHtml getPracticalInformationContent() {
        return practicalInformationContent;
    }

    public void setPracticalInformationContent(HippoHtml practicalInformationContent) {
        this.practicalInformationContent = practicalInformationContent;
    }
}
