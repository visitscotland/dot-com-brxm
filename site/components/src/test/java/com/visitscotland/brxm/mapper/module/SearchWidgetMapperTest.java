package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchWidgetMapperTest {

    @Mock
    private ResourceBundleService bundle;

    @Mock
    private HippoUtilsService hippoUtilsService;

    private ObjectMapper objectMapper;

    @InjectMocks
    private SearchWidgetMapper mapper;

    @Mock
    private DevModule document;

    @Mock
    private ResourceBundle resourceBundle;

    private final Locale locale = Locale.UK;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mapper = new SearchWidgetMapper(bundle, hippoUtilsService, objectMapper);
    }

    @Test
    void shouldCreateModule_WithStandardCategories() {
        when(document.getBespoken()).thenReturn("standard");
        when(bundle.getResourceBundle("standard", locale)).thenReturn(resourceBundle);

        when(resourceBundle.getString("title")).thenReturn("Title");
        when(resourceBundle.getString("description")).thenReturn("Desc");
        when(resourceBundle.getString("placeholder")).thenReturn("Search...");
        when(resourceBundle.getString("button")).thenReturn("Go");

        Map<String, String> categories = Map.of("cat1", "Category 1");
        when(bundle.getAllLabels("main-category-filters", locale)).thenReturn(categories);

        SearchWidgetModule result = mapper.createModule(document, locale);

        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        assertEquals("Desc", result.getDescription());
        assertEquals("Search...", result.getPlaceholder());
        assertEquals("Go", result.getButton());
        assertEquals(categories, result.getCategories());

        verify(bundle).getAllLabels("main-category-filters", locale);
    }

    @Test
    void shouldCreateModule_WithEventFilters() {
        when(document.getBespoken()).thenReturn("search-widget-events");
        when(bundle.getResourceBundle("search-widget-events", locale)).thenReturn(resourceBundle);

        when(resourceBundle.getString(anyString())).thenReturn("value");

        when(bundle.getAllLabels("search-events-subcategories", locale))
                .thenReturn(Map.of("music", "Music"));

        // Mock filter maps
        Map<String, String> datesMap = Map.of("today", "1");
        Map<String, String> locationMap = Map.of("edinburgh", "EH");

        when(hippoUtilsService.getValueMap("vs-events-filters-dates")).thenReturn(datesMap);
        when(hippoUtilsService.getValueMap("vs-events-filters-locations")).thenReturn(locationMap);

        when(bundle.getResourceBundle(anyString(), anyString(), eq(locale)))
                .thenReturn("Label");

        SearchWidgetModule result = mapper.createModule(document, locale);

        assertNotNull(result);
        assertEquals("events", result.getMainCategory());
        assertNotNull(result.getFilters());
        assertTrue(result.getFilters().has("when"));
        assertTrue(result.getFilters().has("postcodeareas"));
    }

    @Test
    void shouldHandleNullFilterMap() {
        when(hippoUtilsService.getValueMap("test")).thenReturn(null);

        ObjectNode filters = objectMapper.createObjectNode();

        JsonNode result = mapper.addFilterJson(
                "test",
                "root",
                "bundle",
                filters,
                locale
        );

        assertNotNull(result);
        assertTrue(result.has("root"));
        assertEquals(0, result.get("root").size());
    }

    @Test
    void shouldFallbackToKeyWhenLabelEmpty() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "id1");

        when(hippoUtilsService.getValueMap("test")).thenReturn(map);
        when(bundle.getResourceBundle("bundle", "key1", locale)).thenReturn("");

        ObjectNode filters = objectMapper.createObjectNode();

        JsonNode result = mapper.addFilterJson(
                "test",
                "root",
                "bundle",
                filters,
                locale
        );

        JsonNode node = result.get("root").get(0);

        assertEquals("id1", node.get("id").asText());
        assertEquals("key1", node.get("label").asText()); // fallback
    }
}