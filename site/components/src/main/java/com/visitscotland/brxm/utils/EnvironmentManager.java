package com.visitscotland.brxm.utils;

import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NonTestable(value = NonTestable.Cause.WRAP)
public class EnvironmentManager {


    Optional<String> getEnvironmentVariable(String name) {
        try {
            String value = System.getenv(name);
            if (!Contract.isEmpty(value)){
                return Optional.of(value);
            }
        } catch (RuntimeException e){

        }
        return Optional.empty();
    }

    Optional<String> getSystemProperty(String name){
        String value = System.getProperty(name, "");
        if (value.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }
}
