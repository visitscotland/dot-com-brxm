<#include "../../include/imports.ftl">
<#-- @ftlvariable name="menu" type="org.hippoecm.hst.core.sitemenu.HstSiteMenu" -->
<#-- @ftlvariable name="enhancedMenu" type="java.util.List" -->
<#-- @ftlvariable name="item" type=""com.visitscotland.www.components.navigation.VsMenuItem" -->
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<#if enhancedMenu??>
<div class="has-edit-button">
    <ul class="nav nav-pills">
      <#list enhancedMenu as item>
          <#if item.title?has_content>
            <#if !item.hstLink?? && !item.externalLink??>
                <#if item.selected || item.expanded>
                <li class="active">${item.title?html}</li>
                <#else>
                <li>${item.title?html}</li>
                </#if>
            <#else>
                <#if item.hstLink??>
                    <#assign href><@hst.link link=item.hstLink/></#assign>
                <#elseif item.externalLink??>
                    <#assign href>${item.externalLink?replace("\"", "")}</#assign>
                </#if>
                <#if item.widget??>
                    <li><a style="background: lightcyan;">Widget (${item.widget.component})</a></li>
                <#elseif  item.selected || item.expanded>
                    <li class="active">${item.title?html} (<a href="${href}">${item.cta?html}</a>)</li>
                <#else>
                    <li>${item.title?html} (<a href="${href}">${item.cta?html}</a>)</li>
                </#if>
            </#if>
          </#if>
      </#list>
    </ul>
    <@hst.cmseditmenu menu=menu/>
</div>
</#if>