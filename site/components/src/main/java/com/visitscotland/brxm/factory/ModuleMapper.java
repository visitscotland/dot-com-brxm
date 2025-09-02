package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.model.ErrorModule;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.pagebuilder.PageCompostionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.MissingResourceException;

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
    private final PreviewModeFactory previewFactory;

    public ModuleMapper(PreviewModeFactory previewModeFactory) {
        this.previewFactory = previewModeFactory;
    }

    /**
     * Converts a documentType into a Module
     *
     * @param document The HippoBean document to include
     * @param compositionHelper The PageCompositionHelper with the context of the request
     */
    abstract M map(H document, PageCompositionHelper compositionHelper) throws PageCompostionException;

    /**
     * Includes the given document in the page composition.
     *
     * @param document The HippoBean document to include
     * @param page The PageCompositionHelper to add the module to
     * @return The module that was added to the page
     */
    public void include(H document, PageCompositionHelper page)  {
        Module<?> module;
        if (document == null ){
            logger.warn("An empty document was sent to the module mapper");
            return;
        }
        try {
            module = map(document, page);
            if (module == null) {
                module = generateErrorModule(document, "The document could not be converted to an UI module");
            }
        } catch (PageCompostionException e){
            logger.error(e.getMessage());
            module = generateErrorModule(document, e.getMessage());
        } catch (MissingResourceException e){
            String message = String.format("The module for %s couldn't be built because some labels do not exist", document.getPath());
            logger.error(message, e);
            module = generateErrorModule(document,message);
        } catch (RuntimeException e){
            logger.error("An unexpected exception happened while building the module for {}", document.getPath(), e);
            module = generateErrorModule(document, "An unexpected exception happened while building the module");
        }
        page.addModule(module);
    }

    private ErrorModule generateErrorModule(H document, String message) {
        return previewFactory.createErrorModule(document, message);
    }
}
