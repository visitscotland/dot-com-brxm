package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class SearchWidgetMapper extends ModuleMapper<DevModule, SearchWidgetModule>{

    private static final String SEARCH_WIDGET_EVENTS = "search-widget-events";
    private final ResourceBundleService bundle;

    public SearchWidgetMapper(ResourceBundleService bundle) {
        this.bundle = bundle;
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
        module.setTitle(bundle.getResourceBundle(document.getBespoken(), "title", locale));
        module.setDescription(bundle.getResourceBundle(document.getBespoken(), "description", locale));
        module.setPlaceholder(bundle.getResourceBundle(document.getBespoken(), "placeholder", locale));
        module.setButton(bundle.getResourceBundle(document.getBespoken(), "button", locale));
        if (SEARCH_WIDGET_EVENTS.equals(document.getBespoken())) {
            module.setMainCategory("events");
            module.setSubcategories(bundle.getAllLabels("search-events-filters", locale));
        } else {
            module.setCategories(bundle.getAllLabels("search-categories", locale));
        }

        return module;
    }
}
