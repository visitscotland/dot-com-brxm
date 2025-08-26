<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-megalinks.ftl">
<#include "./card-group/megalinks-card-group.ftl">
<#include "../../global/preview-warning.ftl">

<#macro cardgroup item type theme="">
    <#if type=="MultiImageLinksModule">
        <#assign variant = "multi-image">
    <#elseif type=="ListLinksModule">
        <#assign variant = "link-list">
    <#elseif type=="SingleImageLinksModule">
        <#assign variant = "single-image">
    <#else>
        <#assign variant = "link-list">
    </#if>

    <#if item.teaserVisible??>
        <#assign showTeaser = item.teaserVisible?string('true', 'false') />
    </#if>

    <@previewWarning editMode item item.errorMessages/>

    <vs-megalinks
        data-personalisation-type="default"
        variant="${variant}"
        title="${item.title}"
        theme="${theme}"
        <#if item.cta?? && type != "SingleImageLinksModule">button-link="${item.cta.link}"</#if>
        no-cookies-message="${label('video', 'video.no-cookies')}"
        cookie-link-text="${label('essentials.global', 'cookie.link-message')}"
        no-js-message="${label('video', 'video.no-js')}"
    >
        <#if item.introduction??>
            <template v-slot:vs-megalinks-intro>
                <@hst.html hippohtml=item.introduction/>
            </template>
        </#if>

        <#if type == "CardGroupModule">
            <@cardGroup item=item theme=theme />
        <#else>
            SOMETHING WENT WRONG!
        </#if>


        <#if item.cta?? >
            <template v-slot:vs-megalinks-button>
                ${item.cta.label}
            </template>
        </#if>
    </vs-megalinks>
</#macro>
