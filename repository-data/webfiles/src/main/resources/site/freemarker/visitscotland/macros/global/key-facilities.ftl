<#include "../../../frontend/components/vs-icon-list.ftl">
<#include "../../../frontend/components/vs-icon-list-item.ftl">

<#include "../shared/icon-lookup.ftl">

<#macro keyFacilities facilitiesList>
    <vs-icon-list title="${label('essentials.global', 'keyfacilities.title')}">
        <#list facilitiesList as facility>
            <#assign iconName = iconLookup(facility.id)>

            <vs-icon-list-item
                icon="${iconName}"
                label="${facility.name}">
            </vs-icon-list-item>
        </#list>
    </vs-icon-list>
</#macro>

