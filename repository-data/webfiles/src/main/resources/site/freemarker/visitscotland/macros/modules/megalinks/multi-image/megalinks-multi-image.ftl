<#include "../../../../../include/imports.ftl">
<#include "../../../../../frontend/components/vs-megalink-multi-image.ftl">
<#include "./multi-image-featured.ftl">
<#include "./multi-image-two-items.ftl">
<#include "./multi-image-three-items.ftl">
<#include "../../../global/cms-errors.ftl">

<#macro multiImage item showTeaser>
    <vs-row>
        <vs-col
            cols="10"
            class="offset-1"
        >
            <vs-row>
                <@cmsErrors errors=item.errorMessages!"" editMode=editMode />
                <#-- if there's at least one featured link -->
                <#if item.featuredLinks?size gt 0>
                    <@multiImageFeatured lastFeatured='false' feature=item.featuredLinks[0] />
                </#if>

                <#list item.links as megalink>
                    <#-- 2 and 4 links will be displayed in 2 columns -->
                    <#if item.links?size == 2 || item.links?size == 4>
                        <@multiImageTwoItems megalink=megalink showTeaser=showTeaser/>
                    </#if>
                    <#-- 3 and 6 links will be displayed in 3 columns -->
                    <#if item.links?size == 3 || item.links?size == 6>
                        <@multiImageThreeItems megalink=megalink showTeaser=showTeaser/>
                    </#if>
                    <#-- 5 links will be displayed in a combintation of 2 and 3 columns -->
                    <#if item.links?size == 5>
                        <#if megalink?index lt 3>
                            <@multiImageThreeItems megalink=megalink showTeaser=showTeaser/>
                        <#else>
                            <@multiImageTwoItems megalink=megalink showTeaser=showTeaser/>
                        </#if>
                    </#if>
                </#list>

                <#-- if there's a second featured link -->
                <#if item.featuredLinks?? && item.featuredLinks?size gt 1>
                    <@multiImageFeatured  lastFeatured='true' feature=item.featuredLinks[1] />
                </#if>
            </vs-row>
        </vs-col>
    </vs-row>
</#macro>