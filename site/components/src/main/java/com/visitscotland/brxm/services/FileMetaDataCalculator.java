package com.visitscotland.brxm.services;

import com.visitscotland.brxm.model.AssetLink;
import com.visitscotland.brxm.model.bsh.FileMetaData;
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
public class FileMetaDataCalculator {

    private static final Logger logger = LoggerFactory.getLogger(FileMetaDataCalculator.class);

    private final HttpConnectionProvider connectionProvider;

    public FileMetaDataCalculator(HttpConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    private FileMetaData getFileMetaData(String link) {
        HttpURLConnection connection = null;
        try {
            connection = connectionProvider.openConnection(link);
            if (connection.getResponseCode() >= 400) {
                return null;
            }
            return new FileMetaData(link, connection.getContentLength(), connection.getContentType());
        } catch (IOException e) {
            logger.error("The URL {} is not valid", link, e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @Cacheable(value = "externalDocument")
    public Optional<String> getDisplayText(String link, Locale locale) {
        FileMetaData metaData = getFileMetaData(link);
        if (metaData != null) {
            Optional<String> displayType = getFileType(metaData.getMimeType(), link);
            return composeText(displayType, metaData.getContentLength(), locale);
        }

        return Optional.empty();
    }

    @Cacheable(value = "externalDocument")
    public Optional<String> getDownloadSize(String link, Locale locale) {
        FileMetaData metaData = getFileMetaData(link);
        if (metaData != null) {
            return composeText(Optional.empty(), metaData.getContentLength(), locale);
        }

        return Optional.empty();
    }

    public Optional<String> getAssetSize(double bytes, Locale locale) {
        final double BYTES_PER_MB = 1024.0 * 1024.0;
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
        final DecimalFormat decimalFormat = new DecimalFormat("#.#", decimalFormatSymbols);

        if (bytes > 0) {
            return Optional.of(decimalFormat.format(bytes / BYTES_PER_MB) + "MB");
        }

        return Optional.empty();
    }

    public Optional<String> getDisplayText(AssetLink asset, Locale locale) {
        Optional<String> mimeType = asset.getMimeType() != null ?
                getFileType(asset.getMimeType(), asset.getLink()) : Optional.empty();
        return composeText(mimeType, (double) asset.getSize(), locale);
    }

    public Optional<String> getFileType(String mimeType, String link) {
        if (!mimeType.startsWith("application") && !mimeType.startsWith("image")) {
            return Optional.empty();
        } else if (mimeType.contains("pdf")) {
            return Optional.of("PDF");
        } else if (link.contains(".")) {
            return Optional.of(link.substring(link.lastIndexOf(".") + 1).toUpperCase());
        }
        return Optional.empty();
    }

    private Optional<String> composeText(Optional<String> fileType, double bytes, Locale locale) {
        final Optional<String> size = getAssetSize(bytes,locale);

        return size.flatMap(sizeText -> fileType.map(type -> type + " " + sizeText).or(() -> Optional.of(sizeText)));
    }
}
