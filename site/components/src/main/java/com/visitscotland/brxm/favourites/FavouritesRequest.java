package com.visitscotland.brxm.favourites;

import java.util.List;

public class FavouritesRequest {
    private List<String> uuids;

    public List<String> getUuids() {
        return uuids;
    }

    public void setUuids(List<String> uuids) {
        this.uuids = uuids;
    }
}
