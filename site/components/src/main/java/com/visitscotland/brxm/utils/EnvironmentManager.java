package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.dms.DMSProxy;
import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EnvironmentManager {

    private static final Logger logger = LoggerFactory.getLogger(DMSProxy.class);

    final SystemWrapper system;

    public EnvironmentManager(final SystemWrapper system) {
        this.system = system;
    }


    Optional<String> getEnvironmentVariable(String name) {
        try {
            var value = system.getenv(name);
            if (!Contract.isEmpty(value)){
                return Optional.of(value);
            }
        } catch (NullPointerException | SecurityException e){
            logger.debug("The environment Variable {} could not be read due to the following error: {}", name, e.getMessage());
        }
        return Optional.empty();
    }

    Optional<String> getSystemProperty(String name){
        try {
            var value = system.getProperty(name);
            if (!Contract.isEmpty(value)){
                return Optional.of(value);
            }
        } catch (NullPointerException | SecurityException e){
            logger.debug("The system property {} could not be read due to the following error: {}", name, e.getMessage());
            // If the variable does not exist or cannot be accessed the error is ignored and an empty value is returned
        }
        return Optional.empty();
    }
}
