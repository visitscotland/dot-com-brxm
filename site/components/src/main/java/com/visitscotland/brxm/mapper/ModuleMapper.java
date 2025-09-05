package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.pagebuilder.PageCompostionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;

/**
 * Abstract Class for mapping Hippo content beans to modules and including them in a page composition.
 * Implementers should convert document beans to the appropriate module types and add them
 * to the PageCompositionHelper.
 *
 * @param <H> The HippoBean type handled by this mapper
 * @param <M> The Module type produced by this mapper
 */
abstract class ModuleMapper<H extends BaseDocument, M extends Module<H>> {

    private final static Logger logger = LoggerFactory.getLogger(ModuleMapper.class);

    /**
     * Includes the given document in the page composition.
     *
     * @param document The HippoBean document to include
     * @param compositionHelper The PageCompositionHelper to add the module to
     *
     * @return The module that was added to the page
     *
     * @throws PageCompostionException if an unrecoverable error was detected during the mapping of the module
     */
    public final void include(H document, PageCompositionHelper compositionHelper) throws PageCompostionException {
        Module<?> module;
        if (document == null ){
            logger.warn("An empty document was sent to the module mapper");
            return;
        }
        try {
            module = map(document, compositionHelper);
            if (module == null) {
                throw new PageCompostionException(document.getPath(), "The document could not be converted to an UI module");
            } else {
                addLabels(compositionHelper);
            }
            compositionHelper.addModule(module);
        } catch (MissingResourceException e){
            throw new PageCompostionException(document.getPath(), "The module couldn't be built because some labels do not exist", e);
        } catch (RuntimeException e){
            throw new PageCompostionException(document.getPath(), "An unexpected exception happened while building the module", e);
        }
    }

    /**
     * Defines the labels required for the module by adding them to the page composition.
     * This method should identify and register all resource bundle keys needed
     * for proper rendering of the module.
     *
     * @param compositionHelper The PageCompositionHelper to add the labels to
     * @throws MissingResourceException if required labels file is not defined on the CMS
     */
    abstract void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException;

    /**
     * Converts a documentType into a Module
     *
     * @param document The HippoBean document to include
     * @param compositionHelper The PageCompositionHelper with the context of the request
     *
     * @throws PageCompostionException if an unrecoverable error was detected during the mapping of the module
     */
    abstract M map(H document, PageCompositionHelper compositionHelper) throws PageCompostionException;

}
