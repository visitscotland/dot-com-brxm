package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.ItineraryDayModule;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.GoogleMapsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class DayMapper extends ModuleMapper<Day, ItineraryDayModule> {

    private static final String BUNDLE_FILE = "itinerary";

    // some of these to be removed
    private final ResourceBundleService bundle;
    private final LinkService linkService;
    private final GoogleMapsService googleMapsService;

    public DayMapper(ResourceBundleService bundle, LinkService linkService, GoogleMapsService googleMapsService) {
        this.bundle = bundle;
        this.linkService = linkService;
        this.googleMapsService = googleMapsService;
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

        return day;
    }

    FlatLink formatCTA(final ExternalLink externalLink, final String defaultCta, final Locale locale) {

        FlatLink ctaLink = linkService.createExternalLink(locale, externalLink.getLink(),
                !externalLink.getLabel().isEmpty() ? externalLink.getLabel() : defaultCta, externalLink.getPath());
        ctaLink.setType(LinkType.EXTERNAL);

        return ctaLink;
    }
}