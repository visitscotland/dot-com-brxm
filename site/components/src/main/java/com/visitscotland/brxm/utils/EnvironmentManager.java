package com.visitscotland.brxm.utils;

import org.springframework.stereotype.Component;

@Component
@NonTestable(value = NonTestable.Cause.WRAP)
public class EnvironmentManager {

    String getEnvironmentVariable(String name){
        try {
            return System.getenv(name);
        } catch (RuntimeException e){
            return null;
        }
    }

    String getSystemProperty(String name){
        return System.getProperty(name, "");
    }
}
