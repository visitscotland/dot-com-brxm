<#include "../../../frontend/components/vs-container.ftl">
<#include "../../../frontend/components/vs-alert.ftl">
<#include "../../../frontend/components/vs-list.ftl">

<#macro previewWarning editMode module hidden=false message="">
    <#if editMode && module.errorMessages?has_content >
        <vs-container
            class="py-4"
        >
            <vs-alert>
                <div>
                    <p class="text-danger pb-2"><strong> ERROR! </strong></p>
                    <#if message?? && message != "">
                        <p>${message}</p>
                    <#elseif hidden>
                        <p>There is a major issue with the document <b>${module.hippoBean.displayName}</b> and It was removed from the page:</p>
                    <#else>
                        <p>The following issues have been detected in the document ${module.hippoBean.displayName}:</p>
                    </#if>
                    <vs-list>
                        <#list module.errorMessages as error>
                            <li>${error}</li>
                        </#list>
                    </vs-list>
                </div>
            </vs-alert>
        </vs-container>
    </#if>
</#macro>