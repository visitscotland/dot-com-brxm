<#include "../../../../../include/imports.ftl">
<#include "../../../../../frontend/components/vs-megalink-link-list.ftl">
<#include "../../../../../frontend/components/vs-card-group.ftl">
<#include "../../../../../frontend/components/vs-card.ftl">
<#include "../../../../../frontend/components/vs-img.ftl">
<#include "../../../../../frontend/components/vs-heading.ftl">
<#include "../../../../../frontend/components/vs-link.ftl">
<#include "../../../../../frontend/components/vs-img.ftl">
<#include "../../video/video-modal.ftl">

<#macro cardGroup item theme>

    Number of cards in the CardList Module: ${item.links?size}

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

        <ol>
            <li>IMAGE = ${image}</li>
            <li>HEADING = ${listItem.label}</li>
            <li>DESCRIPTION = ${listItem.teaser}</li>
        </ol>
        </br>

        <#if listItem.youtubeId??>
            <@videoModal video=listItem />
        </#if>
    </#list>

<#--    NEW COMPONENT  -->

    <vs-card-group
            v-bind="args"
    >
        <vs-card v-for="(card, index) in cards" :key="index">
            <template #vs-card-header>
                <vs-img
                        :src="card.image"
                        class="w-100 aspect-ratio-3-2 rounded-1 object-fit-cover img-zoom-on-hover"
                />
            </template>

            <template #vs-card-body>
                <vs-heading
                        level="3"
                        heading-style="heading-s"
                >
                    <vs-link
                            :href="card.link"
                            class="stretched-link"
                            variant="secondary"
                    >
                        {{ card.title }}
                    </vs-link>
                </vs-heading>

<#--                <vs-body>-->
<#--                    {{ card.description }}-->
<#--                </vs-body>-->
            </template>
        </vs-card>
    </vs-card-group>

<#--    REFERENCE   -->

    <vs-row>
        <vs-col
            cols="12"
            lg="10"
            class="offset-lg-1"
        >
            <vs-row>
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
                    <vs-col
                        cols="12"
                        md="6"
                    >
                        <vs-megalink-link-list
                            img-src="${image}"
                            theme="${theme}"
                            link-type="${listItem.type}"
                            link-url="${listItem.link}"
                            error-message="${label('essentials.global', 'third-party-error')}"
                            <#if listItem.itineraryTransport??>
                                transport="${listItem.itineraryTransport}"
                                transport-name="${label('transports', listItem.itineraryTransport)}"
                            </#if>
                            <#if listItem.itineraryDays??>
                                <#if listItem.itineraryDays == 1>
                                    days-label="${label('itinerary', 'day')}"
                                <#else>
                                    days-label="${label('itinerary', 'days')}"
                                </#if>
                                days="${listItem.itineraryDays}"
                            <#else>
                                days-label="${label('itinerary', 'day')}"
                            </#if>
                            <#if listItem.youtubeId??>
                                video-id="${listItem.youtubeId}"
                                video-btn-text="${label('video', 'video.play-btn')}"
                            </#if>
                        >
                            <template v-slot:vs-link-list-heading>
                                ${listItem.label}
                            </template>

                            <template v-slot:vs-link-list-content>
                                <p>${listItem.teaser}</p>
                            </template>

                        </vs-megalink-link-list>
                    </vs-col>
                
                    <#if listItem.youtubeId??>
                        <@videoModal video=listItem />
                    </#if>
                </#list>
            </vs-row>
        </vs-col>
    </vs-row>
</#macro>