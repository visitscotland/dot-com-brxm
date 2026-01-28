package com.visitscotland.brxm.components.content;


import com.visitscotland.brxm.components.breadcrumb.VsBreadCrumbProvider;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NavigationContentComponent extends CommonComponent {

    private static final Logger logger = LoggerFactory.getLogger(NavigationContentComponent.class.getName());

    private static final String REQUESTED_URI = "requestedURI";
    private static final String IS_HOME = "isHome";
    private static final String BREADCRUMB = "breadcrumb";
    private static final String DOCUMENT = "document";
    private static final String ORDERED_TRANSLATIONS = "orderedTranslations";

    //TODO: Search Funtionality properties (to be moved to pageConfiguration)
    private static final String CONTENT_CATEGORIES_OPTIONS = "content-categories-options";
    private static final String CONTENT_TYPE_OPTIONS = "content-content-type";
    private static final String SEARCH_CATEGORY = "searchCategory";
    private static final String SEARCH_CONTENT_TYPE = "searchContentType";
    private static final String OTHER = "other";

    private VsBreadCrumbProvider breadcrumbProvider;
    private HippoUtilsService hippoUtilsService;
    private DocumentUtilsService documentUtils;

    @Override
    public void init(ServletContext servletContext, ComponentConfiguration componentConfig) throws HstComponentException {
        super.init(servletContext, componentConfig);
        this.breadcrumbProvider = new VsBreadCrumbProvider(this);
        this.hippoUtilsService = VsComponentManager.get(HippoUtilsService.class);
        this.documentUtils = VsComponentManager.get(DocumentUtilsService.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
        super.doBeforeRender(request, response);

        //Requested URL to identify the current page from the breadcrumb
        request.setModel(REQUESTED_URI, request.getRequestURI());
        //Identify if the page is the home page independently of the environment (local, dev, acct, prod) and language
        request.setModel(IS_HOME, "root".equals(request.getRequestContext().getResolvedSiteMapItem().getHstSiteMapItem().getId()));
        //Breadcrumb Items list
        request.setModel(BREADCRUMB, this.breadcrumbProvider.getBreadcrumb(request));
        //Search category for Cludo site search
        request.setModel(SEARCH_CATEGORY, getValueFromValueListAndUrl(request, CONTENT_CATEGORIES_OPTIONS).orElse(OTHER));
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

            request.setModel(SEARCH_CONTENT_TYPE, getContentType(request, document.get()));
        } else {
            logger.debug("{} page not found - redirecting to 404 page", request.getRequestURI());
            this.pageNotFound(response);
        }
    }

    /**
     * Determines the content type for the Cludo search categorization based on the request URL and document type.
     * <p>
     * This method follows a hierarchical approach to determine the content type:
     * <ol>
     *   <li>First checks if a specific content type is configured in the value list that matches the URL path</li>
     *   <li>If no URL-based match is found, determines type based on the document's class type</li>
     *   <li>Falls back to "article" as the default content type</li>
     * </ol>
     *
     * @param request the HST request containing the URL path information
     * @param document the Hippo document bean to analyze for type determination
     *
     * @return the content type string used for search categorization.
     */
    private String getContentType(HstRequest request, HippoBean document) {
        Optional<String> type = getValueFromValueListAndUrl(request, CONTENT_TYPE_OPTIONS);
        if (type.isPresent()){
            return type.get();
        } else if (document instanceof Destination) {
            return "destination";
        } else if (document instanceof Itinerary){
            return "itinerary";
        } else {
            return "article";
        }
    }

    /**
     * Returns the category that best matches the given URL.
     */
    private Optional<String> getValueFromValueListAndUrl(HstRequest request, String valueListPath) {
        for (Map.Entry<String, String> entry : hippoUtilsService.getValueMap(valueListPath).entrySet()) {
            if (request.getPathInfo().contains(entry.getKey())) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

}
