package com.visitscotland.brxm.pagebuilder;

import com.visitscotland.brxm.mapper.*;
import com.visitscotland.brxm.mapper.module.*;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.SiteProperties;
import org.slf4j.Logger;

/**
 * This class is deprecated and It will only be used as a reference to {@code PageAssembler}.
 *
 * @deprecated It will be removed in version 3.0.0
 */
@Deprecated(forRemoval = true, since = "2.10.2")
public final class PageTemplateBuilder extends PageAssembler {

    public PageTemplateBuilder(DocumentUtilsService documentUtils, MegalinkMapper megalinkMapper, ICentreMapper iCentreFactory,
                               IKnowMapper iKnowFactory, ArticleMapper articleMapper, LongCopyMapper longCopyMapper,
                               UserGeneratedContentMapper userGeneratedContentFactory, TravelInformationMapper travelInformationMapper,
                               CannedSearchMapper cannedSearchFactory, PreviewWarningMapper previewFactory, FormMapper marketoFormMapper,
                               MapModuleMapper mapModuleMapper, SkiCentreListMapper skiCentreListMapper, SkiCentreMapper skiCentreMapper,
                               SiteProperties properties, DevModuleMapper devModuleMapper, ResourceBundleService bundle, Logger contentLogger,
                               SpotlightMapper signPostFactory, EventsListingMapper eventsListingFactory) {
        super(documentUtils, megalinkMapper, iCentreFactory, iKnowFactory, articleMapper, longCopyMapper, userGeneratedContentFactory,
                travelInformationMapper, cannedSearchFactory, previewFactory, marketoFormMapper, mapModuleMapper, skiCentreListMapper,
                skiCentreMapper, properties, devModuleMapper, bundle, contentLogger, signPostFactory, eventsListingFactory, null);
    }
}
