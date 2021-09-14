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
            search-type="${module.productType}"
            carousel-next-text="${label('essentials.pagination', 'page.next')}"
            carousel-prev-text="${label('essentials.pagination', 'page.previous')}"
        >
            <template slot="vsCannedSearchButtons">
                <vs-button
                    href="${module.viewAllLink.link}">
                    ${module.viewAllLink.label}
                </vs-button>
            </template>

            <template slot="vsCannedSearchCredit">
                ${module.credit}
            </template>

            <template slot="vsCannedSearchOf">
                ${label('essentials.pagination', 'page.of')}
            </template>
        </vs-canned-search>
    </vs-module-wrapper>
</#macro>