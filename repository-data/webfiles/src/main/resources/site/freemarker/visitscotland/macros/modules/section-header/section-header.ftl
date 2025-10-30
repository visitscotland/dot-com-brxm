<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-section-header.ftl">

<#macro sectionHeader data >
    <vs-section-header
        class="mt-175 mt-md-500"
        heading="${data.heading}"
        lede="${data.lede}"
    />
        <template v-slot:section-header-lede>
           ${data.lede}
        </template>
    </vs-section-header>
</#macro>
