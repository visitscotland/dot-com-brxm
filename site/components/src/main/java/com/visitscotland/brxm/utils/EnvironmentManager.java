package com.visitscotland.brxm.utils;

import com.visitscotland.utils.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class EnvironmentManager {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentManager.class);

    final SystemWrapper system;

    public EnvironmentManager(final SystemWrapper system) {
        this.system = system;
    }

    Optional<String> getEnvironmentVariable(String name) {
        return readSystemValue(system::getProperty, name, "Environment Variable");
    }

    Optional<String> getSystemProperty(String name){
        return readSystemValue(system::getProperty, name, "System property");
    }

    private Optional<String> readSystemValue(Function<String, String> retriever, String parameter, String type){
        try {
            var value = system.getProperty(retriever.apply(parameter));
            if (!Contract.isEmpty(value)){
                return Optional.of(value);
            }
        } catch (NullPointerException | SecurityException e){
            logger.debug("Cannot read {} {}: {}", type, parameter, e.getMessage());
        }
        return Optional.empty();
    }
}
