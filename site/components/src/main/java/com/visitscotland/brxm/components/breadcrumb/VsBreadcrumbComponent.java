package com.visitscotland.brxm.components.breadcrumb;


import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.ComponentConfiguration;
import org.onehippo.cms7.essentials.components.CommonComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.swing.text.StringContent;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VsBreadcrumbComponent extends CommonComponent {

    private static final Logger logger = LoggerFactory.getLogger(VsBreadcrumbComponent.class.getName());

    final String REQUESTED_URI = "requestedURI";
    final String IS_HOME = "isHome";
    final String BREADCRUMB = "breadcrumb";
    final String DOCUMENT = "document";
    final String ORDERED_TRANSLATIONS = "orderedTranslations";
    final String CONTENT_CATEGORIES_OPTIONS = "content-categories-options";
    final String CONTENT_TYPE_OPTIONS = "content-content-type";
    final String SEARCH_CATEGORY = "searchCategory";
    final String SEARCH_CONTENT_TYPE = "searchContentType";
    final String OTHER_VALUE = "other";

    private VsBreadCrumbProvider breadcrumbProvider;
    private HippoUtilsService hippoUtilsService;
    private DocumentUtilsService documentUtils;

    public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
        super.doBeforeRender(request, response);

        //Requested URL to identify the current page from the breadcrumb
        request.setModel(REQUESTED_URI, request.getRequestURI());
        //Identify if the page is the home page independently of the environment (local, dev, acct, prod) and language
        request.setModel(IS_HOME, "root".equals(request.getRequestContext().getResolvedSiteMapItem().getHstSiteMapItem().getId()));
        //Breadcrumb Items list
        request.setModel(BREADCRUMB, this.breadcrumbProvider.getBreadcrumb(request));
        //Search category for Cludo site search
        request.setModel(SEARCH_CATEGORY, getValueFromValueListAndUrl(request, CONTENT_CATEGORIES_OPTIONS));
        //Main document for the page
        setDocument(request, response);
    }

    private void setDocument(HstRequest request, HstResponse response) {
        Optional<HippoBean> document = hippoUtilsService.getContentBeanWithTranslationFallback(request);
        if (document.isPresent() && document.get() instanceof Page) {
            request.setModel(DOCUMENT, document.get());
            // Translations ordered by SEO order
            List<BaseDocument> availableTranslations = ((Page) document.get()).getAvailableTranslations(BaseDocument.class).getTranslations();
            request.setModel(ORDERED_TRANSLATIONS, documentUtils.sortTranslationsForSeo(availableTranslations));
            String contentType = getValueFromValueListAndUrl(request, CONTENT_TYPE_OPTIONS);
            if (contentType.equals(OTHER_VALUE)){
                if (document.get() instanceof Destination) {
                    contentType = "destination";
                } else if (document.get() instanceof Itinerary){
                    contentType = "itinerary";
                } else {
                    contentType = "article";
                }
            }
            //Search contentType for Cludo site search
            request.setModel(SEARCH_CONTENT_TYPE, contentType);
        } else {
            logger.debug("{} page not found - redirecting to 404 page", request.getRequestURI());
            this.pageNotFound(response);
        }
    }

    public void init(ServletContext servletContext, ComponentConfiguration componentConfig) throws HstComponentException {
        super.init(servletContext, componentConfig);
        this.breadcrumbProvider = new VsBreadCrumbProvider(this);
        this.hippoUtilsService = VsComponentManager.get(HippoUtilsService.class);
        this.documentUtils = VsComponentManager.get(DocumentUtilsService.class);
    }

    /**
     * Returns the category that best matches the given URL.
     */
    private String getValueFromValueListAndUrl(HstRequest request, String valueListPath) {
        for (Map.Entry<String, String> entry : hippoUtilsService.getValueMap(valueListPath).entrySet()) {
            if (request.getPathInfo().contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return OTHER_VALUE;
    }

}
