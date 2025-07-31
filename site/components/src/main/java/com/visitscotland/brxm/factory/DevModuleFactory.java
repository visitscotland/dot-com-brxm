package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.comparator.BrxmWrapperException;
import com.visitscotland.brxm.comparator.ComparatorMapper;
import com.visitscotland.brxm.comparator.VsContractException;
import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.hippobeans.SimpleDevModule;
import com.visitscotland.brxm.model.ErrorModule;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.Locale;
import java.util.Map;

@Controller
public class DevModuleFactory {

    private static final String  OBS_BUNDLE = "online-booking-system-comparator";

    private static final Logger logger = LoggerFactory.getLogger(DevModuleFactory.class);

    private final ComparatorMapper comparisonMapper;
    private final ResourceBundleService bundle;



    public DevModuleFactory(ComparatorMapper comparisonMapper, ResourceBundleService bundle) {
        this.comparisonMapper = comparisonMapper;
        this.bundle = bundle;
    }


    public Module<?> getModule(DevModule document, Map<String, Map<String, String>> labels, Locale locale){
        if (Contract.isEmpty(document.getBespoken())) {
            return new SimpleDevModule(document);
        }

        try {
            if (document.getBespoken().equals("obs-form")) {
                var module = comparisonMapper.map(document);
                labels.put(OBS_BUNDLE, bundle.getAllSiteLabels(OBS_BUNDLE, locale));

                return module;
            }
        } catch (VsContractException | BrxmWrapperException e) {
            logger.error("The bespoken Dev module could not be generated", e);
            return new ErrorModule(document, "The bespoken Dev module could not be generated");
        }

        return new ErrorModule(document, "The implementation of this module is not ready");
    }
}
