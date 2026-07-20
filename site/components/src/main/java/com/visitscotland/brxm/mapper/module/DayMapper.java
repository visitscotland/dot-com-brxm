package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.mapper.MediaSectionMapper;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.IntraDayModule;
import com.visitscotland.brxm.model.ItineraryDayModule;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.GoogleMapsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class DayMapper extends ModuleMapper<Day, ItineraryDayModule> {

    private static final Logger logger = LoggerFactory.getLogger(DayMapper.class);

    private static final String BUNDLE_FILE = "itinerary";

    // some of these to be removed
    private final ResourceBundleService bundle;
    private final LinkService linkService;
    private final GoogleMapsService googleMapsService;
    private final MediaSectionMapper mediaSectionMapper;

    public DayMapper(ResourceBundleService bundle, LinkService linkService, GoogleMapsService googleMapsService, MediaSectionMapper mediaSectionMapper) {
        this.bundle = bundle;
        this.linkService = linkService;
        this.googleMapsService = googleMapsService;
        this.mediaSectionMapper = mediaSectionMapper;
    }

    @Override
    public void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        // nothing needed here for now
    }

    @Override
    public ItineraryDayModule map(Day document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        final Locale locale = compositionHelper.getLocale();
        ItineraryDayModule day = new ItineraryDayModule();

        day.setTitle(document.getTitle());
        day.setIntroduction(document.getIntroduction());
        day.setTransports(document.getTransports());
        day.setMapLink(formatCTA(document.getMapLink(),
                bundle.getResourceBundle(BUNDLE_FILE, "days.default-cta", locale),
                compositionHelper.getLocale()));
        if (!Locale.UK.equals(locale)) {
            googleMapsService.localizeUrl(day.getMapLink(), locale);
        }
        day.setCtaLink(formatCTA(document.getCtaLink(), null, compositionHelper.getLocale()));
        day.setMedia(document.getMedia());
        day.setMediaSection(mediaSectionMapper.map(document.getMediaCollection(), day, locale));

        setIntraDayModule(document, compositionHelper);

        return day;
    }

    private void setIntraDayModule(Day document, PageCompositionHelper compositionHelper) {
        try {
            if (!compositionHelper.getModules().isEmpty()) {

                ItineraryDayModule prevDayModule = (ItineraryDayModule) compositionHelper.getModules().get(compositionHelper.getModules().size() - 1);
                final String prevUrl = prevDayModule.getMapLink().getLink();
                final String routeUrl = googleMapsService.getDirectionsUrlForIntraDay(prevUrl, document.getMapLink().getLink());
                final BigDecimal distance = googleMapsService.getDistanceFromUrls(prevUrl, document.getMapLink().getLink());
                if (routeUrl == null || routeUrl.isEmpty()) {
                    logger.info("Unable to calculate intraday route map for day {}", document.getPath());
                }
                if (distance == null ) {
                    logger.info("\n\nUnable to calculate intraday route map for day {}", document.getPath());
                }

                ((ItineraryDayModule) compositionHelper.getModules().get(compositionHelper.getModules().size() - 1)).setIntraDayModule(new IntraDayModule(routeUrl, distance));

            }

        } catch (RuntimeException e) {
            logger.warn("An error occurred while applying IntraDayModule for day {}: ", document.getTitle(), e);
        }
    }

    FlatLink formatCTA(final ExternalLink externalLink, final String defaultCta, final Locale locale) {

        FlatLink ctaLink = linkService.createExternalLink(locale, externalLink.getLink(),
                !externalLink.getLabel().isEmpty() ? externalLink.getLabel() : defaultCta, externalLink.getPath());
        ctaLink.setType(LinkType.EXTERNAL);

        return ctaLink;
    }
}