<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-section-header.ftl">

<#macro sectionHeader heading lede >
    <vs-section-header
        heading="${heading}"
    />
        <template v-slot:section-header-lede>
           <@hst.html hippohtml=lede />
        </template>
    </vs-section-header>
</#macro>
