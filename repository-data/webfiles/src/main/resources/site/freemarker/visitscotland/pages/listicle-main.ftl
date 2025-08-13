<#ftl output_format="XML">
<#compress>
    <#include "../../include/imports.ftl">
    <#include "../../frontend/components/vs-container.ftl">
    <#include "../../frontend/components/vs-row.ftl">
    <#include "../../frontend/components/vs-col.ftl">
    <#include "../../frontend/components/vs-heading.ftl">
    <#include "../../frontend/components/vs-body.ftl">
    <#include "../../frontend/components/vs-listicle-item.ftl">
    <#include "../../frontend/components/vs-panel.ftl">
    <#include "../macros/modules/page-intro/social-share.ftl">
    <#include "../macros/modules/listicles/listicle-item.ftl">
    <#include "../macros/global/otyml.ftl">
    <#include "../macros/shared/module-builder.ftl">
    <#include "../macros/modules/horizontal-list/horizontal-list.ftl">
    <#include "../macros/modules/page-intro/page-intro.ftl">
    <#include "../macros/modules/signpost/signpost.ftl">
    <#include "../macros/modules/product-search/psr-module.ftl">

    <#-- Implicit Request Objects -->
    <#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.Listicle" -->
    <#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brxm.model.Coordinates" -->
    <#-- @ftlvariable name="item" type="com.visitscotland.brxm.model.ListicleModule" -->
    <#-- @ftlvariable name="cta" type="com.visitscotland.brxm.model.FlatLink" -->
</#compress>
<div class="has-edit-button">
    <@hst.manageContent hippobean=document/>

    <@pageIntro content=document author=author isListicle=true/>

    <vs-container class="mt-150">
        <vs-row>
            <vs-col cols="12">
                <ol style="list-style:none; margin:0; padding:0;">
                    <#if items?? && items?has_content>
                        <#list items as listItem>
                            <#if listItem?is_first>
                                <#assign firstListicle="false">
                            <#else>
                                <#assign firstListicle="true">
                            </#if>
                            <@listicleItem item=listItem isFirstListicle=firstListicle/>
                        </#list>
                    </#if>
                </ol>
            </vs-col>
        </vs-row>
    </vs-container>

    <#if document.listicleClosing??>
        <vs-container>
            <vs-row class="mb-150">
                <vs-col cols="12">
                    <vs-panel>
                        <#if document.listicleClosing.title?has_content>
                            <template v-slot:vs-panel-title>
                                <vs-heading
                                    level="4"
                                    heading-style="heading-m"
                                >
                                    ${document.listicleClosing.title}
                                </vs-heading>
                            </template>
                        </#if>

                        <vs-body variant="lead">
                            <@hst.html hippohtml=document.listicleClosing.copy/>
                        </vs-body>
                    </vs-panel>
                </vs-col>
            </vs-row>
        </vs-container>
    </#if>

    <@socialShare nojs=true/>

    <@productSearchWidget psrWidget />

    <#if otyml??>
        <@otymlModule otyml editMode />
    </#if>

    <#if newsletterSignpost??>
		<@signpost module=newsletterSignpost />
	</#if>
</div>
