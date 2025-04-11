package com.visitscotland.brxm.services;

import com.visitscotland.brxm.utils.NonTestable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@NonTestable(NonTestable.Cause.WRAP)
/**
 * Provides configured HTTP connections with customisable timeout settings.
 * Used to create connections for accessing external resources.
 */
public class HttpConnectionProvider {

    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 5000;
    private static final int DEFAULT_READ_TIMEOUT_MS = 10000;

    private final int connectionTimeoutMs;
    private final int readTimeoutMs;

    public HttpConnectionProvider() {
        this(DEFAULT_CONNECTION_TIMEOUT_MS, DEFAULT_READ_TIMEOUT_MS);
    }

    public HttpConnectionProvider(int connectionTimeoutMs, int readTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
    }

    /**
     * Opens an HTTP connection to the specified URL with configured timeouts.
     *
     * @param url the URL to connect to
     * @return the configured HTTP connection
     * @throws IOException if an error occurs while opening the connection
     * @throws IllegalArgumentException if the URL is null or empty
     * @throws NullPointerException if this happens
     * @throws IllegalStateException <why this might happen>
     * @apiNote The caller is responsible for closing the returned connection
     */
    public HttpURLConnection openConnection(String url) throws IOException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        HttpURLConnection connection = getHttpConnection(url);
        // This timeout prevents the CMS from freezing if the connection is unstable
        connection.setConnectTimeout(connectionTimeoutMs);
        connection.setReadTimeout(readTimeoutMs);
        return connection;
    }

    private HttpURLConnection getHttpConnection(String url) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
    }


}
