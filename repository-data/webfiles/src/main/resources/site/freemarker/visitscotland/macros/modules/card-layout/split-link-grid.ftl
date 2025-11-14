<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-row.ftl">
<#include "../../../../frontend/components/vs-col.ftl">
<#include "../../../../frontend/components/vs-card-group.ftl">
<#include "../../../../frontend/components/vs-card.ftl">
<#include "../../../../frontend/components/vs-img.ftl">
<#include "../../../../frontend/components/vs-heading.ftl">
<#include "../../../../frontend/components/vs-link.ftl">
<#include "../../../../frontend/components/vs-body.ftl">
<#include "../../../../frontend/components/vs-section-header.ftl">

<#macro splitLinkGrid item>
    <#assign cardsPerRow = (item.layout == 'Grid 4')?then('4', '3') />
    <vs-container>
        <vs-row>
            <vs-col>
                <vs-card-group 
                    cards-per-row="${cardsPerRow}"
                    scroll-snap
                >
                    <#list item.links as listItem>
                        <#if listItem.image.cmsImage??>
                            <#assign image>
                                <@hst.link hippobean=listItem.image.cmsImage.original/>
                            </#assign>
                        <#else>
                            <#assign image>
                                ${listItem.image.externalImage}
                            </#assign>
                        </#if>
                        <vs-card>
                            <template v-slot:vs-card-header>
                                <vs-img
                                    src="${image}"
                                    class="w-100 aspect-ratio-3-2 rounded-1 object-fit-cover img-zoom-on-hover"
                                />
                            </template>
                            <template v-slot:vs-card-body>
                                <vs-heading
                                    level="3"
                                    heading-style="heading-xs"
                                >
                                    <vs-link
                                        href="${listItem.link}"
                                        class="stretched-link"
                                        variant="secondary"
                                    >
                                        ${listItem.label}
                                    </vs-link>
                                </vs-heading>
                                <vs-body class="mb-150">
                                    <p class="truncate-2-lines">
                                        ${listItem.teaser}
                                    </p>
                                </vs-body>
                            </template> 
                        </vs-card>
                    </#list>
                </vs-card-group>
            </vs-col>
        </vs-row>
    </vs-container>
</#macro>
