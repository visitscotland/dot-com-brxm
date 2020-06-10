<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/listicle-item.ftl">
<#include "../../../../frontend/components/link.ftl">

<#include "../../global/key-facilities.ftl">
<#include "../../global/image-with-caption.ftl">
<#include "../../global/cms-errors.ftl">

<#macro listicleItem item>
<#-- @ftlvariable name="listItem" type="com.visitscotland.brmx.beans.ListicleItem" -->
<#-- @ftlvariable name="item" type="com.visitscotland.brmx.beans.mapping.FlatListicle" -->
<#-- @ftlvariable name="cta" type="com.visitscotland.brmx.beans.mapping.FlatLink" -->
	<#assign image = "" />
    <#if item.image.cmsImage??>
        <#assign image>
            <@hst.link hippobean=item.image.cmsImage.original/>
        </#assign>
    <#elseif item.image.externalImage??>
        <#assign image = item.image.externalImage />
    </#if>
	
	<vs-listicle-item
		index="${item.index}"
		title="${item.title}"
		sub-title="${item.subTitle}"
	>
		<div slot="hippo-details" class="has-edit-button">
			<@hst.manageContent hippobean=item.listicleItem/>
            <@cmsErrors errors=item.errorMessages!"" editMode=editMode />
		</div>

		<#if image?? && image?has_content>
			<div slot="image-slot">
                <@imageWithCaption imageSrc=image imageDetails=item.image variant="large"/>
			</div>
		</#if>

		<div slot="description-slot">
			<@hst.html hippohtml=item.description />

			<#if item.links?has_content>
				<#list item.links as cta>
					<#if cta?has_content>
                        <div class="mb-2">
						    <vs-link href="${cta.link}">${cta.label}</vs-link>
                        </div>
					</#if>
				</#list>
			</#if>
		</div>

        <#if item.facilities?? && item.facilities?size gt 1>
			<div slot="facilities-slot">
				<@keyFacilities facilitiesList=item.facilities />
			</div>
		</#if>
	</vs-listicle-item>
</#macro>
