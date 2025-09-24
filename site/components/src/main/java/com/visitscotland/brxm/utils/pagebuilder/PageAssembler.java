package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.factory.*;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.*;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import com.visitscotland.brxm.model.megalinks.SingleImageLinksModule;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.visitscotland.brxm.components.content.PageContentComponent.LABELS;

@Component
public class PageAssembler {

    private static final Logger logger = LoggerFactory.getLogger(PageAssembler.class);

    //Static Constant
    static final String INTRO_THEME = "introTheme";
    static final String PAGE_ITEMS = "pageItems";

    static final String DEFAULT = "default";


    //Utils
    private final DocumentUtilsService documentUtils;

    //Factories
    private final MegalinkMapper megalinkMapper;
    private final ICentreMapper iCentreMapper;
    private final IKnowMapper iKnowMapper;
    private final ArticleMapper articleMapper;
    private final UserGeneratedContentMapper userGeneratedContentMapper;
    private final TravelInformationMapper travelInformationMapper;
    private final PreviewWarningMapper previewWarningMapper;
    private final MapModuleMapper mapModuleMapper;
    private final SkiCentreMapper skiCentreMapper;
    private final SkiCentreListMapper skiCentreListMapper;
    private final DevModuleMapper devModuleMapper;

    private final LongCopyMapper longCopyMapper;
    private final CannedSearchFactory cannedSearchFactory;
    private final FormFactory formFactory;
    private final SpotlightMapper spotlightMapper;
    private final EventsListingMapper eventsListingMapper;

    private final SiteProperties properties;

    private final ResourceBundleService bundle;
    private final Logger contentLogger;


    @Autowired
    public PageAssembler(DocumentUtilsService documentUtils, MegalinkMapper megalinkMapper, ICentreMapper iCentreMapper,
                         IKnowMapper iKnowMapper, ArticleMapper articleMapper, LongCopyMapper longCopyMapper,
                         UserGeneratedContentMapper userGeneratedContentMapper, TravelInformationMapper travelInformationMapper,
                         CannedSearchFactory cannedSearchFactory, PreviewWarningMapper previewWarningMapper, FormFactory marketoFormFactory,
                         MapModuleMapper mapModuleMapper, SkiCentreListMapper skiCentreListMapper, SkiCentreMapper skiCentreMapper, SiteProperties properties,
                         DevModuleMapper devModuleMapper, ResourceBundleService bundle, Logger contentLogger,
                         SpotlightMapper spotlightMapper, EventsListingMapper eventsListingFactory) {
        this.documentUtils = documentUtils;
        this.megalinkMapper = megalinkMapper;
        this.iCentreMapper = iCentreMapper;
        this.iKnowMapper = iKnowMapper;
        this.articleMapper = articleMapper;
        this.longCopyMapper = longCopyMapper;
        this.userGeneratedContentMapper = userGeneratedContentMapper;
        this.travelInformationMapper = travelInformationMapper;
        this.cannedSearchFactory = cannedSearchFactory;
        this.previewWarningMapper = previewWarningMapper;
        this.formFactory = marketoFormFactory;
        this.mapModuleMapper = mapModuleMapper;
        this.devModuleMapper = devModuleMapper;
        this.skiCentreListMapper = skiCentreListMapper;
        this.skiCentreMapper = skiCentreMapper;
        this.properties = properties;
        this.bundle = bundle;
        this.contentLogger = contentLogger;
        this.spotlightMapper = spotlightMapper;
        this.eventsListingMapper = eventsListingFactory;
    }

    private Page getDocument(HstRequest request) {
        return (Page) request.getAttribute("document");
    }

    public void addModules(HstRequest request) {
        PageCompositionHelper page = new PageCompositionHelper(bundle, request);

        for (BaseDocument item : documentUtils.getAllowedDocuments(getDocument(request))) {
            try {
                logger.debug("A {} module was found. Type {}", item.getClass(), item.getPath());
                addModule(request, page, item);
            } catch (PageCompositionException e){
                if (e instanceof InvalidContentException){
                    contentLogger.error(e.getMessage());
                } else {
                    logger.error(e.getMessage());
                }
                page.addModule(previewWarningMapper.createErrorModule(item, e.getMessage()));
            } catch (RuntimeException e) {
                // Note: This exception should not happen. We are catching it for the sake of recoverability
                logger.error("Uncaught Exception while building a page at {}: {}", request.getRequestURI(), e.getMessage(), e);
            }
        }

        setIntroTheme(request, page.getFirstModule());

        if (page.getModules().isEmpty() && Boolean.FALSE.equals(getDocument(request).getSeoNoIndex())){
            contentLogger.warn("The page {} does not have any modules published", request.getRequestURI());
        }

        request.setModel(PAGE_ITEMS, page.getModules());
    }

