package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.components.content.GeneralContentComponent;
import com.visitscotland.brxm.factory.*;
import com.visitscotland.brxm.factory.event.EventsListingFactory;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.*;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.LinksModule;
import com.visitscotland.brxm.model.megalinks.MultiImageLinksModule;
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
import static com.visitscotland.brxm.services.ResourceBundleService.GLOBAL_BUNDLE_FILE;

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
    private final MegalinkFactory linksFactory;
    private final ICentreFactory iCentreFactory;
    private final IKnowFactory iKnowFactory;
    private final ArticleMapper articleMapper;
    private final LongCopyFactory longCopyFactory;
    private final UserGeneratedContentMapper userGeneratedContentMapper;
    private final TravelInformationFactory travelInformationFactory;
    private final CannedSearchFactory cannedSearchFactory;
    private final PreviewModeFactory previewFactory;
    private final FormFactory formFactory;
    private final MapFactory mapFactory;
    private final SignpostFactory signPostFactory;
    private final SkiCentreMapper skiCentreMapper;
    private final SkiCentreListMapper skiCentreListMapper;
    private final DevModuleFactory devModuleFactory;
    private final EventsListingFactory eventsListingFactory;
    private final SiteProperties properties;

    private final ResourceBundleService bundle;
    private final Logger contentLogger;


    @Autowired
    public PageAssembler(DocumentUtilsService documentUtils, MegalinkFactory linksFactory, ICentreFactory iCentreFactory,
                         IKnowFactory iKnowFactory, ArticleMapper articleMapper, LongCopyFactory longCopyFactory,
                         UserGeneratedContentMapper userGeneratedContentMapper, TravelInformationFactory travelInformationFactory,
                         CannedSearchFactory cannedSearchFactory, PreviewModeFactory previewFactory, FormFactory marketoFormFactory,
                         MapFactory mapFactory, SkiCentreListMapper skiCentreListMapper, SkiCentreMapper skiCentreMapper, SiteProperties properties,
                         DevModuleFactory devModuleFactory, ResourceBundleService bundle, Logger contentLogger,
                         SignpostFactory signPostFactory, EventsListingFactory eventsListingFactory) {
        this.documentUtils = documentUtils;
        this.linksFactory = linksFactory;
        this.iCentreFactory = iCentreFactory;
        this.iKnowFactory = iKnowFactory;
        this.articleMapper = articleMapper;
        this.longCopyFactory = longCopyFactory;
        this.userGeneratedContentMapper = userGeneratedContentMapper;
        this.travelInformationFactory = travelInformationFactory;
        this.cannedSearchFactory = cannedSearchFactory;
        this.previewFactory = previewFactory;
        this.formFactory = marketoFormFactory;
        this.mapFactory = mapFactory;
        this.devModuleFactory = devModuleFactory;
        this.skiCentreListMapper = skiCentreListMapper;
        this.skiCentreMapper = skiCentreMapper;
        this.properties = properties;
        this.bundle = bundle;
        this.contentLogger = contentLogger;
        this.signPostFactory = signPostFactory;
        this.eventsListingFactory = eventsListingFactory;
    }

    private Page getDocument(HstRequest request) {
        return (Page) request.getAttribute("document");
    }

    public void addModules(HstRequest request) {
        addModules(request, null);
    }

    public void addModules(HstRequest request, String location) {
        PageCompositionHelper page = new PageCompositionHelper(bundle, request);

        for (BaseDocument item : documentUtils.getAllowedDocuments(getDocument(request))) {
            try {
                logger.debug("A {} module was found. Type {}", item.getClass(), item.getPath());
                addModule(request, page, item, location);
            } catch (PageCompostionException e){
                logger.error(e.getMessage());
                page.addModule(previewFactory.createErrorModule(item, e.getMessage()));
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

    private void addModule(HstRequest request, PageCompositionHelper compositionHelper, BaseDocument item, String location) throws PageCompostionException {
        if (item instanceof Megalinks) {
            processMegalinks(request, compositionHelper, (Megalinks) item);
        } else if (item instanceof TourismInformation) {
            processTouristInformation(request,compositionHelper, (TourismInformation) item, location);
        } else if (item instanceof Article){
            articleMapper.include((Article) item, compositionHelper);
        } else if (item instanceof LongCopy){
            processLongCopy(request, compositionHelper, (LongCopy) item);
        } else if (item instanceof MapModule) {
            mapFactory.include((MapModule) item, compositionHelper);
        } else if (item instanceof Stackla) {
            userGeneratedContentMapper.include((Stackla) item, compositionHelper);
        } else if (item instanceof TravelInformation) {
            compositionHelper.addModule(travelInformationFactory.getTravelInformation((TravelInformation) item, request.getLocale()));
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
            compositionHelper.addModule(devModuleFactory.getModule((DevModule) item, labels(request), request.getLocale()));
        } else if (item instanceof CTABanner){
            compositionHelper.addModule(signPostFactory.createModule((CTABanner) item));
        } else if (item instanceof EventsListing){
            compositionHelper.addModule(getEventListingModule(request, (EventsListing) item));
        } else {
            String message = String.format("Unrecognized Module Type: %s", item.getClass());
            logger.warn(message);
            throw new PageCompostionException(message);
        }
    }

    private EventsLingsModule  getEventListingModule(HstRequest request,EventsListing document) {
        addAllLabels(request,"events-listings-module");
        addAllSiteLabels(request, "essentials.pagination");

        return eventsListingFactory.createModule(document);
    }

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
     * Convert a LongCopy into a LongCopy module and adds it to the list of modules
     * Note: Consider to create a factory if the creation of the Module requires more logic.
     */
    private void processLongCopy(HstRequest request, PageCompositionHelper compositionHelper, LongCopy document) {
        Page page = getDocument(request);
        if (page instanceof General && ((General) page).getTheme().equals(GeneralContentComponent.SIMPLE)){
            if (compositionHelper.getModules().stream().anyMatch(LongCopyModule.class::isInstance)){
                logger.error("Only one instance of Long Module is allowed");
                compositionHelper.addModule(new ErrorModule(document, "Only one instance of Long Module module is allowed"));
            } else {
                compositionHelper.addModule(longCopyFactory.getModule(document));
            }
        } else {
            logger.error("The document type LongCopy is only allowed in Simple Pages");
            contentLogger.error("The document type LongCopy is not allowed in this page. Path {}", page.getPath());
            compositionHelper.addModule(new ErrorModule(document, "The document type Long Copy is only allowed in Simple Pages"));
        }
    }

    /**
     * Creates a LinkModule from a Megalinks document
     */
    private void processMegalinks(HstRequest request, PageCompositionHelper compositionHelper, Megalinks item) {
        LinksModule<?> al = linksFactory.getMegalinkModule(item, request.getLocale());
        int numLinks = al.getLinks().size();
        if (al instanceof MultiImageLinksModule) {
            numLinks += ((MultiImageLinksModule) al).getFeaturedLinks().size();
        }
        if (numLinks == 0) {
            contentLogger.warn("Megalinks module at {} contains no valid items", item.getPath());
            compositionHelper.addModule(previewFactory.createErrorModule(al));
            return;
        }

        if (al.getType().equalsIgnoreCase(SingleImageLinksModule.class.getSimpleName())) {
            al.setAlignment(compositionHelper.calculateAlignment());
            if (Contract.isEmpty(al.getAlignment())) {
                logger.warn("The Single Image Megalink module for {} does not have the alignment field defined", item.getPath());
            }
        }

        al.setThemeIndex(compositionHelper.calculateThemeIndex(!Contract.isEmpty(al.getTitle())));
        al.setHippoBean(item);

        if (!item.getPersonalization().isEmpty()) {
            PersonalisationModule personalisationModule = new PersonalisationModule();
            List<Module> personalisationList = new ArrayList<>();
            al.setMarketoId(DEFAULT);
            personalisationList.add(al);
            for (Personalization personalisationMegalink : item.getPersonalization()) {
                personalisationList.add(processPersonalisation(request, (Megalinks)personalisationMegalink.getModule(), personalisationMegalink.getId(), al));
            }
            personalisationModule.setModules(personalisationList);

            compositionHelper.addModule(personalisationModule);
        } else {
            compositionHelper.addModule(al);
        }

        addGlobalLabel(request, "third-party-error");
    }
    private Module<Megalinks> processPersonalisation(HstRequest request, Megalinks item, String marketoId, LinksModule<?> parent) {
        LinksModule<?> al = linksFactory.getMegalinkModule(item, request.getLocale());

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

    private boolean isICentreLanding(HstRequest request) {
        return request.getPathInfo().equals(properties.getSiteICentre().substring(0, properties.getSiteICentre().length() - 8));
    }
    /**
     * Creates a LinkModule from a TouristInformation document
     */
    private void processTouristInformation(HstRequest request, PageCompositionHelper compositionHelper, TourismInformation touristInfo, String location) {
        if (!isICentreLanding(request)) {
            ICentreModule iCentreModule = iCentreFactory.getModule(touristInfo.getICentre(), request.getLocale(), location);

            if (iCentreModule != null) {
                iCentreModule.setHippoBean(touristInfo);
                compositionHelper.addModule(iCentreModule);
            }
        }
        if (properties.isIknowEnabled()) {
            IKnowModule iKnowModule = iKnowFactory.getIKnowModule(touristInfo.getIKnow(), location, request.getLocale());
            iKnowModule.setHippoBean(touristInfo);

            compositionHelper.addModule(iKnowModule);
        }
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

    private void addGlobalLabel(HstRequest request, String key) {
        labels(request).get(GLOBAL_BUNDLE_FILE).put(key, bundle.getResourceBundle(GLOBAL_BUNDLE_FILE, key, request.getLocale()));
    }

    private void addAllLabels(HstRequest request, String bundleName) {
        labels(request).put(bundleName, bundle.getAllLabels(bundleName, request.getLocale()));
    }

    private void addAllSiteLabels(HstRequest request, String bundleName) {
        labels(request).put(bundleName, bundle.getAllLabels(bundleName, request.getLocale()));
    }
}
