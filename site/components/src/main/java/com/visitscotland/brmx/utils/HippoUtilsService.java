package com.visitscotland.brmx.utils;

import com.visitscotland.brmx.services.ResourceBundleService;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.resourcebundle.ResourceBundleRegistry;
import org.hippoecm.hst.site.HstServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.util.Locale;

/**
 * Set of utilities related with Hippo that from the whole environment to be running in order to work
 */
public class HippoUtilsService {

    private static final Logger logger = LoggerFactory.getLogger(HippoUtilsService.class.getName());

    final ResourceBundleRegistry resourceBundleRegistry;
    final HstRequestContext requestContext;

    //TODO: eliminate when PageContentComponent.getCtaLabel() gets refactored.
    final ResourceBundleService bundle;

    public HippoUtilsService(){
        resourceBundleRegistry = HstServices.getComponentManager().getComponent(ResourceBundleRegistry.class.getName());
        requestContext = RequestContextProvider.get();
        bundle = new ResourceBundleService();
    }

    public HippoUtilsService(
            ResourceBundleRegistry resourceBundleRegistry,
            HstRequestContext requestContext){
        this.resourceBundleRegistry = resourceBundleRegistry;
        this.requestContext = requestContext;

        bundle = new ResourceBundleService();
    }

    static HippoUtilsService INSTANCE;

    /**
     * This method
     * @return
     */
    @Deprecated
    public static HippoUtilsService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new HippoUtilsService();
        }
        return INSTANCE;
    }

    @Deprecated
    public String getResourceBundle(String bundleName, String key,  Locale locale){
        return bundle.getResourceBundle(bundleName, key,  locale, false);
    }

    /**
     * Convert and HstLink or a HippoBean into a URL String
     *
     * TODO: Verify that the item can be rendered as a page and return null when it doesn't
     *
     * @param document Page document
     *
     * @return URL for the page that renders the document or null when it cannot be rendered as a page.
     */
    public String createUrl(HippoBean document){
        final boolean FULLY_QUALIFIED = true;
        HstRequestContext requestContext = RequestContextProvider.get();

        HstLink link = requestContext.getHstLinkCreator().create(document, requestContext);
        return link.toUrlForm(requestContext, FULLY_QUALIFIED);
    }

    /**
     * @param jcrNode
     * @return
     * @throws QueryException             when the node cannot be found
     * @throws ObjectBeanManagerException when the object is corrupted and cannot be parsed
     */
    public Object getDocumentFromNode(Node jcrNode) throws QueryException, ObjectBeanManagerException {
        HippoBean bean = RequestContextProvider.get().getQueryManager()
                .createQuery(jcrNode).execute().getHippoBeans().nextHippoBean();

        return bean.getObjectConverter().getObject(bean.getNode());

    }
}
