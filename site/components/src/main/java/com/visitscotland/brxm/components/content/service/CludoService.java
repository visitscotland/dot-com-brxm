package com.visitscotland.brxm.components.content.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.brxm.utils.SitePropertyKeys;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Component
public class CludoService {

    private static final Logger logger = LoggerFactory.getLogger(CludoService.class);

    //RefID from HST Sitemap items
    public static final String ROOT_REF_ID = "root";
    public static final String SEARCH_PAGE_REF_ID = "search-page";


    private static final String SEARCH_CARDS_CATEGORIES = "result-cards-content-types";
    private static final String SEARCH_EVENTS_FILTERS = "vs-events-filters";

    private static final String SEARCH_EVENTS_FILTERS_LABELS = "search-events-subcategories";
    private static final String SEARCH_FILTERS_LABELS = "main-category-filters";

    private static final String SEARCH_EVENTS_FILTERS_DATES = "search-events-dates";
    private static final String SEARCH_EVENTS_FILTERS_LOCATIONS = "search-events-locations";


    public static final String INCLUDE_SEARCH_WIDGET = "searchWidget";
    public static final String SEARCH_LOGIC = "cludoApiOperator";

    private final SiteProperties properties;
    private final HippoUtilsService hippoUtilsService;
    private final ResourceBundleService bundle;
    private final ObjectMapper mapper;

    public CludoService(SiteProperties properties, HippoUtilsService hippoUtilsService, ResourceBundleService bundle, ObjectMapper mapper) {
        this.properties = properties;
        this.hippoUtilsService = hippoUtilsService;
        this.bundle = bundle;
        this.mapper = mapper;
    }

    public void applyConfiguration(HstRequest request, PageCompositionHelper pageConfig) {
        //This property is still used for bsh and be to apply search configuration to all pages.
        //TODO: Rename this property. It is misleading
        if (properties.isGlobalSearchDmsBased()) {
            //TODO: The following property is not in use for the front-end. Remove
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
        final boolean isSearchResultsPage = isSearchResultsPage();
        final boolean isHomepage = isHomepage(request);

        getSearchResultsURL(request).ifPresent(v -> pageConfig.addProperty("site-search.path", v));
        properties.getGlobalSearchURL(request.getLocale()).ifPresent(v -> pageConfig.addProperty(SitePropertyKeys.GLOBAL_SEARCH_PATH, v));
        pageConfig.addProperty(INCLUDE_SEARCH_WIDGET, isHomepage && properties.getFeatureSearchWidget());

        if (isHomepage || isSearchResultsPage) {
            setGeneralCludoConfiguration(pageConfig);
            properties.getGlobalSearchEventsEndpoint().ifPresentOrElse(
                    v -> pageConfig.addProperty("events-endpoint", v),
                    () -> logger.error("The URL for the events Endpoint hasn't been defined"));

            pageConfig.addAllLabelsSpecificName(SEARCH_FILTERS_LABELS,"search-categories");

            if (isSearchResultsPage) {
                pageConfig.addAllLabelsSpecificName(SEARCH_CARDS_CATEGORIES, "content.categories");

                properties.getGlobalSearchLogic().ifPresent(v -> pageConfig.addProperty(SEARCH_LOGIC, v));

                //TODO duplicate in mapper refactor to use widget and 1 solution pageConfig.addValueListLabels(SEARCH_EVENTS_SUBCATEGORIES, hippoUtilsService.getValueMap(SEARCH_EVENTS_FILTERS_VALUE_LIST), "search-events-filters");
                pageConfig.addProperty("search-events-filters", pageConfig.addValueListLabels(SEARCH_EVENTS_FILTERS_LABELS, hippoUtilsService.getValueMap(SEARCH_EVENTS_FILTERS), "search-events-filters"));

                ObjectNode filters = mapper.createObjectNode();
                addFilterJson("vs-events-filters-dates","when" ,SEARCH_EVENTS_FILTERS_DATES, filters, pageConfig.getLocale());
                addFilterJson("vs-events-filters-locations","postcodeareas", SEARCH_EVENTS_FILTERS_LOCATIONS, filters, pageConfig.getLocale());
                pageConfig.addProperty("filters", filters);
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

    public boolean isSearchResultsPage() {
        return SEARCH_PAGE_REF_ID.equals(
                RequestContextProvider.get()
                        .getResolvedSiteMapItem()
                        .getHstSiteMapItem()
                        .getRefId()
        );
    }

    private boolean isHomepage (HstRequest request){
        return ROOT_REF_ID.equals(request.getRequestContext().getResolvedSiteMapItem().getHstSiteMapItem().getRefId());
    }

    //TODO review if this method goes here
    /**
     * Creates a JSON structure representing search filters.

     * @param valueListId identifier used to retrieve filter values and labels
     * @param rootNode the root JSON node name under which filters are grouped
     * @param resourceBundleId the resource bundle name under which filters are grouped
     * @param locale the locale used to resolve filter labels
     * @return a {@link JsonNode} representing the filter structure
     */
    public JsonNode addFilterJson (String valueListId, String rootNode, String resourceBundleId, ObjectNode filters, Locale locale) {
        ArrayNode filterType = mapper.createArrayNode();
        Map<String, String> filtersMap = hippoUtilsService.getValueMap(valueListId);

        if (filtersMap != null) {
            for (Map.Entry<String, String> entry : filtersMap.entrySet()) {
                ObjectNode filterSubnodes = mapper.createObjectNode();
                filterSubnodes.put("id", entry.getKey());
                filterSubnodes.put("parameter", entry.getValue());
                String resourceBundleLabel = bundle.getResourceBundle(resourceBundleId, entry.getKey(), locale);
                filterSubnodes.put("label", Contract.isEmpty(resourceBundleLabel) ? entry.getKey() : resourceBundleLabel);
                filterType.add(filterSubnodes);
            }
        }

        filters.set(rootNode, filterType);

        return filters;
    }
}
