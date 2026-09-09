package com.visitscotland.brxm.mapper.page;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.pagebuilder.InvalidContentException;
import com.visitscotland.brxm.services.EnhancedLinkService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class KeyInformationMapper {

    static final String DESTINATIONS_KEY_INFORMATION_CATEGORIES = "destinations-key-information-categories";

    private final Logger contentLogger;
    private final LinkService linkService;
    private final ResourceBundleService resourceBundleService;

    public KeyInformationMapper(Logger contentLogger, LinkService linkService, ResourceBundleService resourceBundleService) {
        this.contentLogger = contentLogger;
        this.linkService = linkService;
        this.resourceBundleService = resourceBundleService;
    }

    public DestinationKeyInformationModule getKeyInformationModule(DestinationsKeyInformation keyInformation, Locale locale) {
        DestinationKeyInformationModule module = new DestinationKeyInformationModule();
        module.setTitle(keyInformation.getTitle());

        module.setHighlights(keyInformation.getHighlights()
                .stream().map(highlight -> getKeyInformationHighlightsModule(highlight, locale))
                .collect(Collectors.toList()));

        module.setCta(linkService.createFindOutMoreLink(module, locale, keyInformation.getCtaItem()));

        return module;
    }


    private DestinationKeyCategory getKeyInformationHighlightsModule (DestinationsKeyCategories highlight, Locale locale) {
        DestinationKeyInformationModule module = new DestinationKeyInformationModule();
        SimpleEntry keyLabel = new SimpleEntry();
        keyLabel.setKey(highlight.getCategory());
        String categoryLabel = resourceBundleService.getResourceBundle(DESTINATIONS_KEY_INFORMATION_CATEGORIES, highlight.getCategory(), locale);
        if (categoryLabel == null) {
            contentLogger.error("Destination Travel Information , the category  {} does not contain key {}", DESTINATIONS_KEY_INFORMATION_CATEGORIES, highlight.getCategory());
        }
        keyLabel.setValue(Contract.defaultIfNull(categoryLabel, highlight.getCategory()));
        DestinationKeyCategory destinationKeyCategory = new DestinationKeyCategory();
        destinationKeyCategory.setCategory(keyLabel);
        destinationKeyCategory.setCopy(highlight.getCopy());
        return destinationKeyCategory;

    }
}
