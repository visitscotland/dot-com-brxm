package com.visitscotland.brxm.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.model.Coordinates;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.ItineraryPage;
import com.visitscotland.brxm.model.Viewports;
import com.visitscotland.utils.CoordinateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * service class for using the google geometry viewport service maps place and directions urls
 */
@Component
public class GeometryViewportService {

    private static final Logger logger = LoggerFactory.getLogger(GeometryViewportService.class);
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String LOW = "low";
    private static final String HIGH = "high";
    private final ObjectMapper mapper;

    /**
     * Constructs the service with the required {@link ObjectMapper}.
     *
     * @param mapper the Jackson object mapper used to build JSON nodes
     */
    public GeometryViewportService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Extracts a viewport (bounding box) from a geometry JSON node.
     *
     * <p>Supported geometry types:
     * <ul>
     *     <li>polygon</li>
     *     <li>multipolygon</li>
     *     <li>bounds</li>
     * </ul>
     *
     * <p>The resulting viewport contains:
     * <ul>
     *     <li>low → southwest corner (min latitude, min longitude)</li>
     *     <li>high → northeast corner (max latitude, max longitude)</li>
     * </ul>
     *
     * @param geometryNode the geometry JSON node containing type and coordinates
     * @return a JSON node representing the viewport, or {@code null} if invalid or unsupported
     */
    public JsonNode extractViewportFromGeometry(JsonNode geometryNode) {
        if (geometryNode == null || geometryNode.isEmpty()) {
            logger.warn("Empty geometry node provided");
            return null;
        }

        try {
            JsonNode typeNode = geometryNode.get("type");
            if (typeNode == null) {
                logger.warn("Geometry node missing 'type' field");
                return null;
            }

            String type = typeNode.asText();
            JsonNode coordinates = geometryNode.get("coordinates");

            if (coordinates == null) {
                logger.warn("Geometry node missing 'coordinates' field");
                return null;
            }

            Viewports viewports = new Viewports();

            switch (type.toLowerCase()) {
                case "polygon":
                    processPolygon(coordinates, viewports);
                    break;
                case "multipolygon":
                    processMultiPolygon(coordinates, viewports);
                    break;
                case "bounds":
                    processSimpleCoordinates(coordinates, viewports);
                    break;
                default:
                    logger.warn("Unsupported geometry type: {}", type);
                    return null;
            }

            if (!viewports.hasValidValues()) {
                logger.warn("No valid coordinates found");
                return null;
            }

            return buildViewportNode(viewports);

        } catch (Exception e) {
            logger.error("Error extracting viewport", e);
            return null;
        }
    }

    /**
     * Calculates the geographic center from a viewport node.
     *
     * <p>The center is calculated as the midpoint between
     * the southwest (low) and northeast (high) coordinates.
     *
     * @param viewportNode JSON node containing viewport information
     * @return JsonNode with "latitude" and "longitude", or {@code null} if invalid
     */
    public JsonNode calculateCenterFromViewport(JsonNode viewportNode) {
        if (viewportNode == null || viewportNode.isEmpty()) {
            logger.warn("Empty viewport node provided");
            return null;
        }

        try {
            Viewports bounds = extractBoundsFromViewport(viewportNode);
            if (bounds == null) {
                return null;
            }

            return buildCenterNode(bounds);

        } catch (Exception e) {
            logger.error("Error calculating center from viewport", e);
            return null;
        }
    }

    /**
     * Processes a Polygon geometry and updates viewport boundaries.
     *
     * <p>Only the outer ring (coordinates[0]) is considered.
     *
     * @param coordinates polygon coordinate array
     * @param viewports   the viewport accumulator object
     */
    private void processPolygon(JsonNode coordinates, Viewports viewports) {
        for (JsonNode point : coordinates.get(0)) {
            updateViewports(point, viewports);
        }
    }

    /**
     * Processes a MultiPolygon geometry and updates viewport boundaries.
     *
     * @param coordinates multipolygon coordinate structure
     * @param viewports   the viewport accumulator object
     */
    private void processMultiPolygon(JsonNode coordinates, Viewports viewports) {
        for (JsonNode polygon : coordinates) {
            for (JsonNode ring : polygon) {
                for (JsonNode point : ring) {
                    updateViewports(point, viewports);
                }
            }
        }
    }

    /**
     * Processes simple coordinate arrays such as bounds.
     *
     * @param coordinates coordinate array
     * @param viewports   the viewport accumulator object
     */
    private void processSimpleCoordinates(JsonNode coordinates, Viewports viewports) {
        for (JsonNode point : coordinates) {
            updateViewports(point, viewports);
        }
    }

    /**
     * Updates the viewport boundaries using a single coordinate point.
     *
     * <p>The point must follow GeoJSON format:
     * [longitude, latitude]
     *
     * @param point     JSON array containing longitude and latitude
     * @param viewports the viewport accumulator object
     */
    private void updateViewports(JsonNode point, Viewports viewports) {
        if (point == null || !point.isArray() || point.size() < 2) {
            logger.warn("Invalid coordinate point structure");
            return;
        }

        JsonNode lngNode = point.get(0);
        JsonNode latNode = point.get(1);

        if (!lngNode.isNumber() || !latNode.isNumber()) {
            logger.warn("Invalid coordinate point values");
            return;
        }

        viewports.update(latNode.doubleValue(), lngNode.doubleValue());
    }

    /**
     * Builds a viewport JSON node from calculated boundaries.
     *
     * @param viewports the calculated viewport bounds
     * @return JSON node containing low and high coordinate objects
     */
    private JsonNode buildViewportNode(Viewports viewports) {
        ObjectNode viewportNode = mapper.createObjectNode();

        ObjectNode lowNode = mapper.createObjectNode();
        lowNode.put(LATITUDE, viewports.getMinLat());
        lowNode.put(LONGITUDE, viewports.getMinLng());

        ObjectNode highNode = mapper.createObjectNode();
        highNode.put(LATITUDE, viewports.getMaxLat());
        highNode.put(LONGITUDE, viewports.getMaxLng());

        viewportNode.set(LOW, lowNode);
        viewportNode.set(HIGH, highNode);

        return viewportNode;
    }

    /**
     * Extracts boundary values from a viewport JSON node.
     *
     * @param viewportNode JSON node containing low/high structure
     * @return a populated {@link Viewports} object, or {@code null} if invalid
     */
    private Viewports extractBoundsFromViewport(JsonNode viewportNode) {
        JsonNode low = viewportNode.get("low");
        JsonNode high = viewportNode.get("high");

        if (low == null || high == null) {
            logger.warn("Viewport missing low/high nodes");
            return null;
        }

        Viewports viewports = new Viewports();
        viewports.setMinLat(low.get(LATITUDE).asDouble());
        viewports.setMinLng(low.get(LONGITUDE).asDouble());
        viewports.setMaxLat(high.get(LATITUDE).asDouble());
        viewports.setMaxLng(high.get(LONGITUDE).asDouble());

        return viewports;
    }

    /**
     * Builds a JSON node representing the geographic center of a viewport.
     *
     * @param bounds the viewport boundaries
     * @return JSON node containing center latitude and longitude
     */
    private JsonNode buildCenterNode(Viewports bounds) {
        double centerLat = (bounds.getMinLat() + bounds.getMaxLat()) / 2;
        double centerLng = (bounds.getMinLng() + bounds.getMaxLng()) / 2;

        ObjectNode centerNode = mapper.createObjectNode();
        centerNode.put(LATITUDE, centerLat);
        centerNode.put(LONGITUDE, centerLng);

        return centerNode;
    }
}