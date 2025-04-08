package com.visitscotland.brxm.services;

import com.visitscotland.brxm.hippobeans.Asset;
import com.visitscotland.brxm.hippobeans.SharedLink;
import com.visitscotland.brxm.model.AssetLink;
import com.visitscotland.brxm.utils.VSRequestContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.RepositoryException;
import java.util.Locale;
import java.util.Optional;

@Component
public class AssetLinkFactory {

    private static final Logger log = LoggerFactory.getLogger(AssetLinkFactory.class);

    private final VSRequestContextProvider requestContextProvider;
    private final FileSizeCalculator fileSizeCalculator;

    public AssetLinkFactory (VSRequestContextProvider requestContextProvider,
            FileSizeCalculator fileSizeCalculator){
        this.requestContextProvider = requestContextProvider;
        this.fileSizeCalculator = fileSizeCalculator;
    }

    public AssetLink create(Asset asset, SharedLink sharedLink, Locale locale) {
        AssetLink link = new AssetLink();

        link.setLabel(sharedLink.getTeaser());
        link.setLink(getURL(asset));
        link.setSize(getSize(asset).orElse(0L));
        link.setMimeType(getMimeType(asset).orElse(null));

        link.setLabel(getLabel(sharedLink.getTeaser(), link, locale));

        return link;
    }

    private String getLabel(String text, AssetLink link, Locale locale) {
        return text + " " + fileSizeCalculator.getAssetSize(link,locale);
    }

    private Optional<String> getMimeType(Asset asset) {
        try {
            return Optional.of(asset.getAsset().getNode().getNode("hippogallery:asset").getProperty("jcr:mimeType").getString());
        } catch (RepositoryException | NullPointerException e) {
            log.warn("The mimetype for the asset {} is not avaliable: Error: {}", asset.getPath(), e.getMessage());
        }
        return Optional.empty();
    }

    private Optional<Long> getSize(Asset asset){
        try {
            return Optional.of(asset.getAsset().getNode().getNode("hippogallery:asset").getProperty("jcr:data").getLength());
        } catch (RepositoryException | NullPointerException e) {
            log.warn("The size of the asset {} could not be calculated. Error: {}", asset.getPath(), e.getMessage());
        }
        return Optional.empty();
    }

    private String getURL(Asset asset){
        return requestContextProvider.getUrl(asset.getAsset().getNode());
    }
}
