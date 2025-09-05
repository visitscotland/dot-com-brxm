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
public class SignpostFactory {

    private static final String BUNDLE_ID = "newsletter-signpost";

    private final Logger contentLogger;
    private final ResourceBundleService bundle;
    private final SiteProperties properties;
    private final HippoUtilsService hippoUtilsService;
    private final LinkService linkService;
    private final AnchorFormatter anchorFormatter;

    public SignpostFactory(ResourceBundleService bundle,
                           SiteProperties properties,
                           HippoUtilsService hippoUtilsService,
                           LinkService linkService,
                           AnchorFormatter anchorFormatter,
                           ContentLogger contentLogger) {
        this.bundle = bundle;
        this.properties = properties;
        this.hippoUtilsService = hippoUtilsService;
        this.linkService = linkService;
        this.anchorFormatter = anchorFormatter;
        this.contentLogger = contentLogger;
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

    public Module<?> createModule(CTABanner ctaBanner) {
        SignpostModule module = new SignpostModule();
        Linkable linkable = (Linkable) ctaBanner.getCtaLink().getLink();
        FlatLink cta = linkService.createSimpleLink(linkable, module, null);

        if (Contract.isNull(cta.getLink())) {
            contentLogger.warn(String.format(
                    "The link for the CTA banner %s is not available. The module has been hidden", ctaBanner.getPath()));
            return new ErrorModule(ctaBanner,
                    "The link for the CTA banner is not available. The module has been hidden");
        }

        if (!Contract.isEmpty(ctaBanner.getCtaLink().getLabel())) {
            cta.setLabel(ctaBanner.getCtaLink().getLabel());
        }

        FlatImage image = new FlatImage();
        image.setCmsImage(ctaBanner.getImage());

        module.setCta(cta);
        module.setImage(image);
        module.setTitle(ctaBanner.getTitle());
        module.setCopy(ctaBanner.getIntroduction());
        module.setHippoBean(ctaBanner);
        module.setNested(Boolean.TRUE.equals(ctaBanner.getNested()));
        module.setAnchor(anchorFormatter.getAnchorOrFallback(ctaBanner.getAnchor(), ctaBanner::getTitle));

        return module;
    }
}
