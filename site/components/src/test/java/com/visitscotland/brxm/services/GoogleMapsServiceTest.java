package com.visitscotland.brxm.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoogleMapsServiceTest {

    GoogleMapsService googleMapsService;

    List<String> urlsToTest;

    @BeforeEach
    void init() {
        urlsToTest = new ArrayList<>();
        googleMapsService = new GoogleMapsService();
    }

    @Test
    @DisplayName("VS-966 - Parse valid URLs")
    void testRegularUrls() {

        urlsToTest.add("https://www.google.com/maps/place/Edinburgh+Castle/@55.9485947,-3.1999135,17z/...");
        urlsToTest.add("https://www.google.com/maps/place/Holyrood+Palace/@55.9527168,-3.1748473,17z/...");
        urlsToTest.add("https://www.google.com/maps/place/Hermitage+of+Braid/@55.9351506,-3.1867314,12.92z/...");

        final String directionsUrl = googleMapsService.getDirectionsUrl(urlsToTest);

        assertNotNull(directionsUrl);

        // should contain 3 coordinates
        assertEquals("https://www.google.com/maps/dir/55.9485947,-3.1999135/55.9527168,-3.1748473/55.9351506,-3.1867314/", directionsUrl);

    }

    @Test
    @DisplayName("VS-966 - Handle invalid URLs")
    void invalidUrl_skipped() {

        urlsToTest.add("https://www.goggle.com/maps/place/Hermitage+of+Braid/@55.9351506,-3.1867314,12.92z/...");

        final String directionsUrl = googleMapsService.getDirectionsUrl(urlsToTest);

        assertNull(directionsUrl);

        urlsToTest.add("https://www.google.com/maps/place/Edinburgh+Castle/@55.9485947,-3.1999135,17z/...");
        urlsToTest.add("https://www.google.com/maps/place/Holyrood+Palace/@55.9527168,-3.1748473,17z/...");

        final String directionsUrl2 = googleMapsService.getDirectionsUrl(urlsToTest);

        assertNotNull(directionsUrl2);
        // should contain 2 coordinates from the 2 good links
        assertEquals("https://www.google.com/maps/dir/55.9485947,-3.1999135/55.9527168,-3.1748473/", directionsUrl2);

    }

    @Test
    @DisplayName("VS-966 - Return null for empty URL list")
    void emptyList_returnsNull() {
        final String directionsUrl = googleMapsService.getDirectionsUrl(List.of());
        assertNull(directionsUrl);
    }

}