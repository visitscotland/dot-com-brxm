package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertiesTest {

    final static String BUNDLE_ID = CMSProperties.DEFAULT_CONFIG;
    final static String PROPERTY_KEY = "property-key";
    final static boolean OPTIONAL_RESOURCE_BUNDLE = true;


    @Mock
    ResourceBundleService bundle;

    @Mock
    HippoUtilsService utils;

    @Mock
    EnvironmentManager environmentManager;

    @InjectMocks
    CMSProperties properties;

    @Test
    @DisplayName("Reads an String value from a property")
    void readString(){
        when(bundle.getResourceBundle(BUNDLE_ID, "string", Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("http://localhost:8181");
        assertEquals("http://localhost:8181", properties.readString("string"));
    }

    @Test
    @DisplayName("Reads an string value from an environment variable")
    void readString_env(){
        final String value = "http://dms.visitscotland.com";
        when(bundle.getResourceBundle(BUNDLE_ID, "string", Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("$TEST_VS");
        when(environmentManager.getEnvironmentVariable("TEST_VS")).thenReturn(Optional.of(value));
        assertEquals(value, properties.readString("string"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "$"})
    @NullSource
    @DisplayName("Empty values return an empty String for String properties")
    void readString_env(String value){
        when(bundle.getResourceBundle(BUNDLE_ID, "string", Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn(value);
        assertEquals("", properties.readString("string"));
    }

    @Test
    @DisplayName("Reads an integer number from a property")
    void readInteger(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_TRIES, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("3718");
        assertEquals(3718, properties.getDmsTries());
    }

    @Test
    @DisplayName("Can parse Integers from environment variables")
    void readInteger_env(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_TRIES, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("%TEST_VS");
        when(environmentManager.getSystemProperty("TEST_VS")).thenReturn(Optional.of("3718"));
        assertEquals(3718, properties.getDmsTries());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "$"})
    @NullSource
    @DisplayName("Empty and wrong values return 0 for Numeric properties")
    void readInteger_empty(String value){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_TRIES, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn(value);
        assertEquals(0, properties.getDmsTries());
    }

    @Test
    @DisplayName("Reads an integer number from a property")
    void readDouble(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_MAP_DEFAULT_DISTANCE, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("3.14");
        assertEquals(3.14, properties.getDmsMapDefaultDistance());
    }

    @Test
    @DisplayName("Can parse Integers from environment variables")
    void readDouble_env(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_MAP_DEFAULT_DISTANCE, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("%TEST_VS");
        when(environmentManager.getSystemProperty("TEST_VS")).thenReturn(Optional.of("3.14"));
        assertEquals(3.14, properties.getDmsMapDefaultDistance());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "$"})
    @NullSource
    @DisplayName("Empty and wrong values return 0 for Numeric properties")
    void readDouble_empty(String value){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_MAP_DEFAULT_DISTANCE, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn(value);
        assertEquals(0.0, properties.getDmsMapDefaultDistance());
    }


    @ParameterizedTest
    @ValueSource(strings = {"true", "TRUE"})
    @DisplayName("Reads true from a property")
    void readBoolean(String value){
        when(bundle.getResourceBundle(BUNDLE_ID, "boolean", Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn(value);
        assertTrue(properties.readBoolean("boolean"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"false", "", "$", "0", "1", "T"})
    @NullSource
    @DisplayName("false, empty and wrong values return false for Boolean properties")
    void readInteger_false(String value){
        when(bundle.getResourceBundle(BUNDLE_ID, "boolean", Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn(value);
        assertFalse(properties.readBoolean("boolean"));
    }

    @Test
    @DisplayName("getDmsEncoding - Can parse the value from a String")
    void getDmsEncoding(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_ENCODING, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("ISO-8859-1");
        assertEquals(StandardCharsets.ISO_8859_1, properties.getDmsEncoding());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "latin","UFT-16"})
    @NullSource
    @DisplayName("getDmsEncoding - non recognized values return UTF-8 encoding")
    void getDmsEncoding_empty(String value){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_ENCODING, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn(value);
        assertEquals(StandardCharsets.UTF_8, properties.getDmsEncoding());
    }

    @Test
    @DisplayName("As requested by WebOps, links to vs-dms-products URLs will be relative when use relative urls is active")
    void getDmsHost(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_HOST, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("http://test-dms.visitscotland.com");
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.USE_RELATIVE_URLS, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("false");
        assertEquals("http://test-dms.visitscotland.com", properties.getDmsHost());

        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.USE_RELATIVE_URLS, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("true");
        assertEquals("", properties.getDmsHost());
    }

    @Test
    @DisplayName("As requested by WebOps, made up links to the CMS, will be relative when use relative urls is active")
    void getLocalHost(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.CMS_BASE_PATH, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("http:/localhost:8080/site");
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.USE_RELATIVE_URLS, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("true");
        assertEquals("", properties.getDmsHost());

        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.USE_RELATIVE_URLS, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("false");
        assertEquals("http:/localhost:8080/site", properties.getCmsBasePath());
    }

    @Test
    @DisplayName("getInstagramURL() Composes the token from the app-id and the client-token (PR-383)")
    void getInstagramURL(){

        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.INSTAGRAM_APP_ID, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("{app-id}");
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.INSTAGRAM_ACCESS_TOKEN, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("{client-token}");
        assertEquals("{app-id}|{client-token}", properties.getInstagramToken());

        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.INSTAGRAM_ACCESS_TOKEN, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("");
        assertEquals("{app-id}", properties.getInstagramToken());
    }

    @Test
    @DisplayName("Read properties from hosts configuration")
    void propertiesFromConfiguration(){
        final String ENV_PROPERTIES = "environment-properties";

        Mount mount = mock(Mount.class);
        when(utils.getResolvedMount(null)).thenReturn(mount);
        when(mount.getProperty("visitscotland:cmsProperties")).thenReturn(ENV_PROPERTIES);

        properties.getInstagramToken();

        Mockito.verify(bundle, atLeastOnce()).getResourceBundle(eq(ENV_PROPERTIES), any(), eq(Locale.UK), eq(true));
    }

    @Test
    @DisplayName("Read properties from hosts configuration for language subsites")
    void propertiesFromParentConfiguration(){
        final String ENV_PROPERTIES = "environment-properties";

        Mount mount = mock(Mount.class);
        Mount parent = mock(Mount.class);

        when(utils.getResolvedMount(null)).thenReturn(mount);
        when(mount.getParent()).thenReturn(parent);
        when(parent.getProperty("visitscotland:cmsProperties")).thenReturn(ENV_PROPERTIES);

        properties.getInstagramToken();

        Mockito.verify(bundle, atLeastOnce()).getResourceBundle(eq(ENV_PROPERTIES), any(), eq(Locale.UK), eq(true));
    }

    @Test
    @DisplayName("VS-3183 - Use default.config as the default properties given by the CMS from the codebase")
    void propertyValues_defaultConfig(){
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_API_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("PROP-VALUE");

        assertEquals("PROP-VALUE", properties.getDmsToken().orElseThrow());
    }

    private final static String ENV_PROPERTIES = "environment-properties";

    private void customizedProperties(){
        Mount mount = mock(Mount.class);
        when(utils.getResolvedMount(null)).thenReturn(mount);
        when(mount.getProperty("visitscotland:cmsProperties")).thenReturn(ENV_PROPERTIES);
    }

    @Test
    @DisplayName("VS-3183 - Override properties when configured in the mount")
    void propertyValues_overrideConfig(){
        customizedProperties();
        when(bundle.getResourceBundle(ENV_PROPERTIES, CMSProperties.DMS_DATA_API_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("OVERRIDE-VALUE");

        //This is not expected to be called but if were, It shouldn't affect the outcome of the method
        lenient().when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_API_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("DEFAULT-VALUE");

        assertEquals("OVERRIDE-VALUE", properties.getDmsToken().orElseThrow());
    }

    @Test
    @DisplayName("VS-3183 - If the property is not found in the file defined in the mount, it takes the value from default.config")
    void propertyValues_overrideConfig_defaultValue(){
        customizedProperties();
        when(bundle.getResourceBundle(ENV_PROPERTIES, CMSProperties.DMS_DATA_API_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn(null);

        //This is not expected to be called but if were, It shouldn't affect the outcome of the method
        when(bundle.getResourceBundle(BUNDLE_ID, CMSProperties.DMS_DATA_API_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("DEFAULT-VALUE");

        assertEquals("DEFAULT-VALUE", properties.getDmsToken().orElseThrow());
    }

    @Test
    @DisplayName("VS-3908 - The properties can have specific values for different locales")
    void locales(){
        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("/site/site-search-results");
        assertEquals("/site/site-search-results", properties.getProperty(PROPERTY_KEY).orElseThrow());

        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.FRANCE, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("/site/fr/site-search-results");
        assertEquals("/site/fr/site-search-results", properties.getProperty(PROPERTY_KEY, Locale.FRANCE).orElseThrow());
    }

    @Test
    @DisplayName("VS-3908 - Properties in the language fallback to English")
    void locales_defaultEnglish(){
        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.FRANCE, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("");
        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("/site/site-search-results");
        assertEquals("/site/site-search-results", properties.getProperty(PROPERTY_KEY, Locale.FRANCE).orElseThrow());
    }

    @Test
    @DisplayName("VS-3908 - non-existing localized custom properties -> Localized global properties")
    void locales_overrideProperties(){
        customizedProperties();
        when(bundle.getResourceBundle(ENV_PROPERTIES, PROPERTY_KEY, Locale.FRANCE, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("");
        when(bundle.getResourceBundle(ENV_PROPERTIES, PROPERTY_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("");
        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.FRANCE, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("555");
        assertEquals("555", properties.getProperty(PROPERTY_KEY, Locale.FRANCE).orElseThrow());
    }

    @Test
    @DisplayName("VS-3908 - non-existing localized custom properties -> English global properties")
    void locales_overrideProperties_defaultEnglish(){
        customizedProperties();
        when(bundle.getResourceBundle(ENV_PROPERTIES, PROPERTY_KEY, Locale.JAPAN, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("");
        when(bundle.getResourceBundle(ENV_PROPERTIES, PROPERTY_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("");
        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.JAPAN, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("");
        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("131");
        assertEquals("131", properties.getProperty(PROPERTY_KEY, Locale.JAPAN).orElseThrow());
    }

    @Test
    @DisplayName("Optional fields can return the value")
    void when_optionalPropertyIsSet_then_returnValue(){
        when(bundle.getResourceBundle(BUNDLE_ID, PROPERTY_KEY, Locale.UK, OPTIONAL_RESOURCE_BUNDLE)).thenReturn("131");
        assertTrue(properties.readOptionalString(PROPERTY_KEY).isPresent());
    }

    @Test
    @DisplayName("Optional fields will return an Optional.empty when the value is not available")
    void when_optionalPropertyIsSet_then_empty(){
        assertTrue(properties.readOptionalString(PROPERTY_KEY).isEmpty());
    }

}
