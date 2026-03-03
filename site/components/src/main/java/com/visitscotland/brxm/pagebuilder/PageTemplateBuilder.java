package com.visitscotland.brxm.pagebuilder;

import com.visitscotland.brxm.mapper.*;
import com.visitscotland.brxm.mapper.module.*;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.slf4j.Logger;

/**
 * This class is deprecated and It will only be used as a reference to {@code PageAssembler}.
 *
 * @deprecated It will be removed in version 3.0.0
 */
@Deprecated(forRemoval = true, since = "2.10.2")
public final class PageTemplateBuilder extends PageAssembler {

    public PageTemplateBuilder(DocumentUtilsService documentUtils, MegalinkMapper megalinkMapper, ICentreMapper iCentreMapper, ArticleMapper articleMapper, UserGeneratedContentMapper userGeneratedContentMapper, TravelInformationMapper travelInformationMapper, PreviewWarningMapper previewWarningMapper, MapModuleMapper mapModuleMapper, SkiCentreMapper skiCentreMapper, SkiCentreListMapper skiCentreListMapper, DevModuleMapper devModuleMapper, LongCopyMapper longCopyMapper, CannedSearchMapper cannedSearchMapper, CannedSearchDMSMapper cannedSearchDMSMapper, FormMapper formMapper, CTABannerMapper ctaBannerMapper, EventsListingMapper eventsListingMapper, SpotlightMapper spotlightMapper, DayMapper dayMapper, Logger contentLogger) {
        super(documentUtils, megalinkMapper, iCentreMapper, articleMapper, userGeneratedContentMapper, travelInformationMapper, previewWarningMapper, mapModuleMapper, skiCentreMapper, skiCentreListMapper, devModuleMapper, longCopyMapper, cannedSearchMapper, cannedSearchDMSMapper, formMapper, ctaBannerMapper, eventsListingMapper, spotlightMapper, dayMapper, contentLogger);
    }
}
