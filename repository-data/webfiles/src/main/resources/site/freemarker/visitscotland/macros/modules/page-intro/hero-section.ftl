
<#include "../../../../include/imports.ftl">

<#include "../../../../frontend/components/vs-hero-section.ftl">

<#-- @ftlvariable name="content" type="com.visitscotland.brxm.hippobeans.Page" -->
<#-- @ftlvariable name="heroDetails" type="com.visitscotland.brxm.model.FlatImage" -->
<#-- @ftlvariable name="itinerary" type="com.visitscotland.brxm.model.ItineraryPage" -->
<#-- @ftlvariable name="introTheme" type="int" -->

<#macro heroSection content heroDetails="" itinerary="" lightBackground=false author="" fullScreenMobile=false isListicle=false>
    <@previewWarning editMode content alerts!"" />
    
    <#if content.heroImage??>
        <@hst.link var="hero" hippobean=content.heroImage.original/>
    </#if>

    <@hst.link var="heroSrc" hippobean=heroImage.cmsImage.original/>

    <#--    payload prop to be updated by back end -->
    <#--    ${pageViewDLEvent()}-->
    <vs-tag-manager-wrapper
        :payload="${pageViewDLEvent(content)}"
    ></vs-tag-manager-wrapper>

    <div class="has-edit-button">
        <#assign videoPlay = label('video', 'video.play-btn') />

        <vs-hero-section
            heading="${content.title}"
            lede="${content.teaser}"
            src="${heroSrc}"
            video-src="${heroVideo.link}"
            video-btn-text="${videoPlay}"
        />
    </div>
</#macro>
