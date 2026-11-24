package com.visitscotland.brxm.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

class GeometryViewportServiceTest {

    private GeometryViewportService service;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        service = new GeometryViewportService(mapper);
    }

    @Test
    @DisplayName("Given polygon coordinates calculate viewports")
    void extractViewportFromGeometry_polygon_success() throws Exception {

        String json =
                "{ \"type\": \"Polygon\", " +
                        "\"coordinates\": [" +
                        "  [" +
                        "    [-3.2, 55.9]," +
                        "    [-3.1, 55.9]," +
                        "    [-3.1, 56.0]," +
                        "    [-3.2, 56.0]" +
                        "  ]" +
                        "]" +
                        "}";

        JsonNode geometry = mapper.readTree(json);

        JsonNode result = service.extractViewportFromGeometry(geometry);

        assertNotNull(result);
        assertEquals(55.9, result.get("low").get("latitude").asDouble());
        assertEquals(-3.2, result.get("low").get("longitude").asDouble());
        assertEquals(56.0, result.get("high").get("latitude").asDouble());
        assertEquals(-3.1, result.get("high").get("longitude").asDouble());
    }

    @Test
    @DisplayName("Given multipolygon coordinates calculate viewports")
    void extractViewportFromGeometry_multiPolygon_success() throws Exception {

        String json =
                "{ \"type\": \"MultiPolygon\", " +
                        "\"coordinates\": [" +
                        "  [[[-3.2,55.9],[-3.1,55.9],[-3.1,56.0],[-3.2,56.0]]]," +
                        "  [[[-3.5,55.8],[-3.4,55.8],[-3.4,55.7],[-3.5,55.7]]]" +
                        "]" +
                        "}";

        JsonNode geometry = mapper.readTree(json);

        JsonNode result = service.extractViewportFromGeometry(geometry);

        assertNotNull(result);
        assertEquals(55.7, result.get("low").get("latitude").asDouble());
        assertEquals(-3.5, result.get("low").get("longitude").asDouble());
        assertEquals(56.0, result.get("high").get("latitude").asDouble());
        assertEquals(-3.1, result.get("high").get("longitude").asDouble());
    }

    @Test
    @DisplayName("Get geometry from bounds")
    void extractViewportFromGeometry_bounds_success() throws Exception {

        String json =
                "{ \"type\": \"bounds\", " +
                        "\"coordinates\": [" +
                        "  [-3.2, 55.9]," +
                        "  [-3.1, 56.0]" +
                        "]" +
                        "}";

        JsonNode geometry = mapper.readTree(json);

        JsonNode result = service.extractViewportFromGeometry(geometry);

        assertNotNull(result);
        assertEquals(55.9, result.get("low").get("latitude").asDouble());
        assertEquals(-3.2, result.get("low").get("longitude").asDouble());
        assertEquals(56.0, result.get("high").get("latitude").asDouble());
        assertEquals(-3.1, result.get("high").get("longitude").asDouble());
    }

    @Test
    @DisplayName("Get geometry from no valid bounds")
    void extractViewportFromGeometry_unsupportedType_returnsNull() throws Exception {

        String json =
                "{ \"type\": \"Point\", " +
                        "\"coordinates\": [-3.2, 55.9] }";

        JsonNode geometry = mapper.readTree(json);

        JsonNode result = service.extractViewportFromGeometry(geometry);

        assertNull(result);
    }

    @Test
    @DisplayName("Get geometry from bounds without the node type")
    void extractViewportFromGeometry_missingType_returnsNull() throws Exception {

        String json =
                "{ \"coordinates\": [[-3.2, 55.9]] }";

        JsonNode geometry = mapper.readTree(json);

        JsonNode result = service.extractViewportFromGeometry(geometry);

        assertNull(result);
    }

    @Test
    @DisplayName("Get geometry from bounds, null input")
    void extractViewportFromGeometry_nullInput_returnsNull() {

        JsonNode result = service.extractViewportFromGeometry(null);

        assertNull(result);
    }

    @Test
    @DisplayName("Calculate centre of the map from viewports")
    void calculateCenterFromViewport_success() throws Exception {

        String json =
                "{ \"low\": {\"latitude\": 55.9, \"longitude\": -3.2}, " +
                        "\"high\": {\"latitude\": 56.1, \"longitude\": -3.0} }";

        JsonNode viewport = mapper.readTree(json);

        JsonNode result = service.calculateCenterFromViewport(viewport);

        assertNotNull(result);
        assertEquals(56.0, result.get("latitude").asDouble());
        assertEquals(-3.1, result.get("longitude").asDouble());
    }

    @Test
    @DisplayName("Calculate centre of the map from viewports with missing nodes")
    void calculateCenterFromViewport_missingNodes_returnsNull() throws Exception {

        String json =
                "{ \"low\": {\"latitude\": 55.9} }";

        JsonNode viewport = mapper.readTree(json);

        JsonNode result = service.calculateCenterFromViewport(viewport);

        assertNull(result);
    }

    @Test
    @DisplayName("Calculate centre of the map from viewports with no input")
    void calculateCenterFromViewport_nullInput_returnsNull() {

        JsonNode result = service.calculateCenterFromViewport(null);

        assertNull(result);
    }
}