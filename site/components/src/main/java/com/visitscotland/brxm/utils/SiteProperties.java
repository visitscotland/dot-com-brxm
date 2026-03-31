package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.visitscotland.brxm.utils.SitePropertyKeys.*;

@Component
public class SiteProperties extends Properties {

    private final CMSProperties cmsProperties;

    public Boolean getFeatureHeroSection() {
        return readBoolean(FEATURE_HERO_SECTION);
    }

    public SiteProperties(ResourceBundleService bundle, HippoUtilsService utils, CMSProperties cmsProperties, EnvironmentManager envrionmentManager) {
        super(bundle, utils, envrionmentManager);
        this.cmsProperties = cmsProperties;
    }

    @Override
    public String getDefaultConfig() {
        return DEFAULT_CONFIG;
    }

    @Override
    public String getOverrideProperty() {
        return OVERRIDE_PROPERTY;
    }

    public String getConvertToRelative() {
        if (cmsProperties.isRelativeURLs()) {
            return readString(CONVERT_TO_RELATIVE);
        } else {
            return "";
        }
    }
    public Optional<String> getGlobalSearchURL(Locale locale) {
        return readOptionalString(GLOBAL_SEARCH_PATH, locale);
    }

    public Optional<String> getCludoCustomerId() {
        return readOptionalString(CLUDO_CUSTOMER_ID);
    }

    public Optional<String> getCludoEngineId(Locale locale) {
        return readOptionalString(CLUDO_ENGINE_ID, locale);
    }

    public Optional<String> getCludoExperienceId() {
        return readOptionalString(CLUDO_EXPERIENCE_ID);
    }

    public String getChannelOrder(){
        return readString(CHANNEL_ORDER);
    }
    public String getIknowCommunityUrl() {
        return readString(IKNOW_COMMUNITY_URL);
    }
    public String getIknowCommunityTaggedDiscussion() {
        return readString(IKNOW_COMMUNITY_TAGGED_DISCUSSION);
    }
    public String getSiteSkiSection() {
        return readString(PATH_SKI_SECTION);
    }
    public String getCampaignSection() {
        return readString(PATH_CAMPAIGN_SECTION);
    }
    // Where is this in use?
    public String getGoogleMapsApiKey() {
        return readString(GOOGLE_MAPS_API_KEY);
    }

    public String getSiteNewsletter() {
        return readString(PATH_NEWSLETTER);
    }
    public String getSiteICentre() {
        return readString(PATH_ICENTRE);
    }
    public String getSiteMap() {
        return readString(PATH_MAP);
    }
    public String getSiteBanner() {
        return readString(PATH_BANNER);
    }
    public String getGtmContainerId (){
        return readString(GTM_CONTAINER_ID);
    }
    public String getGtmIsProduction() {
        return readString(GTM_IS_PRODUCTION);
    }
    public String getGtmPreviewQueryString() {
        return readString(GTM_PREVIEW_QUERY_STRING);
    }

    // enable/disable favourites for each site
    public boolean isFavouritesEnabled(Locale locale) {
        return readBoolean(FAVOURITES_PAGE_ENABLED, locale);
    }

    public String getFavouritesUrl() {
        return readString(FAVOURITES_PAGE_URL);
    }
    public String getFavouritesUrl(Locale locale) {
        return readString(FAVOURITES_PAGE_URL, locale);
    }

    public String getFavouritesEndpoint() { return readString(FAVOURITES_PAGE_ENDPOINT); }

    public boolean isTableOfContentsEnabled() {
        return readBoolean(TABLE_OF_CONTENTS_ENABLED);
    }

    public boolean isGlobalSearchEnabled() {
        return readBoolean(GLOBAL_SEARCH_ENABLED);
    }

    public boolean isGlobalSearchDmsBased() {
        return readBoolean(GLOBAL_SEARCH_DMS_BASED);
    }

    public Optional<String> getGlobalSearchEventsEndpoint() {
        return readOptionalString(GLOBAL_SEARCH_EVENTS_ENDPOINT);
    }
    public Optional<String> getGlobalSearchLogic() {
        return readOptionalString(GLOBAL_SEARCH_LOGIC);
    }

    public List<String> getInternalSites() {
        String sites = readString(INTERNAL_SITES);
        if (!Contract.isEmpty(sites)){
            return Arrays.stream(sites.trim().split("\\s*,\\s*")).filter(Predicate.not(String::isEmpty)).collect(Collectors.toUnmodifiableList());
        }
        return Collections.emptyList();
    }

    @Deprecated
    public String getFormsMarketoUrl() {
        return readString(FORMS_MARKETO_URL);
    }

    /**
     * Enable the Search Widget in the Homepage
     * @return
     */
    public Boolean getFeatureSearchWidget() {
        return readBoolean(SEARCH_WIDGET);
    }

    @Deprecated
    public String getFormsRecaptcha() {
        return readString(FORMS_RECAPTCHA);
    }

    @Deprecated
    public String getFormsMarketoMunchkin() {
        return readString(FORMS_MARKETO_MUNCHKIN);
    }

    @Deprecated
    public String getFormsMarketoScript() {
        return readString(FORMS_MARKETO_SCRIPT);
    }

    public String getSiteId() {
        return readOptionalString(SITE_ID).orElse("");
    }

    public String getFormBregLegalBasisText() {
        return readString(FORM_BREG_LEGAL_BASIS_TEXT);
    }
    public Boolean isFormBregLegalBasisEnabled() {
        return readBoolean(FORM_BREG_LEGAL_BASIS_ENABLE);
    }

    public String getDownloadExtensions() {
        return readString(DOWNLOAD_EXTENSIONS);
    }

    public int getEventsListingsPageSize() {
        int size = readInteger(EVENTS_LISTINGS_PAGE_SIZE);
        return size > 0 ? size : 10;
    }

    public Integer getSkiTimeout() {
        return readInteger(SKI_TIMEOUT);
    }
}
