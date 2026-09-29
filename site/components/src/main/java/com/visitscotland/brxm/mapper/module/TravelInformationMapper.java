package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.TravelInformation;
import com.visitscotland.brxm.hippobeans.TravelInformationArticle;
import com.visitscotland.brxm.hippobeans.TravelInformationTab;
import com.visitscotland.brxm.hippobeans.TravelInformationTransportRow;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.stream.Collectors;

@Component
public class TravelInformationMapper extends ModuleMapper<TravelInformation, TravelInformationModule> {

    private static final Logger logger = LoggerFactory.getLogger(TravelInformationMapper.class);

    static final String TRAVEL_INFO_TRANSPORTS_OPTIONS = "travel-information-transports";

    private final ResourceBundleService resourceBundleService;

    public TravelInformationMapper(ResourceBundleService resourceBundleService) {
        this.resourceBundleService = resourceBundleService;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {}

    @Override
    TravelInformationModule map(TravelInformation document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        return getTravelInformation(document, compositionHelper.getLocale());
    }

    public TravelInformationModule getTravelInformation(TravelInformation document, Locale locale) {
        TravelInformationModule module = new TravelInformationModule();
        module.setTitle(document.getTitle());
        module.setCopy(document.getCopy());
        module.setPracticalInformation(
                document.getPracticalInformation()
                        .stream()
                        .map(content -> getTravelInformationContent(content, locale))
                        .collect(Collectors.toList())
        );

        module.setHippoBean(document);
        return module;
    }

    private TravelInformationTabInterface getTravelInformationContent(
            HippoBean content,
            Locale locale) {

        if (content instanceof TravelInformationArticle) {
            return getTravelInformationArticle(
                    (TravelInformationArticle) content
            );
        }

        if (content instanceof TravelInformationTab) {
            return getTravelInformationTransport(
                    (TravelInformationTab) content,
                    locale
            );
        }

        logger.warn(
                "Unsupported TravelInformation content type: {}",
                content.getClass().getName()
        );

        return null;
    }

    private TravelInformationTransportModule getTravelInformationTransport(
            TravelInformationTab document,
            Locale locale) {

        TravelInformationTransportModule module =
                new TravelInformationTransportModule();

        module.setTitle(document.getTitle());

        module.setPracticalInformationContent(
                document.getAccordion()
                        .stream()
                        .map(row ->
                                getTravelInformationTransportRow(
                                        row,
                                        locale
                                )
                        )
                        .collect(Collectors.toList())
        );

        return module;
    }

    private TravelInformationTransportRowModule
    getTravelInformationTransportRow(
            TravelInformationTransportRow document,
            Locale locale) {

        TravelInformationTransportRowModule module =
                new TravelInformationTransportRowModule();

        module.setTransport(
                getTransportType(document.getTransport(),locale)
        );

        module.setCopy(document.getCopy());

        return module;
    }

    private TransportType getTransportType(String transport, Locale locale) {
        TransportType transportType = new TransportType();
        transportType.setKey(transport);
        String transportLabel = resourceBundleService.getResourceBundle(TRAVEL_INFO_TRANSPORTS_OPTIONS, transport, locale);
        if (transportLabel == null) {
            logger.error("Transport options {} does not contain key {}", TRAVEL_INFO_TRANSPORTS_OPTIONS, transport);
        }
        transportType.setLabel(Contract.defaultIfNull(transportLabel, transport));
        return transportType;
    }

    private TravelInformationArticleModule getTravelInformationArticle(
            TravelInformationArticle document) {

        TravelInformationArticleModule module =
                new TravelInformationArticleModule();

        module.setTitle(document.getHeading());
        module.setPracticalInformationContent(document.getCopy());

        return module;
    }
}
