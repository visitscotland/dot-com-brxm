<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-row.ftl">
<#include "../../../../frontend/components/vs-col.ftl">
<#include "../../../../frontend/components/vs-img.ftl">
<#include "../../../../frontend/components/vs-card-group.ftl">
<#include "../../../../frontend/components/vs-card.ftl">
<#include "../../../../frontend/components/vs-link.ftl">
<#include "../../../../frontend/components/vs-heading.ftl">
<#include "../../../../frontend/components/vs-heading.ftl">

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.CategoryGrid" -->
<#macro categoryGrid cardListOverlay>
<vs-container class="mt-075 mt-lg-200">
    <vs-row>
        <vs-col>
            <vs-card-group scroll-snap="always">
                <#list cardListOverlay as card>
                <vs-card 
                    key="'category-card-list-' + ${card_index}"
                    card-style="overlay"
                >
                    <template v-slot:vs-card-footer>
                            <div class="px-125 pb-125">
                                <vs-heading
                                    level="2"
                                    no-margins
                                    heading-style="heading-m"
                                >
                                    <vs-link
                                        href="${link}"
                                        class="stretched-link text-decoration-none"
                                        variant="on-dark"
                                    >
                                        ${card.title}
                                    </vs-link>
                                </vs-heading>
                            </div>
                        </template>
                    <template v-slot:vs-card-image>
                        <vs-img 
                            src="${card.image}"
                            class="w-100 aspect-ratio-3-2 rounded-1 object-fit-cover img-zoom-on-hover"
                        />
                    </template>
               </vs-card>
                </#list>
            </vs-card-group>
        </vs-col>
    </vs-row>
</vs-container>
</#macro>
