<#--
    Functions
-->

<#-- @ftlvariable name="ResourceBundle" type="com.visitscotland.brxm.services.ResourceBundleService" -->
<#-- @ftlvariable name="Properties" type="com.visitscotland.brxm.utils.Properties" -->

<#--  More reliable method for including labels from resource bundles  -->
<#--  Usage: ${label("essentials.global", "footer.signup")} -->
<#function label bundle key>
    <#if ResourceBundle??>
        <#return ResourceBundle.getResourceBundle(bundle, key, locale, false)>
    <#else>
        <#return labelFallback(bundle, key)>
    </#if>
</#function>

<#--  More reliable method for including labels from resource bundles  -->
<#--  Usage: ${label("essentials.global", "footer.signup")} -->
<#function optionalLabel bundle key>
    <#if ResourceBundle??>
        <#return ResourceBundle.getResourceBundle(bundle, key, locale, true)>
    <#else>
        <#return labelFallback(bundle, key)>
    </#if >
</#function>

<#--  More reliable method for including labels from resource bundles  -->
<#--  Usage: ${property("helpdesk")} -->
<#function property key locale="">
    <#if Properties??>
        <#if locale??>
            <#return Properties.getProperty(key, locale)>
        <#else>
            <#return Properties.getProperty(key)>
        </#if>
    <#else>
        <#return labelFallback("default.config", key)>
    </#if>
</#function>

<#-- Fallback mechanism for requesting a label. It should not be used outside of this File -->
<#-- e.g. DO NOT USE THIS METHOD -->
<#function labelFallback bundle key >
    <@hst.setBundle basename="${bundle}"/>
    <@fmt.message var="message" key="${key}" />
    <@log "ResourceBundle object not defined in the template" />
    <#return message>
</#function>

<#function productSearch locale productType lat lon proximity>
<#--TODO: proximity as optional-->
    <#return ProductSearchBuilder.newInstance().locale(locale).productTypes(productType).proximity(proximity).coordinates(lat, lon).build()>
</#function>

<#--  Get correct URL for internal or external link -->
<#--  Usage: ${getUrl(navItem)} -->
<#function getUrl item>
    <#if item.page??>
        <#return item.plainLink>
    <#elseif item.hstLink??>
        <#return "pagenotfound">
    <#elseif item.externalLink??>
        <#return item.externalLink?replace("\"", "")> 
    <#else>
        <#return "#"> 
    </#if>
</#function>

<#--  Escape some characters from a JSON object so it can be consumed by a Vue component -->
<#--  Usage: ${escapeJSON(stop.opening)} -->
<#function escapeJSON original isJsonObject>
    <#assign escaped = original?replace("'", "\\'")>
    <#if isJsonObject>
        <#assign escaped = escaped?replace("\"", "'")>
    <#else>
        <#assign escaped = escaped?replace("\"", "&quot;")>
    </#if>
    <#return escaped>
</#function>

<#--  Get correct parameter depending on the number of paramters existing -->
<#--  Usage: ${getParameter(imageURL)} -->
<#function addParameter url>
    <#if url?contains("?")>
        <#return "&">
    <#else>
        <#return "?">
    </#if>
</#function>

<#function getDMSIconName dmsKey="">
    <#if dmsKey == "accesstoliet">
        <#return "fa-kit fa-vs-accessible-toilet">
    <#elseif dmsKey == "accessparkdrop">
        <#return "fa-kit fa-vs-accessible-parking">
    <#elseif dmsKey == "acco">
        <#return "fa-regular fa-bed">
    <#elseif dmsKey == "attr">
        <#return "fa-regular fa-attractions">
    <#elseif dmsKey == "audioloop">
        <#return "fa-regular fa-ear-listen">
    <#elseif dmsKey == "cafereston">
        <#return "fa-regular fa-mug-saucer">
    <#elseif dmsKey == "cate">
        <#return "fa-regular fa-utensils">
    <#elseif dmsKey == "cities" || dmsKey == "city">
        <#return "fa-regular fa-city">
    <#elseif dmsKey == "cycling">
        <#return "fa-regular fa-person-biking">
    <#elseif dmsKey == "dsblaccess" || dmsKey == "wheelchairaccess">
        <#return "fa-regular fa-wheelchair">
    <#elseif dmsKey == "even">
        <#return "fa-regular fa-calendar-range">
    <#elseif dmsKey == "familyev">
        <#return "fa-regular fa-family">
    <#elseif dmsKey == "filmev">
        <#return "fa-regular fa-camera-movie">
    <#elseif dmsKey == "hottub">
        <#return "fa-regular fa-hot-tub-person">
    <#elseif dmsKey == "parking">
        <#return "fa-regular fa-square-parking">
    <#elseif dmsKey == "petswelcom">
        <#return "fa-regular fa-dog-leashed">
    <#elseif dmsKey == "wifi">
        <#return "fa-regular fa-wifi">
    <#elseif dmsKey == "public" || dmsKey == "pubtranrte">
        <#return "fa-kit fa-vs-public-transport">
    <#elseif dmsKey == "reta">
        <#return "fa-regular fa-bag-shopping">
    <#elseif dmsKey == "spahealth">
        <#return "fa-regular fa-spa">
    <#elseif dmsKey == "vege">
        <#return "fa-kit fa-vs-vegan">
    <#elseif dmsKey == "walking" || dmsKey == "walk">
        <#return "fa-regular fa-person-walking">
    <#elseif dmsKey == "boat">
        <#return "fa-regular fa-ship">
    <#elseif dmsKey == "transport">
        <#return "fa-regular fa-taxi-bus">
    <#elseif dmsKey == "brekavail">
        <#return "fa-regular fa-egg-fried">
    <#elseif dmsKey == "wetroom">
        <#return "fa-kit fa-vs-accessible-shower">
    <#elseif dmsKey == "car">
        <#return "fa-regular fa-car-side">
    <#elseif dmsKey == "boat">
        <#return "fa-regular fa-ferry">
    <#elseif dmsKey == "map-marker">
        <#return "fa-regular fa-location-dot">
    <#elseif dmsKey == "tram">
        <#return "fa-regular fa-train-subway">
    <#elseif dmsKey == "acti">
        <#return "fa-regular fa-person-hiking">
    <#elseif dmsKey == "castle">
        <#return "fa-regular fa-chess-rook">
    <#elseif dmsKey == "history">
        <#return "fa-regular fa-landmark">
    <#elseif dmsKey == "islands">
        <#return "fa-regular fa-island-tropical">
    <#elseif dmsKey == "landscapes" || dmsKey == "landscape">
        <#return "fa-regular fa-mountain">
    <#elseif dmsKey == "sightseeing">
        <#return "fa-regular fa-binoculars">
    <#elseif dmsKey == "whisky">
        <#return "fa-regular fa-whiskey-glass-ice">
    <#else>
        <#return "fa-regular fa-" + dmsKey>
    </#if>
</#function>

<#--TODO: polimorphism-->
<#--<#function productSearch locale productType location >-->
    <#--<#return ProductSearchBuilder.newInstance().productType(productType).locale(locale).location(location).build()>-->
<#--</#function>-->