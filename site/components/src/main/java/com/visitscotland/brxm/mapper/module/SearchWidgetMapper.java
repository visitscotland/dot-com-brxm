package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.components.content.service.CludoService;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Component
public class SearchWidgetMapper extends ModuleMapper<DevModule, SearchWidgetModule>{


    private static final String SEARCH_CATEGORIES = "main-category-filters";
    private static final String SEARCH_WIDGET_EVENTS = "search-widget-events";
    private static final String SEARCH_EVENTS_SUBCATEGORIES = "search-events-subcategories";
    private static final String SEARCH_EVENTS_FILTERS_VALUE_LIST = "vs-events-filters";
    private static final String SEARCH_EVENTS_FILTERS_DATES = "search-events-dates";
    private static final String SEARCH_EVENTS_FILTERS_LOCATIONS = "search-events-locations";

    private final ResourceBundleService bundle;
    private final HippoUtilsService hippoUtilsService;
    private final ObjectMapper mapper;
    private final CludoService cludoService;

    public SearchWidgetMapper(ResourceBundleService bundle, HippoUtilsService hippoUtilsService, ObjectMapper mapper, CludoService cludoService) {
        this.bundle = bundle;
        this.hippoUtilsService = hippoUtilsService;
        this.mapper = mapper;
        this.cludoService = cludoService;
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
            addEventsFilters(locale, compositionHelper, module);
        } else {
            module.setCategories(bundle.getAllLabels(SEARCH_CATEGORIES, locale));
            if (cludoService.isSearchResultsPage()) {
                addEventsFilters(locale, compositionHelper, module);
            }
        }

        return module;
    }

    private void addEventsFilters(Locale locale, PageCompositionHelper compositionHelper, SearchWidgetModule module) {
        module.setSubcategories(compositionHelper.addValueListLabels(SEARCH_EVENTS_SUBCATEGORIES, hippoUtilsService.getValueMap(SEARCH_EVENTS_FILTERS_VALUE_LIST), "search-events-filters"));
        ObjectNode filters = mapper.createObjectNode();
        cludoService.addFilterJson("vs-events-filters-dates","when" ,SEARCH_EVENTS_FILTERS_DATES, filters, locale);
        module.setFilters(cludoService.addFilterJson("vs-events-filters-locations","postcodeareas", SEARCH_EVENTS_FILTERS_LOCATIONS, filters, locale));
    }
}
