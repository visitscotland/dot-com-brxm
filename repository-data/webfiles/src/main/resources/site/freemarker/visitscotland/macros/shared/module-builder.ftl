<#include "../../../include/imports.ftl">
<#include "../global/cms-errors.ftl">
<#include "../modules/megalinks/megalinks.ftl">
<#include "../modules/article/article.ftl">
<#include "../modules/long-copy/long-copy.ftl">
<#include "../modules/iknow-community/iknow-community.ftl">
<#include "../modules/tourism-information/tourisminformation-iknow.ftl">
<#include "../modules/tourism-information/tourisminformation-icentre.ftl">
<#include "../modules/horizontal-list/horizontal-list.ftl">
<#include "../modules/stackla/stackla.ftl">
<#include "theme-calculator.ftl">

<#-- Implicit Request Objects -->
<#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.Destination" -->
<#-- @ftlvariable name="pageItems" type="com.visitscotland.brxm.hippobeans.Megalinks" -->
<#-- @ftlvariable name="image" type="com.visitscotland.brxm.model.FlatImage" -->

<#-- @ftlvariable name="heroImage" type="com.visitscotland.brxm.model.FlatImage" -->
<#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brxm.model.Coordinates" -->

<#-- @ftlvariable name="hero" type="com.visitscotland.brxm.hippobeans.Image" -->

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.megalinks.LinksModule" -->

<#macro moduleBuilder module colourScheme=[]>

    <#assign themeName = themeCalculator(module, colourScheme)>

    <#if module.getType() == "MultiImageLinksModule" ||  module.getType() == "SingleImageLinksModule" || module.getType()== "ListLinksModule">
        <#assign moduleType = "megalinks">
    <#else>
        <#assign moduleType = module.getType()>
    </#if>

    <div class="has-edit-button">
        <#if module.hippoBean?? >
            <@hst.manageContent hippobean=module.hippoBean />
        </#if>
        <#if moduleType == "megalinks">
            <#-- all Megalinks modules except HorizontalListLinksModule -->
            <@megalinks item=module type=module.getType() theme=themeName />

        <#elseif moduleType == "HorizontalListLinksModule">
            <@horizontalList module themeName/>

        <#elseif moduleType == "ICentreModule">
            <@icentre module themeName/>

        <#elseif moduleType == "IKnowModule">
            <@iknow module themeName/>

        <#elseif module.getType()== "ArticleModule">
            <@article module/>

        <#elseif module.getType()== "LongCopyModule">
            <@longCopy module/>

        <#elseif module.getType()== "IKnowCommunityModule">
            <@iknowCommunity module/>

        <#elseif module.getType()== "StacklaModule">
            <@stackla module/>
        </#if>
    </div>
</#macro>
