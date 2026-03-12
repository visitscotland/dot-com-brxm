package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.brxm.utils.SitePropertyKeys;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CludoService {

    private static final Logger logger = LoggerFactory.getLogger(CludoService.class);

    //RefID from HST Sitemap items
    public static final String ROOT_REF_ID = "root";
    public static final String SEARCH_PAGE_REF_ID = "search-page";

    private static final String SEARCH_EVENTS_CATEGORIES = "content.categories";
    private static final String SEARCH_EVENTS_FILTERS = "search-events-filters";
    private static final String SEARCH_FILTERS = "search-categories";

    public static final String INCLUDE_SEARCH_WIDGET = "searchWidget";
    public static final String SEARCH_LOGIC = "cludoApiOperator";

    private final SiteProperties properties;

    public CludoService(SiteProperties properties) {
        this.properties = properties;
    }

    public void applyConfiguration(HstRequest request, PageCompositionHelper pageConfig) {
        if (properties.isGlobalSearchDmsBased()) {
            //TODO: This method will be removed once the DMS is retired
            pageConfig.addProperty("dms-based", true);
            getSearchResultsURL(request).ifPresent(v -> pageConfig.addProperty("global-search.path", v));
            setGeneralCludoConfiguration(pageConfig);
        } else {
            applyGlobalSearchConfiguration(request, pageConfig);
        }
    }

    /**
     * Set General Cludo Configuration for the Global Search
     * @param pageConfig the page composition helper to add configuration properties to
     */
    private void setGeneralCludoConfiguration(PageCompositionHelper pageConfig) {
        properties.getCludoCustomerId().ifPresent(v -> pageConfig.addProperty(SitePropertyKeys.CLUDO_CUSTOMER_ID, v));
        properties.getCludoExperienceId().ifPresent(v -> pageConfig.addProperty(SitePropertyKeys.CLUDO_EXPERIENCE_ID, v));
        properties.getCludoEngineId(pageConfig.getLocale()).ifPresent(v -> pageConfig.addProperty(SitePropertyKeys.CLUDO_ENGINE_ID, v));
        pageConfig.addProperty("language", pageConfig.getLocale().getLanguage());
    }

    /**
     * Apply the site search configuration to the pages where the search component is available
     * @param request the current HST request
     * @param pageConfig the page composition helper to add configuration properties to
     */
    private void applyGlobalSearchConfiguration(HstRequest request, PageCompositionHelper pageConfig) {
        final boolean isSearchResultsPage = isSearchResultsPage(request);
        final boolean isHomepage = isHomepage(request);

        getSearchResultsURL(request).ifPresent(v -> pageConfig.addProperty("site-search.path", v));
        properties.getGlobalSearchURL(request.getLocale()).ifPresent(v -> pageConfig.addProperty(SitePropertyKeys.GLOBAL_SEARCH_PATH, v));
        pageConfig.addProperty(INCLUDE_SEARCH_WIDGET, isHomepage && properties.getFeatureSearchWidget());

        if (isHomepage || isSearchResultsPage) {
            setGeneralCludoConfiguration(pageConfig);
            properties.getGlobalSearchEventsEndpoint().ifPresentOrElse(
                    v -> pageConfig.addProperty("events-endpoint", v),
                    () -> logger.error("The URL for the events Endpoint hasn't been defined"));

            pageConfig.addAllSiteLabels(SEARCH_FILTERS);

            if (isSearchResultsPage) {
                pageConfig.addAllSiteLabels(SEARCH_EVENTS_FILTERS);
                pageConfig.addAllSiteLabels(SEARCH_EVENTS_CATEGORIES);
                properties.getGlobalSearchLogic().ifPresent(v -> pageConfig.addProperty(SEARCH_LOGIC, v));
            }
        }
    }

    /**
     * Creates and exposes a locale-aware search page link in the HST request context.
     * <br>
     * This method generates an HstLink for the sitemap item with refId "search-page"
     * and stores it as a request attribute named "searchLink".
     * <br>
     * Use this to ensure the search URL is correctly resolved for the current
     * locale and mount, avoiding hardcoded or relative paths in templates
     *
     * @param request the current HstRequest
     */
    private Optional<String> getSearchResultsURL(final HstRequest request) {
        HstRequestContext requestContext = request.getRequestContext();

        HstLink link = requestContext.getHstLinkCreator()
                .createByRefId(SEARCH_PAGE_REF_ID, requestContext.getResolvedMount().getMount());

        if (link != null) {
            // Convert the link to a URL and make it available to the template
            return Optional.of(link.toUrlForm(requestContext, false));
        } else {
            logger.warn("Could not resolve link for siteMapItemRefId 'search-page'. Check HST sitemap configuration.");
        }

        return Optional.empty();
    }

    private boolean isSearchResultsPage(HstRequest request) {
        return SEARCH_PAGE_REF_ID.equals(
                request.getRequestContext()
                        .getResolvedSiteMapItem()
                        .getHstSiteMapItem()
                        .getRefId()
        );
    }

    private boolean isHomepage (HstRequest request){
        return ROOT_REF_ID.equals(request.getRequestContext().getResolvedSiteMapItem().getHstSiteMapItem().getRefId());
    }
}
