<#include "../../../../frontend/components/vs-article-section.ftl">
<#include "article-sidebar.ftl">

<#macro articleSection section alignSidebar>
    <vs-article-section sidebar-align="${alignSidebar}" isStandardPage>
        <template slot="articleSidebar">
            <#if section.quote?? || section.image??>
                <@articleSidebar section=section alignSidebar=alignSidebar />
            </#if>
        </template>

        <@hst.html hippohtml=section.copy/>
    </vs-article-section>
</#macro>