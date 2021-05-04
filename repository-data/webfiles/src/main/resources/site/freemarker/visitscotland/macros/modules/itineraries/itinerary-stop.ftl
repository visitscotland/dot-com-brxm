<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-itinerary-stop.ftl">
<#include "../../../../frontend/components/vs-itinerary-stop-info.ftl">
<#include "../../../../frontend/components/vs-itinerary-tips.ftl">
<#include "../../../../frontend/components/vs-itinerary-border-overlap-wrapper.ftl">
<#include "../../../../frontend/components/vs-description-list.ftl">
<#include "../../../../frontend/components/vs-description-list-item.ftl">
<#include "../../../../frontend/components/vs-link.ftl">
<#include "../../../../frontend/components/vs-svg.ftl">
<#include "../../../../frontend/components/vs-button.ftl">
<#include "../../../../frontend/components/vs-address.ftl">

<#include "../../global/key-facilities.ftl">
<#include "../../global/image-with-caption.ftl">
<#include "../../global/cms-errors.ftl">

<#macro itineraryStop stop isLastStop>
<#-- @ftlvariable name="stop" type="com.visitscotland.brxm.model.ItineraryStopModule" -->

    <#assign image = "" />

    <#if stop.image??>
        <#if stop.image.cmsImage??>
            <#assign image>
                <@hst.link hippobean=stop.image.cmsImage.original/>
            </#assign>
        <#elseif stop.image.externalImage??>
            <#assign image = stop.image.externalImage />
        </#if>
    </#if>
    
    <vs-itinerary-stop
        slot="stops"
        stop-number="${stop.index}"
        stop-label="${stop.title}"
        stop-title="${stop.subTitle!''}"
    >
        <@hst.manageContent hippobean=stop.hippoBean />
        <@cmsErrors errors=stop.errorMessages!"" editMode=editMode />
        <#if image?? && image?has_content> 
            <template slot="stop-image">
                <@imageWithCaption imageSrc=image imageDetails=stop.image variant="fullwidth"/>
            </template>
        </#if>

        <#if stop?? && stop.description?? && stop.description?has_content>
            <template slot="stop-description">
                <@hst.html hippohtml=stop.description/>

                <#if stop.timeToExplore?? && stop.timeToExplore?has_content>
                    <vs-description-list class="my-4 mb-0 justify-content-start" inline slot="stop">
                        <vs-description-list-item title class="mb-0 mr-0 pr-1 col-auto">${label("itinerary", "stop.time-to-explore")}</vs-description-list-item>
                        <vs-description-list-item class="mb-0 col-auto px-0">${stop.timeToExplore}</vs-description-list-item>
                    </vs-description-list>
                </#if>

                <#if (stop.tipsTitle?? && stop.tipsTitle?has_content)>
                    <vs-itinerary-tips>
                        <div slot="text">
                            <strong>${stop.tipsTitle}</strong>
                            <@hst.html hippohtml=stop.tipsBody/>
                        </div>
                        <vs-svg slot="svg" path="highland-cow" />
                    </vs-itinerary-tips>
                </#if>
            </template>
        </#if>

        <#if stop.ctaLink?? && stop.ctaLink.link?? && stop.ctaLink.link?has_content>
            <vs-link href="${stop.ctaLink.link}">
                ${stop.ctaLink.label}
            </vs-link>
        </#if>

        <#if stop??>
            <#if stop.address?? && stop.address.line1?? && stop.address.line1?has_content>
                <vs-address slot="stop-address">
                    <#assign addressArr = [
                        stop.address.line1!"",
                        stop.address.line2!"",
                        stop.address.line3!"",
                        stop.address.city!"",
                        stop.address.postCode!""
                    ]/>

                    <#--
                        Filter out empty strings in address

                        It would be tidier to do this within the loop below, but that
                        causes <#sep> to incorrectly assume that the postCode is always
                        a value that needs a comma before it, even if it is an empty
                        string. The ideal solution would be to iterate over

                        addressArr?filter()

                        rather than constructing a whole filtered copy of the array
                        for readability but that is not doable until we reach a future
                        version of freemarker (2.3.29).

                        TODO: Upgrade to freemarker version 2.3.29.
                    -->
                    <#assign filterAddressArr = [] />
                    <#list addressArr as addrLine>
                        <#if addrLine != "">
                            <#assign filterAddressArr = filterAddressArr + [ addrLine ] />
                        </#if>
                    </#list>

                    <#list filterAddressArr as addressLine>
                        <span>${addressLine?eval}<#sep>,</span>
                    </#list>
                </vs-address>
            </#if>
            
            <#if stop.opening??>
               
                <span slot="stop-info">                    
                    <vs-itinerary-stop-info
                        opening-hours='${stop.opening}'
                        opening-times-link="https://www.visitscotland.com"
                        closed-text="Closed"
                        closing-soon-text="Closing soon"
                        open-text="Open"
                        usual-text="Usually"
                        provisional-text="Provisionally"
                    >
                        <template slot="stop-to">
                            to
                        </template>

                        <template slot="stop-link-text">
                            Check opening times
                        </template>

                        <template slot="stop-charge-text">
                            <#if stop.price??>
                                ${stop.price}
                            </#if>
                        </template>
                    </vs-itinerary-stop-info>
                </span>
                
            </#if>

            <#if stop.facilities?? && stop.facilities?size gt 1>
                <template slot="stop-facilities">
                    <@keyFacilities facilitiesList=stop.facilities />
                </template>
            </#if>

            <#--  <#if isLastStop == 'true' && stop.coordinates.longitude?? && stop.coordinates.longitude?has_content && stop.coordinates.latitude?? && stop.coordinates.latitude?has_content>  -->
            <#if isLastStop == 'true'>
                <#assign nearbyEatsUrl = productSearch(locale, "cate", stop.coordinates.latitude, stop.coordinates.longitude, 5)>
                <#assign nearbyStayUrl = productSearch(locale, "acco", stop.coordinates.latitude, stop.coordinates.longitude, 5)>
                <vs-itinerary-border-overlap-wrapper slot="stop-buttons">
                    <vs-button
                        class="mb-3"
                        background="white"
                        variant="outline-primary"
                        icon="food"
                        href="${nearbyEatsUrl}"
                    >
                        ${label("itinerary", "stop.nearby-eat")}
                    </vs-button>
                    <vs-button
                        background="white"
                        variant="outline-primary"
                        icon="product-accommodation"
                        href="${nearbyStayUrl}"
                    >
                        ${label("itinerary", "stop.nearby-stay")}
                    </vs-button>
                </vs-itinerary-border-overlap-wrapper>
            </#if>

            <#-- <#if prod.openLink?? && prod.openLink.link?? && prod.openLink.link?has_content>
                </br>OPENINGS </br>
                <VsItineraryStopInfo
                    :openingHours="itineraries.sampleItinerary.openingHours"
                    openingTimesLink="https://www.visitscotland.com"
                    closedText="Closed"
                    closingSoonText="Closing soon"
                    openText="Open"
                    hoursLinkUrl="https://www.visitscotland.com"
                    usualText="Usually"
                    provisionalText="Provisionally"
                    slot="stop-info"
                >
                    <template slot="stop-to">
                        to
                    </template>

                    <template slot="stop-link-text">
                        Check opening times
                    </template>

                    <template slot="stop-charge-text">
                        Admission charge
                    </template>
                </VsItineraryStopInfo>
                 ${prod.open}</br>
                <vs-link href="${prod.openLink.link}">
                    ${prod.openLink.label}
                </vs-link>
            </#if>  -->

            
        </#if>        
    </vs-itinerary-stop>
</#macro>