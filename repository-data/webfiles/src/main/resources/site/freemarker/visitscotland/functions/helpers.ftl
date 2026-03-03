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
            <#return Properties.getProperty(key, locale).orElse("")>
        <#else>
            <#return Properties.getProperty(key).orElse("")>
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
    <#assign iconMap = {
        "accesstoliet": "fa-kit fa-vs-accessible-toilet",
        "accessparkdrop": "fa-kit fa-vs-accessible-parking",
        "acco": "fa-regular fa-bed",
        "attr": "fa-regular fa-attractions",
        "audioloop": "fa-regular fa-ear-listen",
        "cafereston": "fa-regular fa-mug-saucer",
        "cate": "fa-regular fa-utensils",
        "cities": "fa-regular fa-city",
        "city": "fa-regular fa-city",
        "cycling": "fa-regular fa-person-biking",
        "dsblaccess": "fa-regular fa-wheelchair",
        "wheelchairaccess": "fa-regular fa-wheelchair",
        "even": "fa-regular fa-calendar-range",
        "familyev": "fa-regular fa-family",
        "filmev": "fa-regular fa-camera-movie",
        "hottub": "fa-regular fa-hot-tub-person",
        "parking": "fa-regular fa-square-parking",
        "petswelcom": "fa-regular fa-dog-leashed",
        "wifi": "fa-regular fa-wifi",
        "public": "fa-kit fa-vs-public-transport",
        "pubtranrte": "fa-kit fa-vs-public-transport",
        "reta": "fa-regular fa-bag-shopping",
        "spahealth": "fa-regular fa-spa",
        "vege": "fa-kit fa-vs-vegan",
        "walking": "fa-regular fa-person-walking",
        "walk": "fa-regular fa-person-walking",
        "boat": "fa-regular fa-ferry",
        "transport": "fa-regular fa-taxi-bus",
        "brekavail": "fa-regular fa-egg-fried",
        "wetroom": "fa-kit fa-vs-accessible-shower",
        "car": "fa-regular fa-car-side",
        "map-marker": "fa-regular fa-location-dot",
        "tram": "fa-regular fa-train-subway",
        "acti": "fa-regular fa-person-hiking",
        "castle": "fa-regular fa-chess-rook",
        "history": "fa-regular fa-landmark",
        "islands": "fa-regular fa-island-tropical",
        "landscapes": "fa-regular fa-mountain",
        "landscape": "fa-regular fa-mountain",
        "sightseeing": "fa-regular fa-binoculars",
        "whisky": "fa-regular fa-whiskey-glass-ice"
    }>

    <#if iconMap[dmsKey]??>
        <#return iconMap[dmsKey]>
    <#else>
        <#return "fa-regular fa-" + dmsKey>
    </#if>
</#function>

<#--TODO: polimorphism-->
<#--<#function productSearch locale productType location >-->
    <#--<#return ProductSearchBuilder.newInstance().productType(productType).locale(locale).location(location).build()>-->
<#--</#function>-->