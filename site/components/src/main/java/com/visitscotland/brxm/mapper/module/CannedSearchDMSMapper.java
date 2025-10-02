package com.visitscotland.brxm.mapper.module;


import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.hippobeans.CannedSearch;
import com.visitscotland.brxm.model.CannedSearchModule;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vs.ase.dms.ProductTypes;

import java.util.Locale;
import java.util.MissingResourceException;

@Component
@Deprecated
public class CannedSearchDMSMapper extends ModuleMapper<CannedSearch, CannedSearchModule> {

    private static final Logger logger = LoggerFactory.getLogger(CannedSearchDMSMapper.class);


    static final String BUNDLE_ID = "canned-search";

    private final ResourceBundleService bundle;
    private final LinkService linkService;
    private final DMSDataService dmsData;
    private final Logger contentLog;

    public CannedSearchDMSMapper(ResourceBundleService bundle, LinkService linkService, DMSDataService dmsData,
                                 ContentLogger contentLogger){
        this.bundle = bundle;
        this.linkService = linkService;
        this.dmsData = dmsData;
        this.contentLog = contentLogger;
    }

    public CannedSearchModule getCannedSearchModule(CannedSearch document, Locale locale){
        logger.info("Creating CannedSearchModule for {}", document.getPath());

        CannedSearchModule module = new CannedSearchModule ();
        module.setHippoBean(document);
        module.setTitle(document.getTitle());
        module.setProductType(document.getCriteria().getSearch().getProductType());
        module.setDescription(document.getCopy());

        FlatLink viewAllCta = linkService.createFindOutMoreLink(module, locale, document.getCriteria());

        if (Contract.isEmpty(viewAllCta.getLabel()) || viewAllCta.getLabel().equals(bundle.getResourceBundle("essentials.global","button.find-out-more",  locale))) {
            viewAllCta.setLabel(bundle.getResourceBundle(BUNDLE_ID, "canned-search.listview", locale));
        }
        module.setViewAllLink(viewAllCta);

        module.setCannedSearchEndpoint(productSearch().fromHippoBean(document.getCriteria().getSearch()).locale(locale).buildCannedSearch());

        if(!dmsData.cannedSearchHasResults(productSearch().fromHippoBean(document.getCriteria().getSearch()).locale(locale).buildCannedSearchInternal() + "&size=1")){
           String message =String.format( "The Canned search module '%s' does not return any results, please review your search criteria for '%s' at: %s", document.getTitle(), document.getDisplayName(), document.getPath());
           module.addErrorMessage(message);
           contentLog.error(message);
        }

        if (document.getCriteria().getSearch().getProductType().equals(ProductTypes.EVENT.getId())){
            module.setCredit(bundle.getResourceBundle(BUNDLE_ID,"canned-search.credit-events", locale));
        }


        return module;
    }

    /**
     * Fetches a new Product Search Object
     */
    private ProductSearchBuilder productSearch(){
        return VsComponentManager.get(ProductSearchBuilder.class);
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {

    }

    @Override
    CannedSearchModule map(CannedSearch document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return getCannedSearchModule(document, compositionHelper.getLocale());
    }
}
