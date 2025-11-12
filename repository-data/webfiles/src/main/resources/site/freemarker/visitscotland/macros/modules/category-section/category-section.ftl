<#include "../section-header/section-header.ftl">
<#include "../category-grid/category-grid.ftl">

<#macro categorySection data >
        <#if data?? && data.title?? && data.introduction?? && data.links?has_content>
                <@sectionHeader heading=data.title lede=data.introduction />
                <@categoryGrid links=data.links />
        </#if>
</#macro>
