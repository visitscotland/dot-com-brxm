package com.visitscotland.brxm.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.hippobeans.DevModule;

import java.util.Map;

public class FavouriteModule extends Module<DevModule>{

    private String id;

     public FavouriteModule(DevModule hippoBean, String id) {
        setHippoBean(hippoBean);
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

