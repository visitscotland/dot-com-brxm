package com.visitscotland.brxm.services;

import com.visitscotland.brxm.utils.NonTestable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@NonTestable(NonTestable.Cause.STATIC)
public class HttpConnectionProvider {

    public HttpURLConnection openConnection(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        // This Timeout (5s) prevents the CMS from freezing if the connection is unstable
        connection.setConnectTimeout(5000);
        return connection;
    }
}
