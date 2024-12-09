<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-footer-social-menu.ftl">
<#include "../../../../frontend/components/vs-footer-social-item.ftl">
<#include "../../../../frontend/components/vs-list.ftl">

<#include "../../shared/icon-lookup.ftl">

<#macro footerSocialMenu>
    <vs-footer-social-menu>
        <template v-slot:title>
            ${label('navigation.static', 'footer.find-us-on')}
        </template>

        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'facebook')}"
            icon="${iconLookup('facebook')}"
        ></vs-footer-social-item>
        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'twitter')}"
            icon="${iconLookup('x-twitter')}"
        ></vs-footer-social-item>
        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'youtube')}"
            icon="${iconLookup('youtube')}"
        ></vs-footer-social-item>
        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'instagram')}"
            icon="${iconLookup('instagram')}"
        ></vs-footer-social-item>
    </vs-footer-social-menu>
</#macro>