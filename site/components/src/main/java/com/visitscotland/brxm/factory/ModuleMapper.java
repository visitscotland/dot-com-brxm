package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.model.ErrorModule;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Optional;

/**
 * Abstract Class for mapping Hippo content beans to modules and including them in a page composition.
 * Implementers should convert document beans to the appropriate module types and add them
 * to the PageCompositionHelper.
 *
 * @param <H> The HippoBean type handled by this mapper
 * @param <M> The Module type produced by this mapper
 */
public abstract class ModuleMapper<H extends BaseDocument, M extends Module<H>> {

    private final static Logger logger = LoggerFactory.getLogger(ModuleMapper.class);

    /**
     * Converts a documentType into a Module
     *
     * @param document The HippoBean document to include
     * @param locale Locale for the request
     */
    abstract M map(H document, Locale locale);

    /**
     * Includes the given document in the page composition.
     *
     * @param document The HippoBean document to include
     * @param page The PageCompositionHelper to add the module to
     * @return The module that was added to the page
     */
    public void include(H document, PageCompositionHelper page) {
        Module<?> module;
        if (document == null ){
            logger.warn("An empty document was sent to the module mapper");
            return;
        }
        try {
            module = map(document, page.getLocale());
            if (module == null) {
                module = generateErrorModule(document);
            }
        } catch (RuntimeException e) {
            // Exceptions should not affect the rest of the page composition
            logger.error("The module for {} could not be generated", document.getPath(), e);
            module = generateErrorModule(document);
        }
        page.addModule(module);
    }

    private ErrorModule generateErrorModule(H document) {
        return new ErrorModule(document,"The document could not be converted to an UI module");
    }
}
