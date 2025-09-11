package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.comparator.BrxmWrapperException;
import com.visitscotland.brxm.comparator.ComparatorMapper;
import com.visitscotland.brxm.comparator.VsContractException;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.hippobeans.SimpleDevModule;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionException;
import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.MissingResourceException;

@Component
public class DevModuleMapper extends  ModuleMapper<DevModule, Module<DevModule>> {

    private static final String OBS_BUNDLE = "online-booking-system-comparator";
    private static final String OBS_MODULE = "online-booking-system";
    private static final String FORMS_BUNDLE = "forms";

    private final ComparatorMapper comparisonMapper;



    public DevModuleMapper(ComparatorMapper comparisonMapper) {
        this.comparisonMapper = comparisonMapper;
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

        try {
            if (document.getBespoken().equals(OBS_MODULE)) {
                var module = comparisonMapper.map(document);
                compositionHelper.addAllSiteLabels(OBS_BUNDLE);
                compositionHelper.addAllSiteLabels(FORMS_BUNDLE);

                return module;
            }
        } catch (VsContractException | BrxmWrapperException e) {
            throw new PageCompositionException(document.getPath(), "The bespoken Dev module could not be generated", e);
        }

        throw new PageCompositionException(document.getPath(), "The implementation of this module is not ready");
    }
}