    private void addModule(HstRequest request, PageCompositionHelper compositionHelper, BaseDocument item) throws PageCompositionException {
        if (item instanceof Megalinks) {
            processMegalinks(request, compositionHelper, (Megalinks) item);
        } else if (item instanceof TourismInformation) {
            iCentreMapper.include((TourismInformation) item, compositionHelper);
        } else if (item instanceof Article){
            articleMapper.include((Article) item, compositionHelper);
        } else if (item instanceof LongCopy){
            longCopyMapper.include((LongCopy) item, compositionHelper);
        } else if (item instanceof MapModule) {
            mapModuleMapper.include((MapModule) item, compositionHelper);
        } else if (item instanceof Stackla) {
            userGeneratedContentMapper.include((Stackla) item, compositionHelper);
        } else if (item instanceof TravelInformation) {
            travelInformationMapper.include((TravelInformation) item, compositionHelper);
        } else if (item instanceof CannedSearch) {
            compositionHelper.addModule(cannedSearchFactory.getCannedSearchModule((CannedSearch) item, request.getLocale()));
        } else if (item instanceof CannedSearchTours) {
            compositionHelper.addModule(cannedSearchFactory.getCannedSearchToursModule((CannedSearchTours) item, request.getLocale()));
        } else if (item instanceof MarketoForm || item instanceof Form) {
            compositionHelper.addModule(getForm(request, item));
        } else if (item instanceof SkiCentre){
            skiCentreMapper.include((SkiCentre) item, compositionHelper);
        } else if (item instanceof SkiCentreList){
            skiCentreListMapper.include((SkiCentreList) item, compositionHelper);
        } else if (item instanceof DevModule){
            devModuleMapper.include((DevModule) item, compositionHelper);
        } else if (item instanceof CTABanner){
            spotlightMapper.include((CTABanner) item, compositionHelper);
        } else if (item instanceof EventsListing){
            eventsListingMapper.include((EventsListing) item, compositionHelper);
        } else {
            throw new PageCompositionException(item.getPath(), String.format("Unrecognized Module Type: %s", item.getClass()));
        }
    }

    /**
     * TODO: Marketo need to be retired before re
     */
    private FormModule getForm(HstRequest request, BaseDocument form){

        addAllLabels(request, "forms");
        Map<String, String> formLabels = labels(request).get("forms");

        //The following files are required independent of the Form Framework
        formLabels.put("cfg.form.json.countries", properties.getProperty("form.json.countries").orElse(""));
        formLabels.put("cfg.form.json.messages", properties.getProperty("form.json.messages").orElse(""));

        if (form instanceof MarketoForm) {
            return formFactory.getModule((MarketoForm) form);
        } else if (form instanceof Form) {
            return formFactory.getModule((Form) form);
        } else if (form != null) {
            logger.error("Form Class not recognized {}, path = {}", form.getClass(), form.getPath());
        }
        return null;
    }

    /**
     * Creates a LinkModule from a Megalinks document
     */
    @Deprecated(forRemoval = true)
    private void processMegalinks(HstRequest request, PageCompositionHelper compositionHelper, Megalinks document) throws PageCompositionException {
        // TODO: Rework personalization so Megalinks could be used as any other mapper
        // megalinkMapper.include(document, compositionHelper);

        LinksModule<?> al = megalinkMapper.getMegalinkModule(document, request.getLocale());

        //Note that personalization is currently disabled.
        if (!document.getPersonalization().isEmpty()) {
            PersonalisationModule personalisationModule = new PersonalisationModule();
            List<Module> personalisationList = new ArrayList<>();
            al.setMarketoId(DEFAULT);
            personalisationList.add(al);
            for (Personalization personalisationMegalink : document.getPersonalization()) {
                personalisationList.add(processPersonalisation(request, (Megalinks)personalisationMegalink.getModule(), personalisationMegalink.getId(), al));
            }
            personalisationModule.setModules(personalisationList);

            compositionHelper.addModule(personalisationModule);
        } else {
            compositionHelper.addModule(al);
        }

        compositionHelper.addGlobalLabel( "third-party-error");
    }

    @Deprecated(forRemoval = true)
    private Module<Megalinks> processPersonalisation(HstRequest request, Megalinks item, String marketoId, LinksModule<?> parent)  throws PageCompositionException {
        LinksModule<?> al = megalinkMapper.getMegalinkModule(item, request.getLocale());

        al.setThemeIndex(parent.getThemeIndex());
        al.setHippoBean(item);

        if (!Contract.isEmpty(marketoId)) {
            al.setMarketoId(marketoId);
        }

        if (al.getType().equalsIgnoreCase(SingleImageLinksModule.class.getSimpleName())) {
            al.setAlignment(parent.getAlignment());
            if (Contract.isEmpty(al.getAlignment())) {
                logger.warn("The Single Image Megalink module for {} does not have the alignment field defined", item.getPath());
            }
        }

        return al;
    }

    /**
     * Sets the theme for the intro of the page based on the list of modules.
     * @param request HstRequest request
     * @param firstModule List Modules
     */
    private void setIntroTheme(HstRequest request, Optional<Module<?>> firstModule) {
        if(firstModule.isPresent() && firstModule.get() instanceof LinksModule){
            request.setModel(INTRO_THEME, ((LinksModule<?>) firstModule.get()).getThemeIndex());
        }
    }

    private Map<String, Map<String, String>> labels(HstRequest request) {
        if (request.getModel(LABELS) == null) {
            Map<String, Map<String, String>> labels = new HashMap<>();
            request.setModel(LABELS, labels);
            return labels;
        }

        return request.getModel(LABELS);
    }

    private void addAllLabels(HstRequest request, String bundleName) {
        labels(request).put(bundleName, bundle.getAllLabels(bundleName, request.getLocale()));
    }

    private void addAllSiteLabels(HstRequest request, String bundleName) {
        labels(request).put(bundleName, bundle.getAllLabels(bundleName, request.getLocale()));
    }
}
