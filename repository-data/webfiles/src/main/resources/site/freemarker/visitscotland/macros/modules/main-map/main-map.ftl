<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-main-map.ftl">

<#macro mainMap module>
    <#assign language = locale?keep_before("-")>

    <div>
        <vs-main-map
            apiKey = "${mapsAPI}"
            :center = "{
                lat: 56.490153,
                lng: 4.10959,
            }"
            :zoom = "6"
            :radius = "5000"
            noJsMessage = "${label('map', 'map.no-js')}"
            noCookiesMessage = "${label('map', 'map.cookies-message')}"
            cookieBtnText = "${label('essentials.global', 'cookie.link-message')}"
            :categories="${escapeJSON(module.filters,true)}"
            :labels = "{
                heading: '${module.tabTitle}',
                closeSidebarBtn: '${label('map', 'map.close-panel')}',
                inputPlaceholder: '${label('map', 'map.placeholder')}',
                searchButton: '${label('map', 'map.search')}',
                clearMap: '${label('map', 'map.clear')}',
                subFilterHeader: '${label('map', 'map.sub-filter')}',
                searchResults: '${label('map', 'map.places')}',
                openSidebarButton: '${label('map', 'map.open-panel')}',
            }"
            language="${language}"
        />
    </div>
</#macro>