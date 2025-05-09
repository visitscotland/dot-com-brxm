package com.visitscotland.brxm.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EnvironmentManagerTest {

    @Mock SystemWrapper system;

    @InjectMocks EnvironmentManager manager;

    @Test
    @DisplayName("System properties are read correctly")
    void when_systemPropertyExists_then_returnValue() {
        Mockito.when(system.getProperty("vs-property")).thenReturn("value");
        assertEquals(Optional.of("value"), manager.getSystemProperty("vs-property"));
    }

    @Test
    @DisplayName("When system properties do not exist return Empty")
    void when_systemPropertyDoesNotExist_then_returnEmpty() {
        assertEquals(Optional.empty(), manager.getSystemProperty("vs-property"));
    }

    @Test
    @DisplayName("When system properties throw an exception, return Empty")
    void when_systemPropertyThrowsException_then_returnEmpty() {
        Mockito.when(system.getProperty("vs-property")).thenThrow(NullPointerException.class);
        assertEquals(Optional.empty(), manager.getSystemProperty("vs-property"));
    }

    @Test
    @DisplayName("Environment variables are read correctly")
    void when_environmentVariableExists_then_returnValue() {
        Mockito.when(system.getenv("VAR_123456")).thenReturn("value");
        assertEquals(Optional.of("value"), manager.getEnvironmentVariable("VAR_123456"));
    }

    @Test
    @DisplayName("When environment variables do not exist return Empty")
    void when_environmentVariableDoesNotExist_then_returnEmpty() {
        Optional<String> result = manager.getEnvironmentVariable("VAR_123456");
        assertEquals(Optional.empty(), result);
    }

    @Test
    @DisplayName("When Environment Variable throw an exception, return Empty")
    void when_environmentVariableThrowsException_then_returnEmpty() {
        Mockito.when(system.getenv("VAR_123456")).thenThrow(SecurityException.class);
        assertEquals(Optional.empty(), manager.getEnvironmentVariable("VAR_123456"));
    }
}
