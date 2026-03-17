package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @InjectMocks
    private SearchWidgetMapper mapper;

    private DevModule document;
    private ResourceBundle resourceBundle;

    @BeforeEach
    void setUp() {
        bundle = mock(ResourceBundleService.class);
        hippoUtilsService = mock(HippoUtilsService.class);
        mapper = new SearchWidgetMapper(bundle, hippoUtilsService);

        document = mock(DevModule.class);
        resourceBundle = mock(ResourceBundle.class);
    }

    @Test
    void shouldCreateModule_ForEventsBespoken() {
        Locale locale = Locale.UK;

        when(document.getBespoken()).thenReturn("search-widget-events");

        when(bundle.getResourceBundle("search-widget-events", locale)).thenReturn(resourceBundle);
        when(resourceBundle.getString("title")).thenReturn("Title");
        when(resourceBundle.getString("description")).thenReturn("Description");
        when(resourceBundle.getString("placeholder")).thenReturn("Placeholder");
        when(resourceBundle.getString("button")).thenReturn("Button");

        when(bundle.getAllLabels("search-events-filters", locale))
                .thenReturn(Map.of("music", "Music"));

        Map<String, String> filtersMap = new HashMap<>();
        filtersMap.put("today", "1");
        when(hippoUtilsService.getValueMap("search-events-filters-date")).thenReturn(filtersMap);

        when(bundle.getResourceBundle("search-events-filters-date", "today", locale))
                .thenReturn("Today");

        SearchWidgetModule result = mapper.createModule(document, locale);

        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
        assertEquals("Placeholder", result.getPlaceholder());
        assertEquals("Button", result.getButton());

        assertEquals("events", result.getMainCategory());
        assertNotNull(result.getSubcategories());
        assertNotNull(result.getFilters());

        JsonNode filters = result.getFilters();
        assertTrue(filters.has("when"));
        assertEquals(1, filters.get("when").size());
        assertEquals("1", filters.get("when").get(0).get("id").asText());
        assertEquals("Today", filters.get("when").get(0).get("label").asText());
    }

    @Test
    void shouldCreateModule_ForNonEventsBespoken() {
        Locale locale = Locale.UK;

        when(document.getBespoken()).thenReturn("other");

        when(bundle.getResourceBundle("other", locale)).thenReturn(resourceBundle);
        when(resourceBundle.getString("title")).thenReturn("Title");
        when(resourceBundle.getString("description")).thenReturn("Description");
        when(resourceBundle.getString("placeholder")).thenReturn("Placeholder");
        when(resourceBundle.getString("button")).thenReturn("Button");

        when(bundle.getAllLabels("search-categories", locale))
                .thenReturn(Map.of("stay", "Stay"));

        SearchWidgetModule result = mapper.createModule(document, locale);

        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());

        assertNotNull(result.getCategories());
        assertEquals("Stay", result.getCategories().get("stay"));

        assertNull(result.getFilters());
    }

    @Test
    void shouldCreateFiltersJson_WhenFiltersExist() {
        Locale locale = Locale.UK;

        Map<String, String> filtersMap = Map.of(
                "today", "1",
                "tomorrow", "2"
        );

        when(hippoUtilsService.getValueMap("filters-id")).thenReturn(filtersMap);
        when(bundle.getResourceBundle("filters-id", "today", locale)).thenReturn("Today");
        when(bundle.getResourceBundle("filters-id", "tomorrow", locale)).thenReturn("Tomorrow");

        JsonNode result = mapper.createFiltersJson("filters-id", "when", locale);

        assertNotNull(result);
        assertTrue(result.has("when"));
        assertEquals(2, result.get("when").size());
    }

    @Test
    void shouldCreateFiltersJson_WhenNoFilters() {
        Locale locale = Locale.UK;

        when(hippoUtilsService.getValueMap("filters-id")).thenReturn(null);

        JsonNode result = mapper.createFiltersJson("filters-id", "when", locale);

        assertNotNull(result);
        assertTrue(result.has("when"));
        assertEquals(0, result.get("when").size());
    }
}