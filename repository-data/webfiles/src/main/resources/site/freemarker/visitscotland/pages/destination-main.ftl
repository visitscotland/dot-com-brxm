<#ftl output_format="XML">
<#include "../../include/imports.ftl">
<#include "../macros/global/cms-errors.ftl">
<#include "../macros/modules/page-intro/social-share.ftl">
<#include "../macros/modules/page-intro/page-intro.ftl">
<#include "../macros/modules/signpost/signpost.ftl">
<#include "../macros/shared/module-builder.ftl">

<#-- Implicit Request Objects -->
<#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.Destination" -->
<#-- @ftlvariable name="pageItems" type="com.visitscotland.brxm.hippobeans.Megalinks" -->
<#-- @ftlvariable name="image" type="com.visitscotland.brxm.model.FlatImage" -->

<#-- @ftlvariable name="heroImage" type="com.visitscotland.brxm.model.FlatImage" -->
<#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brxm.model.Coordinates" -->

<div class="has-edit-button">
	<@hst.manageContent hippobean=document/>
    <@cmsErrors errors=alerts!"" editMode=editMode />

    <@pageIntro content=document heroDetails=heroImage />

	<#list pageItems as item>
        <@moduleBuilder item />
	</#list>

    <@socialShare nojs=true/>

    <#if otyml??>
        <@horizontalList otyml themeName />
    </#if>

    <#if newsletterSignpost??>
        <@signpost newsletterSignpost />
    </#if>
</div>
