<#include "../../include/imports.ftl">
<#include "breadcrumb-schema.ftl">
<#include "../../frontend/components/vs-breadcrumb.ftl">
<#include "../../frontend/components/vs-breadcrumb-item.ftl">

<@hst.setBundle basename="navigation.static"/>

<#-- @ftlvariable name="breadcrumb" type="org.onehippo.forge.breadcrumb.om.Breadcrumb" -->

<#if breadcrumb?? && breadcrumb.items??>

    <vs-breadcrumb>
        <vs-breadcrumb-item
                key="home"
                href="<@hst.link siteMapItemRefId="root"/>"
                <#if isHome>active</#if>
                text="<@fmt.message key="home" />"
        >

        </vs-breadcrumb-item>

        <#list breadcrumb.items as item>
            <#-- Avoid duplicated Home element (Document based pages skip this element)-->
            <#if item.link?? &&  item.link.hstSiteMapItem.id?? && item.link.hstSiteMapItem.id != "root">

                <#if item.link??>
                    <@hst.link var="link" link=item.link/>
                <#else>
                    <#-- This asignation removes the link from the breadcrumb -->
                    <#assign link = requestedURI>
                </#if>


            <#--1(${link}) != 2(${requestedURI})-->
                <vs-breadcrumb-item
                        key="${item.title}"
                        href="${link}"
                        <#if link?? && link == requestedURI >active</#if>
                        text="${item.title?html}"
                >
                </vs-breadcrumb-item>

            </#if>
        </#list>

    </vs-breadcrumb>
</#if>


