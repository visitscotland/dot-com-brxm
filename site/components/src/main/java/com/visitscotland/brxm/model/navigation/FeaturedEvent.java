package com.visitscotland.brxm.model.navigation;

import com.visitscotland.brxm.hippobeans.FeaturedWidget;

public class FeaturedEvent extends NavigationWidget<FeaturedWidget> {

    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
