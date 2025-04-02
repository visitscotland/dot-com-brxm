package com.visitscotland.brxm.translation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.hippoecm.hst.configuration.hosting.Mount;

import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import org.hippoecm.hst.core.container.ContainerConstants;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.core.request.ResolvedVirtualHost;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.core.request.ResolvedMount;
import org.hippoecm.hst.core.component.HstRequest;

import org.hippoecm.hst.util.PathUtils;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TranslationFallbackProvider {
    private static final Logger logger = LogManager.getLogger(TranslationFallbackProvider.class);

    public Optional<HippoBean> getContentBeanForRequest(final HstRequest request) {
        final HstRequestContext requestContext = request.getRequestContext();
        final Optional<HippoBean> contentBean = Optional.ofNullable(requestContext.getContentBean());

        if (contentBean.isPresent()) {
            return contentBean;
        }

        try {
            return getFallbackContentBean(request, requestContext);
        } catch (final TranslationException | ObjectBeanManagerException | ClassCastException exception) {
            logger.error(exception.getMessage(), exception);
            return Optional.empty();
        }
    }

    private Optional<HippoBean> getFallbackContentBean(final HstRequest request,
                                                       final HstRequestContext requestContext) throws ObjectBeanManagerException {
        final ResolvedVirtualHost resolvedVirtualHost = getResolvedVirtualHost(request);
        final String englishContentPath = getEnglishContentPath(resolvedVirtualHost);
        final String relativeContentPath = getRelativeContentPath(requestContext);
        final String fullContentPath = combineContentPaths(englishContentPath, relativeContentPath);
        final HippoBean contentBean = (HippoBean) requestContext.getObjectBeanManager().getObject(fullContentPath);

        return Optional.ofNullable(contentBean);
    }

    private ResolvedVirtualHost getResolvedVirtualHost(final HstRequest request) {
        return Optional
            .ofNullable((ResolvedVirtualHost) request.getAttribute(ContainerConstants.VIRTUALHOSTS_REQUEST_ATTR))
            .orElseThrow(ResolvableVirtualHostNotFoundException::new);
    }

    private String getEnglishContentPath(final ResolvedVirtualHost virtualHost) {
        final String requestPath = "/";

        return Optional
            .ofNullable(virtualHost.matchMount(requestPath))
            .map(ResolvedMount::getMount)
            .map(Mount::getContentPath)
            .orElseThrow(() -> new UnableToMatchMountException(requestPath, virtualHost));
    }

    private String getRelativeContentPath(final HstRequestContext requestContext) {
        return Optional
            .ofNullable(requestContext.getResolvedSiteMapItem())
            .map(ResolvedSiteMapItem::getRelativeContentPath)
            .orElseThrow(RelativeContentPathNotFoundException::new);
    }

    private String combineContentPaths(final String contentPath,
                                       final String relativeContentPath) {
        final String forwardSlash = "/";

        return forwardSlash
            + PathUtils.normalizePath(contentPath)
            + forwardSlash
            + PathUtils.normalizePath(relativeContentPath);
    }
}