package com.visitscotland.brxm.services;

import com.visitscotland.brxm.hippobeans.MegalinkItem;
import com.visitscotland.brxm.hippobeans.Megalinks;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Service for converting HippoBean items into EnhancedLink objects.
 * Handles conversion of Linkable and VideoLink items into enhanced links
 * for use in Megalink modules or other link-based components.
 */
@Component
public class EnhancedLinkService {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedLinkService.class);

    private final LinkService linkService;
    private final ContentLogger contentLogger;

    public EnhancedLinkService(LinkService linkService, ContentLogger contentLogger) {
        this.linkService = linkService;
        this.contentLogger = contentLogger;
    }

    /**
     * Converts a list of MegalinkItem objects into EnhancedLink objects.
     * Featured status from each MegalinkItem is preserved in the resulting EnhancedLink.
     *
     * @param module the module to add error messages to if conversion fails
     * @param items the list of MegalinkItem objects to convert
     * @param locale the locale for the links
     * @param addCategory whether to include category information in the links
     *
     * @return list of converted EnhancedLink objects
     */
    public List<EnhancedLink> convert(Module<Megalinks> module, List<MegalinkItem> items, Locale locale,
                                              boolean addCategory) {
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

    /**
     * Converts a list of HippoBean items into EnhancedLink objects.
     * Optionally uses breadcrumb labels for Page items when available.
     *
     * @param module the module to add error messages to if conversion fails
     * @param items the list of HippoBean items to convert
     * @param locale the locale for the links
     * @param addCategory whether to include category information in the links
     * @param useBreadcrumb whether to use breadcrumb labels for Page items
     *
     * @return list of converted EnhancedLink objects
     */
    public List<EnhancedLink> convert(Module<?> module, List<HippoBean> items, Locale locale, boolean addCategory,
                                       boolean useBreadcrumb) {
        List<EnhancedLink> links = new ArrayList<>();
        for (HippoBean item : items) {
            EnhancedLink link = convertToEnhancedLink(module, item, locale, addCategory);

            if (link != null) {
                if (useBreadcrumb && item instanceof Page && !Contract.isEmpty(((Page) item).getBreadcrumb())){
                    link.setLabel(((Page) item).getBreadcrumb());
                }
                links.add(link);
            }
        }

        return links;
    }

    /**
     * Converts a HippoBean item into an EnhancedLink.
     *
     * @param module the module to add error messages to if conversion fails
     * @param item the HippoBean item to convert (Linkable or VideoLink)
     * @param locale the locale for the link
     * @param addCategory whether to include category information in the link
     *
     * @return the converted EnhancedLink, or null if conversion fails
     */
    private EnhancedLink convertToEnhancedLink(Module<?> module, HippoBean item, Locale locale, boolean addCategory) {
        Optional<EnhancedLink> link;

        if (item instanceof Linkable) {
            link = linkService.createEnhancedLink((Linkable) item, module, locale, addCategory);
        } else if (item instanceof VideoLink) {
            link = linkService.createEnhancedLink(((VideoLink) item).getVideoLink(), module, locale, addCategory);
        } else {
            if (item != null) {
                addError(module, "One of the links cannot be recognized and will not be included in the page.");
                contentLogger.warn("The module {} is pointing to a module of type {} which cannot be rendered as a page", item.getPath(), item.getClass().getSimpleName());
            } else {
                contentLogger.warn("One of the links in module {} seems to contain no link",
                        (module != null ? module.getDocumentPath() : "unknown"));
            }
            return null;
        }

        if (link.isEmpty()) {
            contentLogger.warn("The module {} might be linking an unpublished document", item.getPath());
            return null;
        }

        return link.get();
    }

    /**
     * Adds an error message to the module.
     *
     * @param module the module to add the error message to
     * @param message the error message to add
     */
    private void addError(Module<?> module, String message) {
        if (module != null) {
            module.addErrorMessage(message);
        } else {
            logger.warn("The error message cannot be displayed in preview");
        }
    }
}
