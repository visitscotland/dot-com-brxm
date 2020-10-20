package com.visitscotland.brmx.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import java.util.List;

@HippoEssentialsGenerated(internalName = "visitscotland:Itinerary")
@Node(jcrType = "visitscotland:Itinerary")
public class Itinerary extends Page {
    @HippoEssentialsGenerated(internalName = "visitscotland:start")
    public String getStart() {
        return getSingleProperty("visitscotland:start");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:finish")
    public String getFinish() {
        return getSingleProperty("visitscotland:finish");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:distance")
    public Double getDistance() {
        return getSingleProperty("visitscotland:distance");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:theme")
    public String getTheme() {
        return getSingleProperty("visitscotland:theme");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:areas")
    public String[] getAreas() {
        return getMultipleProperty("visitscotland:areas");
    }

    @Deprecated
    /**
     * This method shouldn't have new invocations. Instead of this, you should invoke {@code new DocumentUtils().getSiblingDocuments()}
     */
    public List<Day> getDays() {
        return getPageChildrenByType(Day.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:transports")
    public String[] getTransports() {
        return getMultipleProperty("visitscotland:transports");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:highlights")
    public String getHighlights() {
        return getSingleProperty("visitscotland:highlights");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:popular")
    public Boolean getPopular() {
        return getSingleProperty("visitscotland:popular");
    }

    @Override
    public String[] getChildJcrTypes() {
        return new String[] { "visitscotland:Day" };
    }
}
