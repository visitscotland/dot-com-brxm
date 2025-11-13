<#include "../../../../../include/imports.ftl">
<#include "../../../../../frontend/components/vs-container.ftl">
<#include "../../../../../frontend/components/vs-row.ftl">
<#include "../../../../../frontend/components/vs-col.ftl">
<#include "../../card-layout/split-link-grid.ftl">
<#include "../../section-header/section-header.ftl">


<#macro megalinksCardGrid item>
    <vs-container>
        <vs-row>
            <vs-col>
                <div class="mb-250">
                    <@sectionHeader heading=item.title lede=item.introduction />
                </div>
                <@splitLinkGrid item />
            </vs-col>
        </vs-row>
    </vs-container>
</#macro>
