package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.model.ArticleModule;
import com.visitscotland.brxm.model.ArticleModuleSection;
import com.visitscotland.brxm.model.AssetLink;
import com.visitscotland.brxm.model.DownloadLink;
import com.visitscotland.brxm.services.AssetLinkFactory;
import com.visitscotland.brxm.services.FileMetaDataCalculator;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.services.LinkService;
import org.apache.commons.io.FilenameUtils;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;

@Component
public class ArticleFactory {

    private static final String STANDARD = "standard";
    private static final String ACCORDION = "accordion";
    private static final String BULLET_LIST = "bullet-list";
    private static final String HORIZONTAL_LIST = "horizontal-list";
    private static final String IMAGE_LIST = "image-list";
    private static final String NUMBERED_LIST = "numbered-list";
    private static final Logger log = LoggerFactory.getLogger(ArticleFactory.class);

    private final ImageFactory imageFactory;
    private final LinkService linkService;
    private final QuoteFactory quoteEmbedder;
    private final AnchorFormatter anchorFormatter;
    private final FileMetaDataCalculator fileMetaDataCalculator;
    private final AssetLinkFactory assetLinkFactory;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");

    public ArticleFactory(ImageFactory imageFactory,
                          QuoteFactory quoteEmbedder,
                          LinkService linkService,
                          AnchorFormatter anchorFormatter,
                          FileMetaDataCalculator fileMetaDataCalculator,
                          AssetLinkFactory assetLinkFactory) {
        this.imageFactory = imageFactory;
        this.quoteEmbedder = quoteEmbedder;
        this.linkService = linkService;
        this.anchorFormatter = anchorFormatter;
        this.fileMetaDataCalculator = fileMetaDataCalculator;
        this.assetLinkFactory = assetLinkFactory;
    }

    public ArticleModule getModule(HstRequest request, Article doc) {
        ArticleModule module = new ArticleModule();

        module.setTitle(doc.getTitle());
        module.setIntroduction(doc.getCopy());
        module.setHippoBean(doc);

        addSpecialFields(doc, module);

        setImage(module, doc, request.getLocale());

        module.setAnchor(anchorFormatter.getAnchorOrFallback(doc.getAnchor(), doc::getTitle));

        setSections(module, doc, request.getLocale());

        if (isEditMode(request) && doc instanceof ArticleBSH) {
            // This validation is only required for Edit Mode
            validate(module);
        }

        return module;
    }

    private void addSpecialFields(Article document, ArticleModule module) {
        if (document instanceof ArticleBSH){
            module.setLayout(STANDARD);
            module.setNested(Boolean.TRUE.equals(((ArticleBSH) document).getNested()));
        } else if (document instanceof  ArticleStyledBSH) {
            module.setLayout(((ArticleStyledBSH) document).getTheme());
            module.setNested(Boolean.TRUE.equals(((ArticleStyledBSH) document).getNested()));
        }
    }

    private void setImage(ArticleModule module, Article doc, Locale locale){
        if (doc.getMediaItem() != null) {
            if (doc.getMediaItem() instanceof VideoLink){
                VideoLink videoLink = ((VideoLink)doc.getMediaItem());
                if (videoLink.getVideoLink() != null) {
                    module.setVideo(linkService.createVideo(videoLink.getVideoLink(), module, locale));
                }
            }else {
                module.setImage(imageFactory.getImage(doc.getMediaItem(), module, locale));
            }
        }
    }

    private void setSections(ArticleModule module, Article doc, Locale locale){
        List<ArticleModuleSection> sections = new ArrayList<>();

        for (ArticleSection paragraph: doc.getParagraph()) {
            ArticleModuleSection section = new ArticleModuleSection();
            section.setCopy(paragraph.getCopy());

            if (paragraph.getMediaItem() != null) {
                if (paragraph.getMediaItem() instanceof VideoLink) {
                    section.setVideo(linkService.createVideo(((VideoLink)paragraph.getMediaItem()).getVideoLink(), module, locale));
                } else {
                    section.setImage(imageFactory.getImage(paragraph.getMediaItem(), module, locale));
                }
            }

            if (paragraph instanceof ArticleStyledSection) {
                ArticleStyledSection styledParagraph = (ArticleStyledSection) paragraph;
                section.setHeading(styledParagraph.getHeading());
            }

            if (paragraph.getCmsLink() != null){
                section.setLink(setDownload(paragraph, module, locale));
            }

            if (paragraph.getQuote() != null) {
                section.setQuote(quoteEmbedder.getQuote(paragraph.getQuote(), module, locale));
            }
            sections.add(section);
        }

        module.setSections(sections);
    }

