package com.visitscotland.brxm.services;

import com.visitscotland.brxm.model.AssetLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;

@Component
public class FileSizeCalculator {

    private static final Logger logger = LoggerFactory.getLogger(FileSizeCalculator.class);

    private final HttpConnectionProvider connectionProvider;

    public FileSizeCalculator(HttpConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    /**
     * Calculates the Size of the External document if the document
     *
     */
    @Cacheable(value="externalDocument")
    public Optional<String> getExternalDocumentSize(String link, Locale locale, boolean includeType) {
        try {
            HttpURLConnection con = connectionProvider.openConnection(link);
            if (con.getResponseCode() >= 400){
                return Optional.empty();
            }
            int bytes = con.getContentLength();
            Optional<String> displayType = includeType? getType(con.getContentType(), link) : Optional.empty();
            return composeText(displayType, (double) bytes, locale);
        } catch (IOException e) {
            logger.error("The URL {} is not valid", link, e);
        }
        return Optional.empty();
    }

    public Optional<String> getAssetSize(AssetLink asset, Locale locale) {
        return getAssetSize(asset, locale, true);
    }

    public Optional<String> getAssetSize(AssetLink asset, Locale locale, boolean includeType) {
        Optional<String> mimeType = includeType && asset.getMimeType() != null ?
                Optional.of(asset.getMimeType()) : Optional.empty();
        return composeText(mimeType, (double) asset.getSize(), locale);
    }

    private Optional<String> getType(String mimeType, String link){
        if (!mimeType.startsWith("application") && !mimeType.startsWith("image")) {
            return Optional.empty();
        } else if (mimeType.contains("pdf")) {
            return Optional.of("PDF");
        } else if (link.contains(".")) {
            return Optional.of(link.substring(link.lastIndexOf(".")+1).toUpperCase());
        }
        return Optional.empty();
    }

    private Optional<String> composeText(Optional<String> type, Double bytes, Locale locale){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#.#", dfs);

        if (bytes > 0) {
            String size = decimalFormat.format(bytes / 1024 / 1024) + "MB";
            return type.map(s -> s + " " + size).or(() -> Optional.of(size));
        }

        return Optional.empty();
    }
}
