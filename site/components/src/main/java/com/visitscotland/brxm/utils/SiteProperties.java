package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class SiteProperties extends Properties {

    static final String DEFAULT_CONFIG = "default.site.config";
    static final String OVERRIDE_PROPERTY = "visitscotland:siteProperties";

    static final String SITE_ID = "site-id";
    static final String IKNOW_COMMUNITY_URL = "iknow-community.url";
    static final String IKNOW_COMMUNITY_TAGGED_DISCUSSION = "iknow-community.tagged-discussion";

    static final String CHANNEL_ORDER = "seo.alternate-link-locale-order";
    static final String GLOBAL_SEARCH_PATH = "global-search.path";
    static final String CLUDO_CUSTOMER_ID = "cludo.customer-id";
    static final String CLUDO_ENGINE_ID = "cludo.engine-id";
    static final String CLUDO_EXPERIENCE_ID = "cludo.experience-id";


    //Environment
    static final String INTERNAL_SITES = "links.internal-sites";
    static final String CONVERT_TO_RELATIVE = "links.convert-to-relative";
    static final String DOWNLOAD_EXTENSIONS = "links.download.extensions";


    //Page References
    private static final String PATH_SKI_SECTION = "site.path.ski-landing";
    private static final String PATH_CAMPAIGN_SECTION = "site.path.campaigns";
    private static final String PATH_NEWSLETTER = "site.path.newsletter";
    private static final String PATH_ICENTRE = "site.path.icentre-landing";

    //Modules References
    private static final String PATH_BANNER = "site.path.banner";
    static final String EVENTS_LISTINGS_PAGE_SIZE = "events-listings.page-size";
    private static final String PRODUCTS_SEARCH_ENABLED = "feature.products-search.enable";
    private static final String TABLE_OF_CONTENTS_ENABLED = "feature.table-of-contents.enable";
    private static final String GLOBAL_SEARCH_ENABLED = "feature.global-search.enable";

    //GTM Properties
    public static final String GTM_CONTAINER_ID = "gtm.container-id";
    public static final String GTM_IS_PRODUCTION = "gtm.is-production";
    public static final String GTM_PREVIEW_QUERY_STRING = "gtm.preview-query-string";

    //Form Properties
    static final String FORMS_RECAPTCHA = "form.recaptcha-key";
    static final String FORMS_MARKETO_URL = "form.maketo.instance-url";
    static final String FORMS_MARKETO_MUNCHKIN = "form.marketo.munchkin";
    static final String FORMS_MARKETO_SCRIPT = "form.marketo.script";
    static final String FORMS_MARKETO_IS_PRODUCTION = "form.is-production";
    static final String FORM_BREG_LEGAL_BASIS_TEXT = "form.breg.legal-basis.text";
    static final String FORM_BREG_LEGAL_BASIS_ENABLE = "form.breg.legal-basis.enable";

    private final CMSProperties cmsProperties;
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
    public Optional<String> getGlobalSearchURL() {
        return readOptionalString(GLOBAL_SEARCH_PATH);
    }

    public Optional<String> getCludoCustomerId() {
        return readOptionalString(CLUDO_CUSTOMER_ID);
    }

    public Optional<String> getCludoEngineId() {
        return readOptionalString(CLUDO_ENGINE_ID);
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

    public String getSiteNewsletter() {
        return readString(PATH_NEWSLETTER);
    }
    public String getSiteICentre() {
        return readString(PATH_ICENTRE);
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

    public boolean isProductSearchEnabled() {
        return readBoolean(PRODUCTS_SEARCH_ENABLED);
    }

    public boolean isTableOfContentsEnabled() {
        return readBoolean(TABLE_OF_CONTENTS_ENABLED);
    }

    public boolean isGlobalSearchEnabled() {
        return readBoolean(GLOBAL_SEARCH_ENABLED);
    }

    public List<String> getInternalSites() {
        String sites = readString(INTERNAL_SITES);
        if (!Contract.isEmpty(sites)){
            // TODO Java 11: Replace & Test: Arrays.stream(sites.trim().split("\\s*,\\s*")).filter(Predicate.not(String::isEmpty)).collect(Collectors.toUnmodifiableList());
            return Arrays.stream(sites.trim().split("\\s*,\\s*")).filter(((Predicate<String>) String::isEmpty).negate()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Deprecated
    public String getFormsMarketoUrl() {
        return readString(FORMS_MARKETO_URL);
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

    public Boolean getFormsMarketoIsProduction() {
        return readBoolean(FORMS_MARKETO_IS_PRODUCTION);
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

}
