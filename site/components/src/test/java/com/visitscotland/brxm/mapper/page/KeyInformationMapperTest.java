package com.visitscotland.brxm.mapper.page;
import com.visitscotland.brxm.hippobeans.DestinationsKeyCategories;
import com.visitscotland.brxm.hippobeans.DestinationsKeyInformation;
import com.visitscotland.brxm.model.DestinationKeyCategory;
import com.visitscotland.brxm.model.DestinationKeyInformationModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeyInformationMapperTest {

    private static final String BUNDLE_NAME =
            "destinations-key-information-categories";

    private static final Locale LOCALE = Locale.ENGLISH;

    @Mock
    private Logger contentLogger;

    @Mock
    private LinkService linkService;

    @Mock
    private ResourceBundleService resourceBundleService;

    @Mock
    private DestinationsKeyInformation keyInformation;

    @Mock
    private DestinationsKeyCategories highlight;

    private KeyInformationMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new KeyInformationMapper(
                contentLogger,
                linkService,
                resourceBundleService
        );
    }

    @Test
    @DisplayName("Should map the title and highlights with translated category labels")
    void shouldMapTitleAndHighlights() {
        when(keyInformation.getTitle()).thenReturn("Dundee");
        when(keyInformation.getHighlights()).thenReturn(List.of(highlight));
        when(highlight.getCategory()).thenReturn("getting-there");
        HippoHtml copy = mock(HippoHtml.class);
        when(highlight.getCopy()).thenReturn(copy);

        when(resourceBundleService.getResourceBundle(
                BUNDLE_NAME, "getting-there", LOCALE
        )).thenReturn("Getting there");

        DestinationKeyInformationModule result =
                mapper.getKeyInformationModule(keyInformation, LOCALE);

        assertEquals("Dundee", result.getTitle());
        assertEquals(1, result.getHighlights().size());

        DestinationKeyCategory category = result.getHighlights().get(0);

        assertEquals("getting-there", category.getCategory().getKey());
        assertEquals("Getting there", category.getCategory().getValue());
        assertSame(copy, category.getCopy());

        verify(resourceBundleService).getResourceBundle(
                BUNDLE_NAME, "getting-there", LOCALE
        );
        verifyNoInteractions(linkService);
    }

    @Test
    @DisplayName("Should use the category key as fallback when the translation is missing")
    void shouldFallbackToCategoryKeyWhenTranslationIsMissing() {
        when(keyInformation.getTitle()).thenReturn("Dundee");
        when(keyInformation.getHighlights()).thenReturn(List.of(highlight));
        when(highlight.getCategory()).thenReturn("accessibility");
        when(highlight.getCopy()).thenReturn(mock(HippoHtml.class));

        when(resourceBundleService.getResourceBundle(
                BUNDLE_NAME, "accessibility", LOCALE
        )).thenReturn(null);

        DestinationKeyInformationModule result =
                mapper.getKeyInformationModule(keyInformation, LOCALE);

        DestinationKeyCategory category = result.getHighlights().get(0);

        assertEquals("accessibility", category.getCategory().getKey());
        assertEquals("accessibility", category.getCategory().getValue());

        verify(contentLogger).error(
                "Destination Travel Information , the category  {} does not contain key {}",
                BUNDLE_NAME,
                "accessibility"
        );
    }

    @Test
    @DisplayName("Should map multiple highlights with their respective translated category labels")
    void shouldMapMultipleHighlights() {
        DestinationsKeyCategories secondHighlight =
                mock(DestinationsKeyCategories.class);

        when(keyInformation.getTitle()).thenReturn("Dundee");
        when(keyInformation.getHighlights())
                .thenReturn(List.of(highlight, secondHighlight));

        when(highlight.getCategory()).thenReturn("getting-there");
        when(highlight.getCopy()).thenReturn(mock(HippoHtml.class));

        when(secondHighlight.getCategory()).thenReturn("accessibility");
        when(secondHighlight.getCopy()).thenReturn(mock(HippoHtml.class));

        when(resourceBundleService.getResourceBundle(
                BUNDLE_NAME, "getting-there", LOCALE
        )).thenReturn("Getting there");

        when(resourceBundleService.getResourceBundle(
                BUNDLE_NAME, "accessibility", LOCALE
        )).thenReturn("Accessibility");

        DestinationKeyInformationModule result =
                mapper.getKeyInformationModule(keyInformation, LOCALE);

        assertEquals(2, result.getHighlights().size());
        assertEquals(
                "Getting there",
                result.getHighlights().get(0).getCategory().getValue()
        );
        assertEquals(
                "Accessibility",
                result.getHighlights().get(1).getCategory().getValue()
        );
    }

    @Test
    @DisplayName("Should return an empty highlights list when no highlights are configured")
    void shouldReturnEmptyHighlightsWhenNoneAreConfigured() {
        when(keyInformation.getTitle()).thenReturn("Dundee");
        when(keyInformation.getHighlights()).thenReturn(List.of());

        DestinationKeyInformationModule result =
                mapper.getKeyInformationModule(keyInformation, LOCALE);

        assertEquals("Dundee", result.getTitle());
        assertNotNull(result.getHighlights());
        assertTrue(result.getHighlights().isEmpty());

        verifyNoInteractions(resourceBundleService, linkService);
    }
}