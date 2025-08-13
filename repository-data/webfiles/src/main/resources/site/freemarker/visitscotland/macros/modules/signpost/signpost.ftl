<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-module-wrapper.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-row.ftl">
<#include "../../../../frontend/components/vs-col.ftl">
<#include "../../../../frontend/components/vs-button.ftl">
<#include "../../../../frontend/components/vs-img.ftl">
<#include "../../../../frontend/components/vs-body.ftl">

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.SignpostModule" -->
<#macro signpost module>
    <vs-module-wrapper theme="neutral">
        <template v-slot:vs-module-wrapper-heading>
            ${module.title}
        </template>

        <vs-container class="text-center text-sm-start mt-050">
            <vs-row>
                <vs-col
                    cols="12"
                    sm="7"
                    md="6"
                    lg="5"
                    offset-lg="1"
                    class="text-center text-sm-start col-xxl-4"
                >   
                    <vs-body variant="lead" class="mb-300 mb-lg-400">
                        <p>
                            <@hst.html hippohtml=module.copy/>
                        </p>
                    </vs-body>
                    <vs-button
                        href="${module.cta.link}"
                    >
                        ${module.cta.label}
                    </vs-button>
                </vs-col>
                <vs-col
                    cols="10"
                    offset="1"
                    sm="5"
                    offset-sm="0"
                    md="6"
                    lg="5"
                    xl="5"
                    class="text-center text-lg-start col-xxl-5 offset-xxl-1"
                >
                    <vs-img
                        src="<@hst.webfile path='${module.image.externalImage}'/>"
                        class="mt-400 mt-sm-050 w-100 h-auto"
                        style="aspect-ratio:267/206"
                    >
                    </vs-img>
                </vs-col>
            </vs-row>
        </vs-container>
    </vs-module-wrapper>
</#macro>