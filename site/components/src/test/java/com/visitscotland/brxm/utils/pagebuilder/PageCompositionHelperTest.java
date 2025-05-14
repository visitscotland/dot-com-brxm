package com.visitscotland.brxm.utils.pagebuilder;

import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.hippoecm.hst.core.component.HstRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.visitscotland.brxm.components.content.PageContentComponent.LABELS;
import static com.visitscotland.brxm.services.ResourceBundleService.GLOBAL_BUNDLE_FILE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PageCompositionHelperTest {

    private ResourceBundleService resourceBundleService;
    private HstRequest request;
    private PageCompositionHelper helper;

    @BeforeEach
    void setUp() {
        resourceBundleService = mock(ResourceBundleService.class);
        request = mock(HstRequest.class);
        helper = new PageCompositionHelper(resourceBundleService, request);
    }

    @Test
    @DisplayName("Returns the locale from the request")
    void When_getLocaleIsCalled_then_returnRequestLocale() {
        Locale locale = Locale.UK;
        when(request.getLocale()).thenReturn(locale);

        assertEquals(locale, helper.getLocale());
    }

    @Test
    @DisplayName("Modules are added to the Page Items list")
    void When_moduleIsAdded_then_itIsStoredInModuleList() {
        Module<?> module = mock(Module.class);
        helper.addModule(Optional.of(module));

        assertEquals(1, helper.getModules().size());
        assertSame(module, helper.getModules().get(0));
    }

    @Test
    @DisplayName("The first module of the page is taken from the list")
    void When_severalModules_then_getFirstModuleReturnsEmpty() {
        Module<?> firstModule = mock(Module.class);
        helper.addModule(Optional.of(firstModule));
        helper.addModule(Optional.of(mock(Module.class)));
        helper.addModule(Optional.of(mock(Module.class)));

        assertSame(firstModule, helper.getFirstModule().orElseThrow());
    }

    @Test
    @DisplayName("The page might not contain any module yet")
    void When_noModulesPresent_then_getFirstModuleReturnsEmpty() {
        assertTrue(helper.getFirstModule().isEmpty());
    }

    @Test
    @DisplayName("When addResourceBundle is called, then service is invoked to fetch labels")
    void When_addResourceBundle_then_serviceIsCalled() {
        Locale locale = Locale.ENGLISH;
        when(request.getLocale()).thenReturn(locale);

        helper.addAllSiteLabels( "bundleId");

        verify(resourceBundleService).getAllSiteLabels("bundleId", locale);
        verify(request).setModel(eq(LABELS), any());

    }

    @Test
    @DisplayName("Global labels are stored appended to the request global labels")
    void When_addGlobalLabel_then_storeLabelInGlobalBundle() {
        Map<String, Map<String, String>> labelsMap = new HashMap<>();
        when(request.getModel(LABELS)).thenReturn(labelsMap);
        when(resourceBundleService.getSiteResourceBundle(any(), eq("testKey"), any()))
                .thenReturn("labelValue");
        when(resourceBundleService.getSiteResourceBundle(any(), eq("testKey2"), any()))
                .thenReturn("labelValue2");
        helper.addGlobalLabel("testKey");
        helper.addGlobalLabel("testKey2");

        assertNotNull(labelsMap.get(GLOBAL_BUNDLE_FILE));
        assertEquals(2, labelsMap.get(GLOBAL_BUNDLE_FILE).size());
        assertEquals("labelValue", labelsMap.get(GLOBAL_BUNDLE_FILE).get("testKey"));
        assertEquals("labelValue2", labelsMap.get(GLOBAL_BUNDLE_FILE).get("testKey2"));
    }

    @Test
    @DisplayName("When calculateAlignment is called, then it delegates to composition model")
    void When_calculateAlignment_then_delegateToModel() {
        assertNotNull(helper.calculateAlignment());
    }

    @Test
    @DisplayName("Calculates the Index depending on the increment")
    void When_calculateThemeIndex_then_returnsIndex() {
        assertEquals(0, helper.calculateThemeIndex(true));
        assertEquals(1, helper.calculateThemeIndex(true));
        assertEquals(1, helper.calculateThemeIndex(false));
        assertEquals(2, helper.calculateThemeIndex(true));
    }
}
