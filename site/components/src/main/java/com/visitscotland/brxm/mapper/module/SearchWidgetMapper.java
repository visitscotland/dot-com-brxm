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
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Component
public class SearchWidgetMapper extends ModuleMapper<DevModule, SearchWidgetModule>{

    private static final String SEARCH_WIDGET_EVENTS = "search-widget-events";
    private final ResourceBundleService bundle;
    private final HippoUtilsService hippoUtilsService;

    public SearchWidgetMapper(ResourceBundleService bundle, HippoUtilsService hippoUtilsService) {
        this.bundle = bundle;
        this.hippoUtilsService = hippoUtilsService;
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
        return createModule(document, compositionHelper.getLocale());
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
    public SearchWidgetModule createModule(DevModule document, Locale locale) {

        SearchWidgetModule module = new SearchWidgetModule(document, document.getBespoken());
        ResourceBundle resourceBundle = bundle.getResourceBundle(document.getBespoken(), locale);
        module.setTitle(resourceBundle.getString( "title"));
        module.setDescription(resourceBundle.getString( "description"));
        module.setPlaceholder(resourceBundle.getString( "placeholder"));
        module.setButton(resourceBundle.getString( "button"));

        if (SEARCH_WIDGET_EVENTS.equals(document.getBespoken())) {
            module.setMainCategory("events");
            module.setSubcategories(bundle.getAllLabels("search-events-filters", locale));
            module.setFilters(createFiltersJson("vs-events-filters-dates","when" ,locale));
        } else {
            module.setCategories(bundle.getAllLabels("search-categories", locale));
        }

        return module;
    }

    /**
     * Creates a JSON structure representing search filters.
     *
     * <p>The filters are built dynamically from a value map retrieved via
     * {@link HippoUtilsService}. Each entry is converted into a JSON object
     * containing an {@code id} and a localised {@code label}.</p>
     *
     * @param filtersId identifier used to retrieve filter values and labels
     * @param rootNode the root JSON node name under which filters are grouped
     * @param locale the locale used to resolve filter labels
     * @return a {@link JsonNode} representing the filter structure
     */
    public JsonNode createFiltersJson (String filtersId, String rootNode ,Locale locale) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode filters = objectMapper.createObjectNode();
        ArrayNode filterType = objectMapper.createArrayNode();
        Map<String, String> filtersMap = hippoUtilsService.getValueMap(filtersId);

        if (filtersMap != null) {
            for (Map.Entry<String, String> entry : filtersMap.entrySet()) {
                ObjectNode filterSubnodes = objectMapper.createObjectNode();
                filterSubnodes.put("id", entry.getValue());
                //TODO try to order nodes based on prox dates
                filterSubnodes.put("label", bundle.getResourceBundle(filtersId, entry.getKey(), locale));
                filterType.add(filterSubnodes);
            }
        }

        filters.set(rootNode, filterType);

        return filters;
    }
}
