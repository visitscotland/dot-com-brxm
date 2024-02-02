<#include "../../../../../include/imports.ftl">
<#include "../../../../../frontend/components/vs-mega-nav.ftl">
<#include "./header-nav.ftl">
<#include "./header-accordion-nav.ftl">
<#-- @ftlvariable name="hstRequestContext" type="org.hippoecm.hst.core.request.HstRequestContext" -->
<#macro headerMegaNav menu=menu>
    <vs-mega-nav
            href="<@hst.link fullyQualified=fullyQualifiedURLs siteMapItemRefId='root'/>"
            menu-toggle-alt-text="${label('navigation.static', 'meganav-toggle-btn-alt-text')}"
            search-button-text="${label('search', 'search')}"
            search-label-text="${label('search', 'search-label')}"
            search-clear-button-text="${label('search', 'clear-form')}"
            search-close-button-text="${label('search', 'close-form')}"
            logo-alt-text="${label('navigation.static', 'meganav.logo-alt-text')}"
    >
        <template v-slot:mega-nav-top-menu-items>
            <@headerDesktopNav menu=menu/>
        </template>

        <template v-slot:mega-nav-accordion-items>
            <@headerAccordionNav menu=menu/>
        </template>
    </vs-mega-nav>

    <#if (!editMode) >
        <@hst.headContribution category="htmlHeadStyles">
            <link rel="stylesheet" href="<@hst.webfile path='/frontend/styles/third-party/_cludo-autocomplete.css'/>" type="text/css"/>
        </@hst.headContribution>
        <@hst.headContribution category="htmlBodyLocalizedScripts">
            <script>
                var cludo_engineId = ${property("global-search.engine-id", locale)}; //Engine ID
                var cludo_language = '${language}'; //Language
                var cludo_searchUrl = '${property("global-search.path", locale)}'; //Search URL
            </script>
        </@hst.headContribution>
        <@hst.headContribution category="htmlBodyEndScriptsLast">
            <script src="https://customer.cludo.com/scripts/bundles/search-script.js"></script>
        </@hst.headContribution>
        <@hst.headContribution category="htmlBodyEndScriptsLast">
            <script src="https://customer.cludo.com/assets/623/12809/cludo-helper.js"></script>
        </@hst.headContribution>
    </#if>
</#macro>
