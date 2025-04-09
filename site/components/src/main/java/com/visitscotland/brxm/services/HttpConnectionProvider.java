package com.visitscotland.brxm.services;

import com.visitscotland.brxm.utils.NonTestable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@NonTestable(NonTestable.Cause.WRAP)
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

    public HttpURLConnection openConnection(String url) throws IOException {
        HttpURLConnection connection = getHttpConnection(url);
        // This Timeout (5s) prevents the CMS from freezing if the connection is unstable
        connection.setConnectTimeout(connectionTimeoutMs);
        connection.setReadTimeout(readTimeoutMs);
        return connection;
    }

    private HttpURLConnection getHttpConnection(String url) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
    }


}
