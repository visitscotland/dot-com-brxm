package com.visitscotland.brxm.mapper;


import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.DMSConstants;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.hippobeans.CannedSearch;
import com.visitscotland.brxm.hippobeans.CannedSearchTours;
import com.visitscotland.brxm.model.CannedSearchModule;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.CMSProperties;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.pagebuilder.InvalidContentException;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import vs.ase.dms.ProductTypes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class CannedSearchMapper extends ModuleMapper<CannedSearchTours, CannedSearchModule> {

    private static final Logger logger = LoggerFactory.getLogger(CannedSearchMapper.class);


    static final String BUNDLE_ID = "canned-search";

    private final ResourceBundleService bundle;
    private final LinkService linkService;
    private final CMSProperties properties;
    private final DMSDataService dmsData;
    private final Logger contentLog;

    public CannedSearchMapper(ResourceBundleService bundle, LinkService linkService, CMSProperties properties, DMSDataService dmsData,
                              ContentLogger contentLogger){
        this.bundle = bundle;
        this.linkService = linkService;
        this.properties = properties;
        this.dmsData = dmsData;
        this.contentLog = contentLogger;
    }

    public CannedSearchModule getCannedSearchToursModule(CannedSearchTours document, Locale locale) throws PageCompositionException {
        logger.info("Creating CannedSearchToursModule for {}", document.getPath());
        CannedSearchModule module = new CannedSearchModule();
        module.setHippoBean(document);
        module.setTitle(document.getTitle());
        module.setDescription(document.getCopy());
        module.setProductType("tour");

        URL documentToursSearchUrl;
        try {
            documentToursSearchUrl = new URL(document.getToursSearch());
        } catch (MalformedURLException e) {
            throw new InvalidContentException(document.getPath(),
                    String.format("Invalid tours search URL %s on CannedSearchToursModule", document.getPath()));
        }

        String toursSearchQueryString = Contract.defaultIfNull(documentToursSearchUrl.getQuery(), "");
        String tmsCannedSearchUrl = UriComponentsBuilder.fromUriString(properties.getDmsDataPublicHost())
                .path(DMSConstants.VS_DMS_CANNED_SEARCH_TOURS)
                .query(toursSearchQueryString)
                .queryParam("locale", locale.toLanguageTag())
                .queryParam("limit", 24)
                .build().toString();

        module.setCannedSearchEndpoint(tmsCannedSearchUrl);

        if(!dmsData.cannedSearchHasResults(tmsCannedSearchUrl + "&limit=1")){
            String message = String.format( "The Canned search tours module '%s' does not return any results, please review your search criteria for '%s' at: %s", document.getTitle(), document.getDisplayName(), document.getPath());
            module.addErrorMessage(message);
            contentLog.error(message);
        }

        FlatLink viewAllCta = linkService.createExternalLink(document.getToursSearch(), document.getPath());
        if (!Contract.isEmpty(document.getViewAll())) {
            viewAllCta.setLabel(document.getViewAll());
        } else {
            viewAllCta.setLabel(bundle.getResourceBundle(BUNDLE_ID, "canned-search.listview", locale));
        }
        module.setViewAllLink(viewAllCta);
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
    CannedSearchModule map(CannedSearchTours document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return getCannedSearchToursModule(document, compositionHelper.getLocale());
    }
}
