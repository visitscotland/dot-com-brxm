<#assign hst=JspTaglibs["http://www.hippoecm.org/jsp/hst/core"] >
<#assign fmt=JspTaglibs ["http://java.sun.com/jsp/jstl/fmt"] >

<@hst.defineObjects />

<#assign locale = hstRequest.requestContext.resolvedMount.mount.locale?replace("_","-")?lower_case>
<#-- Indicates if the URLs need to be fully qualified. (i.e. For integration with 3rd parties) -->
<#assign fullyQualifiedURLs = hstRequestContext.getModel("fullyQualified")???then(true,false)>
<#include "./helpers.ftl">