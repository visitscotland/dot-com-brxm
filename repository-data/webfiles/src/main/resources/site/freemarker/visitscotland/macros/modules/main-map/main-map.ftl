<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-main-map.ftl">

<#macro mainMap module>
    <#assign language = locale?keep_before("-")>

    <div>
        <vs-main-map
            api-key = "${mapsAPI}"
            map-id = "b55b94a250e70307420a5743"
            map-type-vector
            :center = "{
                lat: 56.490153,
                lng: 4.10959,
            }"
            :zoom = "6"
            :radius = "5000"
            no-js-message = "${label('map', 'map.no-js')}"
            no-cookies-message = "${label('map', 'map.cookies-message')}"
            cookie-btn-text = "${label('essentials.global', 'cookie.link-message')}"
            no-results-message = "${label('map', 'map.no-results-message')}"
            reset-map-no-results-message = "${label('map', 'map.reset')}"
            :category-labels="${escapeJSON(module.filters,true)}"
            categories-location="https://static.visitscotland.com/maps-resources/main-map/map-categories.json"
            :labels = "{
                heading: '${module.tabTitle}',
                closeSidebarBtn: '${label('map', 'map.close-panel')}',
                searchBarAriaLabel: '${label('map', 'map.search-map')}',
                inputPlaceholder: '${label('map', 'map.placeholder')}',
                searchButton: '${label('map', 'map.search')}',
                clearMap: '${label('map', 'map.clear')}',
                subFilterHeader: '${label('map', 'map.sub-filter')}',
                searchResults: '${label('map', 'map.search-results')}',
                openSidebarButton: '${label('map', 'map.open-panel')}',
            }"
            language-code="${language}"
        />
    </div>
</#macro>