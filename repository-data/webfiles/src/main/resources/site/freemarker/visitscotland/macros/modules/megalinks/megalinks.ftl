<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-megalinks.ftl">
<#include "./multi-image/megalinks-multi-image.ftl">
<#include "./link-list/megalinks-link-list.ftl">
<#include "./single-image/megalinks-single-image.ftl">
<#include "../../global/preview-warning.ftl">

<#macro megalinks item type theme="">
    <#if type=="MultiImageLinksModule">
        <#assign variant = "multi-image">
    <#elseif type=="ListLinksModule">
        <#assign variant = "link-list">
    <#elseif type=="SingleImageLinksModule">
        <#assign variant = "single-image">
    </#if>

    <#if item.teaserVisible??>
        <#assign showTeaser = item.teaserVisible?string('true', 'false') />
    </#if>

    <@previewWarning editMode item item.errorMessages/>
    <vs-megalinks
        <#if item.marketoId?? && item.marketoId != "default">
            data-personalisation-type="${item.marketoId}"
            class="personalisation--hidden"    
        <#else>
            data-personalisation-type="default" 
        </#if>  
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

        <#if type == "MultiImageLinksModule">
            <@multiImage item=item showTeaser=showTeaser theme=theme />

        <#elseif type == "ListLinksModule">
            <@linkList item=item showTeaser=showTeaser theme=theme />

        <#elseif type == "SingleImageLinksModule">
            <@singleImage item=item theme=theme /> 
        </#if>

        <#if item.cta?? >     
            <template v-slot:vs-megalinks-button>
                ${item.cta.label}
            </template>
        </#if>
    </vs-megalinks>
</#macro>