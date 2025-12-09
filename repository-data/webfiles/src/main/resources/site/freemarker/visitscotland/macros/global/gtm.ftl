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
                // Checks if a given dataLayer event represents either Civic loading or cookie
                // permissions updating, and fires off events for the component library.
                const checkEvent = (event) => {
                    if (event === 'cookie_permission_loaded') {
                        setTimeout(() => {
                            window.dispatchEvent(new Event('cookieManagerLoaded'));
                        });
                    }

                    if (event === 'cookie_permission_changed') {
                        setTimeout(() => {
                            window.dispatchEvent(new Event('cookiesUpdated'));
                        });
                    }
                }

                // To avoid an awkward race condition, we need to check if civic was loaded before we find the
                // datalayer, as well as listening for it being loaded afterwards.
                const checkIfCivicLoaded = () => {
                    for (let x = 0; x < window.dataLayer.length; x++) {
                        const dataLayerEvent = window.dataLayer[x];

                        const eventString = dataLayerEvent?.value?.event ?? dataLayerEvent?.event ?? '';

                        checkEvent(eventString);
                    }
                };

                const attachCivicEvents = (counter = 1) => {
                    if (counter < 20) {
                        if (typeof window !== 'undefined' && window.google_tag_manager) {
                            checkIfCivicLoaded();

                            // GTM can't call browser events directly, so we need to listen for events on the
                            // datalayer and then latch our code onto those.
                            const originalDataLayerPush = window.dataLayer.push;

                            window.dataLayer.push = (arg) => {
                                let res = null;

                                if (arg && arg !== null && typeof arg !== 'undefined') {
                                    res = originalDataLayerPush.apply(window.dataLayer, [arg]);

                                    const eventString = arg.event || (arg.value && arg.value.event) || '';

                                    checkEvent(eventString);

                                    return res;
                                }

                                return originalDataLayerPush.apply(window.dataLayer, [arg]);
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