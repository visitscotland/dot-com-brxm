<#include "../../functions/helpers.ftl">

<#include "../../../frontend/components/vs-icon-list.ftl">
<#include "../../../frontend/components/vs-icon-list-item.ftl">

<#macro keyFacilities facilitiesList>
    <vs-icon-list title="${label('essentials.global', 'keyfacilities.title')}">
        <#list facilitiesList as facility>
            <#assign facilityIcon = getDMSIconName(facility.id)>

            <vs-icon-list-item
                icon="${facilityIcon}"
                label="${facility.name}">
            </vs-icon-list-item>
        </#list>
    </vs-icon-list>
</#macro>

