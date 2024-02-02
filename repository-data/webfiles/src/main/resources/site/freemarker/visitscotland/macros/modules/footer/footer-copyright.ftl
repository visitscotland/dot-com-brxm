<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-footer-copyright.ftl">

<#macro footerCopyright>
    <vs-footer-copyright 
        href="${label('navigation.static', 'footer.logo-url')}"
        link-alt-text="${label('navigation.static', 'footer.logo-alt-text')}"
    >
        <template v-slot:copyright>
            <span>
                ${label('navigation.static', 'footer.text')}
            </span>
        </template>
    </vs-footer-copyright>
</#macro>