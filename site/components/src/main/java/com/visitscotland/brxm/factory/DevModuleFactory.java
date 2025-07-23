package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.comparator.ComparisonMapper;
import com.visitscotland.brxm.comparator.ResultMapper;
import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.hippobeans.SimpleDevModule;
import com.visitscotland.brxm.model.ErrorModule;
import com.visitscotland.brxm.model.Module;
import org.springframework.stereotype.Controller;

@Controller
public class DevModuleFactory {

    private final ComparisonMapper comparisonMapper;
    private final ResultMapper resultMapper;

    public DevModuleFactory(ComparisonMapper comparisonMapper, ResultMapper resultMapper) {
        this.comparisonMapper = comparisonMapper;
        this.resultMapper = resultMapper;
    }

    public Module<? extends BaseDocument> getModule(DevModule document){
        if (document.getBespoken() == null) {
            return new SimpleDevModule(document);
        }
        switch (document.getBespoken()){
            case "obs-form":
                return comparisonMapper.map(document);
            case "obs-results":
                return resultMapper.map();
            default:
                return new ErrorModule(document, "The implementation of this module is not ready");
        }
    }
}
