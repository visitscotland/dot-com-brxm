<#include "../../../../../include/imports.ftl">
<#include "../../../../../frontend/components/vs-megalink-multi-image.ftl">

<#macro multiImageThreeItems megalink>
    <#if megalink.image.cmsImage??>
        <#assign image>
            <@hst.link hippobean=megalink.image.cmsImage.original/>
        </#assign>
    <#else>
        <#assign image>
            ${megalink.image.externalImage}
        </#assign>
    </#if>
    <vs-col
        cols="12"
        md="6"
        xl="4"
    >
        <vs-megalink-multi-image
            img-src="${image}"
            link-type="${megalink.type}"
        >
            <template slot="vsMultiImageHeading">
                ${megalink.label}
            </template>
            <template slot="vsMultiImageContent">
                <p>${megalink.teaser}</p>
            </template>
        </vs-megalink-multi-image>
    </vs-col>
</#macro>