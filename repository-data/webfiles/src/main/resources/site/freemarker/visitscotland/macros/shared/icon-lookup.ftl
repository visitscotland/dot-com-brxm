<#function iconLookup inputName="">
    <#switch inputName>
        <#case "accesstoliet">
            <#return "accessible-toilet">
            <#break>
        <#case "accessparkdrop">
            <#return "facility-accessparkdrop">
            <#break>
        <#case "acco">
            <#return "product-accommodation">
            <#break>
        <#case "acti">
            <#return "product-activitie">
            <#break>
        <#case "attr">
            <#return "product-attractions">
            <#break>
        <#case "audioloop">
            <#return "facility-audioloop">
            <#break>
        <#case "cafereston">
            <#return "cafe">
            <#break>
        <#case "cate">
            <#return "product-food-and-drink">
            <#break>
        <#case "cities">
            <#return "city">
            <#break>
        <#case "cycling">
            <#return "cycle">
            <#break>
        <#case "dsblaccess">
            <#return "facility-dsblaccess">
            <#break>
        <#case "wheelchairaccess">
            <#return "facility-dsblaccess">
            <#break>
        <#case "even">
            <#return "product-events">
            <#break>
        <#case "familyev">
            <#return "family">
            <#break>
        <#case "filmev">
            <#return "film-tv">
            <#break>
        <#case "hottub">
            <#return "hot-tub">
            <#break>
        <#case "parking">
            <#return "facility-parking">
            <#break>
        <#case "petswelcom">
            <#return "facility-petswelcom">
            <#break>
        <#case "wifi">
            <#return "facility-wifi">
            <#break>
        <#case "public">
            <#return "public-transport">
            <#break>
        <#case "pubtranrte">
            <#return "public-transport">
            <#break>
        <#case "reta">
            <#return "product-shopping">
            <#break>
        <#case "spahealth">
            <#return "wellness">
            <#break>
        <#case "vege">
            <#return "vegan-vegetarian">
            <#break>
        <#case "walking">
            <#return "walk">
            <#break>
        <#case "boat">
            <#return "boat">
            <#break>
        <#case "transport">
            <#return "transport">
            <#break>
        <#case "brekavail">
            <#return "breakfast-available">
            <#break>
        <#case "wetroom">
            <#return "level-entry-shower">
            <#break>
        <#case "x-twitter">
            <#return "x-twitter fa-brands">
            <#break>
        <#case "linkedin">
            <#return "linkedin-in fa-brands">
            <#break>
        <#default>
            <#return inputName>
            <#break>
    </#switch>
</#function>
