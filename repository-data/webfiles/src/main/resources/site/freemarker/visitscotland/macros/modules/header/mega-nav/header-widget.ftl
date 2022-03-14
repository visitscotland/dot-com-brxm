<#include "../../../../../include/imports.ftl">
<#include "header-featured-item.ftl">

<#macro headerWidget menu accordion=false>
    <#switch menu.type>
        <#case "FeaturedItem">
            <#list menu.links as link>
                <@headerFeaturedItem link link?index accordion />
            </#list>
            <#break>
        <#case "FeaturedEvent">
            FeaturedEvent = ${menu.apiUrl}
            <#break>
        <#default>
    </#switch>
</#macro>