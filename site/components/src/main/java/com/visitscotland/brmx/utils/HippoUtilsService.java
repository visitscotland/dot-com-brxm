package com.visitscotland.brmx.utils;

import com.visitscotland.brmx.services.ResourceBundleService;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Locale;

/**
 * Set of utilities related with Hippo that from the whole environment to be running in order to work
 */
public class HippoUtilsService {

    /**
     * Dummy HstCompoment used to access stateless methods on the ComponentClass
     */
    final BaseHstComponent hstComponent;

    //TODO: eliminate when PageContentComponent.getCtaLabel() gets refactored.
    final ResourceBundleService bundle;

    public HippoUtilsService(){
        bundle = new ResourceBundleService();
        hstComponent = new BaseHstComponent();
    }

    static HippoUtilsService instance;

    /**
     *  @deprecated This method exists until legacy uses are refactored. Do not use this method statically but
     *  create a new instance so whatever is using this class can be tested
     */
    @Deprecated
    public static HippoUtilsService getInstance(){
        if (instance == null){
            instance = new HippoUtilsService();
        }
        return instance;
    }

    /**
     * @deprecated Use ResourceBundleUtils instead
     */
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
     * Return a HippoBean from the path of the Node
     */
    @NonTestable
    public <T extends HippoBean> T getDocumentFromNode(String path) throws QueryException, ObjectBeanManagerException, RepositoryException {
        return getDocumentFromNode(RequestContextProvider.get().getSession().getNode(path));
    }

    /**
     * Return a HippoBean from the Node
     */
    @NonTestable
    public <T extends HippoBean> T getDocumentFromNode(Node jcrNode) throws QueryException, ObjectBeanManagerException {
        HippoBean bean = RequestContextProvider.get().getQueryManager()
                .createQuery(jcrNode).execute().getHippoBeans().nextHippoBean();

        return (T) bean.getObjectConverter().getObject(bean.getNode());
    }

    public HippoBean getBeanForResolvedSiteMapItem(HstRequest request, ResolvedSiteMapItem sitemapItem) {
        return hstComponent.getBeanForResolvedSiteMapItem(request, sitemapItem);
    }
}
