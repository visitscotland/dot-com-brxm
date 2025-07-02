<#ftl output_format="XML">
<#compress>
    <#include "../../include/imports.ftl">
    <#include "../macros/modules/page-intro/social-share.ftl">
    <#include "../macros/modules/page-intro/intro-image.ftl">
    <#include "../macros/modules/product-search/psr-module.ftl">
    <#include "../macros/modules/signpost/signpost.ftl">
    <#include "../macros/shared/module-builder.ftl">
    <#include "../macros/modules/search-results/search-results.ftl">
    <#include "../../frontend/components/vs-container.ftl">
    <#include "../../frontend/components/vs-row.ftl">
    <#include "../../frontend/components/vs-col.ftl">
    <#include "../../frontend/components/vs-rich-text-wrapper.ftl">
    <#include "../../frontend/components/vs-heading.ftl">
    <#include "../../frontend/components/vs-html-error.ftl">
    <#include "../macros/modules/page-intro/page-intro.ftl">
    <#include "../macros/modules/page-intro/hero-section.ftl">
    <#include "../macros/global/otyml.ftl">

    <#-- Implicit Request Objects -->
    <#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.General" -->
    <#-- @ftlvariable name="heroImage" type="com.visitscotland.brxm.model.FlatImage" -->

    <#assign topLevelTemplate = (document.theme == "Top-Level") />
    <#assign standardTemplate = (document.theme == "Standard") />
    <#assign simpleTemplate = (document.theme == "Simple") />
	<#assign inspirationTemplate = (document.theme == "Inspiration") />

</#compress>
<div class="has-edit-button">
	<@hst.manageContent hippobean=document/>

	<#if author??>
		<@pageIntro content=document lightBackground=true author=author />
		<@introImage mainImage=heroImage />
	<#elseif topLevelTemplate>
		<#if heroVideo?? && !heroVideo.youtubeId??>
			<@heroSection content=document heroDetails=heroImage lightBackground=(psrWidget?has_content && psrWidget.position = "Top") />
		<#else>
			<@pageIntro content=document heroDetails=heroImage lightBackground=(psrWidget?has_content && psrWidget.position = "Top") />
		</#if>
	<#elseif standardTemplate>
        <@pageIntro content=document lightBackground=true />
		<@introImage mainImage=heroImage />
	<#elseif inspirationTemplate>
		<@pageIntro content=document heroDetails=heroImage lightBackground=(psrWidget?has_content && psrWidget.position = "Top") fullScreenMobile=true />
	<#else>
        <@pageIntro content=document lightBackground=true />
    </#if>

	<#if psrWidget?? && psrWidget.position = "Top">
		<@productSearchWidget psrWidget/>
	</#if>

	<#if errorCode??>
		<vs-html-error status-code="${errorCode}"></vs-html-error>
	</#if>

    <#--TODO Control abput colours, change style="background-color:${style}  -->
	<#list pageItems as module>
		<#--TODO Colour should be only added to Megalinks, add this code to macros or create a common macro to control it-->
		<#if standardTemplate || topLevelTemplate || inspirationTemplate >
			<@moduleBuilder module=module pageIndex="${module?index + 1}" />
		<#else>
			<@moduleBuilder module=module pageIndex="${module?index + 1}" colourScheme=["light", "light", "light"] />
		</#if>
	</#list>

    <#if searchResultsPage??>
        <@searchResults />
    </#if>

    <@socialShare nojs=true/>

	<#if psrWidget?? && psrWidget.position = "Bottom">
		<@productSearchWidget psrWidget/>
	</#if>

	<#if otyml??>
		<@otymlModule otyml editMode />
	</#if>

	<#if newsletterSignpost??>
		<@signpost module=newsletterSignpost />
	</#if>
</div>
