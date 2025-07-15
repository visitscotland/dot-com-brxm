<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-footer-accordion-item.ftl">
<#include "../../../../frontend/components/vs-footer-nav-list-item.ftl">
<#include "../../../../frontend/components/vs-icon.ftl">
<#include "../../../../frontend/components/vs-list.ftl">

<#macro footerAccordionItem footerMenuItems>
    <@hst.cmseditmenu menu=menu/>
    <#list footerMenuItems as menuItem>
        <vs-col cols="12" md="4" lg="3">
            <#if menuItem.title?has_content>
                <#assign title>${menuItem.title?html}</#assign>
            </#if>
            <vs-footer-accordion-item
                title="${title}"
                control-id="footer_accordion_item_${menuItem?index}" 
                class="<#if menuItem?is_first>border-start-0 ps-md-0</#if> <#if menuItem?is_last>border-bottom-0</#if>"
            >
                <template v-slot:icon-open>
                    <vs-icon icon="fa-regular fa-chevron-up" variant="inverse" size="xs"></vs-icon>
                </template>

                <template v-slot:icon-closed>
                    <vs-icon icon="fa-regular fa-chevron-down" orientation="down" variant="inverse" size="xs"></vs-icon>
                </template>

                <#if menuItem.childMenuItems?size gt 0>
                    <vs-list
                        unstyled
                        role="menu"
                    >
                        <#list menuItem.childMenuItems as childItem>
                            <#if childItem.title?has_content>
                                <vs-footer-nav-list-item
                                    href="${getUrl(childItem)}"
                                    link-text="${childItem.title}"
                                    type="<#if childItem.externalLink??>external<#else>default</#if>"
                                ></vs-footer-nav-list-item>
                            </#if>
                        </#list>
                    </vs-list>
                </#if>
            </vs-footer-accordion-item>
         </vs-col>
    </#list>
</#macro>