<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-section-header.ftl">

<#macro sectionHeader heading lede="" >
    <vs-section-header
            heading="${heading}"
    >
        <#if lede != "" && lede?has_content >
            <template v-slot:section-header-lede>
                <@hst.html hippohtml=lede />
            </template>
        </#if>
    </vs-section-header>
</#macro>
