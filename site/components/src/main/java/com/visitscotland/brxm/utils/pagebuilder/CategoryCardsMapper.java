package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.CardGroupModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryCardsMapper {

    private static final Logger logger = LoggerFactory.getLogger(CategoryCardsMapper.class);

    private final LinkService linkService;
    private final Logger contentLogger;

    public CategoryCardsMapper(LinkService linkService, Logger contentLogger) {
        this.linkService = linkService;
        this.contentLogger = contentLogger;
    }

    /**
     * TODO HstRequest won't be necessary after version 3.0.0. Adapt solution to the new implementation
     */
    public LinksModule<EnhancedLink> getCategoryCards(HstRequest request, PageLinksSectionCompound section) {
        CardGroupModule module = new CardGroupModule();
        module.setTitle(section.getTitle());
        module.setIntroduction(section.getCopy());
        convertToEnhancedLinks(module, section.getLinks(), request.getLocale(), false);

        return module;
    }


    /**
     * This code has been duplicated from MegalinkFactory. It should be refactored to avoid duplication.
     * TODO: This issue should be fixed in version 3.0.0
     * @deprecated
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    List<EnhancedLink> convertToEnhancedLinks(Module<Megalinks> module, List<MegalinkItem> items, Locale locale, boolean addCategory) {
        List<EnhancedLink> links = new ArrayList<>();
        for (MegalinkItem item : items) {
            EnhancedLink link = convertToEnhancedLink(module, item.getLinkItem(), locale, addCategory);

            if (link != null) {
                link.setFeatured(item.getFeature());
                links.add(link);
            }
        }

        return links;
    }

    private EnhancedLink convertToEnhancedLink(Module<?> module, HippoBean item, Locale locale, boolean addCategory) {
        Optional<EnhancedLink> link;

        if (item instanceof Linkable) {
            link = linkService.createEnhancedLink((Linkable) item, module, locale, addCategory);
            if (link.isPresent() && item instanceof Page) {
                if (!Contract.isEmpty(((Page) item).getBreadcrumb())){
                    link.get().setLabel(((Page) item).getBreadcrumb());
                }
            }
        } else {
            if (item != null) {
                addError(module, "One of the links cannot be recognized and will not be included in the page.");
                contentLogger.warn("The module {} is pointing to a module of type {} which cannot be rendered as a page", item.getPath(), item.getClass().getSimpleName());
            } else {
                contentLogger.warn("One of the links seems contain no link");
            }
            return null;
        }

        if (link.isEmpty()) {
            contentLogger.warn("The module {} might be linking an unpublished document", item.getPath());
            return null;
        }

        return link.get();
    }

    private void addError(Module<?> module, String message) {
        if (module != null) {
            module.addErrorMessage(message);
        } else {
            logger.warn("The error message cannot be displayed in preview");
        }
    }
}
