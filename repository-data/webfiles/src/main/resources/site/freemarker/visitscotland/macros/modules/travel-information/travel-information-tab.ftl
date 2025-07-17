<#include "../../../../frontend/components/vs-tab-item.ftl">
<#include "../../../../frontend/components/vs-accordion.ftl">
<#include "../../../../frontend/components/vs-accordion-item.ftl">
<#include "../../../../frontend/components/vs-icon.ftl">
<#include "../../../../frontend/components/vs-heading.ftl">
<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.TravelInformationModuleTab" -->

<#function getTransportIcon transportName="">
    <#if transportName == "dsblaccess">
        <#return "wheelchair">
    <#elseif transportName == "cycling">
        <#return "person-biking">
    <#elseif transportName == "car">
        <#return "car-side">
    <#elseif transportName == "boat">
        <#return "ferry">
    <#elseif transportName == "map-marker">
        <#return "location-dot">
    <#elseif transportName == "tram">
        <#return "train-subway">
    <#elseif transportName == "transport">
        <#return "taxi-bus">
    <#elseif transportName == "walk">
        <#return "person-walking">
    <#else>
        <#return transportName>
    </#if>
</#function>

<#macro travelInformationTab module>
    <vs-tab-item title="${module.title}">
        <div class="px-075 px-md-150 px-lg-300 px-xl-400 pt-200 pb-125">
            <vs-accordion>
                <#list module.travelInformationTransportRows as row>
                    <#assign transportIcon = getTransportIcon(row.transport.key)>

                    <vs-accordion-item
                        :open-by-default="<#if row?is_first>true<#else>false</#if>"
                        variant="transparent"
                        control-id="accordion-item-${row.transport.key}-${row?index}"
                        class="<#if row?is_first>border-top-0</#if>"
                    >
                        <template v-slot:title>
                            <vs-icon icon="fa-regular fa-${transportIcon}" size="sm" class="me-050"></vs-icon>
                            ${row.transport.label}
                        </template>
                        <template v-slot:icon-open>
                            <vs-icon icon="fa-regular fa-chevron-up" size="sm" />
                        </template>
                        <template v-slot:icon-closed>
                            <vs-icon icon="fa-regular fa-chevron-down" size="sm" />
                        </template>
                        <div class="p-075">
                            <@hst.html hippohtml=row.copy/>
                        </div>
                    </vs-accordion-item>
                </#list>
            </vs-accordion>
        </div>
    </vs-tab-item>
</#macro>