<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-carousel.ftl">
<#include "../../../../frontend/components/vs-carousel-slide.ftl">

<#macro carousel item>
    <vs-carousel
        next-text="${label("essentials.page.next)}"
        prev-text="${label("essentials.page.previous)}"
        slides-xs="1"
        slides-sm="2"
        slides-md="3"
        slides-lg="4"
    >
        <#list item.links as carouselItem>
            <#if carouselItem.image.cmsImage??>
                <#assign image>
                    <@hst.link hippobean=carouselItem.image.cmsImage.original/>
                </#assign>
            <#else>
                <#assign image = carouselItem.image.externalImage!'' />
            </#if>
            <vs-carousel-slide
                link-url="${carouselItem.link}"
                link-type="${carouselItem.type}"
                img-src="${image}"
                img-alt="${(carouselItem.image.altText)!'${label("essentials.global", "default.alt-text")}'}"
                <#if carouselItem.category??>
                    category="${carouselItem.category}"
                </#if>
                <#if carouselItem.itineraryTransport??>
                    transport="${carouselItem.itineraryTransport}"
                    transport-name="${label('transports', carouselItem.itineraryTransport)}"
                </#if>
                <#if carouselItem.itineraryDays??>
                    <#if carouselItem.itineraryDays = 1>
                        days-label="${label('itinerary', 'day')}"
                    <#else>
                        days-label="${label('itinerary', 'days')}"
                    </#if>
                    days="${carouselItem.itineraryDays}"
                <#else>
                    days-label="${label('itinerary', 'day')}"
                </#if>
            >
                <template slot="vsCarouselSlideHeading">
                    ${carouselItem.label}
                </template>
            </vs-carousel-slide>
        </#list>

        <template slot="vsCarouselOf">
            ${label("essentials.pagination", "page.of")}
        </template>
    </vs-carousel>
</#macro>
