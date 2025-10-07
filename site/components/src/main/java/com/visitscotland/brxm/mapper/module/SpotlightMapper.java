package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.CMSLink;
import com.visitscotland.brxm.hippobeans.CTABanner;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.pagebuilder.InvalidContentException;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;

import java.util.MissingResourceException;

@Component
public class SpotlightMapper extends ModuleMapper<CTABanner, SignpostModule> {

    private final LinkService linkService;
    private final AnchorFormatter anchorFormatter;

    public SpotlightMapper(LinkService linkService,
                           AnchorFormatter anchorFormatter) {
        this.linkService = linkService;
        this.anchorFormatter = anchorFormatter;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {

    }

    @Override
    SignpostModule map(CTABanner document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return createModule(document);
    }

    public SignpostModule createModule(CTABanner ctaBanner) throws PageCompositionException {
        SignpostModule module = new SignpostModule();
        Linkable linkable = getLinkable(ctaBanner);
        FlatLink cta = linkService.createSimpleLink(linkable, module, null);

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

    /**
     * Validates the link from the CTA banner and returns the linkable object.
     * 
     * @param ctaBanner the CTA banner containing the link to validate
     * @return the validated Linkable object from the CTA banner
     * @throws PageCompositionException if the link is null, invalid, or not a Linkable type
     */
    private Linkable getLinkable(CTABanner ctaBanner) throws PageCompositionException {
        if (ctaBanner.getCtaLink() == null || ctaBanner.getCtaLink().getLink() == null) {
            throw new InvalidContentException(ctaBanner.getPath(),
                    "The CTA banner does not have a valid link configuration. The module has been hidden");
        }

        if (!(ctaBanner.getCtaLink().getLink() instanceof Linkable)) {
            throw new InvalidContentException(ctaBanner.getPath(),
                    "The link for the CTA banner is not a valid Linkable type. The module has been hidden");
        }
        return (Linkable) ctaBanner.getCtaLink().getLink();
    }
}
