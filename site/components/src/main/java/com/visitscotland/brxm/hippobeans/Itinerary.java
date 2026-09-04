package com.visitscotland.brxm.hippobeans;

import org.hippoecm.hst.content.beans.Node;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import java.util.Calendar;

@HippoEssentialsGenerated(internalName = "visitscotland:Itinerary")
@Node(jcrType = "visitscotland:Itinerary")
public class Itinerary extends Page {
    @HippoEssentialsGenerated(internalName = "visitscotland:distance")
    public Double getDistance() {
        return getSingleProperty("visitscotland:distance");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:transports")
    public String[] getTransports() {
        return getMultipleProperty("visitscotland:transports");
    }

    @Override
    public String[] getChildJcrTypes() {
        return new String[] { "visitscotland:Day" };
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:otherThings")
    public com.visitscotland.brxm.hippobeans.OTYML getOtherThings() {
        return getBean("visitscotland:otherThings", com.visitscotland.brxm.hippobeans.OTYML.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationPriority")
    public String getTranslationPriority() {
        return getSingleProperty("visitscotland:translationPriority");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:heroVideo")
    public VideoLink getHeroVideo() {
        return getBean("visitscotland:heroVideo", VideoLink.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:translationDeadline")
    public Calendar getTranslationDeadline() {
        return getSingleProperty("visitscotland:translationDeadline");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:subheading")
    public String getSubheading() {
        return getSingleProperty("visitscotland:subheading");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:mapLink")
    public ExternalLink getMapLink() {
        return getBean("visitscotland:mapLink", ExternalLink.class);
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:locations")
    public String[] getLocations() {
        return getMultipleProperty("visitscotland:locations");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:seasons")
    public String[] getSeasons() {
        return getMultipleProperty("visitscotland:seasons");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:embeddedMap")
    public String getEmbeddedMap() {
        return getSingleProperty("visitscotland:embeddedMap");
    }
}
