package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.components.content.service.AuthorService;
import com.visitscotland.brxm.components.content.service.CludoService;
import com.visitscotland.brxm.components.content.service.FavouritesService;
import com.visitscotland.brxm.components.content.service.PageLabels;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.BlogFactory;
import com.visitscotland.brxm.factory.NewsletterFactory;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.mapper.PreviewWarningMapper;
import com.visitscotland.brxm.mapper.module.MegalinkMapper;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.HorizontalListLinksModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.MetadataFactory;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.brxm.utils.SitePropertyKeys;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PageContentComponent<T extends Page> extends ContentComponent {

    private static final Logger logger = LoggerFactory.getLogger(PageContentComponent.class);

    //Resource Bundle
    private static final String VIDEO_BUNDLE = "video";
    private static final String SEARCH_BUNDLE = "search-labels";
    private static final String TABLE_CONTENTS_BUNDLE = "table-contents";
    private static final String MEGALINKS_BUNDLE = "megalinks";
    private static final String AMBIENT_VIDEO_BUNDLE = "ambient-video";

    //TODO Duplicate where it is used
    protected static final String OTYML_BUNDLE = "otyml";

    private static final String MAIN_MAP_PATH = "main-map-path";

    private static final String SEARCH = "search";

    //Objects injected in the page payload
    public static final String DOCUMENT = "document";
    public static final String EDIT_MODE = "editMode";

    public static final String AUTHOR = "author";
    public static final String NEWSLETTER_SIGNPOST = "newsletterSignpost";
    public static final String PREVIEW_ALERTS = "alerts";
    public static final String PAGE_CONFIGURATION = "pageConfiguration";

    public static final String HERO_IMAGE = "heroImage";
    public static final String HERO_VIDEO = "heroVideo";
    public static final String VIDEO_HEADER = "videoHeader";
    public static final String HERO_AMBIENT_VIDEO = "hero-ambient-video";

    public static final String METADATA_MODEL = "metadata";
    public static final String GTM = "gtm";

    final BlogFactory blogFactory;
    protected final MegalinkMapper megalinkMapper;
    private final ImageMapper imageMapper;
    private final LinkService linksService;
    private final NewsletterFactory newsletterFactory;
    private final PreviewWarningMapper previewMapper;
    private final ResourceBundleService bundle;
    private final SiteProperties properties;
    private final Logger contentLogger;
    private final CludoService cludoService;
    private final FavouritesService favouritesService;
    private final PageLabels pageLabels;
    private final AuthorService authorService;

    private final MetadataFactory metadata;

    public PageContentComponent() {
        authorService = VsComponentManager.get(AuthorService.class);
        blogFactory = VsComponentManager.get(BlogFactory.class);
        megalinkMapper = VsComponentManager.get(MegalinkMapper.class);
        imageMapper = VsComponentManager.get(ImageMapper.class);
        newsletterFactory = VsComponentManager.get(NewsletterFactory.class);
        linksService = VsComponentManager.get(LinkService.class);
        previewMapper = VsComponentManager.get(PreviewWarningMapper.class);
        contentLogger = VsComponentManager.get(ContentLogger.class);
        properties = VsComponentManager.get(SiteProperties.class);
        bundle = VsComponentManager.get(ResourceBundleService.class);
        metadata = VsComponentManager.get(MetadataFactory.class);
        cludoService = VsComponentManager.get(CludoService.class);
        favouritesService = VsComponentManager.get(FavouritesService.class);
        pageLabels = VsComponentManager.get(PageLabels.class);
    }

    ResourceBundleService getBundle() {
        return bundle;
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        throw new UnsupportedOperationException(
                "doBeforeRender(HstRequest, HstResponse) is not supported. " +
                "Use doBeforeRender(HstRequest, HstResponse, PageCompositionHelper) instead.");
    }

    public void doBeforeRender(HstRequest request, HstResponse response, PageCompositionHelper pageConfig) {
        super.doBeforeRender(request, response);

        addMetadata(request);
        addHeroImage(request, pageConfig);

        addOTYML(request, pageConfig);
        addNewsletterSignup(request);
        addBlog(pageConfig);
        addGtmConfiguration(request);
        pageLabels.includeGeneralLabels(pageConfig, isEditMode(request));
        addSiteSpecificConfiguration(request, pageConfig);
        //TODO review labels for search once we have time to delete current bundles
        pageConfig.addAllLabelsSpecificName(SEARCH_BUNDLE, SEARCH);
    }

    /**
     * Adds Metadata about the application to the request
     *
     * @see MetadataFactory
     */
    private void addMetadata(HstRequest request){
        request.setModel(METADATA_MODEL, metadata.getMetadata());
    }

    /**
     * Include GTM Configuration to the {@link HstRequest}
     *
     * @param request HstRequest
     */
    private void addGtmConfiguration(HstRequest request) {

        Map<String, String> gtmProperties = new HashMap<>();

        gtmProperties.put(SitePropertyKeys.GTM_CONTAINER_ID, properties.getGtmContainerId());
        gtmProperties.put(SitePropertyKeys.GTM_PREVIEW_QUERY_STRING, properties.getGtmPreviewQueryString());
        gtmProperties.put(SitePropertyKeys.GTM_IS_PRODUCTION, properties.getGtmIsProduction());

        request.setModel(GTM, gtmProperties);
    }


    /**
     * - Alerts are only used for issues related with the hero image at the moment
     * - Hero Image is not necessary for all document types. Is it better to add the field in order to keep consistency?
     */
    private void addHeroImage(HstRequest request, PageCompositionHelper pageConfig) {
        Module<T> introModule = new Module<>();

        FlatImage heroImage = imageMapper.createImage(getDocument(request).getHeroImage(), introModule, request.getLocale());
        if (getDocument(request).getHeroImage() == null) {
            String message = String.format("The image selected for '%s' is not available, please select a valid image for '%s' at: %s ",
                    getDocument(request).getTitle(), getDocument(request).getDisplayName(), getDocument(request).getPath());
            contentLogger.warn(message);
            introModule.addErrorMessage(message);
        }
        request.setModel(HERO_IMAGE, heroImage);

        includeHeroVideo(request, introModule, pageConfig);

        if (!Contract.isEmpty(introModule.getErrorMessages())) {
            setErrorMessages(request, introModule.getErrorMessages());
        }
    }

    private void includeHeroVideo(HstRequest request, Module<T> introModule, PageCompositionHelper pageConfig) {
        VideoLink videoDocument = getDocument(request).getHeroVideo();
        if (videoDocument != null && videoDocument.getVideoLink() != null) {
            EnhancedLink video = linksService.createVideo(videoDocument.getVideoLink(), introModule, request.getLocale());
            if (Contract.isEmpty(video.getYoutubeId())) {
                request.setModel(VIDEO_HEADER, true);
                pageConfig.addProperty(HERO_AMBIENT_VIDEO, true);
                pageConfig.addAllSiteLabels(AMBIENT_VIDEO_BUNDLE);
            } else {
                pageConfig.addAllSiteLabels(VIDEO_BUNDLE);
            }
            request.setModel(HERO_VIDEO, video);
        }
    }

    /**
     * Set the OTYML module if present
     */
    protected void addOTYML(HstRequest request, PageCompositionHelper pageConfig) {
        final String PAGINATION_BUNDLE = "essentials.pagination";

        Page page = getDocument(request);
        if (page.getOtherThings() != null) {
            HorizontalListLinksModule otyml = megalinkMapper.horizontalListLayout(page.getOtherThings(), request.getLocale());
            if (Contract.isEmpty(otyml.getLinks())) {
                contentLogger.warn("OTYML at {} contains 0 published items. Skipping module", page.getOtherThings().getPath());
                request.setModel(OTYML_BUNDLE, previewMapper.createErrorModule(otyml));
                return;
            }
            if (otyml.getLinks().size() < MegalinkMapper.MIN_ITEMS_CAROUSEL) {
                contentLogger.warn("OTYML at {} contains only {} published items. Expected a minimum of {}",
                        page.getOtherThings().getPath(), otyml.getLinks().size(), MegalinkMapper.MIN_ITEMS_CAROUSEL);
            }
            request.setModel(OTYML_BUNDLE, otyml);
        }

        pageConfig.addAllSiteLabels(OTYML_BUNDLE);
        pageConfig.addAllSiteLabels(MEGALINKS_BUNDLE);
        pageConfig.addAllSiteLabels(PAGINATION_BUNDLE);
    }
//
//    /**
//     * Returns the labels object from the request if it exists, otherwise, creates a new one and adds it to the request
//     * @param request HstRequest
//     * @return labels object from the request
//     */
//    protected Map<String, Map<String, String>> labels(HstRequest request) {
//        if (request.getModel(LABELS) == null) {
//            Map<String, Map<String, String>> labels = new HashMap<>();
//            request.setModel(LABELS, labels);
//            return labels;
//        }
//
//        return request.getModel(LABELS);
//    }


    //TODO: The author object should be eliminated in future iterations

    /**
     * @deprecated It is discourage to put add objects on the root of thethe payload. This object has been duplicated
     * into the pageIntro object
     *
     */
    @Deprecated(forRemoval = true)
    protected void addBlog(PageCompositionHelper pageConfig) {
        authorService.getBlog(pageConfig).ifPresent( blog ->
            pageConfig.getRequest().setModel(AUTHOR, blog)
        );
    }

    /**
     * Adds the newsletter configuration to the request taking into account the target: (VisitScotland, Business Events or Ski)
     *
     * @param request HstRequest
     */
    protected void addNewsletterSignup(HstRequest request) {
        Page page = getDocument(request);
        if (Boolean.FALSE.equals(Contract.defaultIfNull(page.getHideNewsletter(), false))) {
            Optional<SignpostModule> signpost;
            if (request.getPathInfo().contains(properties.getSiteSkiSection())) {
                signpost = newsletterFactory.createSnowAlertsModule(request.getLocale());
            } else {
                signpost = newsletterFactory.createNewsletterSignpostModule(request.getLocale());
            }

            signpost.ifPresent(signpostModule -> request.setModel(NEWSLETTER_SIGNPOST, signpostModule));
        }
    }

    /**
     * Return the document from the request
     *
     * @param request HstRequest
     * @return the main document of
     */
    @SuppressWarnings("unchecked")
    protected T getDocument(HstRequest request) {
        if (request.getAttribute(DOCUMENT) instanceof Page) {
            return (T) request.getAttribute(DOCUMENT);
        } else {
            logger.error("The main document is not an instance of Page. Requested URL = {}", request.getRequestURI(), new ClassCastException());
            return null;
        }
    }

    /**
     * Add a List of errors to the HstRequest
     * @param request HstRequest
     * @param errorMessages List of messages to be added to the request
     */
    public static void setErrorMessages(HstRequest request, Collection<String> errorMessages) {
        if (request.getModel(PREVIEW_ALERTS) != null) {
            Collection<String> requestMessages = request.getModel(PREVIEW_ALERTS);
            requestMessages.addAll(errorMessages);
        } else {
            request.setModel(PREVIEW_ALERTS, errorMessages);
        }
    }





    /**
     * Add Configuration specific to the VisitScotland.com or businessevents site
     * @param request HSt request
     */
    private void addSiteSpecificConfiguration(HstRequest request, PageCompositionHelper pageConfig) {

        if (properties.isFavouritesEnabled(request.getLocale())){
            favouritesService.applyConfiguration(request, pageConfig);
        }

        if (properties.isTableOfContentsEnabled()){
            pageConfig.addAllSiteLabels(TABLE_CONTENTS_BUNDLE);
        }

        pageConfig.addProperty(SitePropertyKeys.FEATURE_HERO_SECTION, properties.getFeatureHeroSection());

        if (!Contract.isEmpty(properties.getSiteMap(request.getLocale()))) {
            //TODO remove this request setModel once fed are not using it
            request.setModel(MAIN_MAP_PATH, properties.getSiteMap(request.getLocale()));
            pageConfig.addProperty(MAIN_MAP_PATH, properties.getSiteMap(request.getLocale()));
        }

        if (properties.isGlobalSearchEnabled()){
            cludoService.applyConfiguration(request, pageConfig);
        }
    }

    boolean isEditMode(HstRequest request) {
        return Boolean.TRUE.equals(request.getAttribute(EDIT_MODE));
    }
}

