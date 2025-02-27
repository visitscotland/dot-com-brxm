<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-tourism-info.ftl">
<#include "../../../../frontend/components/vs-quote.ftl">
<#include "../../../../frontend/components/vs-img.ftl">
<#include "../../../../frontend/components/vs-module-wrapper.ftl">

<#include "../../global/image-with-caption.ftl">
<#include "../../global/quote.ftl">
<#include "../../global/preview-warning.ftl">

<#macro tourismInfo module themeName="">
    <@previewWarning editMode module module.errorMessages />

    <#if module.image.cmsImage??>
        <#assign image>
            <@hst.link hippobean=module.image.cmsImage.original/>
        </#assign>
    <#else>
        <#assign image = module.image.externalImage!'' />
    </#if>

    <vs-module-wrapper theme="<#if themeName?has_content>${themeName}<#else>light</#if>">
        <template v-slot:vs-module-wrapper-heading>
            ${module.title}
        </template>
        <vs-container>
            <vs-tourism-info>
                <template v-slot:tourism-info-image-with-caption>
                    <@imageWithCaption imageSrc=image imageDetails=module.image noAltText="true" />
                </template>
                <#if module.quote??>
                    <template v-slot:tourism-info-quote>
                        <@quote quoteItem=module.quote variant="wide"/>
                    </template>
                </#if>
            </vs-tourism-info>
        </vs-container>
    </vs-module-wrapper>
</#macro>
