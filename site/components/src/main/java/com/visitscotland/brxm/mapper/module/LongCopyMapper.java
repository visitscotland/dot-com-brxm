package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.components.content.GeneralContentComponent;
import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.LongCopy;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.LongCopyModule;
import com.visitscotland.brxm.pagebuilder.InvalidContentException;
import com.visitscotland.brxm.pagebuilder.PageAssembler;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.MissingResourceException;

@Component
public class LongCopyMapper extends ModuleMapper<LongCopy, LongCopyModule> {

    private static final Logger logger = LoggerFactory.getLogger(LongCopyMapper.class);

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {

    }

    @Override
    LongCopyModule map(LongCopy document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        validate(document, compositionHelper);
        return getModule(document);
    }

    public LongCopyModule getModule(LongCopy doc){
        LongCopyModule module = new LongCopyModule();
        module.setCopy(doc.getCopy());
        module.setHippoBean(doc);
        return module;
    }

    /**
     * LongCopy module are only allowed in
     * @param compositionHelper
     * @throws PageCompositionException
     */
    private void validate(LongCopy document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        Page page = compositionHelper.getPage();
        if (page instanceof General && ((General) page).getTheme().equals(GeneralContentComponent.SIMPLE)){
            if (compositionHelper.getModules().stream().anyMatch(LongCopyModule.class::isInstance)){
                throw new InvalidContentException(document.getPath(), "Only one instance of Long Copy module is allowed");
            }
        } else {
            throw new InvalidContentException(document.getPath(), "The document type LongCopy is not allowed in this type of page. Path " + page.getPath());
        }
    }
}
