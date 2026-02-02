package com.visitscotland.brxm.hippobeans;

import com.visitscotland.brxm.model.Module;

public class SimpleDevModule extends Module<DevModule> {

    private String id;

    public SimpleDevModule(DevModule hippoBean) {
        setHippoBean(hippoBean);
    }

    public SimpleDevModule(DevModule hippoBean, String id) {
        this(hippoBean);
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
