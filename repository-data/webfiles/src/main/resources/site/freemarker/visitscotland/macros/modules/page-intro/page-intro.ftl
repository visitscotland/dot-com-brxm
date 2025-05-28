
<#include "../../../../include/imports.ftl">

<#include "./summary-box.ftl">
<#include "./social-share.ftl">
<#include "../../shared/theme-calculator.ftl">
<#include "../../global/image-with-caption.ftl">
<#include "../../global/preview-warning.ftl">
<#include "../../../macros/modules/video/video.ftl">
<#include "../../../macros/modules/modal/modal.ftl">
<#include "../../../functions/data-layer.ftl">

<#include "../../../../frontend/components/vs-page-intro.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-row.ftl">
<#include "../../../../frontend/components/vs-col.ftl">
<#include "../../../../frontend/components/vs-img.ftl">
<#include "../../../../frontend/components/vs-image-with-caption.ftl">
<#include "../../../../frontend/components/vs-description-list.ftl">
<#include "../../../../frontend/components/vs-description-list-item.ftl">
<#include "../../../../frontend/components/vs-alert.ftl">
<#include "../../../../frontend/components/vs-tag-manager-wrapper.ftl">
<#include "../../../../frontend/components/vs-blog-details.ftl">

<#-- @ftlvariable name="content" type="com.visitscotland.brxm.hippobeans.Page" -->
<#-- @ftlvariable name="heroDetails" type="com.visitscotland.brxm.model.FlatImage" -->
<#-- @ftlvariable name="itinerary" type="com.visitscotland.brxm.model.ItineraryPage" -->
<#-- @ftlvariable name="introTheme" type="int" -->

<#macro pageIntro content heroDetails="" itinerary="" lightBackground=false author="" fullScreenMobile=false isListicle=false>
    <@previewWarning editMode content alerts!"" />
    <#if lightBackground>
        <#assign themeName = themeCalculator(1)>
    <#else>
        <#assign themeName = themeCalculator(introTheme)>
    </#if>

    <#if content.heroImage??>
        <@hst.link var="hero" hippobean=content.heroImage.original/>
    </#if>

    <#--    payload prop to be updated by back end -->
    <#--    ${pageViewDLEvent()}-->
            <vs-tag-manager-wrapper
                :payload="${pageViewDLEvent(content)}"
            ></vs-tag-manager-wrapper>

    <div class="has-edit-button">
        <vs-page-intro
            background="${themeName}"
            <#if heroDetails?has_content>hero-intro</#if>
            <#if itinerary?has_content>is-itinerary</#if>
            <#if fullScreenMobile>fullscreen-mobile</#if>
        >
            <#if heroDetails?has_content>
                <#if (heroVideo.cta)??>
                    <#assign ctaText = heroVideo.cta>
                <#else>
                    <#assign ctaText = "">
                </#if>
                <@hst.link var="heroSrc" hippobean=heroImage.cmsImage.original/>
                <template v-slot:vs-intro-hero>
                    <#if (heroVideo)??>
                        <@modal
                            modalId="${heroVideo.youtubeId}"
                            closeBtnText="${label('essentials.global', 'close')}"
                            isVideoModal="true"
                        >
                            <vs-row>
                                <vs-col cols="12">
                                    <@video video=heroVideo />
                                </vs-col>
                            </vs-row>

                            <vs-row class="mt-200">
                                <vs-col
                                    cols="10"
                                    offset="1"
                                >
                                    <vs-rich-text-wrapper>
                                        <p>${heroVideo.teaser}</p>
                                    </vs-rich-text-wrapper>
                                </vs-col>
                        </@modal>

                        <@imageWithCaption
                            imageSrc=heroSrc
                            imageDetails=heroDetails
                            variant="large"
                            isHero="true"
                            isVideo="true"
                            videoId="${heroVideo.youtubeId}"
                            videoTitle="${heroVideo.label}"
                            videoBtn="${ctaText}"
                            useLazyLoading="false"
                        />
                    <#else>
                        <@imageWithCaption
                            imageSrc=heroSrc
                            imageDetails=heroDetails
                            variant="large"
                            isHero="true"
                            isVideo="false"
                            useLazyLoading="false"
                        />
                    </#if>
                </template>
            </#if>

            <template v-slot:vs-intro-breadcrumb>
                <@hst.include ref="breadcrumb"/>
            </template>

            <template v-slot:vs-intro-heading>
                ${content.title}
            </template>


            <#if author?? && author?has_content>
                <template
                    v-slot:vs-blog-data
                >
                    <vs-blog-details
                        blog-author="<#if author.authorName?has_content>${author.authorName}</#if>"
                        blog-publish-date="${author.publishDate}"
                        blog-read-time="${author.readingTime}"
                    ></vs-blog-details>
                </template>
            </#if>

            <template v-slot:vs-share-button>
                <@socialShare nojs=false />
            </template>

            <#if !searchResultsPage??>
                <template v-slot:vs-intro-content>
                    <@hst.html hippohtml=content.introduction/>
                    <#if isListicle>
                        <vs-rich-text-wrapper>
                            <p class="mt-200">${label("listicle", "listicle.disclaimer")}</p>
                        </vs-rich-text-wrapper>
                    </#if>
                </template>
            </#if>

            <#if itinerary?has_content>
                <#if itinerary.firstStopLocation?has_content && itinerary.lastStopLocation?has_content>
                    <template v-slot:vs-intro-start-finish>
                        <dt class="list-inline-item">${label("itinerary", "start-finish")}</dt>
                        <dd class="list-inline-item">${itinerary.firstStopLocation} / ${itinerary.lastStopLocation}</dd>
                    </template>
                </#if>

                <template v-slot:vs-intro-summary-box>
                    <@summaryBox itinerary />
                </template>

                <#if itinerary.document.areas?has_content>
                    <template v-slot:vs-intro-lower>
                        <vs-container >
                            <vs-row>
                                <vs-col cols="12" lg="5" xl="6" offset-lg="1">
                                    <vs-description-list class="mb-150">
                                        <vs-description-list-item title>
                                            ${label("itinerary", "highlights")}
                                        </vs-description-list-item>

                                        <#list itinerary.document.highlights as highlight>
                                            <vs-description-list-item>
                                                ${highlight}
                                            </vs-description-list-item>
                                        </#list>
                                    </vs-description-list>

                                    <vs-description-list class="mb-200">
                                        <vs-description-list-item title>
                                            ${label("itinerary", "areas-covered")}
                                        </vs-description-list-item>

                                        <#list  itinerary.document.areas as area>
                                            <vs-description-list-item>
                                                ${label("areas", "${area}")}
                                            </vs-description-list-item>
                                        </#list>
                                    </vs-description-list>
                                </vs-col>
                            </vs-row>
                        </vs-container>
                    </template>
                </#if>
            </#if>
        </vs-page-intro>
    </div>
</#macro>
