<#compress>
<#include "../../include/imports.ftl">
<#include "../macros/modules/header/mega-nav/header-mega-nav.ftl">
<#include "../macros/modules/header/header-global-menu.ftl">
<#include "../macros/modules/header/skip-to.ftl">
<#include "../macros/modules/header/banner.ftl">
<#include "../macros/global/dev-env-menu.ftl">

<#-- @ftlvariable name="menu" type="org.hippoecm.hst.core.sitemenu.HstSiteMenu" -->
<#-- @ftlvariable name="banner" type="com.visitscotland.brxm.model.BannerModule" -->
<#-- @ftlvariable name="widgetList" type="com.visitscotland.brxm.model.navigation.FeaturedItem" -->
<#-- @ftlvariable name="enhancedMenu" type="java.util.List" -->
<#-- @ftlvariable name="item" type=""com.visitscotland.www.components.navigation.VsMenuItem" -->
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
</#compress>

<#if menu??>
    <#if ciBranch??>
        <@devEnvMenu />
    </#if>
    <@hst.cmseditmenu menu=menu />
    <div class="has-edit-button vs-sticky-nav">
        <@headerSkipTo />
        <header>
            <@headerGlobalMenu />
            <!-- Navigation generated at ${menu.timeStamp?string["yyyy-MM-dd HH:mm:ss"]} -->
            <@headerMegaNav menu=menu/>
        </header>
    </div>
    <#if widgetList??>
        <#list widgetList as navigationWidget>
            <@previewWarning editMode navigationWidget navigationWidget.errorMessages/>
        </#list>
    </#if>
<#elseif !integration??>
    <@log "The main navigation menu is not available" />
</#if>

<#if banner??>
    <@emergencyBanner module=banner/>
</#if>