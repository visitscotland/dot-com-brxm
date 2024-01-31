package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.*;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.SignpostModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.HorizontalListLinksModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.HippoUtilsService;
import com.visitscotland.brxm.utils.Properties;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;


public class PageContentComponent<T extends Page> extends ContentComponent {

    private static final Logger logger = LoggerFactory.getLogger(PageContentComponent.class);
    //TODO: Content Logger?
    private final Logger freemarkerLogger = LoggerFactory.getLogger("freemarker");

    public static final String DOCUMENT = "document";
    public static final String OTYML = "otyml";
    public static final String BLOG = "blog";
    public static final String NEWSLETTER_SIGNPOST = "newsletterSignpost";
    public static final String PREVIEW_ALERTS = "alerts";
    public static final String HERO_IMAGE = "heroImage";
    public static final String HERO_VIDEO = "heroVideo";
    public static final String PSR_WIDGET = "psrWidget";
    public static final String SOCIAL_LINKS = "social-links";

    public static final String SEARCH_RESULTS = "searchResultsPage";
    private final BlogFactory blogFactory;
    private final MegalinkFactory megalinkFactory;
    private final ImageFactory imageFactory;
    private final LinkService linksService;
    private final SignpostFactory signpostFactory;
    private final ProductSearchWidgetFactory psrFactory;
    private final PreviewModeFactory previewFactory;
    private final HippoUtilsService hippoUtils;
    private final ResourceBundleService bundle;
    private final Properties properties;
    private final Logger contentLogger;

    public PageContentComponent() {
        blogFactory = VsComponentManager.get(BlogFactory.class);
        megalinkFactory = VsComponentManager.get(MegalinkFactory.class);
        imageFactory = VsComponentManager.get(ImageFactory.class);
        signpostFactory = VsComponentManager.get(SignpostFactory.class);
        linksService = VsComponentManager.get(LinkService.class);
        psrFactory = VsComponentManager.get(ProductSearchWidgetFactory.class);
        previewFactory = VsComponentManager.get(PreviewModeFactory.class);
        contentLogger = VsComponentManager.get(ContentLogger.class);
        hippoUtils = VsComponentManager.get(HippoUtilsService.class);
        properties = VsComponentManager.get(Properties.class);
        bundle = VsComponentManager.get(ResourceBundleService.class);

    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        addHeroImage(request);

        addOTYML(request);
        addNewsletterSignup(request);
        addLogging(request);
        addFlags(request);
        addBlog(request);

        addSiteSpecificConfiguration(request);
    }

    /**
     * Add flags to the freekarker to indicate what type of page is being processed
     */
    private void addFlags(HstRequest request){
        if (request.getPathInfo().contains(properties.getSiteGlobalSearch())){
            request.setModel(SEARCH_RESULTS, true);
        }
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
                    getDocument(request).getTitle(), getDocument(request).getDisplayName(),getDocument(request).getPath());
            contentLogger.warn(message);
            introModule.addErrorMessage(message);
        }
        request.setModel(HERO_IMAGE, heroImage);

        VideoLink videoDocument = getDocument(request).getHeroVideo();
        if (videoDocument != null && videoDocument.getVideoLink() != null) {
            EnhancedLink video = linksService.createVideo(videoDocument.getVideoLink(), introModule, request.getLocale());
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
        Page page = getDocument(request);
        if (page.getOtherThings() != null) {
            HorizontalListLinksModule otyml = megalinkFactory.horizontalListLayout(page.getOtherThings(), request.getLocale());
            if (Contract.isEmpty(otyml.getLinks())) {
                contentLogger.warn("OTYML at {} contains 0 published items. Skipping module", page.getOtherThings().getPath());
                request.setModel(OTYML, previewFactory.createErrorModule(otyml));
                return;
            }
            if (otyml.getLinks().size() < MegalinkFactory.MIN_ITEMS_CAROUSEL) {
                contentLogger.warn("OTYML at {} contains only {} published items. Expected a minimum of 5", page.getOtherThings().getPath(), otyml.getLinks().size());
            }
            request.setModel(OTYML, otyml);
        }
    }

    /**
     * Set the blog if present
     */
    protected void addBlog(HstRequest request) {
        Page page = getDocument(request);
        if (page.getBlog() != null) {
            Collection<String> errorMessages = new ArrayList<>();
            //TODO: DO IT ALL RIGHT
            request.setAttribute(BLOG, blogFactory.getBlog(page.getBlog(), request.getLocale(), errorMessages));
            request.setModel("Author", blogFactory.getBlog(page.getBlog(), request.getLocale(), errorMessages));
            //END OF TODO
            setErrorMessages(request, errorMessages);
        }
    }

    protected void addNewsletterSignup(HstRequest request) {
        Page page = getDocument(request);
        if (Boolean.FALSE.equals(Contract.defaultIfNull(page.getHideNewsletter(), false))) {
            SignpostModule signpost;
            if (request.getPathInfo().contains(properties.getSiteSkiSection())) {
                signpost = signpostFactory.createSnowAlertsModule(request.getLocale());
            } else {
                signpost = signpostFactory.createNewsletterSignpostModule(request.getLocale());
            }
            if (signpost != null) {
                request.setModel(NEWSLETTER_SIGNPOST, signpost);
            }
        }
    }

    /**
     * Add the configuration related to the Product Search Widget for the page
     */
    private void addProductSearchWidget(HstRequest request) {
        if (!request.getPathInfo().contains(properties.getSiteSkiSection()) && !request.getPathInfo().contains(properties.getCampaignSection())) {
            request.setModel(PSR_WIDGET, psrFactory.getWidget(request));
        }
    }

    public void addLogging(HstRequest request){
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

    public static void setErrorMessages(HstRequest request, Collection<String> errorMessages) {
        if (request.getAttribute(PREVIEW_ALERTS) != null) {
            Collection<String> requestMessages = (Collection<String>) request.getAttribute(PREVIEW_ALERTS);
            requestMessages.addAll(errorMessages);
        } else {
            request.setModel(PREVIEW_ALERTS, errorMessages);
        }
    }

    private void addSiteSpecificConfiguration(HstRequest request){
        String socialMediaBundle;

        if (hippoUtils.isBusinessEventsSite(request)) {
            request.setModel(HippoUtilsService.BUSINESS_EVENTS_SITE, true);
            socialMediaBundle = "be.navigation.social-media";
        } else {
            addProductSearchWidget(request);
            socialMediaBundle = "navigation.social-media";
        }

        request.setModel(SOCIAL_LINKS, bundle.getAllLabels(socialMediaBundle, request.getLocale()));

    }
}
