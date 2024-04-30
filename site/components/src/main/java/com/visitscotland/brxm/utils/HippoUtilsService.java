package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.manager.ObjectConverter;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.container.ContainerConstants;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.core.request.ResolvedMount;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.core.request.ResolvedVirtualHost;
import org.hippoecm.hst.site.HstServices;
import org.hippoecm.hst.util.PathUtils;
import org.jetbrains.annotations.NotNull;
import org.onehippo.forge.selection.hst.contentbean.ValueList;
import org.onehippo.forge.selection.hst.util.SelectionUtil;
import org.onehippo.taxonomy.api.Category;
import org.onehippo.taxonomy.api.Taxonomy;
import org.onehippo.taxonomy.api.TaxonomyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static org.hippoecm.hst.content.beans.query.builder.ConstraintBuilder.constraint;

/**
 * Set of utilities related with Hippo that from the whole environment to be running in order to work
 */
@Component
public class HippoUtilsService {

    private static final Logger logger = LoggerFactory.getLogger(HippoUtilsService.class);

    public static final String BUSINESS_EVENTS_SITE = "business-events";


    private SiteProperties properties;

    public HippoUtilsService(@Lazy SiteProperties properties){
        this.properties = properties;
    }

    /**
     * Convert and HstLink or a HippoBean into a URL String
     *
     * @param document Page document
     *
     * @return URL for the page that renders the document or null when it cannot be rendered as a page.
     *
     *
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public String createUrl(Page document) {
        return createUrl(document, true);
    }

    @NonTestable(NonTestable.Cause.BRIDGE)
    public String createUrlFromNode(String path, boolean localize) {
        try {
            return createUrl(getDocumentFromContent(path), localize);
        } catch (QueryException | ObjectBeanManagerException | RepositoryException e) {
            logger.warn("A link could not be created for {}", path);
            return null;
        }
    }

    public String createUrl(Page document, boolean localize) {
        if (document == null) {
            logger.info("The linked page does not exist.");
            return null;
        } else {
            final boolean FULLY_QUALIFIED = false;
            HstRequestContext requestContext = RequestContextProvider.get();
            Mount requestMount = requestContext.getResolvedMount().getMount();

            HstLink link = requestContext.getHstLinkCreator().create(localize ? getLocalizedDocument(document) : document, requestContext);
            // Ensure links always link to the current mount
            // If the document does not exist on the current mount, HstLinkCreatorImpl will fall back to the english mount
            // However we want to link to the current mount, and let the translation fallback handle resolution of the english document
            if (localize && link.getMount().getLocale().equals(Locale.UK.toString()) && !requestMount.getLocale().equals(Locale.UK.toString())) {
                link.setPath(String.format("%s/%s", requestMount.getMountPath(), link.getPath()));
            }
            return link.toUrlForm(requestContext, FULLY_QUALIFIED);
        }
    }

    /**
     * Convert Image into a URL String
     *
     * @param image CMS image
     *
     * @return URL for the image that renders the document's image or null when it cannot be rendered the image.
     *
     *
     */
    public static String createUrl(Image image) {
        if (image == null) {
            logger.info("The linked image does not exist.");
            return null;
        } else {
            final boolean FULLY_QUALIFIED = false;
            HstRequestContext requestContext = RequestContextProvider.get();
            HstLink link = requestContext.getHstLinkCreator().create(image, requestContext);
            return link.toUrlForm(requestContext, FULLY_QUALIFIED);
        }
    }

    private HippoBean getLocalizedDocument(@NotNull Page document) {
        HippoBean localizedDocument = document;

        if (!getRequestLocale().toLanguageTag().contains(document.getLocaleString())) {
            localizedDocument = document.getAvailableTranslations().getTranslation(getRequestLocale().getLanguage());
        }

        return localizedDocument != null ? localizedDocument : document;
    }


