<#include "../section-header/section-header.ftl">
<#include "../category-grid/category-grid.ftl">

<#macro categorySection data >
        <@sectionHeader heading=data.title lede=data.introduction />
        <@categoryGrid links=data.links />
</#macro>
