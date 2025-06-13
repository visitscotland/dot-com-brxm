package com.visitscotland.brxm.obs.model;

public class ContractFee {

    private String key;
    private String value;

    public ContractFee(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
