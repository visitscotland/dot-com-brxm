<#include "../../../../../include/imports.ftl">
<#include "../../../../../frontend/components/vs-mega-nav.ftl">
<#include "./header-nav.ftl">
<#include "./header-accordion-nav.ftl">

<#macro headerMegaNav menu=menu>
    <vs-mega-nav 
        href="<@hst.link fullyQualified=fullyQualifiedURLs siteMapItemRefId='root'/>"
        menu-toggle-alt-text="${label('navigation.static', 'meganav-toggle-btn-alt-text')}"
        search-button-text="${label('search', 'search')}"
        search-label-text="${label('search', 'search-label')}"
        search-clear-button-text="${label('search', 'clear-form')}"
        search-close-button-text="${label('search', 'close-form')}"
    >
        <template slot="megaNavTopMenuItems">
            <@headerDesktopNav menu=menu/>
        </template>

        <template slot="megaNavAccordionItems">
            <@headerAccordionNav menu=menu/>
        </template>
    </vs-mega-nav>

    <#assign language = locale?keep_before("-")>

    <@hst.headContribution category="htmlBodyEndScripts"> 
        <script type="text/javascript" src="https://customer.cludo.com/scripts/bundles/search-script.js"></script>
    </@hst.headContribution>
    <@hst.headContribution category="htmlBodyEndScripts"> 
        <script>
            var engine = {
				de: 8740,
				es: 8741,
				nl: 8744,
				fr: 8742,
				it: 8743,
				en: 8738,
			};

            var cludo_engineId = engine["${language}"]; //Engine ID
            var cludo_language = '${language}'; //Language
            var cludo_searchUrl = 'site-search-results'; //Search URL
        </script>
    </@hst.headContribution>
    <@hst.headContribution category="htmlBodyEndScripts"> 
        <script type="text/javascript" src="https://customer.cludo.com/assets/623/12809/cludo-search.js"></script>
    </@hst.headContribution>
</#macro>