package com.visitscotland.brxm.config;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.container.ContainerConstants;
import org.hippoecm.hst.core.internal.HstMutableRequestContext;
import org.hippoecm.hst.core.request.ResolvedMount;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.core.request.ResolvedVirtualHost;
import org.hippoecm.hst.core.request.SiteMapItemHandlerConfiguration;
import org.hippoecm.hst.core.sitemapitemhandler.HstSiteMapItemHandler;
import org.hippoecm.hst.core.sitemapitemhandler.HstSiteMapItemHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceAPITranslationHstSiteMapItemHandler implements HstSiteMapItemHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResourceAPITranslationHstSiteMapItemHandler.class);

    private static final String PAGE_NOT_FOUND_COMPONENT = "hst:pages/pagenotfound";
    private static final String ENGLISH_ROOT_PATH = "/resourceapi";

    /**
     * Implement the translation fallback mechanism.
     * For a non-english locale, if the translated content is not found but the english version exists, then the english content should be shown instead
     * By returning an English sitemap but keeping the translated mount, brxm will attempt to look up all  content (including menus, breadcrumb etc.) in
     * the translated repository (instead of returning to a pagenotfound sitemap). Then the content component can override the
     * content lookup to check the english repository instead of the translated repository
     */
    @Override
    public ResolvedSiteMapItem process(final ResolvedSiteMapItem resolvedSiteMapItem, final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws HstSiteMapItemHandlerException {
        if (isEnglishSite(resolvedSiteMapItem) || !isPageNotFound(resolvedSiteMapItem)){
            return resolvedSiteMapItem;
        }
        try {
            String path = resolvedSiteMapItem.getPathInfo();
            if (path.isEmpty() || path.charAt(0) != '/'){
                path = "/" + path;
            }
            var resolvedVirtualHost = getResolvedVirtualHost(httpServletRequest);
            var englishMount = resolvedVirtualHost.matchMount(ENGLISH_ROOT_PATH);
            var englishSiteMapItem = englishMount.matchSiteMapItem(path);

            if (!isPageNotFound(englishMount, englishSiteMapItem)) {
                // English content does exist - use translated mount with an english sitemap
                return englishSiteMapItem;
            }
        } catch (IllegalStateException e) {
            logger.error("The translation fallback could not be calculated", e);
        }

        return resolvedSiteMapItem;
    }

    /**
     * Checks if the sitemap item is in the english site mount
     */
    private boolean isEnglishSite(ResolvedSiteMapItem resolvedSiteMapItem) {
        if (resolvedSiteMapItem == null || resolvedSiteMapItem.getResolvedMount() == null) {
             return false;
        }
        String mountPath = resolvedSiteMapItem.getResolvedMount().getResolvedMountPath();
        return ENGLISH_ROOT_PATH.equals(mountPath);
    }
    /**
     * Alters the context of the request to check if the page would be available in the main site mount
     */
    private boolean isPageNotFound(ResolvedMount mount, ResolvedSiteMapItem siteMapItem) {
        HstMutableRequestContext requestContext = (HstMutableRequestContext) RequestContextProvider.get();
        ResolvedMount originalMount = requestContext.getResolvedMount();
        try {
            requestContext.setResolvedMount(mount);
            return isPageNotFound(siteMapItem);
        } finally {
            requestContext.setResolvedMount(originalMount);
        }
    }

    private boolean isPageNotFound(ResolvedSiteMapItem resolvedSiteMapItem) {
        if (resolvedSiteMapItem == null || resolvedSiteMapItem.getHstComponentConfiguration() == null) {
            throw new IllegalStateException("ResolvedSiteMapItem or HstComponentConfiguration is null");
        }
        return resolvedSiteMapItem.getHstComponentConfiguration().getId().equals(PAGE_NOT_FOUND_COMPONENT);
    }

    private ResolvedVirtualHost getResolvedVirtualHost(HttpServletRequest httpServletRequest) throws IllegalStateException {
        ResolvedVirtualHost resolvedVirtualHost = (ResolvedVirtualHost) httpServletRequest.getAttribute(ContainerConstants.VIRTUALHOSTS_REQUEST_ATTR);
        if (resolvedVirtualHost == null) {
            throw new IllegalStateException("Failed to get ResolvedVirtualHost from request servlet");
        }
        return resolvedVirtualHost;
    }

    @Override
    public void init(ServletContext servletContext, SiteMapItemHandlerConfiguration siteMapItemHandlerConfiguration) {
        logger.debug("Initialising ResourceAPITranslationHstSiteMapItemHandler");
    }

    @Override
    public void destroy() {
        logger.debug("Destroying ResourceAPITranslationHstSiteMapItemHandler");
    }
}
