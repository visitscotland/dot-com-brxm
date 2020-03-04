package com.visitscotland.brmx.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@HippoEssentialsGenerated(internalName = "visitscotland:ProductsSearch")
@Node(jcrType = "visitscotland:ProductsSearch")
public class ProductsSearch extends HippoCompound {
    @HippoEssentialsGenerated(internalName = "visitscotland:location")
    public String getLocation() {
        return getSingleProperty("visitscotland:location");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:dmsCategories", allowModifications = false)
    public String[] getDmsCategories() {
        return getMultipleProperty("visitscotland:dmsCategories");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:dmsFacilities", allowModifications = false)
    public String[] getDmsFacilities() {
        return getMultipleProperty("visitscotland:dmsFacilities");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:dmsAwards", allowModifications = false)
    public String[] getDmsAwards() {
        return getMultipleProperty("visitscotland:dmsAwards");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:officialrating")
    public String[] getOfficialrating() {
        return getMultipleProperty("visitscotland:officialrating");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:productType")
    public String getProductType() {
        return getSingleProperty("visitscotland:productType");
    }
}
