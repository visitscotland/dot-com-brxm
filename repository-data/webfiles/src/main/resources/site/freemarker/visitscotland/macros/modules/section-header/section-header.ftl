<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-section-header.ftl">

<#macro sectionHeader heading lede >
    <vs-section-header
        heading="${heading}"
    >
        <template v-slot:section-header-lede>
        <#if lede?is_string>
            ${lede}
        <#else>
            <@hst.html hippohtml=lede />
        </#if>
        </template>
    </vs-section-header>
</#macro>
