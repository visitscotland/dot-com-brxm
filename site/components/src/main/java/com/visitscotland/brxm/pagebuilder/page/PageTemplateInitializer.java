package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.components.content.service.AuthorService;
import com.visitscotland.brxm.components.content.service.CludoService;
import com.visitscotland.brxm.components.content.service.FavouritesService;
import com.visitscotland.brxm.factory.NewsletterFactory;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.mapper.module.MegalinkMapper;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageTemplate;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.brxm.utils.SitePropertyKeys;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PageTemplateInitializer  {

    private static final String HERO_AMBIENT_VIDEO_PROPERTY = "hero-ambient-video";
    private static final String MAIN_MAP_PATH_PROPERTY = "main-map-path";

    private static final String AMBIENT_VIDEO_BUNDLE = "ambient-video";
    private static final String VIDEO_BUNDLE = "video";
    private static final String OTYML_BUNDLE = "otyml";
    private static final String MEGALINKS_BUNDLE = "megalinks";
    private static final String PAGINATION_BUNDLE = "essentials.pagination";
    private static final String TABLE_CONTENTS_BUNDLE = "table-contents";

    private final AuthorService authorService;
    private final NewsletterFactory newsletterFactory;
    private final SiteProperties properties;
    private final ImageMapper imageMapper;
    private final LinkService linksService;
    private final MegalinkMapper megalinkMapper;
    private final CludoService cludoService;
    private final FavouritesService favouritesService;
    private final Logger contentLogger;

    public PageTemplateInitializer(AuthorService authorService, NewsletterFactory newsletterFactory,
           SiteProperties properties, ImageMapper imageMapper, LinkService linksService, MegalinkMapper megalinkMapper,
           CludoService cludoService, FavouritesService favouritesService,
           ContentLogger contentLogger) {
        this.authorService = authorService;
        this.newsletterFactory = newsletterFactory;
        this.properties = properties;
        this.imageMapper = imageMapper;
        this.linksService = linksService;
        this.megalinkMapper = megalinkMapper;
        this.cludoService = cludoService;
        this.favouritesService = favouritesService;
        this.contentLogger = contentLogger;
    }

    public PageTemplate getPageTemplate(PageCompositionHelper pageCompositionHelper) throws PageCompositionException {
        final PageTemplate pageTemplate = pageCompositionHelper.getPageTemplate();

        setHeroImage(pageCompositionHelper, pageTemplate);
        setVideo(pageCompositionHelper, pageTemplate);
        addOTYML(pageCompositionHelper, pageTemplate);

        authorService.getBlog(pageCompositionHelper).ifPresent(pageTemplate::setAuthor);
        addNewsletterSignup(pageCompositionHelper).ifPresent(pageTemplate::setNewsletter);

        addSiteSpecificConfiguration(pageCompositionHelper);

        return pageTemplate;
    }

    /**
     * - Alerts are only used for issues related with the hero image at the moment
     * - Hero Image is not necessary for all document types. Is it better to add the field in order to keep consistency?
     */
    private void setHeroImage(PageCompositionHelper pageConfig, PageTemplate pageTemplate) throws PageCompositionException {
        Page page = pageConfig.getPage();

        if (page.getHeroImage() == null) {
            String message = String.format("The image selected for '%s' is not available, please select a valid image for '%s' at: %s ",
                    page.getTitle(), page.getDisplayName(), page.getPath());
            contentLogger.warn(message);
            pageTemplate.addErrorMessage(message);
        } else {
            pageTemplate.setHeroImage(
                    imageMapper.createImage(pageConfig.getPage().getHeroImage(), pageTemplate, pageConfig.getLocale()));
        }
    }

    private void setVideo(PageCompositionHelper pageConfig, PageTemplate pageTemplate) throws PageCompositionException {
        VideoLink videoDocument = pageConfig.getPage().getHeroVideo();
        if (videoDocument != null && videoDocument.getVideoLink() != null) {
            EnhancedLink video = linksService.createVideo(videoDocument.getVideoLink(), pageTemplate, pageConfig.getLocale());
            if (Contract.isEmpty(video.getYoutubeId())) {
                pageConfig.addProperty(HERO_AMBIENT_VIDEO_PROPERTY, true);
                pageConfig.addAllSiteLabels(AMBIENT_VIDEO_BUNDLE);
            } else {
                pageConfig.addAllSiteLabels(VIDEO_BUNDLE);
            }
            pageTemplate.setVideo(video);
        }
    }

    /**
     * Set the OTYML module if present
     */
    protected void addOTYML(PageCompositionHelper pageConfig, PageTemplate pageTemplate) throws PageCompositionException {
        Page page = pageConfig.getPage();
        if (page.getOtherThings() != null) {
            pageTemplate.setOtyml(megalinkMapper.horizontalListLayout(page.getOtherThings(), pageConfig.getLocale()));

            if (Contract.isEmpty(pageTemplate.getOtyml().getLinks())) {
                contentLogger.warn("OTYML at {} contains 0 published items. Skipping module", page.getOtherThings().getPath());
                pageTemplate.addErrorMessage("Other things You might like contains 0 published items. Skipping module");
            } else if (pageTemplate.getOtyml().getLinks().size() < MegalinkMapper.MIN_ITEMS_CAROUSEL) {
                contentLogger.warn("OTYML at {} contains only {} published items. Expected a minimum of {}",
                        page.getOtherThings().getPath(), pageTemplate.getOtyml().getLinks().size(),
                        MegalinkMapper.MIN_ITEMS_CAROUSEL);
            }
        }

        pageConfig.addAllSiteLabels(OTYML_BUNDLE);
        pageConfig.addAllSiteLabels(MEGALINKS_BUNDLE);
        pageConfig.addAllSiteLabels(PAGINATION_BUNDLE);
    }

    /**
     * Adds the newsletter configuration to the request taking into account the target: (VisitScotland, Business Events or Ski)
     */
    protected Optional<SignpostModule> addNewsletterSignup(PageCompositionHelper pageCompositionHelper) throws PageCompositionException {
        Page page = pageCompositionHelper.getPage();
        if (Boolean.TRUE.equals(page.getHideNewsletter())) {
            return Optional.empty();
        } else if (pageCompositionHelper.getRequestPathInfo().contains(properties.getSiteSkiSection())) {
            return newsletterFactory.createSnowAlertsModule(pageCompositionHelper.getLocale());
        } else {
            return newsletterFactory.createNewsletterSignpostModule(pageCompositionHelper.getLocale());
        }
    }

    /**
     * Add Configuration specific to the VisitScotland.com or businessevents site
     */
    private void addSiteSpecificConfiguration(PageCompositionHelper pageConfig) {

        if (properties.isFavouritesEnabled(pageConfig.getLocale())){
            favouritesService.applyConfiguration(pageConfig.getRequest(), pageConfig);
        }

        if (properties.isTableOfContentsEnabled()){
            pageConfig.addAllSiteLabels(TABLE_CONTENTS_BUNDLE);
        }

        pageConfig.addProperty(SitePropertyKeys.FEATURE_HERO_SECTION, properties.getFeatureHeroSection());

        if (!Contract.isEmpty(properties.getSiteMap(pageConfig.getLocale()))) {
            pageConfig.addProperty(MAIN_MAP_PATH_PROPERTY, properties.getSiteMap(pageConfig.getLocale()));
        }

        if (properties.isGlobalSearchEnabled()){
            cludoService.applyConfiguration(pageConfig.getRequest(), pageConfig);
        }
    }
}
