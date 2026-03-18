package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.comparator.BrxmWrapperException;
import com.visitscotland.brxm.comparator.ComparatorMapper;
import com.visitscotland.brxm.comparator.VsContractException;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.hippobeans.SimpleDevModule;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.MissingResourceException;

@Component
public class DevModuleMapper extends ModuleMapper<DevModule, Module<DevModule>> {

    private static final Logger logger = LoggerFactory.getLogger(DevModuleMapper.class);

    private static final String OBS_BUNDLE = "online-booking-system-comparator";
    private static final String OBS_MODULE = "online-booking-system";
    private static final String CARBON_CALCULATOR = "carbon-calculator";
    private static final String SEARCH_WIDGET = "search-widget";
    private static final String SEARCH_WIDGET_EVENTS = "search-widget-events";
    private static final String FAVOURITES_LIST = "favourites-list";
    private static final String API = "api";
    private static final String FAVOURITES = "favourites";
    private static final String FORMS_BUNDLE = "forms";
    private static final String FAVOURITES_API = "/site/api/favourites";

    private final ComparatorMapper comparisonMapper;
private final SearchWidgetMapper searchWidgetMapper;

    public DevModuleMapper(ComparatorMapper comparisonMapper,SearchWidgetMapper searchWidgetMapper) {
        this.comparisonMapper = comparisonMapper;
        this.searchWidgetMapper = searchWidgetMapper;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        // Each implementation of the Dev Module has its own label requirements
    }

    @Override
    Module<DevModule> map(DevModule document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        if (Contract.isEmpty(document.getBespoken())) {
            return new SimpleDevModule(document);
        }

        logger.debug("Bespoke module: {}", document.getBespoken());

        try {
            if (OBS_MODULE.equals(document.getBespoken())) {
                var module = comparisonMapper.map(document);
                compositionHelper.addAllSiteLabels(OBS_BUNDLE);
                compositionHelper.addAllSiteLabels(FORMS_BUNDLE);

                return module;
            } else if (CARBON_CALCULATOR.equals(document.getBespoken())) {
                compositionHelper.addAllSiteLabels(CARBON_CALCULATOR);
                return new SimpleDevModule(document, document.getBespoken());
            } else if (SEARCH_WIDGET_EVENTS.equals(document.getBespoken()) || SEARCH_WIDGET.equals(document.getBespoken())  ) {
                return searchWidgetMapper.map(document,compositionHelper);
            } else if (FAVOURITES_LIST.equals(document.getBespoken())) {
                compositionHelper.addProperty(API, FAVOURITES_API);
                compositionHelper.addAllSiteLabels(FAVOURITES);
                return new SimpleDevModule(document, document.getBespoken());
            }


        } catch (VsContractException | BrxmWrapperException e) {
            throw new PageCompositionException(document.getPath(), "The bespoken Dev module could not be generated", e);
        }

        throw new PageCompositionException(document.getPath(), "The implementation of this module is not ready");
    }
}
