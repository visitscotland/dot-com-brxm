
<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-social-share.ftl">
<#include "../../../../frontend/components/vs-social-share-item.ftl">

<#macro socialShare id nojs>
    <vs-social-share 
        id="${id}"
        :no-js="${nojs}"
        page-url="<@hst.link hippobean=document canonical=true fullyQualified=true/>" 
        page-title="${document.title}"
        share-btn-text="${label('social.share', 'share.button.text')}"
        close-alt-text="${label('social.share', 'close.share')}"
        share-popover-title="${label('social.share', 'share.on')}"
    >     
        <template slot-scope="{onCopyLink}">   
            <vs-social-share-item
                name="facebook"
                link-text="${label('social.share', 'facebook.link.text')}"
            ></vs-social-share-item>
            <vs-social-share-item
                name="pinterest"
                link-text="${label('social.share', 'pinterest.link.text')}"
            ></vs-social-share-item>
            <vs-social-share-item
                name="whatsapp"
                link-text="${label('social.share', 'whatsapp.link.text')}"
            ></vs-social-share-item>
            <vs-social-share-item
                name="twitter"
                link-text="${label('social.share', 'twitter.link.text')}"
            ></vs-social-share-item>
            <vs-social-share-item
                name="email"
                link-text="${label('social.share', 'email.link.text')}"
            ></vs-social-share-item>
            <#if nojs == 'false'>
                <vs-social-share-item
                    @on-copy-link="onCopyLink"
                    name="link"
                    link-text="${label('social.share', 'copy.link.text')}"
                    link-copied-text="${label('social.share', 'link.copied.text')}"
                ></vs-social-share-item>
            </#if>
        </template>
    </vs-social-share>
</#macro>