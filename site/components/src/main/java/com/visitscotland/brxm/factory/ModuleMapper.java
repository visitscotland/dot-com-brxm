package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import org.hippoecm.hst.content.beans.standard.HippoBean;

/**
 * Interface for mapping Hippo content beans to modules and including them in a page composition.
 * Implementers should convert document beans to the appropriate module types and add them
 * to the PageCompositionHelper.
 *
 * @param <H> The HippoBean type handled by this mapper
 * @param <M> The Module type produced by this mapper
 */
public interface ModuleMapper<H extends HippoBean, M extends Module<H>> {

    /**
     * Converts a documentType into a Module
     *
     * @param document The HippoBean document to include
     * @param locale Locale for the request
     */
    M map(H document, Locale locale);

    /**
     * Includes the given document in the page composition.
     * 
     * @param document The HippoBean document to include
     * @param page The PageCompositionHelper to add the module to
     * @return The module that was added to the page
     */
    M include(H document, PageCompositionHelper page);

}
