package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.hippobeans.CTABanner;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.utils.pagebuilder.InvalidContentException;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
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

    public SignpostModule createModule(CTABanner ctaBanner) throws InvalidContentException{
        SignpostModule module = new SignpostModule();
        Linkable linkable = (Linkable) ctaBanner.getCtaLink().getLink();
        FlatLink cta = linkService.createSimpleLink(linkable, module, null);

        if (Contract.isNull(cta.getLink())) {
            throw new InvalidContentException(ctaBanner.getPath(),
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
