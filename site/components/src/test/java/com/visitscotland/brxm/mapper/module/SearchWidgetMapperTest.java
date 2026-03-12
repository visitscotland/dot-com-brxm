package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchWidgetMapperTest {

    @Mock
    private ResourceBundleService bundle;

    @Mock
    private DevModule document;

    @Mock
    private PageCompositionHelper compositionHelper;

    @InjectMocks
    private SearchWidgetMapper mapper;

    private Locale locale;

    @BeforeEach
    void setup() {
        locale = Locale.UK;
    }

    @Test
    @DisplayName("Should map a standard search widget with correct labels")
    void shouldMapStandardSearchWidget() {

        when(document.getBespoken()).thenReturn("search-widget");

        when(bundle.getResourceBundle("search-widget", "title", locale)).thenReturn("Title");
        when(bundle.getResourceBundle("search-widget", "description", locale)).thenReturn("Description");
        when(bundle.getResourceBundle("search-widget", "placeholder", locale)).thenReturn("Placeholder");
        when(bundle.getResourceBundle("search-widget", "button", locale)).thenReturn("Search");

        when(bundle.getAllLabels("search-categories", locale)).thenReturn(Map.of("food", "Food"));

        SearchWidgetModule module = mapper.createModule(document, locale);

        assertEquals("Title", module.getTitle());
        assertEquals("Description", module.getDescription());
        assertEquals("Placeholder", module.getPlaceholder());
        assertEquals("Search", module.getButton());
        assertNotNull(module.getCategories());
    }

    @Test
    @DisplayName("Should map an events search widget with main category and filters")
    void shouldMapEventsSearchWidget() {

        when(document.getBespoken()).thenReturn("search-widget-events");

        when(bundle.getResourceBundle("search-widget-events", "title", locale)).thenReturn("Events Title");
        when(bundle.getResourceBundle("search-widget-events", "description", locale)).thenReturn("Events Description");
        when(bundle.getResourceBundle("search-widget-events", "placeholder", locale)).thenReturn("Search events");
        when(bundle.getResourceBundle("search-widget-events", "button", locale)).thenReturn("Find");

        when(bundle.getAllLabels("search-events-filters", locale)).thenReturn(Map.of("music", "Music"));

        SearchWidgetModule module = mapper.createModule(document, locale);

        assertEquals("events", module.getMainCategory());
        assertNotNull(module.getSubcategories());
    }

    @Test
    @DisplayName("Map method should use locale from PageCompositionHelper")
    void mapShouldUseLocaleFromCompositionHelper() throws Exception {

        when(compositionHelper.getLocale()).thenReturn(locale);
        when(document.getBespoken()).thenReturn("search-widget");

        when(bundle.getResourceBundle(anyString(), anyString(), any(Locale.class)))
                .thenReturn("value");

        when(bundle.getAllLabels(anyString(), any(Locale.class)))
                .thenReturn(Collections.emptyMap());

        SearchWidgetModule module = mapper.map(document, compositionHelper);

        assertNotNull(module);
        verify(compositionHelper).getLocale();
    }
}