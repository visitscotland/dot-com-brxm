package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Component
public class SearchWidgetMapper extends ModuleMapper<DevModule, SearchWidgetModule>{


    private static final String SEARCH_CATEGORIES = "main-category-filters";
    private static final String SEARCH_WIDGET_EVENTS = "search-widget-events";
    private static final String SEARCH_EVENTS_SUBCATEGORIES = "search-events-subcategories";
    private static final String SEARCH_EVENTS_FILTERS_DATES = "search-events-dates";
    private static final String SEARCH_EVENTS_FILTERS_LOCATIONS = "search-events-locations";

    private final ResourceBundleService bundle;
    private final HippoUtilsService hippoUtilsService;
    private final ObjectMapper mapper;

    public SearchWidgetMapper(ResourceBundleService bundle, HippoUtilsService hippoUtilsService, ObjectMapper mapper) {
        this.bundle = bundle;
        this.hippoUtilsService = hippoUtilsService;
        this.mapper = mapper;
    }

    /**
     * Adds labels required for the module to the {@link PageCompositionHelper}.
     *
     * <p>Currently no labels are added as the search widget only requires
     * minimal configuration in the first implementation iteration.</p>
     *
     * @param compositionHelper helper used during page composition
     * @throws MissingResourceException if required resource bundles are missing
     */
    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        //Search widget does not need all labels for this first iteration but the labels will be needed once the
        // resource bundle is ready and we can move the homepage widget to this solution
    }

    /**
     * Maps a {@link DevModule} document to a {@link SearchWidgetModule}.
     *
     * @param document the CMS document representing the module
     * @param compositionHelper helper containing contextual information
     * @return mapped {@link SearchWidgetModule}
     * @throws PageCompositionException if mapping fails
     */
    @Override
    SearchWidgetModule map(DevModule document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return createModule(document, compositionHelper.getLocale(), compositionHelper);
    }

    /**
     * Creates a {@link SearchWidgetModule} from the given CMS document.
     *
     * <p>Localised labels are retrieved using the {@link ResourceBundleService}.
     * Depending on the {@code bespoken} configuration, either event filters
     * or standard search categories will be populated.</p>
     *
     * @param document CMS document containing widget configuration
     * @param locale the locale used to resolve labels
     * @return populated {@link SearchWidgetModule}
     */
    public SearchWidgetModule createModule(DevModule document, Locale locale, PageCompositionHelper compositionHelper) {

        SearchWidgetModule module = new SearchWidgetModule(document, document.getBespoken());
        ResourceBundle resourceBundle = bundle.getResourceBundle(document.getBespoken(), locale);
        module.setTitle(resourceBundle.getString( "title"));
        module.setDescription(resourceBundle.getString( "description"));
        module.setPlaceholder(resourceBundle.getString( "placeholder"));
        module.setButton(resourceBundle.getString( "button"));

        if (SEARCH_WIDGET_EVENTS.equals(document.getBespoken())) {
            module.setMainCategory("events");
            //TODO review addAllLabelsSpecificName and move to Cludoservice if possible
            compositionHelper.addAllLabelsSpecificName(SEARCH_EVENTS_SUBCATEGORIES, "search-events-filters");

            ObjectNode filters = mapper.createObjectNode();
            addFilterJson("vs-events-filters-dates","when" ,SEARCH_EVENTS_FILTERS_DATES, filters, locale);
            module.setFilters(addFilterJson("vs-events-filters-locations","postcodeareas", SEARCH_EVENTS_FILTERS_LOCATIONS, filters, locale));
        } else {
            module.setCategories(bundle.getAllLabels(SEARCH_CATEGORIES, locale));
            /*  TODO if (isSearchResultsPage) then we need to load the event fiters:
            compositionHelper.addAllLabelsSpecificName(SEARCH_EVENTS_SUBCATEGORIES, "search-events-filters");

            ObjectNode filters = mapper.createObjectNode();
            addFilterJson("vs-events-filters-dates","when" ,SEARCH_EVENTS_FILTERS_DATES, filters, locale);
            module.setFilters(addFilterJson("vs-events-filters-locations","postcodeareas", SEARCH_EVENTS_FILTERS_LOCATIONS, filters, locale));
            */

        }

        return module;
    }

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
                filterSubnodes.put("id", entry.getValue());
                String resourceBundleLabel = bundle.getResourceBundle(resourceBundleId, entry.getKey(), locale);
                filterSubnodes.put("label", Contract.isEmpty(resourceBundleLabel) ? entry.getKey() : resourceBundleLabel);
                filterType.add(filterSubnodes);
            }
        }

        filters.set(rootNode, filterType);

        return filters;
    }
}
