<#ftl output_format="XML">
<#compress>
<#include "../../include/imports.ftl">

<#include "../../frontend/components/vs-container.ftl">
<#include "../../frontend/components/vs-row.ftl">
<#include "../../frontend/components/vs-col.ftl">
<#include "../../frontend/components/vs-icon.ftl">
<#include "../../frontend/components/vs-social-share.ftl">
<#include "../../frontend/components/vs-heading.ftl">
<#include "../../frontend/components/vs-img.ftl">
<#include "../../frontend/components/vs-link.ftl">
<#include "../../frontend/components/vs-rich-text-wrapper.ftl">
<#include "../../frontend/components/vs-listicle-item.ftl">
<#include "../../frontend/components/vs-panel.ftl">

<#include "../macros/modules/listicles/listicle-item.ftl">
<#include "../macros/global/cms-errors.ftl">

<#-- Implicit Request Objects -->
<#-- @ftlvariable name="document" type="com.visitscotland.brmx.beans.Listicle" -->
<#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brmx.beans.mapping.Coordinates" -->
<#-- @ftlvariable name="item" type="com.visitscotland.brmx.beans.mapping.FlatListicle" -->
<#-- @ftlvariable name="cta" type="com.visitscotland.brmx.beans.mapping.FlatLink" -->

</#compress>
<div class="has-edit-button">
	<@hst.manageContent hippobean=document documentTemplateQuery="new-module" rootPath="site" defaultPath="${path}" />
    <@cmsErrors errors=alerts!"" editMode=editMode />
    
    
	<vs-container slot="upper" class="py-lg-4">
		<vs-row>
			<vs-col cols="12" lg="8">
				<@hst.include ref="breadcrumb"/>
			</vs-col>
		</vs-row>

		<vs-row>
			<vs-col cols="10" lg="8">
				<vs-heading level="1">${document.title}</vs-heading>
			</vs-col>
			<vs-col cols="2">
				<div class="d-flex justify-content-center justify-content-sm-end">
					<vs-social-share></vs-social-share>
				</div>
			</vs-col>
		</vs-row>

		<vs-row class="mb-6">
			<vs-col cols="12" lg="8">
                <vs-rich-text-wrapper variant="lead">
                    <@hst.html hippohtml=document.introduction/>
                </vs-rich-text-wrapper>
			</vs-col>
		</vs-row>

		<ol style="list-style:none; margin:0; padding:0;">
			<#list items as listItem>
				<@listicleItem item=listItem />
			</#list>
		</ol>

        <#assign summary>
            <@hst.html hippohtml=document.summary/>
        </#assign>

        <#if summary?has_content>
            <vs-row class="mb-6">
                <vs-col cols="12">
                    <vs-panel>
                        <#if document.summaryTitle?has_content>
                            <vs-heading thin level="4" slot="vs-panel-title">${document.summaryTitle}</vs-heading>
                        </#if>

                        <vs-rich-text-wrapper variant="lead">
                            ${summary}
                        </vs-rich-text-wrapper>
                    </vs-panel>
                </vs-col>
            </vs-row>
        </#if>
	</vs-container>
</div>
