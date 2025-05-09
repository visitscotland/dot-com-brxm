package com.visitscotland.brxm.utils;

import com.visitscotland.utils.Contract;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EnvironmentManager {

    @NonTestable(value = NonTestable.Cause.WRAP)
    Optional<String> getEnvironmentVariable(String name) {
        try {
            var value = System.getenv(name);
            if (!Contract.isEmpty(value)){
                return Optional.of(value);
            }
        } catch (RuntimeException e){
            // If the variable does not exist or cannot be accessed the error is ignored and an empty value is returned
        }
        return Optional.empty();
    }

    Optional<String> getSystemProperty(String name){
        try {
            var value = System.getProperty(name, "");
            if (!Contract.isEmpty(value)){
                return Optional.of(value);
            }
        } catch (RuntimeException e){
            // If the variable does not exist or cannot be accessed the error is ignored and an empty value is returned
        }
        return Optional.empty();
    }
}
