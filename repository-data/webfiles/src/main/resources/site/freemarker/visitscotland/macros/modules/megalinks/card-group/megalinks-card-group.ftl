<#include "../../../../../include/imports.ftl">
<#include "../../../../../frontend/components/vs-card-group.ftl">
<#include "../../../../../frontend/components/vs-card.ftl">
<#include "../../../../../frontend/components/vs-img.ftl">
<#include "../../../../../frontend/components/vs-heading.ftl">
<#include "../../../../../frontend/components/vs-link.ftl">
<#include "../../../../../frontend/components/vs-heading.ftl">
<#include "../../../../../frontend/components/vs-link.ftl">
<#include "../../video/video-modal.ftl">
<#include "../../video/video-modal.ftl">

<#macro cardGroup item theme>
    <vs-card-group scroll-snap cards-per-row="
        <#if item.layout=='Grid 4'>
        4
        <#else>
        3
        </#if>
    ">
        <#list item.links as listItem>
            <#if listItem.image.cmsImage??>
                <#assign image>
                    <@hst.link hippobean=listItem.image.cmsImage.original/>
                </#assign>
            <#else>
                <#assign image>
                    ${listItem.image.externalImage}
                </#assign>
            </#if>
            <vs-card>
                <template v-slot:vs-card-header>
                    <vs-img
                        src="${image}"
                        class="w-100 aspect-ratio-3-2 rounded-1 object-fit-cover img-zoom-on-hover"
                    />
                </template>
                <template v-slot:vs-card-body>
                    <vs-heading
                        level="3"
                        heading-style="heading-xs"
                    >
                        <vs-link
                            href="${listItem.link}"
                            class="stretched-link"
                            variant="secondary"
                        >
                            ${listItem.label}
                        </vs-link>
                    </vs-heading>
                    <p>
                        ${listItem.teaser}
                    </p>
                </template>
            </vs-card>
        </#list>
    </vs-card-group>

</#macro>
