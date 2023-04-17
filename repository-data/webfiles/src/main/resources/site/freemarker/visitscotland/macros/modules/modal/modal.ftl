<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-modal.ftl">

<#-- This variable is globally initialized (per page)-->
<#assign modalCounter = 0 />

<#macro modal isVideoModal closeBtnText modalId>
    <#assign modalCounter++>
    <vs-modal
        modal-id="${modalId}-${modalCounter}"
        close-btn-text="${closeBtnText}"
        :is-video-modal="${isVideoModal}"
    >
        <#nested>
    </vs-modal>
</#macro>