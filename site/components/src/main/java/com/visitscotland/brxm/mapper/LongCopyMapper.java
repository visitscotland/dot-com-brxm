package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.hippobeans.LongCopy;
import com.visitscotland.brxm.model.LongCopyModule;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import org.springframework.stereotype.Component;

import java.util.MissingResourceException;

@Component
public class LongCopyMapper extends ModuleMapper<LongCopy, LongCopyModule> {

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {

    }

    @Override
    LongCopyModule map(LongCopy document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return getModule(document);
    }

    public LongCopyModule getModule(LongCopy doc){
        LongCopyModule module = new LongCopyModule();
        module.setCopy(doc.getCopy());
        module.setHippoBean(doc);
        return module;
    }
}
