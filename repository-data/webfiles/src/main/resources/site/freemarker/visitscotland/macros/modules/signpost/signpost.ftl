<#include "../../../../include/imports.ftl">
<#include "../../modules/spotlight-section/spotlight-section.ftl">

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.SignpostModule" -->
<#macro signpost module>
    <#assign image>
        <@hst.webfile path='${module.image.externalImage}'/>
    </#assign>

    <div class="mt-175 mt-md-500 mb-175 mb-md-500">
        <@spotlightSection module.title image module.cta.link module.cta.label module.copy true/>
    </div>
</#macro>