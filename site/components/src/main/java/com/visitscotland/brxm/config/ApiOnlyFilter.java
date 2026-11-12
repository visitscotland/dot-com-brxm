package com.visitscotland.brxm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiOnlyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ApiOnlyFilter.class);

    private static final String[] ALLOW_LIST = new String[]{
            "/api/",
            "/icons/",
            "/binaries/"
    };

    private static final String RESOURCEAPI = "/resourceapi/";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (isAllowed(path)) {
            chain.doFilter(request, response);
        } else if (path.startsWith("/webfiles/")) {
            logger.warn("A static asset has been requested: {}.", path);
            chain.doFilter(request, response);
        } else {
            logger.warn("An invalid path was requested and will be ignored: {}.", path);
            httpResponse.sendError(HttpServletResponse.SC_GONE);
        }
    }


    private boolean isAllowed(String path) {
        if (path.contains(RESOURCEAPI)){
            return true;
        }
        for (String allow : ALLOW_LIST) {
            if (path.startsWith(allow)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing API Only Filter");
    }

    @Override
    public void destroy() {
        logger.info("Destroying API Only Filter");
    }
}