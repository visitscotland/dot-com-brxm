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

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        //Search widget does not need all labels
    }

    @Override
    SearchWidgetModule map(DevModule document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return createModule(document, compositionHelper.getLocale());
    }

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