    /**
     * Return a HippoBean from the content folder associated with the current mount
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public <T extends HippoBean> T getDocumentFromContent(String relativeContentPath) throws QueryException, ObjectBeanManagerException, RepositoryException  {
        Mount requestMount = RequestContextProvider.get().getResolvedMount().getMount();
        String bannerPath = "/" + PathUtils.normalizePath(requestMount.getContentPath()) + "/" + PathUtils.normalizePath(relativeContentPath);
        return getDocumentFromNode(bannerPath);
    }

    /**
     * Return a HippoBean from the path of the Node
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public <T extends HippoBean> T getDocumentFromNode(String path) throws QueryException, ObjectBeanManagerException, RepositoryException {
        return getDocumentFromNode(RequestContextProvider.get().getSession().getNode(path));
    }

    /**
     * Return a HippoBean from the Node
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public <T extends HippoBean> T getDocumentFromNode(Node jcrNode) throws QueryException, ObjectBeanManagerException {
        HstQueryResult result = RequestContextProvider.get().getQueryManager().createQuery(jcrNode).execute();

        if (result.getSize() > 0){
            HippoBean bean = result.getHippoBeans().nextHippoBean();
            return (T) bean.getObjectConverter().getObject(bean.getNode());
        }

        return null;
    }

    /**
     * Return a (possibly unavailable) hippo bean from a node
     * @param jcrNode CMS node
     * @param includeUnavailable If true, then the bean will be found even if hippo:availability is not set to 'live'
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public <T extends HippoBean> T getDocumentFromNode(Node jcrNode, boolean includeUnavailable) throws QueryException, ObjectBeanManagerException {
        if (!includeUnavailable) {
            return getDocumentFromNode(jcrNode);
        }
        ObjectConverter objectConverter = RequestContextProvider.get().getObjectConverter();
        if (objectConverter == null) {
            logger.warn("ObjectConverter retrieved from HstRequestContext is null");
            return null;
        }

        return (T) objectConverter.getObject(jcrNode);
    }

    @NonTestable(NonTestable.Cause.BRIDGE)
    public HippoBean getBeanForResolvedSiteMapItem(HstRequest request, ResolvedSiteMapItem sitemapItem) {
        Optional<HippoBean> bean = getBeanForSiteMapItem(request, sitemapItem, request.getRequestContext().getResolvedMount());
        return bean.orElse(null);
    }

    /**
     * TODO: To be reused by getContentBeanWithTranslationFallback()
     *
     * @param request the HstRequest request
     * @param resolvedSiteMapItem item from the cms sitemap
     * @param resolvedMount ResolvedSiteMapItem
     *
     * @return Main Document model for the request
     *
     * @see org.hippoecm.hst.component.support.bean.BaseHstComponent#getBeanForResolvedSiteMapItem(HstRequest, ResolvedSiteMapItem)
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    private Optional<HippoBean> getBeanForSiteMapItem(HstRequest request, ResolvedSiteMapItem resolvedSiteMapItem, ResolvedMount resolvedMount){
        HstRequestContext requestContext = request.getRequestContext();

        if (resolvedMount != null && resolvedSiteMapItem != null && requestContext.getObjectBeanManager() != null && resolvedMount.getMount() != null) {
            String contentPath = resolvedMount.getMount().getContentPath();
            String content = "/" + PathUtils.normalizePath(contentPath) + "/" +
                    PathUtils.normalizePath(resolvedSiteMapItem.getRelativeContentPath());
            try {
                Object bean = requestContext.getObjectBeanManager().getObject(content);
                return (bean instanceof HippoBean) ? Optional.of((HippoBean) bean) : Optional.empty();
            } catch (ObjectBeanManagerException e) {
                logger.info("Failed to get hippo bean at {}", content, e);
            }
        }

        return Optional.empty();
    }

    /**
     * Resolves the mount for the current request
     *
     * @param request the HstRequest request
     * @param mount String (i.e: "es-es", "business-events", ...)
     *
     * @return Resolved BR Mount
     */
    public ResolvedMount getMount(HstRequest request, String mount) {
        ResolvedVirtualHost resolvedVirtualHost = (ResolvedVirtualHost) request.getAttribute(ContainerConstants.VIRTUALHOSTS_REQUEST_ATTR);
        if (resolvedVirtualHost != null) {
            ResolvedMount resolvedMount = resolvedVirtualHost.matchMount(mount);
            if (resolvedMount != null){
                return resolvedMount;
            }
        }

        logger.warn("The mount {} could not be resolver for the following request: {}", mount, request.getRequestURI());
        return request.getRequestContext().getResolvedMount();
    }

