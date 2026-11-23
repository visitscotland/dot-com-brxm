package com.visitscotland.brxm.mapper.page;

import com.visitscotland.brxm.hippobeans.PageLinksSectionCompound;
import com.visitscotland.brxm.model.megalinks.CardGroupModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import com.visitscotland.brxm.services.EnhancedLinkService;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CategoryCardsMapper {

    private final Logger contentLogger;
    private final EnhancedLinkService enhancedLinkService;

    public CategoryCardsMapper(Logger contentLogger, EnhancedLinkService enhancedLinkService) {
        this.contentLogger = contentLogger;
        this.enhancedLinkService = enhancedLinkService;
    }

    /**
     * Creates a category cards module from a page links section.
     *
     * @param locale the locale for the links
     * @param section the page links section containing the cards data
     * @return the LinksModule with enhanced links, or null if no links are available
     */
    public LinksModule<EnhancedLink> getCategoryCards(Locale locale, PageLinksSectionCompound section) {
        CardGroupModule module = new CardGroupModule();
        module.setTitle(section.getTitle());
        module.setIntroduction(section.getCopy());
        module.setLinks(enhancedLinkService.convert(module, section.getLinks(), locale, false, true));

        if (module.getLinks().isEmpty()) {
            contentLogger.warn(
                    "The category Cards Section has been hidden because there were not available links. Path = {}",
                    section.getPath());
            return null;
        }

        return module;
    }
}
