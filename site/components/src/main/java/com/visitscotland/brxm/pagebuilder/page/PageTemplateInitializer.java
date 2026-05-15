package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.components.content.service.AuthorService;
import com.visitscotland.brxm.factory.NewsletterFactory;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PageTemplateInitializer  {

    private static final String HERO_AMBIENT_VIDEO = "hero-ambient-video";
    private static final String AMBIENT_VIDEO_BUNDLE = "ambient-video";
    private static final String VIDEO_BUNDLE = "video";

    private final AuthorService authorService;
    private final NewsletterFactory newsletterFactory;
    private final SiteProperties properties;
    private final ImageMapper imageMapper;
    private final LinkService linksService;
    private final Logger contentLogger;

    public PageTemplateInitializer(AuthorService authorService, NewsletterFactory newsletterFactory,
           SiteProperties properties, ImageMapper imageMapper, LinkService linksService,
           ContentLogger contentLogger) {
        this.authorService = authorService;
        this.newsletterFactory = newsletterFactory;
        this.properties = properties;
        this.imageMapper = imageMapper;
        this.linksService = linksService;
        this.contentLogger = contentLogger;
    }

    public PageIntro getPageIntro(PageCompositionHelper pageCompositionHelper) throws PageCompositionException {
        final Page page = pageCompositionHelper.getPage();
        final PageIntro pageTemplate = new PageIntro(page);

        setHeroImage(pageCompositionHelper, pageTemplate);
        setVideo(pageCompositionHelper, pageTemplate);

        authorService.getBlog(pageCompositionHelper).ifPresent(pageTemplate::setAuthor);
        addNewsletterSignup(pageCompositionHelper).ifPresent(pageTemplate::setNewsletter);

        return pageTemplate;
    }

    /**
     * - Alerts are only used for issues related with the hero image at the moment
     * - Hero Image is not necessary for all document types. Is it better to add the field in order to keep consistency?
     */
    private void setHeroImage(PageCompositionHelper pageConfig, PageIntro pageTemplate) throws PageCompositionException {
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

    private void setVideo(PageCompositionHelper pageConfig, PageIntro pageTemplate) throws PageCompositionException {
        VideoLink videoDocument = pageConfig.getPage().getHeroVideo();
        if (videoDocument != null && videoDocument.getVideoLink() != null) {
            EnhancedLink video = linksService.createVideo(videoDocument.getVideoLink(), pageTemplate, pageConfig.getLocale());
            if (Contract.isEmpty(video.getYoutubeId())) {
                pageConfig.addProperty(HERO_AMBIENT_VIDEO, true);
                pageConfig.addAllSiteLabels(AMBIENT_VIDEO_BUNDLE);
            } else {
                pageConfig.addAllSiteLabels(VIDEO_BUNDLE);
            }
            pageTemplate.setVideo(video);
        }
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
}
