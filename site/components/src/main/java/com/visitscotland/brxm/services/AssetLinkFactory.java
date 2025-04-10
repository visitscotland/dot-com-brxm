package com.visitscotland.brxm.services;

import com.visitscotland.brxm.hippobeans.Asset;
import com.visitscotland.brxm.hippobeans.SharedLink;
import com.visitscotland.brxm.model.AssetLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.utils.VsRequestContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.RepositoryException;
import java.util.Locale;

@Component
public class AssetLinkFactory {

    private static final Logger log = LoggerFactory.getLogger(AssetLinkFactory.class);

    private final VsRequestContextProvider requestContextProvider;
    private final FileMetaDataCalculator fileSizeCalculator;

    public AssetLinkFactory (VsRequestContextProvider requestContextProvider,
                             FileMetaDataCalculator fileSizeCalculator){
        this.requestContextProvider = requestContextProvider;
        this.fileSizeCalculator = fileSizeCalculator;
    }

    public AssetLink create(SharedLink sharedLink, Locale locale) {
        Asset asset = getAsset(sharedLink);
        AssetLink link = new AssetLink();

        link.setLink(getURL(asset));
        link.setType(LinkType.DOWNLOAD);
        link.setSize(getSize(asset));
        link.setMimeType(getMimeType(asset));

        link.setLabel(getLabel(sharedLink.getTitle(), link, locale));
        link.setTeaser(sharedLink.getTeaser());

        return link;
    }

    private Asset getAsset(SharedLink sharedLink){
        if (sharedLink != null && sharedLink.getLinkType() instanceof Asset){
            return (Asset)sharedLink.getLinkType();
        } else {
            throw new UnsupportedOperationException("Link type not supported");
        }
    }

    private String getLabel(String text, AssetLink link, Locale locale) {
        return fileSizeCalculator.getDisplayText(link, locale).map(sizeType -> text + " | " + sizeType).orElse(text);
    }

    private String getMimeType(Asset asset) {
        try {
            return asset.getAsset().getNode().getNode("hippogallery:asset").getProperty("jcr:mimeType").getString();
        } catch (RepositoryException | NullPointerException e) {
            log.warn("The mimetype for the asset {} is not available: Error: {}", asset.getPath(), e.getMessage());
        }
        return null;
    }

    private Long getSize(Asset asset){
        try {
            return asset.getAsset().getNode().getNode("hippogallery:asset").getProperty("jcr:data").getLength();
        } catch (RepositoryException | NullPointerException e) {
            log.warn("The size of the asset {} could not be calculated. Error: {}", asset.getPath(), e.getMessage());
        }
        return 0L;
    }

    private String getURL(Asset asset){
        return requestContextProvider.getUrl(asset.getAsset().getNode());
    }
}
