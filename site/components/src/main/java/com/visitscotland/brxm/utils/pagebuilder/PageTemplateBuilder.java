package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.factory.*;
import com.visitscotland.brxm.factory.event.EventsListingFactory;
import com.visitscotland.brxm.mapper.*;
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

    public PageTemplateBuilder(DocumentUtilsService documentUtils, MegalinkMapper megalinkMapper, ICentreMapper iCentreFactory, IKnowMapper iKnowFactory, ArticleMapper articleMapper, LongCopyFactory longCopyFactory, UserGeneratedContentMapper userGeneratedContentFactory, TravelInformationMapper travelInformationMapper, CannedSearchFactory cannedSearchFactory, PreviewWarningMapper previewFactory, FormFactory marketoFormFactory, MapModuleMapper mapModuleMapper, SkiCentreListMapper skiCentreListMapper, SkiCentreMapper skiCentreMapper, SiteProperties properties, DevModuleMapper devModuleMapper, ResourceBundleService bundle, Logger contentLogger, SignpostFactory signPostFactory, EventsListingFactory eventsListingFactory) {
        super(documentUtils, megalinkMapper, iCentreFactory, iKnowFactory, articleMapper, longCopyFactory, userGeneratedContentFactory, travelInformationMapper, cannedSearchFactory, previewFactory, marketoFormFactory, mapModuleMapper, skiCentreListMapper, skiCentreMapper, properties, devModuleMapper, bundle, contentLogger, signPostFactory, eventsListingFactory);
    }
}
