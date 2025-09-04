<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-spotlight-section.ftl">

<#macro spotlightSection module>
     <#if module.image.cmsImage??>
        <#assign image>
            <@hst.link hippobean=module.image.cmsImage.original/>
        </#assign>
    <#else>
        <#assign image>
            ${module.image.externalImage}
        </#assign>
    </#if>
    <vs-container>
    <vs-row>
        <vs-col>
    <vs-spotlight-section
        heading="${module.title}"
        image-src="${image}"
        cta-link="${module.cta.link}"
        cta-text="${module.cta.label}"
        description='${module.copy.content?replace("<[^>]+>", "", "r")}'
    />
    </col>
    </row>
    </vs-container>
</#macro>
