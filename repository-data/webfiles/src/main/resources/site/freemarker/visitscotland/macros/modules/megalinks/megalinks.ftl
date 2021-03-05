<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-megalinks.ftl">
<#include "./multi-image/megalinks-multi-image.ftl">
<#include "./link-list/megalinks-link-list.ftl">
<#include "./single-image/megalinks-single-image.ftl">

<#macro megalinks item type theme>
    <@hst.manageContent hippobean=item.hippoBean />

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

    <vs-megalinks 
        variant="${variant}"
        title="${item.title}"
        theme="${theme}"
        <#if item.cta?? && type != "SingleImageLinksModule">button-link="${item.cta.link}"</#if>
    >
        <template slot="vsMegalinksHeading">
            ${item.title}
        </template>
        <vs-rich-text-wrapper
            variant="lead"
            slot="vsMegalinksIntro"
        >
            <@hst.html hippohtml=item.introduction/>
        </vs-rich-text-wrapper>

        <#if type == "MultiImageLinksModule">
            <@multiImage item=item showTeaser=showTeaser theme=theme />

        <#elseif type == "ListLinksModule">
            <@linkList item=item showTeaser=showTeaser theme=theme />

        <#elseif type == "SingleImageLinksModule">
            <@singleImage item=item theme=theme /> 
        </#if>

        <#if item.cta?? >     
            <template slot="vsMegalinksButton">
                ${item.cta.label}
            </template>
        </#if>
         
    </vs-megalinks>
</#macro>
