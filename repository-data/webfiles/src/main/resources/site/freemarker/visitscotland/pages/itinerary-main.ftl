<#include "../../include/imports.ftl">

<#--  <#include "../../frontend/stores/vs-store-itineraries-store.ftl">  -->
<#include "../../frontend/components/vs-page-intro.ftl">
<#include "../../frontend/components/vs-hero.ftl">
<#include "../../frontend/components/vs-container.ftl">
<#include "../../frontend/components/vs-row.ftl">
<#include "../../frontend/components/vs-col.ftl">
<#include "../../frontend/components/vs-icon.ftl">
<#include "../../frontend/components/vs-rich-text-wrapper.ftl">
<#include "../../frontend/components/vs-image-with-caption.ftl">
<#include "../../frontend/components/vs-image-location-map.ftl">
<#include "../../frontend/components/vs-button.ftl">
<#include "../../frontend/components/vs-heading.ftl">
<#include "../../frontend/components/vs-img.ftl">
<#include "../../frontend/components/vs-tooltip.ftl">
<#include "../../frontend/components/vs-summary-box-list.ftl">
<#include "../../frontend/components/vs-summary-box-list-item.ftl">
<#include "../../frontend/components/vs-summary-box-display.ftl">
<#include "../../frontend/components/vs-summary-box-label.ftl">
<#include "../../frontend/components/vs-summary-box-distance-display.ftl">
<#include "../../frontend/components/vs-summary-box-distance-label.ftl">
<#include "../../frontend/components/vs-summary-box-icon-with-label.ftl">
<#include "../../frontend/components/vs-description-list.ftl">
<#include "../../frontend/components/vs-description-list-term.ftl">
<#include "../../frontend/components/vs-description-list-detail.ftl">
<#include "../../frontend/components/vs-itinerary-day.ftl">
<#include "../../frontend/components/vs-itinerary.ftl">
<#include "../../frontend/components/vs-svg.ftl">

<#include "../macros/modules/itineraries/itinerary-stop.ftl">
<#include "../macros/modules/itineraries/itinerary-map.ftl">
<#include "../macros/global/cms-errors.ftl">

<#-- Implicit Request Objects -->
<#-- @ftlvariable name="document" type="com.visitscotland.brmx.beans.Itinerary" -->
<#-- @ftlvariable name="firstStopLocation" type="java.lang.String" -->
<#-- @ftlvariable name="lastStopLocation" type="java.lang.String" -->
<#-- @ftlvariable name="heroImage" type="com.visitscotland.brmx.beans.mapping.FlatImage" -->
<#-- @ftlvariable name="heroCoordinates" type="com.visitscotland.brmx.beans.mapping.Coordinates" -->

<#-- Template defined objects -->
<#-- @ftlvariable name="day" type="com.visitscotland.brmx.beans.Day" -->
<#-- @ftlvariable name="hero" type="com.visitscotland.brmx.beans.Image" -->

<#assign mainTransport = "">
<#assign dayNumber = 0>
<#assign stopNumber = 0>
<#assign lastStop = 0>

<#if document.transports?has_content >
    <#assign mainTransport = document.transports[0]>
</#if>

