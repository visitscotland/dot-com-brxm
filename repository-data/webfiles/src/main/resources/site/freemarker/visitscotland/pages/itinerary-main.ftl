<#compress>
<#include "../../include/imports.ftl">
<#include "../../frontend/components/vs-icon.ftl">
<#include "../../frontend/components/vs-tooltip.ftl">
<#include "../../frontend/components/vs-itinerary-day.ftl">
<#include "../../frontend/components/vs-itinerary.ftl">

<#include "../macros/modules/page-intro/social-share.ftl">
<#include "../macros/modules/itineraries/itinerary-stop.ftl">
<#include "../macros/modules/itineraries/itinerary-map.ftl">
<#include "../macros/modules/page-intro/page-intro.ftl">
<#include "../macros/global/cms-errors.ftl">
<#include "../macros/shared/module-builder.ftl">
<#include "../macros/modules/horizontal-list/horizontal-list.ftl">
<#include "../macros/modules/signpost/signpost.ftl">

<#-- Implicit Request Objects -->
<#-- @ftlvariable name="document" type="com.visitscotland.brxm.hippobeans.Itinerary" -->
<#-- @ftlvariable name="itinerary" type="com.visitscotland.brxm.model.ItineraryPage" -->
<#-- @ftlvariable name="heroImage" type="com.visitscotland.brxm.model.FlatImage" -->
<#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brxm.model.Coordinates" -->

<#-- Template defined objects -->
<#-- @ftlvariable name="day" type="com.visitscotland.brxm.hippobeans.Day" -->
<#-- @ftlvariable name="stop" type="com.visitscotland.brxm.hippobeans.Stop" -->


<#assign mainTransport = "">
<#assign dayNumber = 0>
<#assign lastStop = 0>

<#if document.transports?has_content >
    <#assign mainTransport = document.transports[0]>
</#if>
</#compress>
<div class="has-edit-button">
    <@hst.manageContent hippobean=document/>
    <@cmsErrors errors=alerts!"" editMode=editMode />

    <@pageIntro content=document heroDetails=heroImage itinerary=itinerary />

    <vs-itinerary>
        <@itineraryMap itinerary />
        <#list itinerary.days as day>
            <#assign dayNumber++>

            <vs-itinerary-day 
                slot="list"
                :default-show="${(dayNumber < 3)?c}"
                day-number="${dayNumber}"
                day-label="${label("itinerary", "day")}"
                day-title="${day.title}"
                >
                <#if day.transports?has_content>
                    <#assign dayTransport = day.transports[0]>
                    <vs-description-list class="text-center justify-content-center align-items-center has-edit-button" slot="day-transport">
                         <@hst.manageContent hippobean=day />
                        <#-- 
                            Note - can't use vs-description-list-item
                            here yet as font style and layout are different 
                            #VS-2985
                        -->
                        <dt class="list-inline-item">${label("itinerary", "transport")}:</dt>
                        <#list day.transports as transport>
                            <dd class="list-inline-item">
                                <vs-button
                                    id="transport-${dayNumber}-${transport}"
                                    tabindex="0"
                                    href="#"
                                    icon="${transport}"
                                    size="lg"
                                    icon-only
                                    icon-variant-override="dark"
                                    class="p-0"
                                    variant="transparent"
                                >
                                    <span class="sr-only">
                                        ${label("transports", "${transport}")}
                                    </span>
                                </vs-button>
                                <vs-tooltip
                                    target="transport-${dayNumber}-${transport}"
                                    title="${label('transports', '${transport}')}"
                                ></vs-tooltip>
                            </dd>
                        </#list>
                    </vs-description-list>
                <#else>
                    <#assign dayTransport = "">
                </#if>

                <div slot="day-introduction">
                    <@hst.html hippohtml=day.introduction/>
                </div>

                <!-- STOP STARTS HERE -->
                <#assign lastStop = lastStop + day.stops?size>
                <#list day.stops as stop>
                    <#assign stopModule = itinerary.stops[stop.identifier]>
                    <@itineraryStop stop=stopModule isLastStop=(stopModule.index==lastStop)?c/>
                </#list>
                <!-- STOP ENDS HERE -->
            </vs-itinerary-day>
        </#list>
    </vs-itinerary>

    <@socialShare nojs=true/>

    <#if otyml??>
        <@horizontalList otyml themeName />
    </#if>

    <#if newsletterSignpost??>
		<@signpost module=newsletterSignpost imgSrc="assets/images/illustrations/newsletter.svg"/>
	</#if>
</div>