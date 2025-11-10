<#include "../../../../include/imports.ftl">
<#include "../../modules/spotlight-section/spotlight-section.ftl">

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.SignpostModule" -->
<#macro signpost module>
    <#assign image>
        <@hst.webfile path='${module.image.externalImage}'/>
    </#assign>

    </br>
    <@spotlightSection module.title image module.cta.link module.cta.label module.copy true/>
    </br>
</#macro>