    private void validate(ArticleModule module){
        if (!module.getLayout().equals(STANDARD) && module.getImage() != null) {
            module.addErrorMessage("The current Article layout doesn't allow a main Image");
        }
        if (in(module.getLayout(), IMAGE_LIST, BULLET_LIST)) {
            module.addErrorMessage("The current Article layout doesn't allow any Copy text");
        }
        switch (module.getLayout()) {
            case HORIZONTAL_LIST:
                if (module.getSections().size() > 4) {
                    module.addErrorMessage("The current Article layout should not contain more than 4 paragraph. The extra items will be ignored.");
                }
                break;
            case NUMBERED_LIST:
                if (module.getSections().size() > 5) {
                    module.addErrorMessage("The current Article layout should not contain more than 5 paragraph.");
                }
                break;
            case STANDARD:
            case BULLET_LIST:
            case IMAGE_LIST:
                if (module.getSections().size() > 9) {
                    module.addErrorMessage("The current Article layout should not contain more than 9 paragraph.");
                }
                break;
        }

        if (!module.getLayout().equals(STANDARD)) {
            for (int i=0; i<module.getSections().size(); i++){
                ArticleModuleSection section = module.getSections().get(i);

                if (section.getCopy().getContent().indexOf("<h3>") != section.getCopy().getContent().lastIndexOf("<h3>")) {
                    module.addErrorMessage(String.format("The paragraph #%s contains multiple headings and only one is allowed.", i));
                } else if (section.getCopy().getContent().indexOf("</h3>") - section.getCopy().getContent().indexOf("</h3>") > 84) {
                    module.addErrorMessage(String.format("The heading line in paragraph #%s is too long. It should not be longer than 80 characters.", i));
                }
                if (module.getLayout().equals(BULLET_LIST)) {
                    if (section.getCopy().getContent().indexOf("<p>") - section.getCopy().getContent().lastIndexOf("</p>") > 204) {
                        module.addErrorMessage(String.format("The copy in paragraph #%s is too long. It should not be longer than 200 characters, excluding the heading.", i));
                    }
                } else if (module.getLayout().equals(IMAGE_LIST)) {
                    if (section.getCopy().getContent().indexOf("<p>") - section.getCopy().getContent().lastIndexOf("</p>") > 304) {
                        module.addErrorMessage(String.format("The copy in paragraph #%s is too long. It should not be longer than 300 characters, excluding the heading.", i));
                    }
                }

                if (module.getLayout().equals(BULLET_LIST) && module.getImage() != null) {
                    module.addErrorMessage(String.format("The paragraph #%s contains an image and it will not be displayed.", i));
                }
                if (section.getQuote() != null) {
                    module.addErrorMessage(String.format("The paragraph #%s contains an quote and it will not be displayed", i));
                }
            }
        }
    }

    private String getPublishDate(SharedLink sharedLink, Locale locale) {
        try {
            Property property = sharedLink.getNode().getProperty("hippostdpubwf:creationDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", locale);
            return dateFormat.format(property.getDate().getTime());
        } catch (RepositoryException e) {
            log.warn("An error has occurred while calculating the publish Date link. Path: {}. Error Message {}",
                    sharedLink.getPath(), e.getMessage());
        }
        return null;
    }

    private DownloadLink createDownloadFromAsset(SharedLinkBSH sharedLink, Locale locale) {
        AssetLink assetLink = assetLinkFactory.create(sharedLink, locale);
        if (assetLink.getLink() == null) {
            return null;
        }
        DownloadLink downloadLink = new DownloadLink(assetLink);
        downloadLink.setSize(fileMetaDataCalculator.getAssetSize(assetLink.getSize(), locale).orElse(null));
        downloadLink.setExtension(fileMetaDataCalculator.getFileType(
                assetLink.getMimeType(), downloadLink.getLink()).orElse(null));

        downloadLink.setLabel(sharedLink.getTitle());
        downloadLink.setTeaser(sharedLink.getTeaser());
        downloadLink.setPublishedDate(addPublishDate(sharedLink, locale));

        return downloadLink;
    }

    private DownloadLink createDownloadFromExternalDocument(SharedLinkBSH sharedLink, ArticleModule module, Locale locale) {
        DownloadLink downloadLink = new DownloadLink(linkService.createSimpleLink(sharedLink, module, locale));
        if (downloadLink.getLink() == null){
            return null;
        }
        downloadLink.setSize(fileMetaDataCalculator.getDownloadSize(downloadLink.getLink(), locale).orElse(null));
        downloadLink.setExtension(FilenameUtils.getExtension(downloadLink.getLink()));

        downloadLink.setTeaser(sharedLink.getTeaser());
        downloadLink.setPublishedDate(addPublishDate(sharedLink, locale));

        return downloadLink;
    }

    private DownloadLink setDownload(ArticleSection paragraph, ArticleModule module, Locale locale) {
        if (paragraph.getCmsLink().getLink() instanceof SharedLinkBSH) {
            SharedLinkBSH sharedLink = (SharedLinkBSH) paragraph.getCmsLink().getLink();

            if (sharedLink.getLinkType() instanceof ExternalDocument
                    || sharedLink.getLinkType() instanceof FileLink){
                return createDownloadFromExternalDocument(sharedLink, module, locale);
            } else if (sharedLink.getLinkType() instanceof Asset){
                return createDownloadFromAsset(sharedLink, locale);
            } else {
                log.warn("Unsupported link type: {}", sharedLink.getLinkType());
                module.addErrorMessage("The section for the Article only allows File Links or Assets");
            }
        } else {
            module.addErrorMessage("The section for the Article only allows Shared Links");
        }
        return null;
    }

    private boolean in (String field, String... values) {
        for (String value: values) {
            if (field.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private String addPublishDate (SharedLinkBSH sharedLink, Locale locale) {
        Calendar publishDate = null;

        if (sharedLink.getLinkType() instanceof FileLink){
            publishDate = ((FileLink) sharedLink.getLinkType()).getPublishDate();
        } else if (sharedLink.getLinkType() instanceof Asset){
            publishDate = ((Asset) sharedLink.getLinkType()).getPublishDate();
        }
        if (publishDate == null) {
            try {
                Property p;
                p = sharedLink.getNode().getProperty("hippostdpubwf:creationDate");
                publishDate = p.getDate();
            } catch (RepositoryException e) {
                log.error("An error has occurred while calculating the publish Date link. Path: {}. Error Message {}",
                        sharedLink.getPath(), e.getMessage());
                return "";
            }
        }
        return sdf.format(publishDate.getTime());
    }

    boolean isEditMode(HstRequest request) {
        return Boolean.TRUE.equals(request.getAttribute("editMode"));
    }

}
