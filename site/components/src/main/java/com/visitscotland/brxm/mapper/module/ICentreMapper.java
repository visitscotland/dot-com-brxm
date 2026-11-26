package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.hippobeans.ICentre;
import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.hippobeans.TourismInformation;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.mapper.QuoteMapper;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.ICentreModule;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.CMSProperties;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jcr.RepositoryException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

@Component
@Deprecated
public class ICentreMapper extends ModuleMapper<TourismInformation, ICentreModule> {

    private static final Logger logger = LoggerFactory.getLogger(ICentreMapper.class);

    static final String DEFAULT_IMAGE = "icentre.image.default";
    static final String BUNDLE_ID = "tourism.information";

    private final DMSDataService dmsData;
    private final ResourceBundleService bundle;
    private final QuoteMapper quoteEmbedder;
    private final ImageMapper imageMapper;
    private final CMSProperties cmsProperties;
    private final SiteProperties siteProperties;
    private final HippoUtilsService hippoUtilsService;

    @Autowired
    public ICentreMapper(DMSDataService dmsData, ResourceBundleService bundle, QuoteMapper quoteEmbedder,
                         ImageMapper imageMapper, CMSProperties cmsProperties, SiteProperties siteProperties,
                         HippoUtilsService hippoUtilsService) {
        this.dmsData = dmsData;
        this.bundle = bundle;
        this.quoteEmbedder = quoteEmbedder;
        this.imageMapper = imageMapper;
        this.cmsProperties = cmsProperties;
        this.siteProperties = siteProperties;
        this.hippoUtilsService = hippoUtilsService;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        compositionHelper.addAllSiteLabels(BUNDLE_ID);
    }

    @Override
    ICentreModule map(TourismInformation document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        ICentreModule module =  getModule(document.getICentre(), compositionHelper.getLocale());

        if (module != null) {
            module.setHippoBean(document);
        }

        return module;
    }

    /**
     * Builds an iCentre module
     */
    public ICentreModule getModule(ICentre doc, Locale locale) throws PageCompositionException {
        logger.info("Creating iCentreModule for {}", doc.getPath());

        ICentreModule module = new ICentreModule();

        //Populate Title
        if (Contract.isEmpty(doc.getTitle())) {
            module.setTitle(bundle.getResourceBundle(BUNDLE_ID, "icentre.title.default", locale));
        } else {
            module.setTitle(doc.getTitle());
        }

        //Quote
        if (doc.getQuote() != null) {
            module.setQuote(quoteEmbedder.getQuote(doc.getQuote(), module, locale));
            if (module.getImage() == null && module.getQuote().getLink() != null && module.getQuote().getLink().getImage() != null) {
                module.setImage(module.getQuote().getLink().getImage());
            }
        }

        //Populate Image
        if (doc.getImage() != null) {
            var image = imageMapper.createImage(doc.getImage(), module, locale);
            if (image == null) {
                //Default the Image if it hasn't been set
                module.setImage(getDefaultImage(module, locale));
            } else {
                module.setImage(imageMapper.createImage(doc.getImage(), module, locale));
            }
        }

        //Default the Image if it hasn't been set
        if (module.getImage() == null) {
            module.setImage(getDefaultImage(module, locale));
        }

        return module;
    }

    private FlatImage getDefaultImage(ICentreModule module, Locale locale) throws PageCompositionException {
        try {
            String defaultPath = bundle.getResourceBundle(BUNDLE_ID, DEFAULT_IMAGE, locale);
            if (defaultPath == null) {
                throw new PageCompositionException(module.getDocumentPath(), "The default image has not being defined");
            }
            Image defaultImage = hippoUtilsService.getDocumentFromNode(defaultPath);
            return imageMapper.createImage(defaultImage, module, locale);
        } catch (QueryException | ObjectBeanManagerException | RepositoryException e) {
            throw new PageCompositionException(module.getDocumentPath(), "The location for the  default iCentre image is not valid", e);
        }
    }


    private List<FlatLink> getICentreLandingLink(Locale locale) {
        String url = hippoUtilsService.createUrlFromNode(siteProperties.getSiteICentre(), true);
        String text = bundle.getResourceBundle(BUNDLE_ID, "icentre.description.link.text", locale);

        return Collections.singletonList(new FlatLink(text, url, LinkType.INTERNAL));
    }
}
