
package com.visitscotland.brxm.mapper.page;

import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.mapper.module.TransportMapper;
import com.visitscotland.brxm.model.ItineraryPage;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.GoogleMapsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItineraryMapperTest {

    @Mock
    ResourceBundleService bundle;

    @Mock
    DocumentUtilsService documentUtils;

    @Mock
    GoogleMapsService googleMapsService;

    @Mock
    ContentLogger contentLogger;

    @Mock
    TransportMapper transportMapper;

    @Mock
    Itinerary itinerary;

    @InjectMocks
    ItineraryMapper mapper;

    private static final Locale LOCALE = Locale.UK;

    private void mockEmptyItinerary() {
        when(itinerary.getPath()).thenReturn("/content/itinerary/test");
        when(itinerary.getMapLink()).thenReturn(null);
        when(itinerary.getTransports()).thenReturn(null);
        when(itinerary.getSeasons()).thenReturn(null);
        when(itinerary.getLocations()).thenReturn(null);

        when(documentUtils.getAllowedDocuments(itinerary, Day.class))
                .thenReturn(Collections.emptyList());
    }

    @Test
    @DisplayName("Itinerary without days should have zero day count and distance")
    void buildItinerary_noDays() {

        mockEmptyItinerary();

        when(itinerary.getDistance()).thenReturn(0.0);

        ItineraryPage result = mapper.buildItinerary(itinerary, LOCALE);

        assertNotNull(result);
        assertEquals(0, result.getDayCount());
        assertEquals(BigDecimal.ZERO, result.getDistance());

        verify(googleMapsService, never())
                .calculateDistanceFromDays(anyList());

        verify(contentLogger).info(
                "The itinerary page {} does not have any modules published",
                "/content/itinerary/test"
        );
    }

    @Test
    @DisplayName("Calculate itinerary distance when no distance is provided")
    void buildItinerary_calculateDistance() {

        mockEmptyItinerary();

        List<Day> days = Collections.singletonList(mock(Day.class));

        when(documentUtils.getAllowedDocuments(itinerary, Day.class))
                .thenReturn(days);

        when(itinerary.getDistance()).thenReturn(0.0);

        when(googleMapsService.calculateDistanceFromDays(days))
                .thenReturn(BigDecimal.valueOf(125));

        ItineraryPage result = mapper.buildItinerary(itinerary, LOCALE);

        assertNotNull(result);
        assertEquals(1, result.getDayCount());
        assertEquals(BigDecimal.valueOf(125), result.getDistance());

        verify(googleMapsService).calculateDistanceFromDays(days);
    }

    @Test
    @DisplayName("Use itinerary distance when provided by CMS")
    void buildItinerary_useProvidedDistance() {

        mockEmptyItinerary();

        when(itinerary.getDistance()).thenReturn(25.0);

        ItineraryPage result = mapper.buildItinerary(itinerary, LOCALE);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(25.0), result.getDistance());

        verify(googleMapsService, never())
                .calculateDistanceFromDays(anyList());
    }

    @Test
    @DisplayName("Populate itinerary days and day count")
    void buildItinerary_populateDays() {

        mockEmptyItinerary();

        List<Day> days = List.of(
                mock(Day.class),
                mock(Day.class),
                mock(Day.class)
        );

        when(documentUtils.getAllowedDocuments(itinerary, Day.class))
                .thenReturn(days);

        when(itinerary.getDistance()).thenReturn(0.0);

        when(googleMapsService.calculateDistanceFromDays(days))
                .thenReturn(BigDecimal.valueOf(50));

        ItineraryPage result = mapper.buildItinerary(itinerary, LOCALE);

        assertEquals(3, result.getDayCount());
        assertEquals(3, result.getDays().size());

        verify(documentUtils).getAllowedDocuments(itinerary, Day.class);
    }

    @Test
    @DisplayName("Populate empty seasons when no seasons are provided")
    void buildItinerary_noSeasons() {

        mockEmptyItinerary();

        when(itinerary.getDistance()).thenReturn(0.0);

        ItineraryPage result = mapper.buildItinerary(itinerary, LOCALE);

        assertNotNull(result);
        assertNotNull(result.getSeasons());
        assertTrue(result.getSeasons().isEmpty());

        verify(bundle, never())
                .existsResourceBundleKey(anyString(), anyString(), any());
    }

    @Test
    @DisplayName("Populate empty locations when no locations are provided")
    void buildItinerary_noLocations() {

        mockEmptyItinerary();

        when(itinerary.getDistance()).thenReturn(0.0);

        ItineraryPage result = mapper.buildItinerary(itinerary, LOCALE);

        assertNotNull(result);
        assertNotNull(result.getLocations());
        assertTrue(result.getLocations().isEmpty());
    }

    @Test
    @DisplayName("Ignore empty and null locations")
    void buildItinerary_ignoreEmptyLocations() {

        mockEmptyItinerary();

        when(itinerary.getLocations()).thenReturn(
                new String[]{"Edinburgh", "", null, "Glasgow"}
        );

        when(itinerary.getDistance()).thenReturn(0.0);

        ItineraryPage result = mapper.buildItinerary(itinerary, LOCALE);

        assertEquals(
                List.of("Edinburgh", "Glasgow"),
                result.getLocations()
        );
    }

    @Test
    @DisplayName("Populate transports")
    void buildItinerary_populateTransports() {

        mockEmptyItinerary();

        String[] transports = {"car", "train"};

        when(itinerary.getTransports()).thenReturn(transports);
        when(itinerary.getDistance()).thenReturn(0.0);

        mapper.buildItinerary(itinerary, LOCALE);

        verify(transportMapper).getTransports(transports, LOCALE);
    }
}
