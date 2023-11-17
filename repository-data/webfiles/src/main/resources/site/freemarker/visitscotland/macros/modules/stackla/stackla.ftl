<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-module-wrapper.ftl">
<#include "../../../../frontend/components/vs-embed-wrapper.ftl">

<#-- @ftlvariable name="module" type="com.visitscotland.brxm.model.StacklaModule" -->

<#macro stackla module>
    <@hst.headContribution category="htmlBodyEnd">
        <script type="text/plain" class="optanon-category-C0001-C0003-C0004">
            (function (d, id) {
                var t, el = d.scripts[d.scripts.length - 1].previousElementSibling;
                if (el) el.dataset.initTimestamp = (new Date()).getTime();
                if (d.getElementById(id)) return;
                t = d.createElement('script');
                t.src = '//assetscdn.stackla.com/media/js/widget/fluid-embed.js';
                t.id = id;
                (d.getElementsByTagName('head')[0] || d.getElementsByTagName('body')[0]).appendChild(t);
            }(document, 'stackla-widget-js'));
        </script>
    </@hst.headContribution>

    <@previewWarning editMode module module.errorMessages />

    <vs-module-wrapper theme="<#if themeName?has_content>${themeName}<#else>light</#if>">
        <template v-slot:vs-module-wrapper-heading>
            ${module.title}
        </template>
        <vs-embed-wrapper
            no-cookie-text="${module.noCookiesMessage}"
            error-text = "${label('essentials.global', 'third-party-error')}"
            no-js-text="${module.noJsMessage}"
        >
            <template v-slot:embed-intro-copy>
                <@hst.html hippohtml=module.copy/>
            </template>

            <template v-slot:embed-widget>
                <div class="stackla-widget" data-ct="" data-hash="${module.dataHash}"
                    data-id="${module.dataId}" data-title="social_vs.org_IGfeed" data-ttl="60"
                    style="width: 100%; overflow: hidden;"></div>
            </template>

            <template v-slot:embed-button-text>
                ${label('essentials.global', 'cookie.link-message')}
            </template>
        </vs-embed-wrapper>
    </vs-module-wrapper>

    <@hst.headContribution category="htmlHeadStyles">
        <link rel="stylesheet" href="<@hst.webfile path='/frontend/styles/third-party/_stackla-styles.css'/>" type="text/css"/>
    </@hst.headContribution>
</#macro>