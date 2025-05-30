package com.visitscotland.brxm.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.hippobeans.SkiCentre;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.SkiModule;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionHelper;
import com.visitscotland.utils.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.*;

@Component
public class SkiCentreMapper extends ModuleMapper<SkiCentre, SkiModule>{

    static final String BUNDLE_FILE = "ski";

    private final DMSDataService dataService;
    private final ResourceBundleService bundle;

    private final ContentLogger contentLogger;

    public SkiCentreMapper(DMSDataService dataService, ResourceBundleService bundle, ContentLogger contentLogger) {
        this.dataService = dataService;
        this.bundle = bundle;
        this.contentLogger = contentLogger;
    }

    @Override
    public void include(SkiCentre document, PageCompositionHelper page) {
        super.include(document, page);
        page.addAllSiteLabels(BUNDLE_FILE);
    }

    @Override
    SkiModule map(SkiCentre document, Locale locale) {
        SkiModule module = new SkiModule();

        module.setHippoBean(document);
        module.setTitle(document.getTitle());
        module.setIntroduction(document.getCopy());
        module.setFeedURL(document.getFeed());
        module.setPisteMap(document.getPisteMap());

        /*
         * Note: ProductID is currently mandatory in the CMS but, the DMS might be retired by the end of 2025 and this
         * field might be removed
         */
        if (!Contract.isEmpty(document.getProductId())) {
            JsonNode product = dataService.productCard(document.getProductId(), locale);

            if (product == null) {
                String errorMessage = String.format("The DMS product associated with this Ski Centre (ID=%s) is not available", document.getProductId());
                contentLogger.error("{}. Path={} ", errorMessage, document.getPath());
                module.addErrorMessage(errorMessage);
            } else {
                populateDmsData(module, product, locale);
            }
        }

        return module;
    }

    /**
     * Reads the data from the Json Object to populate the fields in the module.
     *
     * @param module SkiCentre Module
     *
     * @param product JSON Object containing the Product data.
     */
    private void populateDmsData(@NotNull SkiModule module, @NotNull JsonNode product,@NotNull Locale locale){
        List<JsonNode> links;

        if (product.has(ADDRESS)) {
            module.setAddress(product.get(ADDRESS));
        }

        if (product.has(TELEPHONE_NUMBER)) {
            module.setPhone(product.get(TELEPHONE_NUMBER).asText());
        }

        if (product.has(WEBSITE)) {
            module.setWebsite(product.get(WEBSITE));
        }

        if (product.has(OPENING)){
            if (product.has(URL) && product.get(URL).has(URL_LINK)) {
                module.setOpeningLink(new FlatLink(bundle.getResourceBundle(BUNDLE_FILE, "ski-centre.opening-times.label", locale),
                    product.get(URL).get(URL_LINK).asText() + "#opening", LinkType.INTERNAL));
            } else {
                contentLogger.warn("Missing URL or URL_LINK for Ski Centre with opening times. Path={}",
                module.getHippoBean() != null ? module.getHippoBean().getPath() : "unknown");
            }
        }

        if (product.has(SOCIAL_CHANNEL)) {
            links = new ArrayList<>(product.get(SOCIAL_CHANNEL).size());
            for (JsonNode channel : product.get(SOCIAL_CHANNEL)) {
                links.add(channel);
            }
        } else {
             links = Collections.emptyList();
        }
        module.setSocialChannels(links);
    }
}
