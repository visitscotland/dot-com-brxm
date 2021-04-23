<#ftl output_format="XML">
<#compress>
<#include "../../include/imports.ftl">

<#include "../../frontend/components/vs-container.ftl">
<#include "../../frontend/components/vs-row.ftl">
<#include "../../frontend/components/vs-col.ftl">
<#include "../../frontend/components/vs-social-share.ftl">
<#include "../../frontend/components/vs-heading.ftl">
<#include "../../frontend/components/vs-rich-text-wrapper.ftl">
<#include "../../frontend/components/vs-listicle-item.ftl">
<#include "../../frontend/components/vs-panel.ftl">

<#include "../macros/modules/listicles/listicle-item.ftl">
<#include "../macros/global/cms-errors.ftl">
<#include "../macros/shared/module-builder.ftl">
<#include "../macros/modules/otyml/otyml.ftl">
<#include "../macros/modules/page-intro/page-intro.ftl">

<#-- Implicit Request Objects -->
<#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.Listicle" -->
<#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brxm.model.Coordinates" -->
<#-- @ftlvariable name="item" type="com.visitscotland.brxm.model.ListicleModule" -->
<#-- @ftlvariable name="cta" type="com.visitscotland.brxm.model.FlatLink" -->

</#compress>
<div class="has-edit-button">
	<@hst.manageContent hippobean=document documentTemplateQuery="new-listicle-item" rootPath="site" defaultPath="${path}" />
    <@cmsErrors errors=alerts!"" editMode=editMode />

    <@pageIntro content=document heroDetails="" />
        <vs-container class="mt-6">
            <vs-row>
                <vs-col cols="12">
                    <ol style="list-style:none; margin:0; padding:0;">
                        <#if items?? && items?has_content >
                            <#list items as listItem>
                                <@listicleItem item=listItem />
                            </#list>
                        </#if>
                    </ol>
                </vs-col>
            </vs-row>
        </vs-container>

        <#if document.listicleClosing??>
                <vs-row class="mb-6">
                    <vs-col cols="12">
                        <vs-panel>
                            <#if document.listicleClosing.title?has_content>
                                <template slot="vs-panel-title">
                                    <vs-heading thin level="4">${document.listicleClosing.title}</vs-heading>
                                </template>
                            </#if>

                            <vs-rich-text-wrapper variant="lead">
                                <@hst.html hippohtml=document.listicleClosing.copy/>
                            </vs-rich-text-wrapper>
                        </vs-panel>
                    </vs-col>
                </vs-row>
        </#if>
        <!-- commented out to prevent stack trace errors - needs fixing -->
        <#--  <#if otyml??>
            <@moduleBuilder module />
        </#if>  -->
	</vs-container>
</div>
