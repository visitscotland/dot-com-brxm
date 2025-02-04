# Integrating back-end and front-end applications for SPA-SDK (Decopled SSR) sites using the Bloomreach ResourceAPI

## One-way integration

In order for feature branches of front-end sites using the ResourceAPI to render correctly, the Node application needs to be aware of the specifics of the feature environment providing that ResourceAPI endpoint.

By default each branch will point to the feature environment for the `develop` branch of this project, but that can be overridden.

This is achieved by setting the value of the `BR_RESOURCE_API_ENDPOINT` variable in a branch-specific properties file for the branch of the front-end project. The value that should be used is provided in the build report for this project, e.g.:

    HH:MM:SS  Resource API URLs for SPA-SDK/DSSR sites
    HH:MM:SS   - https://feature-businessevents.visitscotland.com/resourceapi?vs_brxm_host=[]&vs_brxm_port=[]&vs-no-redirect
    HH:MM:SS   - https://feature-support.visitscotland.org/resourceapi?vs_brxm_host=[]&vs_brxm_port=[]&vs-no-redirect

The full URL, including query string should be copied and added to your branch-specific properties file in Groovy KEY="value" format, e.g.:

    `env.BR_RESOURCE_API_ENDPOINT = "https://feature-businessevents.visitscotland.com/resourceapi?vs_brxm_host=[]&vs_brxm_port=[]&vs-no-redirect"`

An example file can be found in `ci/properties/feature-branch-example.properties`, and detailed instructions for the creation and use of properties files can be found in that directory.

## Two-way integration

Complete integration of the back-end and front-end environments, including CMS preview functionality of the decoupled site, requires the back-end BRXM environment to know the URLs of the content it should display.

This is achieved by setting the values of the `VS_BRXM_[siteIdentifier]_SPA_URL` variables for each decoupled site in a branch-specific properties file for the branch of this project. The value that should be used is provided in the build report for the front-end project, e.g.:

    HH:MM:SS  # To configure your browser session for this branch please follow this link:
    HH:MM:SS  #   - https://feature-support.visitscotland.org/?vs-dssr-host=[]&vs-dssr-http-port=[]&vs-brxm-host=&vs-brxm-port=&vs_brxm_http_host=feature-support.visitscotland.org&vs_tln_http_port=[]&vs_feature_branch=ops/integrated-backend-and-frontend

The full URLs, including query string should be copied and added to your branch-specific properties file in this project, in Groovy KEY="value" format, e.g.:

```
env.VS_BRXM_BVC_SPA_URL = "https://feature-businessevents.visitscotland.com/?vs-dssr-host=[]&vs-dssr-http-port=[]&vs-brxm-host=&vs-brxm-port=&vs_brxm_http_host=feature-businessevents.visitscotland.com&vs_tln_http_port=[]&vs_feature_branch=ops/integrated-backend-and-frontend"
env.VS_BRXM_SVO_SPA_URL = "https://feature-support.visitscotland.org/?vs-dssr-host=[]&vs-dssr-http-port=[]&vs-brxm-host=&vs-brxm-port=&vs_brxm_http_host=feature-support.visitscotland.org&vs_tln_http_port=[]&vs_feature_branch=ops/integrated-backend-and-frontend"
```

These values are then passed to the feature environment's container and picked up by BRXM for use in each channel's `org.hippoecm.hst.configuration.channel.PreviewURLChannelInfo_url` property.