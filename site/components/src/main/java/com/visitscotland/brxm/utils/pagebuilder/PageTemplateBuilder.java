package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.factory.*;
import com.visitscotland.brxm.factory.event.EventsListingFactory;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.SiteProperties;
import org.slf4j.Logger;

/**
 * This class is deprecated and It will only be used as a reference to {@code PageAssembler}.
 *
 * @deprecated It will be removed on version 3.0.0 is released
 */
@Deprecated(forRemoval = true, since = "2.10.2")
public final class PageTemplateBuilder extends PageAssembler {

    public PageTemplateBuilder(DocumentUtilsService documentUtils, MegalinkFactory linksFactory, ICentreFactory iCentreFactory, IKnowFactory iKnowFactory, ArticleFactory articleFactory, LongCopyFactory longCopyFactory, UserGeneratedContentMapper userGeneratedContentFactory, TravelInformationFactory travelInformationFactory, CannedSearchFactory cannedSearchFactory, PreviewModeFactory previewFactory, FormFactory marketoFormFactory, MapFactory mapFactory, SkiCentreListMapper skiCentreListMapper, SkiCentreMapper skiCentreMapper, SiteProperties properties, DevModuleFactory devModuleFactory, ResourceBundleService bundle, Logger contentLogger, SignpostFactory signPostFactory, EventsListingFactory eventsListingFactory) {
        super(documentUtils, linksFactory, iCentreFactory, iKnowFactory, articleFactory, longCopyFactory, userGeneratedContentFactory, travelInformationFactory, cannedSearchFactory, previewFactory, marketoFormFactory, mapFactory, skiCentreListMapper, skiCentreMapper, properties, devModuleFactory, bundle, contentLogger, signPostFactory, eventsListingFactory);
    }
}
