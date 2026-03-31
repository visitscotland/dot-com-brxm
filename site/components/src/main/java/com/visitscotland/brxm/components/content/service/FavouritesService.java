package com.visitscotland.brxm.components.content.service;

import com.visitscotland.brxm.components.content.GeneralContentComponent;
import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FavouritesService {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesService.class);

    static final String FAVOURITES_BUNDLE = "favourites";
    static final String FAVOURITES_BUTTON_BUNDLE = "favourites-button";
    static final String ALLOW_FAVOURITE = "allowFavourite";
    static final String FAVOURITES_PAGE_ENABLED = "feature.favourites.enable";
    static final String FAVOURITES_SITE_URL = "feature.favourites.url";

    private final SiteProperties properties;

    public FavouritesService(SiteProperties properties) {
        this.properties = properties;
    }

    public void applyConfiguration(HstRequest request, PageCompositionHelper pageConfig) {
        pageConfig.addProperty(FAVOURITES_PAGE_ENABLED, true);
        pageConfig.addProperty(FAVOURITES_SITE_URL, properties.getFavouritesUrl(request.getLocale()));
        pageConfig.addAllSiteLabels(FAVOURITES_BUNDLE);

        addFavouritesButton(pageConfig);
    }

    private void addFavouritesButton(PageCompositionHelper pageConfig) {
        try {
            pageConfig.addProperty(ALLOW_FAVOURITE, isFavouritable(pageConfig.getPage()));
            pageConfig.addAllSiteLabels(FAVOURITES_BUTTON_BUNDLE);
            logger.debug("Favourites button enabled for page {}", pageConfig.getPage().getPath());
        } catch (PageCompositionException e) {
            pageConfig.addProperty(ALLOW_FAVOURITE, false);
            logger.error("Failed to set favourites boolean. Defaulting to false.", e);
        }
    }

    private boolean isFavouritable(Page page) {
        return !(page instanceof General) || GeneralContentComponent.STANDARD.equals(((General) page).getTheme());
    }
}
