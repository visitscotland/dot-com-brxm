<#include "../../../include/imports.ftl">
<#include "../global/cms-errors.ftl">
<#include "../modules/megalinks/megalinks.ftl">
<#include "../modules/megalinks/megalinks-horizontal-list.ftl">
<#include "../modules/tourism-information/tourisminformation-iknow.ftl">
<#include "../modules/tourism-information/tourisminformation-icentre.ftl">

<#-- Implicit Request Objects -->
<#-- @ftlvariable name="document" type="com.visitscotland.brmx.beans.Destination" -->
<#-- @ftlvariable name="pageItems" type="com.visitscotland.brmx.beans.Megalinks" -->
<#-- @ftlvariable name="image" type="com.visitscotland.brmx.beans.mapping.FlatImage" -->

<#-- @ftlvariable name="heroImage" type="com.visitscotland.brmx.beans.mapping.FlatImage" -->
<#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brmx.beans.mapping.Coordinates" -->

<#-- @ftlvariable name="hero" type="com.visitscotland.brmx.beans.Image" -->

<#macro moduleBuilder module theme>
    <#if theme="theme1">
        <#assign themeName = "dark">
    <#elseif theme="theme2">
        <#assign themeName = "light">
    </#if>

    <#if module.getType() == "MultiImageLinksModule" ||  module.getType() == "SingleImageLinksModule" || module.getType()== "ListLinksModule">
        <#assign moduleType = "megalinks">
    <#else>
        <#assign moduleType = module.getType()>
    </#if>

    <div class="has-edit-button">       
        <#if moduleType == "megalinks">
            <#-- all Megalinks modules -->
            <@megalinks item=module type=module.getType() theme=themeName />

        <#elseif moduleType == "HorizontalListLinksModule">
            <@horizontalList module/>

        <#elseif moduleType == "ICentreModule">
            <@icentre module/>

        <#elseif moduleType == "IKnowModule">
            <@iknow module/>

        </#if>
    </div>
</#macro>
