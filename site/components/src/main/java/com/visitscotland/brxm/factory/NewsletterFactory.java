package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.CTABanner;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.ErrorModule;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.HippoHtmlWrapper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class NewsletterFactory {

    private static final String BUNDLE_ID = "newsletter-signpost";

    private final ResourceBundleService bundle;
    private final SiteProperties properties;
    private final HippoUtilsService hippoUtilsService;

    public NewsletterFactory(ResourceBundleService bundle,
                             SiteProperties properties,
                             HippoUtilsService hippoUtilsService) {
        this.bundle = bundle;
        this.properties = properties;
        this.hippoUtilsService = hippoUtilsService;
    }

    public Optional<SignpostModule> createNewsletterSignpostModule(Locale locale) {
        String newsletterUrl = hippoUtilsService.createUrlFromNode(properties.getSiteNewsletter(), true);
        if (!Contract.isNull(newsletterUrl)) {
            Optional<SignpostModule> signpostModule = createSignPostModule("newsletter", locale);
            if (signpostModule.isPresent()) {
                signpostModule.get().getCta().setLink(newsletterUrl);
                return signpostModule;
            }
        }

        return Optional.empty();
    }


    public Optional<SignpostModule> createSnowAlertsModule(Locale locale) {
        return createSignPostModule("snow-alerts", locale);
    }

    private Optional<SignpostModule> createSignPostModule(String prefix, Locale locale) {
        SignpostModule signpostModule = new SignpostModule();
        FlatLink cta = new FlatLink(
                bundle.getResourceBundle(BUNDLE_ID, prefix + ".cta.text", locale),
                bundle.getResourceBundle(BUNDLE_ID, prefix + ".cta.link", locale),
                LinkType.INTERNAL);

        if (Contract.isNull(cta.getLink())) {
            return Optional.empty();
        }

        FlatImage image = new FlatImage();
        image.setExternalImage(bundle.getResourceBundle(BUNDLE_ID, prefix + ".image", locale));
        signpostModule.setCta(cta);
        signpostModule.setImage(image);
        signpostModule.setTitle(bundle.getResourceBundle(BUNDLE_ID, prefix + ".title", locale));
        signpostModule.setCopy(new HippoHtmlWrapper(bundle.getResourceBundle(BUNDLE_ID, prefix + ".copy", locale)));

        return Optional.of(signpostModule);
    }
}
