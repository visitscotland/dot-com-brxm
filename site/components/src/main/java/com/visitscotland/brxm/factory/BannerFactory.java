package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Banner;
import com.visitscotland.brxm.model.BannerModule;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.util.Collections;
import java.util.Locale;
import java.util.NoSuchElementException;

@Component
public class BannerFactory {

    private final LinkService linkService;
    private final HippoUtilsService hippoUtilsService;
    private final ResourceBundleService bundle;
    private static final Logger logger = LoggerFactory.getLogger(BannerFactory.class);
    private final Logger contentLogger;
    private final SiteProperties siteProperties;

    public BannerFactory(LinkService linkService, HippoUtilsService hippoUtilsService, ResourceBundleService bundle, ContentLogger contentLogger, SiteProperties siteProperties) {
        this.linkService = linkService;
        this.hippoUtilsService = hippoUtilsService;
        this.bundle = bundle;
        this.contentLogger = contentLogger;
        this.siteProperties = siteProperties;
    }

    public BannerModule getBannerModule(HstRequest request) {
        String relativeBannerPath = siteProperties.getSiteBanner();
        if (Contract.isEmpty(relativeBannerPath)) {
            return null;
        }
        try {
            HippoBean bean = hippoUtilsService.getDocumentFromContent(relativeBannerPath);
            if (bean instanceof Banner) {
                return getBannerModule((Banner)bean, request.getLocale());
            } else {
               contentLogger.warn("Expected Banner bean at {}, but found {}", relativeBannerPath, bean == null ? "null" : bean.getClass().getSimpleName());
            }
        } catch (PathNotFoundException | NoSuchElementException e) {
            logger.trace("No published banner found at {}", relativeBannerPath);
        } catch (RepositoryException | ObjectBeanManagerException | QueryException e) {
            logger.error("Failed to access repo to access banner at location {}", relativeBannerPath, e);
        }
        return null;
    }

    private BannerModule getBannerModule(Banner document, Locale locale) {
        BannerModule module = new BannerModule();

        FlatLink ctaLink = linkService.createFindOutMoreLink(module, locale, document.getCtaLink());
        if (ctaLink == null || ctaLink.getLink() == null) {
            String errorMessage = String.format("Failed to create the emergency banner '%s' , please review the document attached at: %s", document.getDisplayName(), document.getPath() );
            module.setErrorMessages(Collections.singletonList(errorMessage));
            contentLogger.error(errorMessage);
        }else{
            module.setCopy(document.getCopy());
            module.setCtaLink(ctaLink);
        }
        module.setHippoBean(document);
        return module;
    }
}
