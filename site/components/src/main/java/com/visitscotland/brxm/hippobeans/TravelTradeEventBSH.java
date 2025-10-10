package com.visitscotland.brxm.hippobeans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import java.util.Calendar;
import com.visitscotland.brxm.hippobeans.Price;

@HippoEssentialsGenerated(internalName = "visitscotland:TravelTradeEventBSH")
@Node(jcrType = "visitscotland:TravelTradeEventBSH")
public class TravelTradeEventBSH extends EventBSH {
    @HippoEssentialsGenerated(internalName = "visitscotland:international")
    public Boolean getInternational() {
        return getSingleProperty("visitscotland:international");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:email")
    public String getEmail() {
        return getSingleProperty("visitscotland:email");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:deadline")
    public Calendar getDeadline() {
        return getSingleProperty("visitscotland:deadline");
    }

    @HippoEssentialsGenerated(internalName = "visitscotland:timezone")
    public String getTimezone() {
        return getSingleProperty("visitscotland:timezone");
    }
}
