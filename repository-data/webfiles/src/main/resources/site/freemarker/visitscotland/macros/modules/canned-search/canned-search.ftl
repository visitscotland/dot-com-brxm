<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-iknow-community.ftl">
<#include "../../../../frontend/components/vs-tag.ftl">
<#include "../../../../frontend/components/vs-link.ftl">
<#include "../../../../frontend/components/vs-module-wrapper.ftl">
<#include "../../../../frontend/components/vs-canned-search.ftl">
<#include "../../../../frontend/components/vs-button.ftl">

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.CannedSearchModule" -->

<#macro cannedSearch module themeName="">
    <vs-module-wrapper class="theme-${themeName}">
        <template slot="vsModuleWrapperHeading">
            ${module.title}
        </template>

        <template slot="vsModuleWrapperIntro">
            <@hst.html hippohtml=module.description/>
        </template>

        <vs-canned-search
            api-url="${module.cannedSearchEndpoint}"
            searchType="${module.productType}"
        >
            <template slot="vsCannedSearchButtons">
                <vs-button
                    href="${module.viewAllLink.link}">
                    ${module.viewAllLink.label}
                </vs-button>
            </template>

        </vs-canned-search>

        <#-- ${module.credit} - Credit variable for events -->
    </vs-module-wrapper>
</#macro>