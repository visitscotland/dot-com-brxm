<#function themeCalculator module="" colourSchemeParam=[]>
    <#assign colourScheme = ["light", "light", "dark"]>

    <#if colourSchemeParam?size = 0>
        <#if breadcrumbs?? && breadcrumbs.items?size == 2>
            <!-- Note: There was a requirement about level 2 starting on light but It hasn't been either confirmed or discarded yet -->
            <!-- The following line allow to make the difference. Otherwise this if block can be simplified -->
            <#-- <#assign colourScheme = ["light", "light", "dark"]> -->
            <#assign colourScheme = ["dark", "light", "light"]>
        <#else>
            <#assign colourScheme = ["dark", "light", "light"]>
        </#if>
    <#else>
        <#assign colourScheme = colourSchemeParam>
    </#if>

    <#if module.themeIndex?has_content>
        <#return colourScheme[index]>
    <#elseif module.getType() == "ICentreModule">
        <#return "grey">
    <#else>
        <#return "light">
    </#if>
</#function>
