<#include "../../../../include/imports.ftl">
<#include "../carousel/carousel.ftl">
<#include "../../../../frontend/components/vs-module-wrapper.ftl">

<#macro horizontalList item themeName="" testId="">
    <vs-module-wrapper
        theme="<#if themeName?has_content>${themeName}<#else>light</#if>"
        data-test="<#if testId?has_content>${testId}<#else>vs-otyml</#if>"
    >
        <template slot="vsModuleWrapperHeading">
            ${item.title}
        </template>

        <template slot="vsModuleWrapperIntro">
            <@hst.html hippohtml=item.introduction/>
        </template>

        <@carousel item />
    </vs-module-wrapper>
</#macro>


