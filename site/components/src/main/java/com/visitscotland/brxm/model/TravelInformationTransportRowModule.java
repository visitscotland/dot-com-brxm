package com.visitscotland.brxm.model;

import org.hippoecm.hst.content.beans.standard.HippoHtml;

public class TravelInformationTransportRowModule {

    private TransportType transport;
    private HippoHtml copy;

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }

    public HippoHtml getCopy() {
        return copy;
    }

    public void setCopy(HippoHtml copy) {
        this.copy = copy;
    }
}
