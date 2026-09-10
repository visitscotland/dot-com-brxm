package com.visitscotland.brxm.model;

import com.visitscotland.brxm.hippobeans.TravelInformationTransportRow;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class TravelInformationArticleModule extends Module<TravelInformationTransportRow>  implements TravelInformationTabInterface {

    private HippoHtml copy;

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }
}
