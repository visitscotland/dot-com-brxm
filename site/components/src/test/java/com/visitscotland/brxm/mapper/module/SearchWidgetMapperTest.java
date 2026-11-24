package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.components.content.service.CludoService;
import com.visitscotland.brxm.hippobeans.SearchWidget;
import com.visitscotland.brxm.model.SearchWidgetModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchWidgetMapperTest {

    @Mock
    private ResourceBundleService bundle;

    @Mock
    private HippoUtilsService hippoUtilsService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private CludoService cludoService;

    @Mock
    private PageCompositionHelper compositionHelper;

    @Mock
    private SearchWidget document;

    @InjectMocks
    private SearchWidgetMapper mapper;

    private final Locale locale = Locale.UK;

    @BeforeEach
    void setUp() {
        when(compositionHelper.getLocale()).thenReturn(locale);
    }

    @Test
    void shouldMapBasicFields() {
        when(document.getType()).thenReturn("standard");
        when(document.getTitle()).thenReturn("Title");
        HippoHtml hippoHtml = mock(HippoHtml.class);
        when(hippoHtml.getContent()).thenReturn("Intro");
        when(document.getCopy()).thenReturn(hippoHtml);
        when(document.getPlaceholder()).thenReturn("Search here");
        when(document.getCtaLabel()).thenReturn("Go");
        when(bundle.getAllLabels(anyString(), eq(locale))).thenReturn(Map.of("key", "value"));
        when(cludoService.isSearchResultsPage()).thenReturn(false);

        SearchWidgetModule result;
        try {
            result = mapper.map(document, compositionHelper);
        } catch (PageCompositionException e) {
            throw new RuntimeException(e);
        }

        assertEquals("Title", result.getTitle());
        HippoHtml intro = result.getIntroduction();
        assertNotNull(intro);
        assertEquals("Intro", intro.getContent());
        assertEquals("Search here", result.getPlaceholder());
        assertEquals("Go", result.getButton());
        assertNotNull(result.getCategories());
    }

    @Test
    void shouldHandleEventTypeWidget() {
        when(document.getType()).thenReturn("search-widget-events");
        when(document.getTitle()).thenReturn("Events");

        ObjectNode filtersNode = mock(ObjectNode.class);

        when(objectMapper.createObjectNode()).thenReturn(filtersNode);
        when(hippoUtilsService.getValueMap(anyString())).thenReturn(Map.of());
        when(compositionHelper.addValueListLabels(anyString(), any(), anyString()))
                .thenReturn(Map.of("sub", "value"));

        when(cludoService.addFilterJson(anyString(), anyString(), anyString(), any(), eq(locale)))
                .thenReturn(filtersNode);

        SearchWidgetModule result;
        try {
            result = mapper.map(document, compositionHelper);
        } catch (PageCompositionException e) {
            throw new RuntimeException(e);
        }

        assertEquals("events", result.getMainCategory());
        assertNotNull(result.getFilters());
        assertNotNull(result.getSubcategories());

        verify(cludoService, atLeastOnce())
                .addFilterJson(anyString(), anyString(), anyString(), any(), eq(locale));
    }

    @Test
    void shouldAddEventFiltersWhenSearchResultsPage() {
        when(document.getType()).thenReturn("standard");
        when(bundle.getAllLabels(anyString(), eq(locale))).thenReturn(Map.of());
        when(cludoService.isSearchResultsPage()).thenReturn(true);

        ObjectNode filtersNode = mock(ObjectNode.class);

        when(objectMapper.createObjectNode()).thenReturn(filtersNode);
        when(hippoUtilsService.getValueMap(anyString())).thenReturn(Map.of());
        when(compositionHelper.addValueListLabels(anyString(), any(), anyString()))
                .thenReturn(Map.of());

        when(cludoService.addFilterJson(anyString(), anyString(), anyString(), any(), eq(locale)))
                .thenReturn(filtersNode);

        SearchWidgetModule result;
        try {
            result = mapper.map(document, compositionHelper);
        } catch (PageCompositionException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(result.getFilters());
        verify(cludoService).isSearchResultsPage();
    }

    @Test
    void shouldNotAddEventFiltersWhenNotSearchResultsPage() {
        when(document.getType()).thenReturn("standard");
        when(bundle.getAllLabels(anyString(), eq(locale))).thenReturn(Map.of());
        when(cludoService.isSearchResultsPage()).thenReturn(false);

        SearchWidgetModule result;
        try {
            result = mapper.map(document, compositionHelper);
        } catch (PageCompositionException e) {
            throw new RuntimeException(e);
        }

        assertNull(result.getFilters());
        verify(cludoService, never())
                .addFilterJson(anyString(), anyString(), anyString(), any(), any());
    }
}