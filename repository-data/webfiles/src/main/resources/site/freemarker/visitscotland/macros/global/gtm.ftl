<#include "../../functions/helpers.ftl">
<#--
    Both versions (script and noscript) are supposed to be included in every page. The implementation
    has been done in the same macro so all future ammends will be done in on single point.
-->
<#macro gtm noscript=false >
    <#if (!editMode) >
        <#assign id= property("gtm.container-id")>
        <#assign queryString = (property("gtm.is-production")?boolean)?then("", (property("gtm.preview-query-string"))) >

        <!-- Google Tag Manager -->
        <#if noscript >
            <noscript>
                <iframe src="https://www.googletagmanager.com/ns.html?id=${id}${queryString}" height="0" width="0" style="display:none;visibility:hidden"></iframe>
            </noscript>
        <#else>
            <script>
                const attachCivicEvents = (counter = 1) => {
                    if (counter < 20) {
                        if (typeof window !== 'undefined' && window.google_tag_manager) {
                            // GTM can't call browser events directly, so we need to listen for events on the
                            // datalayer and then latch our code onto those.
                            const originalDataLayerPush = window.dataLayer.push;

                            window.dataLayer.push = (arg : any) => {
                                if (arg) {
                                    originalDataLayerPush(arg);
                                } else {
                                    originalDataLayerPush();
                                }

                                if (arg && arg.event === 'cookie_permission_loaded') {
                                    setTimeout(() => {
                                        window.dispatchEvent(new Event('cookieManagerLoaded'));
                                    });
                                }

                                if (arg && arg.event === 'cookie_permission_changed') {
                                    setTimeout(() => {
                                        window.dispatchEvent(new Event('cookiesUpdated'));
                                    });
                                }
                            };
                        } else {
                            setTimeout(() => {
                                attachCivicEvents(counter + 1);
                            }, 500);
                        }
                    }
                };

                (function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
                        new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
                    j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
                    'https://www.googletagmanager.com/gtm.js?id='+i+dl+ '${queryString}';f.parentNode.insertBefore(j,f);
                })(window,document,'script','dataLayer','${id}');

                attachCivicEvents();
            </script>
        </#if>
    </#if>
    <!-- End Google Tag Manager -->

    <#if (editMode) >
        <script>
            window.bypassCookiesRequired = true;
            window.bypassCookiesLoaded = true;
        </script>
    </#if>
</#macro>