<#include "../../../../include/imports.ftl">
<#include "../../macros/shared/module-builder.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-federated-search.ftl">

<#macro searchResults pageItems>

    <#assign searchEventFilters =  ResourceBundle.getAllLabels("search-events-filters", locale) />
    <#assign searchCategories =  ResourceBundle.getAllLabels("search-categories", locale) />

    <vs-container>
        <vs-federated-search
            cludo-api-key="${cludoAPI}"
            :cludo-customer-id="${cludoCustomerId}"
            :cludo-engine-id="${cludoEngineId}"
            events-api="${eventsAPI}"
            :sub-filters="[
                <#list searchEventFilters?keys?sort as key>
                    { Key: '${key}', Label: '${escapeJSON(searchEventFilters[key], false)}'},
                </#list>
            ]"
            :cludo-categories="[
                <#list searchCategories?keys?sort as key>
                    { Key: '${key}', Label: '${escapeJSON(searchCategories[key], false)}'},
                </#list>
            ]"
            :pagination-labels="{
                nextButtonLabel: '${label('essentials.pagination', 'page.next')}',
                previousButtonLabel: '${label('essentials.pagination', 'page.previous')}',
                pageLabel: '${label('essentials.pagination', 'page.page')}',
                ofLabel: '${label('essentials.pagination', 'page.of')}',
            }"
            :search-labels="{
                refine: '${label('search', 'refine')}',
                search: '${label('search', 'search')}',
                searchLabel: '${label('search', 'search-label')}',
                searchResults: '${label('search', 'search.results')}',
                resultsFirst: '${label('search', 'results.first-sentence')}',
                resultsSecond: '${label('search', 'results.second-sentence')}',
                noJs:  '${label('search', 'no-js')}',
            }"
            :sort-labels="{
                dateFrom: '${label('search', 'date.from')}',
                dateTo: '${label('search', 'date.to')}',
                sort: '${label('search', 'sort')}',
                sortOptions: [
                    {
                        key: 'dateAsc',
                        label: '${label('search', 'sort.dateAsc')}',
                    },
                    {
                        key: 'priceAsc',
                        label: '${label('search', 'sort.priceAsc')}',
                    },
                ]
            }"
            :error-messages="{
                noResults: 'no-results',
                incorrectDateOrder: '${label('search', 'error.date')}',
                cludoError: '${label('search', 'error.cludo')}',
                eventError: '${label('search', 'error.events')}',
            }"
            from-text="${label('search', 'price.from')}"
        >
            <#list pageItems as module>
                <template v-slot:federated-search__spotlight-${module.hippoBean.name}>
                    <@moduleBuilder module=module />
                </template>
            </#list>
        </vs-federated-search>
    </vs-container>

</#macro>
