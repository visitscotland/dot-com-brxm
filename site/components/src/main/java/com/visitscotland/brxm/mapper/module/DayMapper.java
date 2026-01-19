package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.mapper.EntryMapper;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.ItineraryDayModule;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class DayMapper extends ModuleMapper<Day, ItineraryDayModule> {


    private static final String BUNDLE_FILE = "itinerary";

    private Locale locale;

    // some of these to be removed
    private final ResourceBundleService bundle;
    private final DMSDataService dmsData;
    private final ImageMapper imageMapper;
    private final DMSUtils utils;
    private final EntryMapper entryMapper;
    private final DocumentUtilsService documentUtils;
    private final LinkService linkService;
    private final Logger contentLogger;

    public DayMapper(ResourceBundleService bundle, DMSDataService dmsData, ImageMapper imageMapper,
                           DMSUtils utils, DocumentUtilsService documentUtils, LinkService linkService,
                           ContentLogger contentLogger, EntryMapper entryMapper) {
        this.bundle = bundle;
        this.dmsData = dmsData;
        this.imageMapper = imageMapper;
        this.utils = utils;
        this.documentUtils = documentUtils;
        this.linkService = linkService;
        this.contentLogger = contentLogger;
        this.entryMapper = entryMapper;
    }


    @Override
    public void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        // nothing needed here for now
    }

    /**
     * Set the locale from the parent itinerary for translations
     * @param locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public ItineraryDayModule map(Day document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        ItineraryDayModule day = new ItineraryDayModule();

        day.setTitle(document.getTitle());
        day.setIntroduction(document.getIntroduction());
        day.setTransports(document.getTransports());
        day.setMapLink(formatCTA(document.getMapLink(), bundle.getResourceBundle(BUNDLE_FILE, "days.default-cta", locale)));
        day.setCtaLink(formatCTA(document.getCtaLink(), "default CTA here"));
        day.setMedia(document.getMedia());

        return day;
    }

    FlatLink formatCTA(final ExternalLink externalLink, final String defaultCta) {

        FlatLink ctaLink = linkService.createExternalLink(locale, externalLink.getLink(),
                !externalLink.getLabel().isEmpty() ? externalLink.getLabel() : defaultCta, externalLink.getPath());
        ctaLink.setType(LinkType.EXTERNAL);

        return ctaLink;
    }

}
