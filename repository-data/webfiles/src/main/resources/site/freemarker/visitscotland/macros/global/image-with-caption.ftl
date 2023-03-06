<#include "../../../frontend/components/vs-social-credit-link.ftl">
<#include "../../../frontend/components/vs-image-with-caption.ftl">
<#include "../../../frontend/components/vs-caption.ftl">

<#macro imageWithCaption imageSrc imageDetails variant="fullwidth" isHero="false" mobileOverlap="false" alignment="left" isVideo="false" videoId="" videoTitle="" videoBtn="" smallPlayButton="false" useLazyLoading="true" noAltText="false">
    <vs-image-with-caption
        latitude="<#if variant != 'fullwidth'>${(imageDetails.coordinates.latitude)!''}</#if>"
        longitude="<#if variant != 'fullwidth'>${(imageDetails.coordinates.longitude)!''}</#if>"
        variant="${variant}"
        toggle-button-text="${label('essentials.global', 'image.toggle.text')}"
        :is-hero-image="${isHero}"
        :mobile-overlap="${mobileOverlap}"
        :is-video="${isVideo}"
        video-id="${videoId}"
        :small-play-button="${smallPlayButton}"
        no-cookies-message="${label('video', 'video.no-cookies')}"
        no-js-message="${label('video', 'video.no-js')}"
        cookie-link-text="${label('essentials.global', 'cookie.link-message')}"
        :use-lazy-loading="${useLazyLoading}"
        error-message="${label('essentials.global', 'third-party-error')}"
        <#if videoBtn?? && videoBtn != "">
            play-button-text="${videoBtn}"
        <#else>
            play-button-text="${label('video', 'video.play-btn')}"
        </#if>
    >
        <template slot="video-title">
            ${videoTitle}
        </template>
        
        <vs-img
            src="${imageSrc}?size=xxs"
            class="position-absolute"
            <#if noAltText?? && noAltText == "true">
                alt=""
            <#else>
                alt="${(imageDetails.altText)!'${label("essentials.global", "default.alt-text")}'}"
            </#if>
            :use-lazy-loading="${useLazyLoading}"
        >
        </vs-img>

        <vs-img
            src="${imageSrc}"
            class="position-relative"
            <#if noAltText?? && noAltText == "true">
                alt=""
            <#else>
                alt="${(imageDetails.altText)!'${label("essentials.global", "default.alt-text")}'}"
            </#if>
            srcset="${imageSrc}?size=xs 300w, 
                    ${imageSrc}?size=sm 600w,
                    ${imageSrc}?size=md 1200w, 
                    ${imageSrc}?size=lg 2048w"
            sizes="(min-width: 768px) 75vw, 100vw"
            :use-lazy-loading="${useLazyLoading}"
        >
        </vs-img>

        <vs-caption
            slot="img-caption"
            latitude="<#if variant != 'fullwidth'>${(imageDetails.coordinates.latitude)!''}</#if>"
            longitude="<#if variant != 'fullwidth'>${(imageDetails.coordinates.longitude)!''}</#if>"
            variant="${variant}"
            text-align="${alignment}"
        >
            <template slot="caption">
                <#if isVideo == "true">${label('essentials.global', 'image.title')}: </#if>${(imageDetails.description)!''}
            </template>

            <#if imageDetails.source?has_content>
                <vs-icon
                    slot="toggle-icon"
                    name="${imageDetails.source + '-filled'}"
                    size="md"
                ></vs-icon>

                <vs-social-credit-link
                    slot="credit"
                    credit="<#if imageDetails.credit??>${imageDetails.credit}<#else>${label('essentials.global', 'image.no.credit')}</#if>"
                    social-post-url="${imageDetails.postUrl}"
                    source="${imageDetails.source}"
                ></vs-social-credit-link>
            <#else>
                <#if imageDetails.credit?has_content>
                    <template slot="credit">
                        &copy; ${imageDetails.credit}
                    </template>
                </#if>
            </#if>
        </vs-caption>
    </vs-image-with-caption>
</#macro>	