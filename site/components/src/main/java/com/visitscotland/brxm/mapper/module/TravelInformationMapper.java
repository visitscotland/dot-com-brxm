package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.TravelInformation;
import com.visitscotland.brxm.hippobeans.TravelInformationTab;
import com.visitscotland.brxm.hippobeans.TravelInformationTransportRow;
import com.visitscotland.brxm.model.TransportType;
import com.visitscotland.brxm.model.TravelInformationModule;
import com.visitscotland.brxm.model.TravelInformationModuleTab;
import com.visitscotland.brxm.model.TravelInformationTransportRowModule;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.utils.Contract;
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
        module.setGettingTo(getTravelInformationModuleTab(document.getGettingTo(), locale));
        module.setGettingAround(getTravelInformationModuleTab(document.getGettingAround(), locale));
        module.setHippoBean(document);
        return module;
    }

    private TravelInformationModuleTab getTravelInformationModuleTab(TravelInformationTab document, Locale locale) {
        TravelInformationModuleTab module = new TravelInformationModuleTab();
        module.setTitle(document.getTitle());
        module.setTravelInformationTransportRows(document.getAccordion()
                .stream().map(travelInformationTransportRow -> getTravelInformationTransportRowModule(travelInformationTransportRow, locale))
                .collect(Collectors.toList()));
        module.setHippoBean(document);
        return module;
    }

    private TravelInformationTransportRowModule getTravelInformationTransportRowModule(TravelInformationTransportRow document, Locale locale) {
        TravelInformationTransportRowModule module = new TravelInformationTransportRowModule();
        module.setCopy(document.getCopy());
        module.setHippoBean(document);
        TransportType transportType = new TransportType();
        transportType.setKey(document.getTransport());
        String transportLabel = resourceBundleService.getResourceBundle(TRAVEL_INFO_TRANSPORTS_OPTIONS, document.getTransport(), locale);
        if (transportLabel == null) {
            logger.error("Transport options {} does not contain key {}", TRAVEL_INFO_TRANSPORTS_OPTIONS, document.getTransport());
        }
        transportType.setLabel(Contract.defaultIfNull(transportLabel, document.getTransport()));
        module.setTransport(transportType);
        return module;
    }

}
