package com.visitscotland.brxm.mapper.utils;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.model.ArticleModule;
import com.visitscotland.brxm.model.AssetLink;
import com.visitscotland.brxm.model.DownloadLink;
import com.visitscotland.brxm.services.AssetLinkService;
import com.visitscotland.brxm.services.FileMetaDataCalculator;
import com.visitscotland.brxm.services.LinkService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Component
public class DownloadLinkMapper {

    private static final Logger log = LoggerFactory.getLogger(DownloadLinkMapper.class);

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");

    private final FileMetaDataCalculator fileMetaDataCalculator;
    private final AssetLinkService assetLinkFactory;
    private final LinkService linkService;

    public DownloadLinkMapper(FileMetaDataCalculator fileMetaDataCalculator, AssetLinkService assetLinkFactory, LinkService linkService) {
        this.fileMetaDataCalculator = fileMetaDataCalculator;
        this.assetLinkFactory = assetLinkFactory;
        this.linkService = linkService;
    }

    public DownloadLink getDownloadLink(ArticleSection paragraph, ArticleModule module, Locale locale) {
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
        downloadLink.setPublishedDate(addPublishDate(sharedLink));

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
        downloadLink.setPublishedDate(addPublishDate(sharedLink));

        return downloadLink;
    }



    private String addPublishDate (SharedLinkBSH sharedLink) {
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
        return dateFormat.format(publishDate.getTime());
    }
}
