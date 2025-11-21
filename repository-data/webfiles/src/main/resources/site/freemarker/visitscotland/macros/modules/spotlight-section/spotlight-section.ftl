<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-spotlight-section.ftl">

<#macro spotlightSection title image ctaLink ctaText copy compact>
    <#assign description>
    <#--
    Data comes from CTA as stringified HTML
    Remove tags so it can be passed to spotlight-section as plaintext
    -->
        ${copy.content?replace("<[^>]+>", "", "r")}
    </#assign>

    <vs-container>
        <vs-row>
            <vs-col>
                <vs-spotlight-section
                    heading="${title}"
                    image-src="${image}"
                    cta-link="${ctaLink}"
                    cta-text="${ctaText}"
                    description="${description}"
                    <#if compact>
                    compact
                    </#if>
                />
            </vs-col>
        </vs-row>
    </vs-container>
</#macro>