    /**
     * Obtain the content bean for a request. If the content bean is not found in request mount, then check english
     * mount (as part of the translation fallback)
     *
     * @param request HstRequest
     * @return Optional<HippoBean>
     */
    public Optional<HippoBean> getContentBeanWithTranslationFallback(HstRequest request) {
        HstRequestContext context = request.getRequestContext();
        HippoBean contentBean = request.getRequestContext().getContentBean();
        if (contentBean != null) {
            return Optional.of(contentBean);
        }
        ResolvedVirtualHost resolvedVirtualHost = (ResolvedVirtualHost) request.getAttribute(ContainerConstants.VIRTUALHOSTS_REQUEST_ATTR);
        if (resolvedVirtualHost == null) {
            logger.error("Failed to get ResolvedVirtualHost from request servlet");
            return Optional.empty();
        }
        ResolvedMount englishMount = resolvedVirtualHost.matchMount("/");
        if (englishMount == null || context.getResolvedSiteMapItem() == null || context.getObjectBeanManager() == null || englishMount.getMount() == null) {
            return Optional.empty();
        }
        String englishContentPath = englishMount.getMount().getContentPath();
        String englishContent = "/" + PathUtils.normalizePath(englishContentPath) + "/" + PathUtils.normalizePath(context.getResolvedSiteMapItem().getRelativeContentPath());
        try {
            Object bean = context.getObjectBeanManager().getObject(englishContent);
            return (bean instanceof HippoBean) ? Optional.of((HippoBean) bean) : Optional.empty();
        } catch (ObjectBeanManagerException e) {
            logger.info("Failed to get hippo bean at {}", englishContent, e);
            return Optional.empty();
        }
    }

    /**
     * Extract a parameter from the URL (without namespace)
     *
     * @param request   HstRequest
     * @param parameter Name of the parameter
     * @return value of the query parameter or null if such parameter hasn't been defined
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public String getParameterFromUrl(HstRequest request, String parameter) {
        return request.getRequestContext().getServletRequest().getParameter(parameter);
    }

    /**
     * Extract the resolved mount for the request if provided or from RequestContextProvider when it is not
     *
     * @param request HstRequest
     *
     * @return Resolved mount for the current request.
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public Mount getResolvedMount(HstRequest request){
        HstRequestContext context = request == null? RequestContextProvider.get(): request.getRequestContext();
        return context.getResolvedMount().getMount();
    }

    /**
     * @return Returns the locale for the current request
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public Locale getRequestLocale(){
        Locale locale = RequestContextProvider.get().getPreferredLocale();
        return locale==null?Locale.UK:locale;
    }

    /**
     * Retrieves a ValueList as a Map
     * <br>
     * New value lists can be configured in essentials and must be added to META-INF/valueList.xml
     * @param valueListIdentifier The identifier as specified in the value list YAML
     * @return A mapping from the value list key to the value list value
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public Map<String, String> getValueMap(String valueListIdentifier) {
        ValueList valueList = SelectionUtil.getValueListByIdentifier(valueListIdentifier, RequestContextProvider.get());
        return SelectionUtil.valueListAsMap(valueList);
    }

    @NonTestable(NonTestable.Cause.BRIDGE)
    public Taxonomy getTaxonomy() {
        TaxonomyManager taxonomyManager = HstServices.getComponentManager().getComponent("TaxonomyManager", "org.onehippo.taxonomy.contentbean");
        return taxonomyManager.getTaxonomies().getTaxonomy("Visitscotland-categories");

    }

    /**
     * Get all the Destinations and stops categorised with the taxonomy wanted in alphabetic order
     *
     * @param request the request
     * @param category the category or taxonomy wanted
     * @param constraint constrait or field in the cms with the taxonomy
     * @param order field to short the results by
     * @param types to filter the document types we want to perform the search
     *
     * @return all the destinations and stop with that category selected
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public HstQueryResult getDocumentsByTaxonomy(HstRequest request, Category category, String constraint, String order, Class<? extends HippoBean>... types) {
        HstRequestContext requestContext = request.getRequestContext();
        HippoBean scope = requestContext.getSiteContentBaseBean();
        HstQuery hstQuery = HstQueryBuilder.create(scope)
                .ofTypes(types)
                .where(constraint(constraint).contains(category.getKey())).orderByAscending(order).build();
        try {
            return hstQuery.execute();
        } catch (QueryException e) {
            logger.warn("HstQuery failed to obtain documents by taxonomy", e);
        }
        return null;
    }

    /**
     * Verifies if the request is being resolved by the Business Events subdomain. This is determined
     * by checking the {@code visitscotland:site}  node in the Mount node.
     * <br>
     * The only allowed subdomain is currently business-events. If that changes in the future this method
     * might need to be redesigned
     *
     * @param request HstRequest
     *
     * @return {@code true} if the request is being resolved by the business events subdomain
     */
    @NonTestable(NonTestable.Cause.BRIDGE)
    public boolean isBusinessEventsSite(HstRequest request){
        String site = properties.getSiteId();
        if (!Contract.isEmpty(site)){
            if (site.equals(BUSINESS_EVENTS_SITE)){
                return true;
            } else {
                logger.error("The configuration for the mount cannot be interpreted (site-id = {}) for the following request: {}",
                        site, request.getRequestURI());
            }
        }
        return false;
    }

}
