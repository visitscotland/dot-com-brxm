<#compress>
    <#include "../../include/imports.ftl">
    <#include "../../frontend/components/vs-icon.ftl">
    <#include "../../frontend/components/vs-tooltip.ftl">
    <#include "../../frontend/components/vs-itinerary-day.ftl">
    <#include "../../frontend/components/vs-itinerary.ftl">
    <#include "../../frontend/components/vs-description-list.ftl">
    <#include "../../frontend/components/vs-description-list-item.ftl">
    <#include "../macros/modules/page-intro/social-share.ftl">
    <#include "../macros/modules/itineraries/itinerary-stop.ftl">
    <#include "../macros/modules/itineraries/itinerary-map.ftl">
    <#include "../macros/modules/page-intro/page-intro.ftl">
    <#include "../macros/global/otyml.ftl">
    <#include "../macros/shared/module-builder.ftl">
    <#include "../macros/modules/horizontal-list/horizontal-list.ftl">
    <#include "../macros/modules/signpost/signpost.ftl">
    <#include "../macros/modules/product-search/psr-module.ftl">

    <#-- Implicit Request Objects -->
    <#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.Itinerary" -->
    <#-- @ftlvariable name="itinerary" type="com.visitscotland.brxm.model.ItineraryPage" -->
    <#-- @ftlvariable name="heroImage" type="com.visitscotland.brxm.model.FlatImage" -->
    <#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brxm.model.Coordinates" -->

    <#-- Template defined objects -->
    <#-- @ftlvariable name="day" type="com.visitscotland.brxm.hippobeans.Day" -->
    <#-- @ftlvariable name="stop" type="com.visitscotland.brxm.hippobeans.Stop" -->
</#compress>

<#assign mainTransport = "">
<#assign dayNumber = 0>
<#assign lastStop = 0>

<#if document.transports?has_content >
    <#assign mainTransport = document.transports[0]>
</#if>

<div class="has-edit-button">
    <@hst.manageContent hippobean=document/>
    <@pageIntro content=document heroDetails=heroImage itinerary=itinerary />

    <vs-itinerary>
        <template v-slot:map>
            <@itineraryMap itinerary />
        </template>
        <template v-slot:list>
            <#list itinerary.days as day>
                <#assign dayNumber++>

                <vs-itinerary-day
                    :default-show="${(dayNumber < 3)?c}"
                    day-number="${dayNumber}"
                    day-label="${label("itinerary", "day")}"
                    day-title="${day.title}"
                >
                    <#if day.transports?has_content>
                        <#assign dayTransport = day.transports[0]>
                        <template v-slot:day-transport> 
                            <vs-description-list 
                                class="text-center justify-content-center mb-075 has-edit-button"
                                inline
                            >
                                <@hst.manageContent hippobean=day />
                                <vs-description-list-item 
                                    title
                                    class="col-auto px-0"
                                >
                                    ${label("itinerary", "transport")}
                                </vs-description-list-item>
                                <#list day.transports as transport>
                                    <vs-description-list-item 
                                        class="col-auto px-0"
                                    >
                                        <#assign transportIcon = getDMSIconName(transport)>

                                        <vs-tooltip
                                            title="${label('transports', '${transport}')}"
                                            href="#"
                                            icon="${transportIcon}"
                                            size="sm"
                                            icon-only
                                            variant="transparent"
                                        >
                                            <span class="visually-hidden">
                                                ${label("transports", "${transport}")}
                                            </span>
                                        </vs-tooltip>
                                    </vs-description-list-item>
                                </#list>
                            </vs-description-list>
                        </template>
                    <#else>
                        <#assign dayTransport = "">
                    </#if>

                    <template v-slot:day-introduction>
                        <@hst.html hippohtml=day.introduction/>
                    </template>
                    
                    <template v-slot:stops>
                        <!-- STOP STARTS HERE -->
                        <#assign lastStop = lastStop + day.stops?size>
                        <#list day.stops as stop>
                            <#assign stopModule = itinerary.stops[stop.identifier]>
                            <@itineraryStop stop=stopModule isLastStop=(stopModule.index==lastStop)?c/>
                        </#list>
                        <!-- STOP ENDS HERE -->
                    </template>
                </vs-itinerary-day>
            </#list>
        </template>
    </vs-itinerary>

    <@socialShare nojs=true/>

    <@productSearchWidget psrWidget />

    <#if otyml??>
        <@otymlModule otyml editMode />
    </#if>

    <#if newsletterSignpost??>
		<@signpost module=newsletterSignpost />
	</#if>
</div>