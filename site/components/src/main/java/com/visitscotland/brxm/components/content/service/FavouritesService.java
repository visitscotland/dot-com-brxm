package com.visitscotland.brxm.components.content.service;

import com.visitscotland.brxm.components.content.GeneralContentComponent;
import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class FavouritesService {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesService.class);

    static final String FAVOURITES_BUNDLE = "favourites";
    static final String FAVOURITES_BUTTON_BUNDLE = "favourites-button";
    static final String ALLOW_FAVOURITE = "allow-favourite";
    static final String IS_SHARE_PAGE = "is-favourites-share-page";
    static final String FAVOURITES_PAGE_ENABLED = "feature.favourites.enable";
    static final String FAVOURITES_SITE_URL = "feature.favourites.url";
    static final String FAVOURITES_BASE_URL = "feature.favourites.share-service-base-url";
    static final String FAVOURITES_SHARE_URL = "feature.favourites.share-url";

    private final SiteProperties properties;
    private final HippoUtilsService hippoUtilsService;

    public FavouritesService(SiteProperties properties, HippoUtilsService hippoUtilsService) {
        this.properties = properties;
        this.hippoUtilsService = hippoUtilsService;
    }

    public void applyConfiguration(HstRequest request, PageCompositionHelper pageConfig) {
        pageConfig.addProperty(FAVOURITES_PAGE_ENABLED, true);
        pageConfig.addProperty(FAVOURITES_SITE_URL, properties.getFavouritesUrl(request.getLocale()));
        pageConfig.addProperty(FAVOURITES_BASE_URL, properties.getFavouritesBaseUrl());
        try {
            pageConfig.addProperty(FAVOURITES_SHARE_URL, hippoUtilsService.createUrlFromNode(
                    properties.getFavouritesShareUrl(), true, true));
            if (request.getPathInfo() != null) {
                pageConfig.addProperty(IS_SHARE_PAGE, properties.getFavouritesShareUrl().contains(request.getPathInfo()));
            } else {
                pageConfig.addProperty(IS_SHARE_PAGE, false);
            }
        } catch (Exception e) {
            logger.warn("An exception occurred while trying to generate share-url in FavouritesService: ", e);
            pageConfig.addProperty(FAVOURITES_SHARE_URL, "share-url test");
            pageConfig.addProperty(IS_SHARE_PAGE, false);
        }


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

    public boolean isFavouritable(HippoBean page) {
        return  page instanceof Page
                && (!(page instanceof General)
                        || GeneralContentComponent.STANDARD.equals(((General) page).getTheme())
                        || GeneralContentComponent.TOP_LEVEL.equals(((General) page).getTheme()));
    }
}
