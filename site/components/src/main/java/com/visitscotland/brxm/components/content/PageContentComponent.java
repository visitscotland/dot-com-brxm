package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.*;
import com.visitscotland.brxm.factory.NewsletterFactory;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.mapper.module.MegalinkMapper;
import com.visitscotland.brxm.mapper.PreviewWarningMapper;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.HorizontalListLinksModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.MetadataFactory;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class PageContentComponent<T extends Page> extends ContentComponent {

    private static final Logger logger = LoggerFactory.getLogger(PageContentComponent.class);

    /* Should we use Content Logger instead of Freemarker?
     *
     * TODO: Verify usage of this logger and decide what to do with this
     *
     * Note: This freemarker logger is not available to SPA SDK
     */
    private final Logger freemarkerLogger = LoggerFactory.getLogger("freemarker");

    //Resource Bundle
    private static final String SOCIAL_SHARE_BUNDLE = "social.share";
    private static final String VIDEO_BUNDLE = "video";
    private static final String SKIP_TO_BUNDLE = "skip-to";
    private static final String SEARCH_BUNDLE = "search";
    private static final String CMS_MESSAGES_BUNDLE = "cms-messages";
    private static final String SEO_BUNDLE = "seo";
    private static final String TABLE_CONTENTS_BUNDLE = "table-contents";
    private static final String MEGALINKS_BUNDLE = "megalinks";

    //TODO Duplicate where it is used
    protected static final String OTYML_BUNDLE = "otyml";

    //Objects injected in the page payload
    public static final String DOCUMENT = "document";
    public static final String EDIT_MODE = "editMode";

    public static final String AUTHOR = "author";
    public static final String NEWSLETTER_SIGNPOST = "newsletterSignpost";
    public static final String PREVIEW_ALERTS = "alerts";
    public static final String LABELS = "labels";

    public static final String HERO_IMAGE = "heroImage";
    public static final String HERO_VIDEO = "heroVideo";
    public static final String VIDEO_HEADER = "videoHeader";
    public static final String PSR_WIDGET = "psrWidget";

    public static final String SEARCH_RESULTS = "searchResultsPage";
    public static final String METADATA_MODEL = "metadata";
    public static final String GTM = "gtm";

    final BlogFactory blogFactory;
    protected final MegalinkMapper megalinkMapper;
    private final ImageMapper imageFactory;
    private final LinkService linksService;
    private final NewsletterFactory newsletterFactory;
    private final ProductSearchWidgetFactory psrFactory;
    private final PreviewWarningMapper previewMapper;
    private final ResourceBundleService bundle;
    private final SiteProperties properties;
    private final Logger contentLogger;

    private final MetadataFactory metadata;

    public PageContentComponent() {
        blogFactory = VsComponentManager.get(BlogFactory.class);
        megalinkMapper = VsComponentManager.get(MegalinkMapper.class);
        imageFactory = VsComponentManager.get(ImageMapper.class);
        newsletterFactory = VsComponentManager.get(NewsletterFactory.class);
        linksService = VsComponentManager.get(LinkService.class);
        psrFactory = VsComponentManager.get(ProductSearchWidgetFactory.class);
        previewMapper = VsComponentManager.get(PreviewWarningMapper.class);
        contentLogger = VsComponentManager.get(ContentLogger.class);
        properties = VsComponentManager.get(SiteProperties.class);
        bundle = VsComponentManager.get(ResourceBundleService.class);
        metadata = VsComponentManager.get(MetadataFactory.class);

    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        addMetadata(request);

        addHeroImage(request);

        addOTYML(request);
        addNewsletterSignup(request);
        addLogging(request);
        addBlog(request);
        addGtmConfiguration(request);

        addLabels(request);
        addSiteSpecificConfiguration(request);
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
     * Adds labels that are necessary for type of pages. Please notice that there are two strategies for including properties
     * <br>
     * When all labels are required you should use {@code bundle.getAllLabels(...)}. However, in the case that only
     * some of them are needed we can create a new {@code Map} object and include them one by one. (i.e. global labels)
     * </ul>
     *
     * @param request HstRequest
     */
    private void addLabels(HstRequest request) {


        labels(request).put(ResourceBundleService.GLOBAL_BUNDLE_FILE, getGlobalLabels(request.getLocale()));

        addNavigationLabels(request);
        
        addAllLabels(request, SOCIAL_SHARE_BUNDLE);
        addAllLabels(request, SEARCH_BUNDLE);
        addAllLabels(request, VIDEO_BUNDLE);
        addAllLabels(request, SEO_BUNDLE);
        addAllLabels(request, SKIP_TO_BUNDLE);

        if (isEditMode(request)) {
             addAllLabels(request, CMS_MESSAGES_BUNDLE);
        }
    }

    private static final String SEARCH = "search";
    private static final String NAVIGATION_STATIC = "navigation.static";
    private static final String NAVIGATION_SOCIAL_MEDIA = "navigation.social-media";

    private void addNavigationLabels(HstRequest request) {
        addAllLabels(request, SEARCH);
        addAllLabels(request, NAVIGATION_STATIC);
        addAllLabels(request, NAVIGATION_SOCIAL_MEDIA);
    }

    /**
     * Add all label from a Hippo Resource Bundle File to the {@code label} request attribute
     *
     * @param request Current Request
     * @param bundleId Hippo Resource Bundle id (from the CMS)
     */
    protected void addAllLabels(HstRequest request, String bundleId) {
        labels(request).put(bundleId, bundle.getAllLabels(bundleId, request.getLocale()));
    }

    /**
     * Include GTM Configuration to the {@link HstRequest}
     *
     * @param request HstRequest
     */
    private void addGtmConfiguration(HstRequest request) {

        Map<String, String> gtmProperties = new HashMap<>();

        gtmProperties.put(SiteProperties.GTM_CONTAINER_ID, properties.getGtmContainerId());
        gtmProperties.put(SiteProperties.GTM_PREVIEW_QUERY_STRING, properties.getGtmPreviewQueryString());
        gtmProperties.put(SiteProperties.GTM_IS_PRODUCTION, properties.getGtmIsProduction());

        request.setModel(GTM, gtmProperties);
    }
    /**
     * Returns a subset of labels that are requires for all pages
     * @param locale Locale of the request
     * @return subset of labels that are requires for all pages
     */
    private Map<String, String> getGlobalLabels(Locale locale) {
        Map<String, String> globalLabels = new HashMap<>();

        addGlobalLabel(globalLabels, "close", locale);
        addGlobalLabel(globalLabels, "cookie.link-message", locale);
        addGlobalLabel(globalLabels, "third-party-error", locale);
        addGlobalLabel(globalLabels, "default.alt-text", locale);
        addGlobalLabel(globalLabels, "image.title", locale);
        addGlobalLabel(globalLabels, "image.no.credit", locale);
        addGlobalLabel(globalLabels, "image.toggle.text", locale);
        addGlobalLabel(globalLabels, "home", locale);
        addGlobalLabel(globalLabels, "page.next", locale);
        addGlobalLabel(globalLabels, "page.previous", locale);
        addGlobalLabel(globalLabels, "back-to-top", locale);
        addGlobalLabel(globalLabels, "last-update", locale);

        return globalLabels;
    }

    /**
     * Gets a label from the General resource bundle and adds it to a map
     *
     * @param map: Map where the labels will be added to
     * @param key: Resource bundle key
     * @param locale: Locale of the request
     */
    private void addGlobalLabel(Map<String, String> map, String key, Locale locale) {
        map.put(key, bundle.getResourceBundle(ResourceBundleService.GLOBAL_BUNDLE_FILE, key, locale));
    }

    /**
     * - Alerts are only used for issues related with the hero image at the moment
     * - Hero Image is not necessary for all document types. Is it better to add the field in order to keep consistency?
     */
    private void addHeroImage(HstRequest request) {
        Module<T> introModule = new Module<>();

        FlatImage heroImage = imageFactory.createImage(getDocument(request).getHeroImage(), introModule, request.getLocale());
        if (getDocument(request).getHeroImage() == null) {
            String message = String.format("The image selected for '%s' is not available, please select a valid image for '%s' at: %s ",
                    getDocument(request).getTitle(), getDocument(request).getDisplayName(), getDocument(request).getPath());
            contentLogger.warn(message);
            introModule.addErrorMessage(message);
        }
        request.setModel(HERO_IMAGE, heroImage);

        VideoLink videoDocument = getDocument(request).getHeroVideo();
        if (videoDocument != null && videoDocument.getVideoLink() != null) {
            EnhancedLink video = linksService.createVideo(videoDocument.getVideoLink(), introModule, request.getLocale());
            if (Contract.isEmpty((video.getYoutubeId()))) {
                request.setModel(VIDEO_HEADER, true);
            }
            request.setModel(HERO_VIDEO, video);
        }

        if (!Contract.isEmpty(introModule.getErrorMessages())) {
            setErrorMessages(request, introModule.getErrorMessages());
        }
    }

    /**
     * Set the OTYML module if present
     */
    protected void addOTYML(HstRequest request) {
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

         addAllLabels(request, OTYML_BUNDLE);
         addAllLabels(request, MEGALINKS_BUNDLE);
         addAllLabels(request, PAGINATION_BUNDLE);
    }

    /**
     * Returns the labels object from the request if it exists, otherwise, creates a new one and adds it to the request
     * @param request HstRequest
     * @return labels object from the request
     */
    protected Map<String, Map<String, String>> labels(HstRequest request) {
        if (request.getModel(LABELS) == null) {
            Map<String, Map<String, String>> labels = new HashMap<>();
            request.setModel(LABELS, labels);
            return labels;
        }

        return request.getModel(LABELS);
    }

    /**
     * Set the blog if present
     */
    protected void addBlog(HstRequest request) {
        Page page = getDocument(request);
        if (page.getBlog() != null) {
            Collection<String> errorMessages = new ArrayList<>();

            FlatBlog blog = blogFactory.getBlog(page.getBlog(), request.getLocale(), errorMessages);

            request.setModel(AUTHOR, blog);

            setErrorMessages(request, errorMessages);
        }
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

            if (signpost.isPresent()) {
                request.setModel(NEWSLETTER_SIGNPOST, signpost.get());
            }
        }
    }

    /**
     * Add the configuration related to the Product Search Widget for the page
     */
    private void addProductSearchWidget(HstRequest request) {
        final String PRODUCT_SEARCH_BUNDLE = "product-search-widget";

        if (!request.getPathInfo().contains(properties.getSiteSkiSection())
                && !request.getPathInfo().contains(properties.getCampaignSection())) {
            request.setModel(PSR_WIDGET, psrFactory.getWidget(request));
            labels(request).put(PRODUCT_SEARCH_BUNDLE,
                    bundle.getAllLabels(PRODUCT_SEARCH_BUNDLE, request.getLocale()));
        }
    }

    /**
     * Adds the logging object to the request.
     *
     * @param request HstRequest
     */
    public void addLogging(HstRequest request) {
        request.setModel("Logger", freemarkerLogger);
    }

    /**
     * Return the document from the request
     *
     * @param request HstRequest
     * @return the main document of
     */
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
    private void addSiteSpecificConfiguration(HstRequest request) {
        if (properties.isProductSearchEnabled()){
            addProductSearchWidget(request);
        }
        if (properties.isTableOfContentsEnabled()){
            addAllLabels(request, TABLE_CONTENTS_BUNDLE);
        }
        if (properties.isGlobalSearchEnabled()){
            enableGlobalSearch(request);
        }
    }

    /**
     * Enable Global Search in the site
     * @param request
     */
    private void enableGlobalSearch(HstRequest request){
        Map<String, String> searchProperties = new HashMap<>();
        properties.getGlobalSearchURL().ifPresent(v -> searchProperties.put("global-search-url", v));
        properties.getCludoCustomerId().ifPresent(v -> searchProperties.put("customer-id", v));
        properties.getCludoEngineId().ifPresent(v -> searchProperties.put("engine-id", v));
        properties.getCludoExperienceId().ifPresent(v -> searchProperties.put("experience-id", v));
        searchProperties.put("language", request.getLocale().getLanguage());

        request.setModel("cludo", searchProperties);
    }

    boolean isEditMode(HstRequest request) {
        return Boolean.TRUE.equals(request.getAttribute(EDIT_MODE));
    }
}

