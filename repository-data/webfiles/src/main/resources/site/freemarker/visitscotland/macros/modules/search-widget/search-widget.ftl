<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-fed-search-input.ftl">

<#macro siteSearchWidget>

    <#assign searchCategories =  ResourceBundle.getAllLabels("search-categories", locale) />

    <vs-container>
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
            search-url="<@hst.link path="${property('global-search.path', locale)}" />"
        >
        </vs-fed-search-input>
    </vs-container>
</#macro>
