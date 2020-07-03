package com.visitscotland.brmx.translation.plugin;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TranslationWorkflowPluginTest {
    @Test
    public void htmlVisible() {
        // Checking that the wicket html file is visible on the classpath
        ClassLoader classLoader = getClass().getClassLoader();
        String resourceName = TranslationWorkflowPlugin.class.getCanonicalName().replace(".", "/");
        // This will return null if the icon file identified by the Key does not exist.
        InputStream stream = classLoader.getResourceAsStream(resourceName + ".html");
        assertNotNull(stream);
    }

    @Test
    public void propertiesVisible() {
        // Checking that the wicket html file is visible on the classpath
        ClassLoader classLoader = getClass().getClassLoader();
        String resourceName = TranslationWorkflowPlugin.class.getCanonicalName().replace(".", "/");
        // This will return null if the icon file identified by the Key does not exist.
        InputStream stream = classLoader.getResourceAsStream(resourceName + ".properties");
        assertNotNull(stream);
    }
}
