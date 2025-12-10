package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.Spotlight;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.SpotlightModule;
import com.visitscotland.brxm.pagebuilder.InvalidContentException;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class SpotlightMapper extends ModuleMapper<Spotlight, SpotlightModule>{

    private final Logger contentLogger;
    private final LinkService linkService;
    private final AnchorFormatter anchorFormatter;

    public SpotlightMapper(LinkService linkService,
                           AnchorFormatter anchorFormatter,
                           ContentLogger contentLogger) {
        this.linkService = linkService;
        this.anchorFormatter = anchorFormatter;
        this.contentLogger = contentLogger;
    }

    @Override
    SpotlightModule map(Spotlight document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return createModule(document, compositionHelper.getLocale());
    }

    public SpotlightModule createModule(Spotlight spotlight, Locale locale) throws InvalidContentException {
        SpotlightModule module = new SpotlightModule();
        Linkable linkable = (Linkable) spotlight.getCtaLink().getLink();
        FlatLink cta = linkService.createSimpleLink(linkable, module, locale);

        if (Contract.isNull(cta.getLink())) {
            throw new InvalidContentException(spotlight.getPath(),
                    "The link for the Spotlight is not available. The module has been hidden");
        }

        if (!Contract.isEmpty(spotlight.getCtaLink().getLabel())) {
            cta.setLabel(spotlight.getCtaLink().getLabel());
        }

        FlatImage image = new FlatImage();
        image.setCmsImage(spotlight.getImage());

        module.setCta(cta);
        module.setImage(image);
        module.setTitle(spotlight.getTitle());
        module.setCopy(spotlight.getCopy());
        module.setLayout(spotlight.getLayout());
        module.setHippoBean(spotlight);
        module.setAnchor(anchorFormatter.getAnchorOrFallback(spotlight.getAnchor(), spotlight::getTitle));

        return module;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        // No labels needed for Spotlight
    }
}
