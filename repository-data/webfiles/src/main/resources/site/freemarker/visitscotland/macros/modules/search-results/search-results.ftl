<#include "../../../../include/imports.ftl">
<#include "../../../../frontend/components/vs-container.ftl">
<#include "../../../../frontend/components/vs-row.ftl">
<#include "../../../../frontend/components/vs-col.ftl">
<#include "../../../../frontend/components/vs-embed-wrapper.ftl">

<#macro searchResults>
    <div id="cludo-search-results" class="cludo-search-results">
        <div class="cludo-search-results__layout mb-300 mb-md-500">
            <vs-embed-wrapper 
                no-cookies-required
                no-cookie-text="You need cookies enabled to view this content"
                error-text = "${label('essentials.global', 'third-party-error')}"
                no-js-text="${label('search', 'no-js')}"
            >
                <template v-slot:embed-widget>
                    <div class="row">
                        <div class="col-12 col-lg-10 offset-lg-1 mb-100 mb-lg-200">
                            <div class="cludo-search-results__search-result-count search-result-count" role="status"></div>
                            <div class="cludo-search-results__did-you-mean search-did-you-mean" role="Complementary"></div>
                            <div class="cludo-search-results__facets search-filters" aria-controls="search-results"></div>
                        </div>
                        
                        <div class="col-12 col-lg-10 offset-lg-1" role="main">
                            <div class="cludo-search-results__results-wrapper">
                                <div class="cludo-search-results__results search-results" role="region" id="search-results" aria-live="polite"></div>
                            </div>
                        </div>
                    </div>
                </template>
            </vs-embed-wrapper>
        </div>
    </div>

    <@hst.headContribution category="htmlHeadStyles">
        <link rel="stylesheet" href="<@hst.webfile path='/frontend/styles/third-party/_cludo-search-results.css'/>" type="text/css"/>
    </@hst.headContribution>
</#macro>


