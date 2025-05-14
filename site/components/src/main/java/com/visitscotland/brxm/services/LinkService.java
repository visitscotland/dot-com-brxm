package com.visitscotland.brxm.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.DMSConstants;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.factory.ImageFactory;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.hippobeans.capabilities.UrlLink;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.utils.*;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LinkService {

    private static final Logger logger = LoggerFactory.getLogger(LinkService.class);

    private static final String VL_ITINERARY_MAP = "vs-itinerary-transports";

    private final DMSDataService dmsData;
    private final ResourceBundleService bundle;
    private final HippoUtilsService utils;
    private final CMSProperties cmsProperties;
    private final SiteProperties siteProperties;
    private final ImageFactory imageFactory;
    private final DocumentUtilsService documentUtilsService;
    private final YoutubeApiService youtubeApiService;
    private final Logger contentLogger;
    private final AssetLinkFactory assetLinkFactory;
    private final FileMetaDataCalculator fileMetaDataCalculator;

    @Autowired
    public LinkService(DMSDataService dmsData, ResourceBundleService bundle, HippoUtilsService utils,
                       CMSProperties cmsProperties, SiteProperties siteProperties, ImageFactory imageFactory,
                       DocumentUtilsService documentUtilsService,
                       YoutubeApiService youtubeApiService, ContentLogger contentLogger,
                       AssetLinkFactory assetLinkFactory, FileMetaDataCalculator fileMetaDataCalculator) {

        this.dmsData = dmsData;
        this.bundle = bundle;
        this.utils = utils;
        this.cmsProperties = cmsProperties;
        this.siteProperties = siteProperties;
        this.imageFactory = imageFactory;
        this.documentUtilsService = documentUtilsService;
        this.youtubeApiService = youtubeApiService;
        this.contentLogger = contentLogger;
        this.assetLinkFactory = assetLinkFactory;
        this.fileMetaDataCalculator = fileMetaDataCalculator;
    }

    /**
     * Fetches a new Product Search Object
     */
    private ProductSearchBuilder productSearch() {
        return VsComponentManager.get(ProductSearchBuilder.class);
    }

    /**
     * Creates a link from a Compound item of one of the following types:
     * DMSLink, ProductSearchLink, ExternalLink, CMSLink
     *
     * @param locale locale Language for the labels
     * @param item   Compound Item
     */
    public FlatLink createFindOutMoreLink(Module<?> module, Locale locale, HippoBean item) {
        if (item instanceof DMSLink) {
            DMSLink dmsLink = (DMSLink) item;
            JsonNode product = dmsData.productCard(dmsLink.getProduct(), locale);

            if (product == null) {
                contentLogger.warn("There is no product with the id '{}', ({}) ", dmsLink.getProduct(), item.getPath());
                module.addErrorMessage("There is no product with the id " + dmsLink.getProduct());
            } else {
                return createDmsLink(locale, dmsLink, product);
            }
        } else if (item instanceof ProductSearchLink) {
            ProductSearchLink productSearchLink = (ProductSearchLink) item;
            ProductSearchBuilder psb = productSearch().fromHippoBean(productSearchLink.getSearch()).locale(locale);

            return new FlatLink(bundle.getCtaLabel(productSearchLink.getLabel(), locale), psb.build(), LinkType.INTERNAL);
        } else if (item instanceof ExternalLink) {
            return createExternalLink(locale, ((ExternalLink) item).getLink(), bundle.getCtaLabel(((ExternalLink) item).getLabel(), locale), item.getPath());
        } else if (item instanceof CMSLink) {
            return createCMSLink(module, locale, (CMSLink) item);
        } else if (item instanceof ProductsSearch) {
            ProductSearchBuilder psb = productSearch().fromHippoBean((ProductsSearch)item ).locale(locale);

            return new FlatLink(bundle.getCtaLabel(null, locale), psb.build(), LinkType.INTERNAL);
        }
        logger.warn("The document {} could not be turned into a link", item.getPath());
        module.addErrorMessage("The link was not correctly processed");
        return null;
    }

    /**
     * Creates a FlatLink from a CMSLink Document
     * @param module actual module
     * @param locale locale value
     * @param cmsLink link to CMS document
     * @return FLatLink to a CMS document
     */
    FlatLink createCMSLink(Module<?> module, Locale locale, CMSLink cmsLink){
        FlatLink link = null;
        if (cmsLink.getLink() instanceof SharedLink) {
            link = createFindOutMoreLink(module, locale, ((SharedLink) cmsLink.getLink()).getLinkType());
            if (!Contract.isEmpty(cmsLink.getLabel()) && link != null && !Contract.isEmpty(link.getLink())) {
                link.setLabel(cmsLink.getLabel());
            }
        } else if (cmsLink.getLink() instanceof Linkable) {
            Linkable linkable = (Linkable) cmsLink.getLink();
            link = createSimpleLink(linkable, module, locale);

            if (link != null && !Contract.isEmpty(link.getLink())) {
                link.setLabel(formatLabel(cmsLink.getLink(), bundle.getCtaLabel(cmsLink.getLabel(), locale), module, locale));
            }
        }

        if (link == null || link.getLink() == null){
            contentLogger.warn("There is an unexpected issue with the link at {}", cmsLink.getPath());
            module.addErrorMessage("There is an unexpected issue with the link at " + cmsLink.getPath());
            return null;
        }

        return link;
    }

    /**
     * Creates a localized FlatLink from a URL.
     *
     * @param url: URl
     */
    public FlatLink createExternalLink(final String url, String parentDocument) {
        return createExternalLink(utils.getRequestLocale(), url, null, parentDocument);
    }

   public FlatLink createExternalLink(final Locale locale, final String url, final String label, String parentDocument) {
        LinkType linkType = getType(url);
        String localizedUrl = processURL(locale, url);

        if (url != null && url.contains("pagenotfound")) {
            contentLogger.warn("The document {} contains an invalid URL{} ", parentDocument, url);
        } else if (localizedUrl != null && !localizedUrl.contains(locale.toLanguageTag().toLowerCase())){
            if (!locale.equals(Locale.UK) && linkType == LinkType.INTERNAL && !url.startsWith("#")) {
                logger.warn("The URL {} could not be localized added to the document {} the label for the link is {}", url, parentDocument, label);
            }
        }

        return new FlatLink(label, localizedUrl, linkType);
    }

    private String processURL(Locale locale, String url) {
        if (url == null) {
            return null;
        } else if (url.startsWith("/")) {
            return localize(locale, "", url);
        }else if (url.startsWith("#")) {
            return url;
        }

        if (!Contract.isEmpty(siteProperties.getInternalSites())) {
            try {
                URL urlObject = new URL(url);

                for (String host : siteProperties.getInternalSites()) {
                    if (urlObject.getHost().equals(host)) {
                        String site = host.equals(siteProperties.getConvertToRelative()) ? "" : url.substring(0, url.lastIndexOf(urlObject.getFile()));
                        return localize(locale, site, url.substring(url.lastIndexOf(urlObject.getFile())));
                    }
                }
            } catch (IllegalArgumentException | MalformedURLException e) {
                logger.error("The URL {} cannot be parsed for localization", url);
            }
        }

        return url;
    }

    private String localize(Locale locale, String site, String path) {
        String languagePath = Language.getLanguageForLocale(locale).getPathVariable();

        if (path.startsWith(languagePath) ||
                (languagePath.length() == 6 && path.startsWith(languagePath.substring(0,3)))) {
            return site + path;
        } else {
            return site + languagePath + path;
        }
    }

    public String localize(Locale locale, String url) {
        if (isInternalDomain(url)){
            try {
                String authority = new URL(url).getAuthority();
                String domain = url.substring(0, url.indexOf(authority)) + authority;
                return localize(locale, domain, url.substring(domain.length()));
            } catch (MalformedURLException e) {
                //Note: this exception would never happen as isInternalDomain(url) does the same operation.
                logger.error("Unexpected Exception. The link could not be localized");
            }
        }
        return url;
    }

    public FlatLink createDmsLink(Locale locale, DMSLink dmsLink, JsonNode dmsProductJson, String defaultCta) {
        String cta = Contract.isEmpty(dmsLink.getLabel()) ? defaultCta : dmsLink.getLabel();
        return new FlatLink(cta, getPlainLink(locale, dmsLink, dmsProductJson), LinkType.INTERNAL);
    }

    public FlatLink createDmsLink(Locale locale, DMSLink dmsLink, JsonNode dmsProductJson) {
        return new FlatLink(bundle.getCtaLabel(dmsLink.getLabel(), locale), getPlainLink(locale, dmsLink, dmsProductJson), LinkType.INTERNAL);
    }

    /**
     * Creates a standard link from a Shared Link
     *
     * @param locale Locale
     * @param link   SharedLink Object;
     * @return Plain link
     */
    public String getPlainLink(Module<?> module, Locale locale, SharedLink link) {
        return getPlainLink(locale, link.getLinkType(), getNodeFromSharedLink(link, module,locale));
    }

    /**
     * Creates a standard link from a Shared Link
     *
     * @param locale  Locale
     * @param link    SharedLink Object;
     * @param product JsonNode with the data of the product. It is only used when the type of SharedLink is DMSLink.
     * @return String URL from the SharedLink
     */
    public String getPlainLink(Locale locale, HippoBean link, JsonNode product) {
        String url = null;

        if (link instanceof DMSLink) {
            if (product == null) {//((DMSLink) link).getDmsData(locale)
                contentLogger.warn("The product id '{}' does not exist but is linked - {}", ((DMSLink) link).getProduct(), link.getPath());
            } else {
                url = cmsProperties.getDmsHost() + product.get(DMSConstants.DMSProduct.URL).get(DMSConstants.DMSProduct.URL_LINK).asText();
            }
        } else if (link instanceof ProductsSearch) {
            url = productSearch().fromHippoBean(((ProductsSearch) link)).locale(locale).build();
        } else if (link instanceof ProductSearchLink) {
            url = productSearch().fromHippoBean(((ProductSearchLink) link).getSearch()).locale(locale).build();
        } else if (link instanceof Video) {
            url = ((Video) link).getUrl();
        } else if (link instanceof FileLink) {
            url = ((FileLink) link).getLink();
        } else if (link instanceof Asset) {
            url = createAsset((Asset) link);
        } else if (link instanceof UrlLink) {
            url = ((UrlLink) link).getLink();
        } else {
            String linkType = link == null ? "null" : link.getClass().getSimpleName();
            logger.warn("This class {} is not recognized as a link type and cannot be converted", linkType);
        }
        return processURL(locale, url);
    }

    private String createAsset(Asset asset){
        final boolean FULLY_QUALIFIED = false;
        HstRequestContext requestContext = RequestContextProvider.get();
        HstLink hstLink = requestContext.getHstLinkCreator().create(asset.getAsset().getNode(), requestContext);
        return hstLink.toUrlForm(requestContext, FULLY_QUALIFIED);
    }

    /**
     * Analyzes the URL and identifies what type of link it is.
     *
     * @param url URL to analyze
     * @return linkType
     */
    public LinkType getType(String url) {
        if (Contract.isEmpty(url)) {
            return null;
        } else if (isDownload(url)) {
            return LinkType.DOWNLOAD;
        } else if (url.toLowerCase().startsWith("mailto:")){
            return LinkType.MAIL;
        } else if (url.startsWith("/") || url.startsWith("#")) {
            return LinkType.INTERNAL;
        } else if (isInternalDomain(url)) {
            return LinkType.INTERNAL;
        }

        return LinkType.EXTERNAL;
    }

    private boolean isDownload(String url) {
        String[] validExtensions = siteProperties.getDownloadExtensions().split(",");

        for (String ext : validExtensions) {
            if (url.toLowerCase().endsWith("." + ext.trim())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the host of the URL is marked as an internal URL
     * <p>
     * Note: Malformed URLs will be treated as external URLs
     *
     * @param url ULR domain
     * @return boolean if the URL is internal
     */
    private boolean isInternalDomain(String url) {
        try {
            String host = new URL(url).getHost();
            return ((!Contract.isEmpty(siteProperties.getInternalSites()) && siteProperties.getInternalSites().contains(host)) ||
                    (!Contract.isEmpty(cmsProperties.getDmsHost()) && host.equals(cmsProperties.getDmsHost())));
        } catch (MalformedURLException e) {
            logger.info("Malformed URL detected {}", url);
        }
        return false;
    }

    /**
     * TODO : Reduce Cognitive Complexity
     * Method to assign the right category based on the url/cms structure
     *
     * @param path   String path of the document
     * @param locale Locale
     * @return category
     */
    public String getLinkCategory(String path, Locale locale) {
        String navigationCategory = "navigation.categories";
        try {
            if (getType(path) == LinkType.EXTERNAL) {
                java.net.URL url = new URL(path);
                String host = url.getHost();
                String category = host.toUpperCase().startsWith("WWW.") ? host.substring(4) : host;
                return category.toUpperCase();
            } else if (path.contains("ebooks.visitscotland.com")) {
                return bundle.getResourceBundle(navigationCategory, "ebooks", locale);
            } else if (path.contains("blog")) {
                return bundle.getResourceBundle(navigationCategory, "travel-blog", locale);
            } else if (path.contains("see-do") || path.contains("events") || path.contains("tours") || path.contains("things-to-do")) {
                return bundle.getResourceBundle(navigationCategory, "see-do", locale);
            } else if (path.contains("accommodation")|| path.contains("places-to-stay") ) {
                return bundle.getResourceBundle(navigationCategory, "accommodation", locale);
            } else if (path.contains("destination") || path.contains("towns-villages") || path.contains("places-to-go")) {
                return bundle.getResourceBundle(navigationCategory, "destinations-map", locale);
            } else if (path.contains("travel") || path.contains("holidays") || path.contains("transport")) {
                return bundle.getResourceBundle(navigationCategory, "travel-planning", locale);
            } else if (path.contains("brochures")|| path.contains("inspiration")) {
                return bundle.getResourceBundle(navigationCategory, "inspiration", locale);
            } else if (path.contains("about") || path.contains("contact") || path.contains("policies") || path.contains("services")) {
                return bundle.getResourceBundle(navigationCategory, "footer.visitor-information", locale);
            }

            return bundle.getResourceBundle(navigationCategory, "see-do", locale);

        } catch (MalformedURLException e) {
            logger.error("The URL " + path + " is not valid", e);
            return null;
        }
    }

    /**
     * Creates an enhanced link form a {@code Linkable} object
     *
     * @param linkable    Page or Shared link that contains the information about the link
     * @param module      Module
     * @param locale locale
     * @param addCategory category for OTYML
     * @return Enhanced link
     */
    public Optional<EnhancedLink> createEnhancedLink(Linkable linkable, Module<?> module, Locale locale, boolean addCategory) {
        EnhancedLink link = null;

        if (linkable instanceof Page) {
            link = enhancedLinkFromPage((Page) linkable, module, locale);
        } else if (linkable instanceof SharedLink) {
            link = enhancedLinkFromSharedLink((SharedLink) linkable, module, locale, addCategory);
        } else if (linkable instanceof Video) {
            link = enhancedLinkFromVideo((Video) linkable, module, locale, addCategory);
        } else if (linkable != null) {
            logger.warn("The type {} was not expected and will be skipped", linkable.getClass().getSimpleName());
        }

        if (link == null || link.getLink() == null){
            return Optional.empty();
        }

        if (addCategory && link.getLink() != null && link.getCategory() == null) {
            link.setCategory(getLinkCategory(link.getLink(), locale));
        }

        if (link.getImage() == null) {

            if (module != null) {
                module.addErrorMessage(String.format("The Link to '%s' does not contain an image, please review the document %s at: %s", link.getLabel(), ((BaseDocument) linkable).getDisplayName(), ((BaseDocument) linkable).getPath()));
            } else {
                logger.error("The error message cannot be displayed in preview");
            }
            contentLogger.warn("The link to {} does not have an image but it is expecting one", ((BaseDocument) linkable).getPath());
        }

        return Optional.of(link);
    }

    /**
     * Creates a FlatLink from a Page or a Shared Document
     *
     * @param linkable document to be linked
     * @param module the actual module
     * @param locale locale value
     * @return FlatLink simple format
     */
    public FlatLink createSimpleLink(Linkable linkable, Module<?> module, Locale locale) {
        FlatLink link = new FlatLink();
        if (linkable == null) {
            return link;
        }
        link.setLabel(linkable.getTitle());

        if (linkable instanceof Page) {
            link.setLink(utils.createUrl((Page) linkable));
            link.setType(LinkType.INTERNAL);
        } else if (linkable instanceof SharedLink) {
            SharedLink sharedLink = (SharedLink) linkable;
            link.setLink(getPlainLink(module, locale, sharedLink));
            link.setType(getType(link.getLink()));
        } else if (module != null) {
            module.addErrorMessage(String.format("The type %s cannot be converted into a link", linkable.getClass().getSimpleName()));
            logger.warn("The type {} was not expected and will be skipped", linkable.getClass().getSimpleName());
            return null;
        }
        return link;
    }

    /**
     * Query the DMSDataService and extract the information about the product as a {@code JsonNode}
     *
     * @param sharedLink   SharedLink where the DMS product (ID) is defined
     * @param locale User language to consume DMS texts such a category, location, facilities...
     * @return JSON with DMS product information to create the card or null if the product does not exist
     */
    private JsonNode getNodeFromSharedLink(SharedLink sharedLink, Module<?> module, Locale locale) {
        JsonNode product = null;

        if (sharedLink.getLinkType() instanceof DMSLink) {
            product = dmsData.productCard(((DMSLink) sharedLink.getLinkType()).getProduct(), locale);

            if (product == null) {
                String message =  String.format("The DMS ID for '%s' is not valid. Please review the document '%s' at %s", sharedLink.getTitle(),sharedLink.getDisplayName(), sharedLink.getPath());
                contentLogger.warn(message);
                if (module == null) {
                    contentLogger.warn("The module has not been defined, and the DMS error cannot be reported back");
                } else {
                    module.addErrorMessage(message);
                }
            }
        }
        return product;
    }

    /**
     * Populated the information about an enhanced Link from a Page Document.
     *
     * @param page   SharedLink document that contains extra information
     * @param module Module to feed with any possible issue found while creating the page.
     * @param locale Language for the label
     */
    private EnhancedLink enhancedLinkFromPage(Page page, Module<?> module, Locale locale) {
        EnhancedLink link = new EnhancedLink();
        link.setTeaser(page.getTeaser());
        link.setLabel(page.getTitle());
        link.setLink(utils.createUrl(page));
        link.setType(LinkType.INTERNAL);

        if (page.getImage() == null){
            module.addErrorMessage(String.format("The image selected for '%s' is not available. Please select a valid image for the page '%s' at: %s",  page.getTitle(), page.getDisplayName(), page.getPath()));
        }
        link.setImage(imageFactory.createImage(page.getImage(), module, locale));

        if (page instanceof Itinerary) {
            Itinerary itinerary = (Itinerary) page;
            link.setItineraryDays(documentUtilsService.getSiblingDocuments(page, Day.class, "visitscotland:Day").size());
            if (itinerary.getTransports().length > 0) {
                link.setItineraryTransport(itinerary.getTransports()[0]);
                link.setItineraryMainTransport(getItineraryTransport(itinerary.getTransports()[0]));
            }
        }  else if (page instanceof GeneralBSH){
            GeneralBSH generalBSH = (GeneralBSH) page;
            if (generalBSH.getReadingTime() > 0) {
                //TODO change the resource bundle to use the global one
                link.setReadTime(generalBSH.getReadingTime() +" "+ bundle.getResourceBundle("bsh.megalinks", "readtime", locale));
            }
            setBSHFields(link, generalBSH.getType(), generalBSH.getSectors(), generalBSH.getSkill(), generalBSH.getTopic(), generalBSH.getRegions());
        }

        return link;
    }

    private Entry getItineraryTransport(String key) {
        String displayText = utils.getValueMap(VL_ITINERARY_MAP).get(key);
        if (displayText == null) {
            logger.warn("No display text found for transport key: {}", key);
            displayText = key; // Fallback to using the key as display text
        }
        return new Entry (key, displayText);
    }

    /**
     * Populates the information about an enhanced Link from a SharedLink Document.
     *
     * @param sharedLink  SharedLink document that contains extra information
     * @param module      Module to feed with any possible issue found while creating the page.
     * @param locale      Language for the label
     * @param addCategory whether the category field is populated.
     */
    private EnhancedLink enhancedLinkFromSharedLink(SharedLink sharedLink, Module<?> module, Locale locale, boolean addCategory) {
        EnhancedLink link;
        JsonNode product = getNodeFromSharedLink(sharedLink, module, locale);

        if (sharedLink.getLinkType() instanceof Asset){
            link = assetLinkFactory.create(sharedLink, locale);
        } else {
            link = new EnhancedLink();
            link.setTeaser(sharedLink.getTeaser());
            link.setLink(getPlainLink(locale, sharedLink.getLinkType(), product));
        }

        if (link.getLink() == null){
            return null;
        }

        if (sharedLink instanceof SharedLinkBSH) {
            SharedLinkBSH sharedLinkBSH = (SharedLinkBSH) sharedLink;
            link.setSource(sharedLinkBSH.getSource());
            if (!Contract.isEmpty(sharedLinkBSH.getWebsiteType())) {
                link.setCategory(utils.getValueMap("bsh-external-links").get(sharedLinkBSH.getWebsiteType()));
            }
            setBSHFields(link, sharedLinkBSH.getType(), sharedLinkBSH.getSectors(), sharedLinkBSH.getSkill(), sharedLinkBSH.getTopic(), sharedLinkBSH.getRegions());
        }

        if (product != null && !hasOverrideImage(sharedLink) && product.has(DMSConstants.DMSProduct.IMAGE)) {
            link.setImage(imageFactory.createImage(product, module, locale));
        } else {
            link.setImage(imageFactory.createImage(sharedLink.getImage(), module, locale));
        }

        if (sharedLink.getLinkType() instanceof ExternalDocument || sharedLink.getLinkType() instanceof FileLink) {
            link.setLabel(formatLabel(sharedLink, sharedLink.getTitle(), module, locale));
            link.setType(LinkType.DOWNLOAD);

            if (addCategory && sharedLink.getLinkType() instanceof ExternalDocument) {
                link.setCategory(((ExternalDocument) sharedLink.getLinkType()).getCategory());
            }
        } else if (!(sharedLink.getLinkType() instanceof Asset)){
            link.setLabel(sharedLink.getTitle());
            link.setType(getType(link.getLink()));
            if (sharedLink.getLinkType() instanceof DMSLink){
                link.setCta(bundle.getCtaLabel(((DMSLink)sharedLink.getLinkType()).getLabel(), locale));
            }
        }
        if (sharedLink.getImage() == null && !(sharedLink.getLinkType() instanceof DMSLink) && !(sharedLink instanceof SharedLinkBSH)){
            module.addErrorMessage(String.format("The image selected for '%s' is not available. Please select a valid image for the shared document '%s' at: %s",  sharedLink.getTitle(), sharedLink.getDisplayName(), sharedLink.getPath()));
        }
        return link;
    }

    private boolean hasOverrideImage(SharedLink sharedLink){
        return sharedLink.getImage() != null && !Contract.isEmpty(sharedLink.getImage().getPath());
    }

    /**
     * Creates a Video Link from a Video document.
     *
     * @param video       Video document
     * @param module      Module to feed with any possible issue found while creating the page.
     * @param locale      Language for the label
     * @param addCategory Indicates whether the category field is populated
     */
    private EnhancedLink enhancedLinkFromVideo(Video video, Module<?> module, Locale locale, boolean addCategory) {
        EnhancedLink link = createVideo(video, module, locale);

        if (addCategory) {
            link.setCategory("Video");
            module.addErrorMessage("This module should not contain a Video Link");
            contentLogger.warn("This module '{}' should not contain a Video Link", getSource(module));
        }

        return link;
    }

    private String getSource(Module<?> module){
        if (module == null || module.getHippoBean() == null){
            return "unknown";
        } else {
            return module.getHippoBean().getPath();
        }
    }

    /**
     * Formats label and includes additional information when needed
     *
     * @param linkable link to external document
     * @param locale   Language for the label
     * @param module   Module to feed with any possible issue found while creating the page.
     * @return Formatted label
     */
    public String formatLabel(HippoBean linkable, String label, Module<?> module, Locale locale) {
        if (linkable instanceof SharedLink){
            HippoBean linkType = ((SharedLink) linkable).getLinkType();
            if (linkType instanceof ExternalDocument || linkType instanceof FileLink) {
                return label + getDownloadText(((UrlLink) linkType).getLink(), locale, module);
            }
        }
        return label;
    }

    public String getDownloadText(String link) {
        return getDownloadText(link, utils.getRequestLocale(), null);
    }


    public String getDownloadText(String link, Locale locale, Module<?> module) {
        return getDownloadText(link, module, fileMetaDataCalculator.getDisplayText(link, locale));
    }

    /**
     * Formats a download text with size information for a given link.
     *
     * @param link URL to the downloadable resource
     * @param module Module that will log all issues for the modules
     * @param sizeType Optional containing the size and type of the document
     * @return Formatted text with size information or empty string if size is not available
     */
    public String getDownloadText(String link, Module<?> module, Optional<String> sizeType) {

        if (sizeType.isEmpty()) {
            String errorMessage = String.format("The size and type of the document %s is not available.", link);
            if (module != null) {
                module.addErrorMessage(errorMessage);
            }
            contentLogger.warn(errorMessage);
            return "";
        } else {
            return " | " + sizeType.get();
        }
    }

    /**
     * Creates a PlainVideoLink Object from a videoLink
     *
     * @param video  Video Document
     * @param module Module that will log all issues for the modules.
     * @param locale Locale for the localization
     * @return enhancedLink for the video
     */
    public EnhancedLink createVideo(Video video, Module<?> module, Locale locale) {
        EnhancedLink videoLink = new EnhancedLink();
        videoLink.setImage(imageFactory.createImage(video.getImage(), module, locale));
        videoLink.setLabel(video.getTitle());
        videoLink.setTeaser(video.getTeaser());
        videoLink.setLink(video.getUrl());
        videoLink.setCta(bundle.getVideoCtaLabel(video.getLabel(), locale));
        videoLink.setYoutubeId(getYoutubeId(video.getUrl()));
        videoLink.setType(LinkType.VIDEO);
        Optional<YoutubeVideo> videoInfo = youtubeApiService.getVideoInfo(videoLink.getYoutubeId());
        // If the upload date can not be obtained from YouTube, set the published date to today to prevent a malformed
        // schema
        if (videoInfo.isPresent()) {
            videoLink.setPublishedDate(videoInfo.get().getPublishDate());
        } else {
            videoLink.setPublishedDate(new Date());
        }
        return videoLink;
    }

    private String getYoutubeId(String url) {
        String id = UriComponentsBuilder.fromUriString(url).build().getQueryParams().getFirst("v");
        if (Contract.isEmpty(id)){
            logger.warn("The Youtube ID could not be calculated from the URL {}", url);
        }
        return id;
    }



    /**
     * Populates the specific fields for the Business support hub website
     *
     * @param link  the link/card that is being built
     * @param contentType    the field content type
     * @param sectors   sectors selected
     * @param skill skill level field
     * @param regions regions selected
     */
    private void setBSHFields (EnhancedLink link, String contentType, String[] sectors, String skill, String[] topics, String[] regions) {
        final String CONTENT_TYPES_VALUE_LIST = "bsh-content-types";
        final String SKILL_LEVELS_VALUE_LIST = "bsh-skill-levels";
        final String SECTORS_VALUE_LIST = "bsh-sectors";
        final String TOPICS_VALUE_LIST = "bsh-topics";
        final String REGIONS_VALUE_LIST = "bsh-regions";

        link.setContentType(utils.getValueMap(CONTENT_TYPES_VALUE_LIST).get(contentType));
        link.setSkillLevel(utils.getValueMap(SKILL_LEVELS_VALUE_LIST).get(skill));
        link.setSector(getTextFromValueList(sectors, SECTORS_VALUE_LIST));
        link.setTopic(getTextFromValueList(topics, TOPICS_VALUE_LIST));
        link.setRegion(getTextFromValueList(regions, REGIONS_VALUE_LIST));
    }

    /**
     * Converts an array of keys to a list of text values taken from ValueList (Drop Down options)
     *
     * @param keys the array of keys
     * @param valueListName the name of the valuelist defined on {@code valueListManager.xml}
     *
     * @return the list of texts
     */
    private List<String> getTextFromValueList(String[] keys, String valueListName){
        if (keys != null){
            return Arrays.stream(keys)
                    .map(key -> utils.getValueMap(valueListName).get(key))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
