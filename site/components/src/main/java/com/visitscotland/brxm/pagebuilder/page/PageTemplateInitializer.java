package com.visitscotland.brxm.pagebuilder.page;

import com.visitscotland.brxm.components.content.service.AuthorService;
import com.visitscotland.brxm.factory.NewsletterFactory;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.model.PageIntro;
import com.visitscotland.brxm.utils.SiteProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PageTemplateInitializer  {

    private final AuthorService authorService;
    private final NewsletterFactory newsletterFactory;
    private final SiteProperties properties;

    public PageTemplateInitializer(AuthorService authorService, NewsletterFactory newsletterFactory
            , SiteProperties properties) {
        this.authorService = authorService;
        this.newsletterFactory = newsletterFactory;
        this.properties = properties;
    }

    public PageIntro getPageIntro(PageCompositionHelper pageCompositionHelper) throws PageCompositionException {
        final Page page = pageCompositionHelper.getPage();
        final PageIntro pageTemplate = new PageIntro(page);

        authorService.getBlog(pageCompositionHelper).ifPresent(pageTemplate::setAuthor);
        addNewsletterSignup(pageCompositionHelper);

        return pageTemplate;
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