<div class="has-edit-button">
    <@hst.manageContent hippobean=document documentTemplateQuery="new-document" rootPath="site" defaultPath="${path}" />
    <@cmsErrors errors=alerts!"" editMode=editMode />

    <vs-page-intro>
        <#if heroImage??>
            <@hst.link var="hero" hippobean=document.heroImage.original/>
            <vs-hero
                slot="hero"
                alt-text="${heroImage.altText}"
                credit="${heroImage.credit}"
                caption="${heroImage.description}"
                image-src="${hero}"
                latitude="${(heroCoordinates.latitude)!''}"
                longitude="${(heroCoordinates.longitude)!''}"
            >
                <vs-img
                    class="lazyload"
                    src="${hero}"
                    srcset="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw=="
                    data-srcset="${hero}"
                    alt="${document.heroImage.altText}"
                    data-sizes="auto"
                        > </vs-img>
            </vs-hero>
        </#if>
        <vs-container slot="upper" class="py-lg-4">
            <vs-row class="justify-content-md-between">
                <vs-col cols="12" lg="8" offset-lg="1">
                <#-- TODO: BREADCRUMB as part of the main template -->
                 <@hst.include ref="breadcrumb"/>
                </vs-col>
            </vs-row>
            <vs-row>
                <vs-col cols="10" lg="8" offset-lg="1">
                    <vs-heading level="1">
                        ${document.title}
                    </vs-heading>
                </vs-col>
                <vs-col cols="2">
                    <div class="d-flex justify-content-center justify-content-sm-end">
                        <!-- TODO - Below icon is FPO. Replace with icon with text component and a share component -->
                        <vs-icon name="share" variant="dark" size="sm"></vs-icon>
                    </div>
                </vs-col>
            </vs-row>
            <vs-row>
                <vs-col cols="12" md="6" lg="5" xl="6" offset-lg="1">
                    <vs-rich-text-wrapper variant="lead">
                        <@hst.html hippohtml=document.introduction/>
                    </vs-rich-text-wrapper>
                    <dl class="list-inline">
                        <dt class="list-inline-item">${label("itinerary", "start-finish")}</dt>
                        <dd class="list-inline-item">${firstStopLocation} / ${lastStopLocation}</dd>
                    </dl>
                </vs-col>
                <vs-col cols="12" md="6" lg="5" xl="4">
                    <vs-summary-box-list>
                        <vs-summary-box-list-item>
                            <vs-summary-box-display text="${document.days?size}"/></vs-summary-box-display>
                            <vs-summary-box-label label="${label('itinerary', 'days')}"></vs-summary-box-label>
                        </vs-summary-box-list-item>
                        <vs-summary-box-list-item>
                            <vs-summary-box-distance-display
                                miles="${distance}"
                                kilometres="${(distance*1.6)}"
                                miles-label="${label("itinerary", "miles")}"
                                kilometres-label="${label("itinerary", "kilometres")}">
                            </vs-summary-box-distance-display>
                            <vs-summary-box-distance-label
                                distance-label="${label("itinerary", "distance")}"
                                kilometres-abbr="${label("itinerary", "kilometres-abbreviation")}"
                                kilometres-label="${label("itinerary", "kilometres")}"
                                miles-abbr="${label("itinerary", "miles-abbreviation")}"
                                miles-label="${label("itinerary", "miles")}">
                            </vs-summary-box-distance-label>
                        </vs-summary-box-list-item>
                              <vs-summary-box-list-item>
                            <vs-summary-box-icon-with-label
                                icon="${mainTransport}"
                                label="${label("transports", "${mainTransport}")}">
                            </vs-summary-box-icon-with-label>
                            <vs-summary-box-label 
                                label="${label("itinerary", "transport")}">
                            </vs-summary-box-label>
                        </vs-summary-box-list-item>
                        <vs-summary-box-list-item>
                            <vs-summary-box-icon-with-label
                                icon="${document.theme}"
                                label="${label("themes", "${document.theme}")}">
                            </vs-summary-box-icon-with-label>
                            <vs-summary-box-label label="${label("itinerary", "theme")}"></vs-summary-box-label>
                        </vs-summary-box-list-item>
                    </vs-summary-box-list>
                </vs-col>
            </vs-row>
        </vs-container>
        <vs-container slot="lower">
            <vs-row>
                <vs-col cols="12" lg="11" offset-lg="1">
                    <vs-description-list class="mb-6">
                        <vs-description-list-term>
                            ${label("itinerary", "highlights")}
                        </vs-description-list-term>
                        <#-- TODO: each ${document.highlight} should render a new dd element -->
                        <vs-description-list-detail>
                            <div style="white-space: pre-wrap">${document.highlights}</div>
                        </vs-description-list-detail>
                    </vs-description-list>
                    <vs-description-list class="mb-8">
                        <vs-description-list-term>
                            ${label("itinerary", "areas-covered")}
                        </vs-description-list-term>
                        <#list document.areas as area>
                            <vs-description-list-detail>
                                ${label("areas", "${area}")}${"\n"}
                            </vs-description-list-detail>
                        </#list>
                    </vs-description-list>
                </vs-col>
            </vs-row>
        </vs-container>
    </vs-page-intro>
    <vs-itinerary>
        <@itineraryMap days=document.days />
        <#list document.days as day>
            <#assign dayNumber++>
            <#assign dayTransport = "">
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
                            Note - can't use vs-description-list-term and vs-description-list-detail 
                            here yet as font style and layout are different 
                        -->
                        <dt class="list-inline-item">${label("itinerary", "transport")}:</dt>
                        <#list day.transports as transport>
                            <dd class="list-inline-item">
                                <vs-tooltip title="${label("transports", "${transport}")}">
                                    <vs-icon name="${transport}" variant="dark" size="sm"></vs-icon>
                                </vs-tooltip>
                                <span class="sr-only">${label("transports", "${transport}")}</span>
                            </dd>
                        </#list>
                    </vs-description-list>
                </#if>

                <div slot="day-introduction">
                    <@hst.html hippohtml=day.introduction/>
                </div>

                <!-- STOP STARTS HERE -->
                <#assign lastStop = lastStop + day.stops?size>
                <#list day.stops as stop>
                    <#assign stopNumber++>
                    <@itineraryStop stop=stop lastStop=(stopNumber==lastStop)?c/>
                </#list>
                <!-- STOP ENDS HERE -->
            </vs-itinerary-day>
        </#list>
    </vs-itinerary>
</div>