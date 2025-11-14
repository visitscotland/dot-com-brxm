<#include "../../../../include/imports.ftl">
<#include "../spotlight-section/spotlight-section.ftl">


<#macro featuredContent module>
    <#if module.image.cmsImage??>
        <#assign image>
            <@hst.link hippobean=module.image.cmsImage.original/>
        </#assign>
    <#else>
        <#assign image>
            ${module.image.externalImage}
        </#assign>
    </#if>


<#--    <#assign compact=false>-->
    <#assign compact= (module.layout?? && module.layout == "compact") >
    <@spotlightSection module.title image module.cta.link module.cta.label module.copy compact />
</#macro>
