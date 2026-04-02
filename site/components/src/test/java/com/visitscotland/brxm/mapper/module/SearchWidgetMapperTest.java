package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.components.content.CludoService;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchWidgetMapperTest {

    private static final String BESPOKEN_EVENTS = "search-widget-events";

    @Mock
    private ResourceBundleService bundleService;

    @Mock
    private HippoUtilsService hippoUtilsService;

    @Mock
    private CludoService cludoService;

    @Mock
    private DevModule document;

    @Mock
    private PageCompositionHelper compositionHelper;

    @Mock
    private ResourceBundle resourceBundle;

    private ObjectMapper objectMapper;

    private SearchWidgetMapper mapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mapper = new SearchWidgetMapper(bundleService, hippoUtilsService, objectMapper, cludoService);
    }

    @Test
    void shouldMapBasicFieldsFromResourceBundle() {
        Locale locale = Locale.UK;

        when(document.getBespoken()).thenReturn("default");
        when(bundleService.getResourceBundle("default", locale)).thenReturn(resourceBundle);

        when(resourceBundle.getString("title")).thenReturn("Title");
        when(resourceBundle.getString("description")).thenReturn("Description");
        when(resourceBundle.getString("placeholder")).thenReturn("Placeholder");
        when(resourceBundle.getString("button")).thenReturn("Button");

        when(bundleService.getAllLabels(anyString(), eq(locale))).thenReturn(Map.of());

        SearchWidgetModule result = mapper.createModule(document, locale, compositionHelper);

        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
        assertEquals("Placeholder", result.getPlaceholder());
        assertEquals("Button", result.getButton());
    }

    @Test
    void shouldMapEventSpecificFieldsWhenBespokenIsEvents() {
        Locale locale = Locale.UK;

        when(document.getBespoken()).thenReturn(BESPOKEN_EVENTS);
        when(bundleService.getResourceBundle(BESPOKEN_EVENTS, locale)).thenReturn(resourceBundle);

        when(resourceBundle.getString(anyString())).thenReturn("value");

        when(hippoUtilsService.getValueMap(anyString())).thenReturn(Map.of());

        when(compositionHelper.addValueListLabels(anyString(), any(), anyString()))
                .thenReturn(Map.of("key", "label"));

        ObjectNode filtersNode = new ObjectMapper().createObjectNode();

        when(cludoService.addFilterJson(anyString(), anyString(), anyString(), any(ObjectNode.class), eq(locale)))
                .thenReturn(filtersNode);

        SearchWidgetModule result = mapper.createModule(document, locale, compositionHelper);

        assertNotNull(result);
        assertEquals("events", result.getMainCategory());
        assertNotNull(result.getSubcategories());
        assertNotNull(result.getFilters());

        verify(hippoUtilsService).getValueMap("vs-events-filters");
        verify(compositionHelper).addValueListLabels(eq("search-events-subcategories"), any(), eq("search-events-filters"));

        verify(cludoService, times(2))
                .addFilterJson(anyString(), anyString(), anyString(), any(ObjectNode.class), eq(locale));
    }

    @Test
    void shouldMapCategoriesWhenNotEvents() {
        Locale locale = Locale.UK;

        when(document.getBespoken()).thenReturn("default");
        when(bundleService.getResourceBundle("default", locale)).thenReturn(resourceBundle);

        when(resourceBundle.getString(anyString())).thenReturn("value");

        Map<String, String> categories = Map.of("cat1", "Category 1");
        when(bundleService.getAllLabels("main-category-filters", locale)).thenReturn(categories);

        SearchWidgetModule result = mapper.createModule(document, locale, compositionHelper);

        assertNotNull(result);
        assertEquals(categories, result.getCategories());

        verify(bundleService).getAllLabels("main-category-filters", locale);
        verifyNoInteractions(cludoService);
    }

    @Test
    void map_shouldDelegateToCreateModule() {
        Locale locale = Locale.UK;

        when(compositionHelper.getLocale()).thenReturn(locale);
        when(document.getBespoken()).thenReturn("default");
        when(bundleService.getResourceBundle(anyString(), eq(locale))).thenReturn(resourceBundle);

        when(resourceBundle.getString(anyString())).thenReturn("value");
        when(bundleService.getAllLabels(anyString(), eq(locale))).thenReturn(Map.of());

        SearchWidgetModule result = null;
        try {
            result = mapper.map(document, compositionHelper);
        } catch (PageCompositionException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(result);

        verify(compositionHelper).getLocale();
    }
}