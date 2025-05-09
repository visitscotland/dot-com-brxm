package com.visitscotland.brxm.utils;

import org.springframework.stereotype.Component;

@Component
@NonTestable(value = NonTestable.Cause.WRAP)
public class SystemWrapper {

    public String getenv(String key) {
        return System.getenv(key);
    }

    public String getProperty(String key) {
        return System.getProperty(key);
    }
}
