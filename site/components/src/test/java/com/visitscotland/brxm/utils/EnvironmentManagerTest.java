package com.visitscotland.brxm.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnvironmentManagerTest {

    private final EnvironmentManager manager = new EnvironmentManager();

    @Test
    @DisplayName("System properties are read correctly")
    void when_systemPropertyExists_then_returnValue() {
        System.setProperty("vs-property", "value");
        assertEquals(Optional.of("value"), manager.getSystemProperty("vs-property"));
    }

    @Test
    @DisplayName("When system properties do not exist return Empty")
    void when_systemPropertyDoesNotExist_then_returnEmpty() {
        System.clearProperty("vs-property");
        assertEquals(Optional.empty(), manager.getSystemProperty("vs-property"));
    }

    @Test
    @DisplayName("When environment variables do not exist return Empty")
    void when_environmentVariableDoesNotExist_then_returnEmpty() {
        Optional<String> result = manager.getEnvironmentVariable("VAR_123456");
        assertEquals(Optional.empty(), result);
    }
}
