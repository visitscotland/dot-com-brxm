<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-image-with-caption.ftl">
<#include "../../global/image-with-caption.ftl">

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.beans.mapping.LongCopyModule" -->

<#macro longCopy module>
    <@hst.manageContent hippobean=module.hippoBean />

    <vs-col cols="12" lg="8" offset-lg="2" style="border: 1px solid grey;">
        <vs-row>
            <@hst.html hippohtml=module.copy/>
        </vs-row>
    </vs-col>

    </br> </br>
</#macro>