<#compress>
    <#include "../../../include/imports.ftl">
    <#include "theme-calculator.ftl">

    <#include "../global/include-once.ftl">

    <#-- Implicit Request Objects -->
    <#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.Destination" -->
    <#-- @ftlvariable name="pageItems" type="com.visitscotland.brxm.hippobeans.Megalinks" -->
    <#-- @ftlvariable name="image" type="com.visitscotland.brxm.model.FlatImage" -->
    <#-- @ftlvariable name="heroImage" type="com.visitscotland.brxm.model.FlatImage" -->
    <#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brxm.model.Coordinates" -->
    <#-- @ftlvariable name="hero" type="com.visitscotland.brxm.hippobeans.Image" -->
    <#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.megalinks.LinksModule" -->
</#compress>
<#macro moduleBuilder module pageIndex="" colourScheme=[]>
    <#assign themeName = themeCalculator(module.themeIndex, module, colourScheme)>
    <#if module.getType() == "MultiImageLinksModule" ||  module.getType() == "SingleImageLinksModule" || module.getType() == "ListLinksModule">
        <#assign moduleType = "megalinks">
    <#else>
        <#assign moduleType = module.getType()>
    </#if>

    <div
        class="has-edit-button vs-module-wrapper__outer--${themeName}"
        <#if pageIndex?? >
            id="section-${pageIndex}"
        </#if>
    >
        <#if module.hippoBean?? >
            <@hst.manageContent hippobean=module.hippoBean />
        </#if>
        <#if moduleType == "PersonalisationModule">
        <div data-personalisation>
            <#list module.modules as personalisedModule>
                <@moduleBuilder personalisedModule pageIndex colourScheme />
            </#list>
        </div>
        <#elseif moduleType == "megalinks">
            <@includeOnce "../modules/megalinks/megalinks.ftl" />
            <#-- all Megalinks modules except HorizontalListLinksModule -->
            <@megalinks item=module type=module.getType() theme=themeName />
        <#elseif moduleType == "HorizontalListLinksModule">
            <@includeOnce "../modules/horizontal-list/horizontal-list.ftl" />
            <@horizontalList module themeName "vs-megalinks-carousel" />
        <#elseif moduleType == "ICentreModule">
            <@includeOnce "../modules/tourism-information/tourisminformation-icentre.ftl" />
            <@icentre module themeName/>
        <#elseif moduleType == "IKnowModule">
            <@includeOnce "../modules/tourism-information/tourisminformation-iknow.ftl" />
            <@iknow module themeName/>
        <#elseif moduleType == "ArticleModule">
            <@includeOnce "../modules/article/article.ftl" />
            <@article module/>
        <#elseif moduleType == "LongCopyModule">
            <@includeOnce "../modules/long-copy/long-copy.ftl" />
            <@longCopy module/>
        <#elseif moduleType == "UserGeneratedContentModule">
            <@includeOnce "../modules/ugc/ugc.ftl" />
            <@ugc module/>
        <#elseif moduleType == "TravelInformationModule">
            <@includeOnce "../modules/travel-information/travel-information.ftl" />
            <@travelInformation module/>
        <#elseif moduleType == "MapsModule">
            <@includeOnce "../modules/map-with-sidebar/map-with-sidebar.ftl" />
            <@mapWithSidebar module/>
        <#elseif moduleType == "CannedSearchModule">
            <@includeOnce "../modules/canned-search/canned-search.ftl" />
            <@cannedSearch module themeName/>
        <#elseif moduleType == "FormModule">
            <@includeOnce "../modules/marketo/marketo.ftl" />
            <@marketo module/>
        <#elseif moduleType == "SkiModule">
            <@includeOnce "../modules/ski-centre/ski-centre.ftl" />
            <@skiCentre module/>
        <#elseif moduleType == "SkiListModule">
            <@includeOnce "../modules/ski-centre-list/ski-centre-list.ftl" />
            <@skiCentreList module/>
        <#elseif module.getType()== "SimpleDevModule">
            <@includeOnce "../modules/dev-module/dev-module.ftl" />
            <@devModule module/>
        <#else >
            <@includeOnce "../global/preview-warning.ftl" />
            <@previewWarning editMode module module.errorMessages true />
        </#if>
    </div>
</#macro>
