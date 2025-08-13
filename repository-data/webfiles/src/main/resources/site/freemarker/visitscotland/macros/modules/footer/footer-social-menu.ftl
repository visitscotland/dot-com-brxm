<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-footer-social-menu.ftl">
<#include "../../../../frontend/components/vs-footer-social-item.ftl">
<#include "../../../../frontend/components/vs-list.ftl">

<#macro footerSocialMenu>
    <vs-footer-social-menu>
        <template v-slot:title>
            ${label('navigation.static', 'footer.find-us-on')}
        </template>

        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'facebook')}"
            icon="fab fa-facebook"
            aria-label-override="facebook"
        ></vs-footer-social-item>
        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'twitter')}"
            icon="fab fa-x-twitter"
            aria-label-override="x-twitter"
        ></vs-footer-social-item>
        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'youtube')}"
            icon="fab fa-youtube"
            aria-label-override="youtube"
        ></vs-footer-social-item>
        <vs-footer-social-item
            href="${optionalLabel('navigation.social-media', 'instagram')}"
            icon="fab fa-instagram"
            aria-label-override="instagram"
        ></vs-footer-social-item>
    </vs-footer-social-menu>
</#macro>