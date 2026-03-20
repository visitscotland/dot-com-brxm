package com.visitscotland.brxm.utils;

public final class SitePropertyKeys {
    
    private SitePropertyKeys(){}

    public static final String DEFAULT_CONFIG = "default.site.config";
    public static final String OVERRIDE_PROPERTY = "visitscotland:siteProperties";

    public static final String SITE_ID = "site-id";
    public static final String IKNOW_COMMUNITY_URL = "iknow-community.url";
    public static final String IKNOW_COMMUNITY_TAGGED_DISCUSSION = "iknow-community.tagged-discussion";

    public static final String CHANNEL_ORDER = "seo.alternate-link-locale-order";
    public static final String GLOBAL_SEARCH_PATH = "global-search.path";
    public static final String CLUDO_CUSTOMER_ID = "cludo.customer-id";
    public static final String CLUDO_ENGINE_ID = "cludo.engine-id";
    public static final String CLUDO_EXPERIENCE_ID = "cludo.experience-id";


    //Environment
    public static final String INTERNAL_SITES = "links.internal-sites";
    public static final String CONVERT_TO_RELATIVE = "links.convert-to-relative";
    public static final String DOWNLOAD_EXTENSIONS = "links.download.extensions";

    //Page References
    public static final String PATH_SKI_SECTION = "site.path.ski-landing";
    public static final String PATH_CAMPAIGN_SECTION = "site.path.campaigns";
    public static final String PATH_NEWSLETTER = "site.path.newsletter";
    public static final String PATH_ICENTRE = "site.path.icentre-landing";
    public static final String PATH_MAP = "site.path.map";

    //Modules References
    public static final String PATH_BANNER = "site.path.banner";
    public static final String GOOGLE_MAPS_API_KEY = "google-maps.api-key";
    public static final String EVENTS_LISTINGS_PAGE_SIZE = "events-listings.page-size";


    //GTM Properties
    public static final String GTM_CONTAINER_ID = "gtm.container-id";
    public static final String GTM_IS_PRODUCTION = "gtm.is-production";
    public static final String GTM_PREVIEW_QUERY_STRING = "gtm.preview-query-string";

    //Form Properties
    public static final String FORM_BREG_LEGAL_BASIS_ENABLE = "form.breg.legal-basis.enable";
    public static final String FORM_BREG_LEGAL_BASIS_TEXT = "form.breg.legal-basis.text";
    public static final String FORMS_MARKETO_IS_PRODUCTION = "form.is-production";
    public static final String FORMS_RECAPTCHA = "form.recaptcha-key";
    public static final String FORMS_MARKETO_URL = "form.maketo.instance-url";
    public static final String FORMS_MARKETO_MUNCHKIN = "form.marketo.munchkin";
    public static final String FORMS_MARKETO_SCRIPT = "form.marketo.script";

    public static final String SKI_TIMEOUT = "ski.timeout";

    //Feature switch
    public static final String GLOBAL_SEARCH_ENABLED = "feature.global-search.enable";
    public static final String GLOBAL_SEARCH_DMS_BASED = "feature.global-search.dms-based";
    public static final String GLOBAL_SEARCH_EVENTS_ENDPOINT = "feature.global-search.events-endpoint";
    public static final String GLOBAL_SEARCH_LOGIC = "feature.global-search.logic";
    public static final String FAVOURITES_PAGE_ENABLED = "feature.favourites.enable";
    public static final String FAVOURITES_PAGE_URL = "feature.favourites.url";
    public static final String FAVOURITES_PAGE_ENDPOINT = "feature.favourites.endpoint";
    public static final String FEATURE_HERO_SECTION = "feature.hero-section.enable";
    public static final String TABLE_OF_CONTENTS_ENABLED = "feature.table-of-contents.enable";
    public static final String SEARCH_WIDGET = "feature.search-widget.enable";
}
