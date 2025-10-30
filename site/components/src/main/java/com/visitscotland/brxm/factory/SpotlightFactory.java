package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Spotlight;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SpotlightFactory {

    private final Logger contentLogger;
    private final LinkService linkService;
    private final AnchorFormatter anchorFormatter;

    public SpotlightFactory(LinkService linkService,
                           AnchorFormatter anchorFormatter,
                           ContentLogger contentLogger) {
        this.linkService = linkService;
        this.anchorFormatter = anchorFormatter;
        this.contentLogger = contentLogger;
    }


    public Module<?> createModule(Spotlight spotlight, Locale locale) {
        SpotlightModule module = new SpotlightModule();
        Linkable linkable = (Linkable) spotlight.getCtaLink().getLink();
        FlatLink cta = linkService.createSimpleLink(linkable, module, locale);

        if (Contract.isNull(cta.getLink())) {
            contentLogger.warn(String.format(
                    "The link for the Spotlight %s is not available. The module has been hidden", spotlight.getPath()));
            return new ErrorModule(spotlight,
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
        module.setHippoBean(spotlight);
        module.setAnchor(anchorFormatter.getAnchorOrFallback(spotlight.getAnchor(), spotlight::getTitle));

        return module;
    }
}
