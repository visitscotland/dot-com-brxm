<#include "../../../../include/imports.ftl">
<#include "hst:hst/macros.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-fed-search-input.ftl">
<#include "../section-header/section-header.ftl">

<#-- @ftlvariable name="hstRequestContext" type="org.hippoecm.hst.core.request.HstRequestContext" -->
<#macro siteSearchWidget>

    <#assign searchCategories =  ResourceBundle.getAllLabels("search-categories", locale) />
    <#assign widgetHeading = label('search', 'search.widget-title') />
    <#assign widgetLede = label('search', 'search.widget-label') />
    
    <vs-container>
        <@sectionHeader heading=widgetHeading lede=widgetLede />

        <vs-fed-search-input
            cludo-api-key="${cludoAPI}"
            :cludo-customer-id="${cludoCustomerId}"
            :cludo-engine-id="${cludoEngineId}"
            :filters="[
                <#list searchCategories?keys?sort as key>
                    { Key: '${key}', Label: '${escapeJSON(searchCategories[key], false)}'},
                </#list>
            ]"
            is-home-page
            :labels="{
                refine: '${label('search', 'refine')}',
                search: '${label('search', 'search')}',
                searchLabel: '${label('search', 'search-label')}',
                searchResults: '${label('search', 'search.results')}',
                resultsFirst: '${label('search', 'results.first-sentence')}',
                resultsSecond: '${label('search', 'results.second-sentence')}',
                noJs: '${label('search', 'no-js')}',
            }"
            search-url="${searchLink!}"
        >
        </vs-fed-search-input>
    </vs-container>
</#macro>
