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
    <#assign description>
        <#--
        Data comes from CTA as stringified HTML 
        Remove tags so it can be passed to spotlight-section as plaintext
        -->
        ${module.copy.content?replace("<[^>]+>", "", "r")}
    </#assign>

    <vs-container>
        <vs-row>
            <vs-col>
                <vs-spotlight-section
                    heading="${module.title}"
                    image-src="${image}"
                    cta-link="${module.cta.link}"
                    cta-text="${module.cta.label}"
                    description="${description}"
                    <#if module.layout?? && module.layout == "compact">
                    compact
                    </#if>
                />
            </vs-col>
        </vs-row>
    </vs-container>
</#macro>
