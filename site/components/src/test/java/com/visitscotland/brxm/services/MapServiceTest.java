package com.visitscotland.brxm.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.LocationLoader;
import com.visitscotland.brxm.dms.model.LocationObject;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.MapsModule;
import com.visitscotland.brxm.utils.ContentLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Locale;

import static com.visitscotland.brxm.services.MapService.FEATURE;
import static com.visitscotland.brxm.services.MapService.ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MapServiceTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private HippoUtilsService hippoUtilsService;

    @Mock
    private ContentLogger logger;

    private MapService service;

    @Mock
    ImageMapper imageMapper;
    @Mock
    LinkService linkService;
    @Mock
    LocationLoader locationLoader;
    @Mock
    GeometryViewportService geometryViewportService;
    @Mock
    ResourceBundleService bundle;
    @Mock
    DMSDataService dmsData;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();

        service = new MapService(
                dmsData,
                bundle,
                imageMapper,
                linkService,
                mapper,
                locationLoader,
                hippoUtilsService,
                logger,
                geometryViewportService
        );
    }


    @Test
    @DisplayName("VS-3996 - Create category node for maps json data")
    void buildCategoryNode() {

        ObjectNode result = service.buildCategoryNode("acco", "Accommodation");

        assertNotNull(result);
        assertEquals("acco", result.get(ID).asText());
        assertEquals("Accommodation", result.get(MapService.LABEL).asText());
        assertFalse(result.has(MapService.CMS_DATA));
    }
    @Test
    @DisplayName("buildPageNode with Destination using realistic geometry")
    void testBuildPageNodeDestinationWithGeometry() {
        ObjectMapper om = new ObjectMapper();

        // Mock Destination
        Destination dest = mock(Destination.class);
        when(dest.getTitle()).thenReturn("Title");
        when(dest.getTeaser()).thenReturn("Teaser");
        when(dest.getImage()).thenReturn(null);
        when(dest.getKeys()).thenReturn(new String[]{});
        when(dest.getLocation()).thenReturn("locId");
        when(dest.getCanonicalUUID()).thenReturn("uuid-123");

        // Mock LocationObject
        LocationObject location = mock(LocationObject.class);
        when(location.getLongitude()).thenReturn(-3.0);
        when(location.getLatitude()).thenReturn(55.0);
        when(location.getId()).thenReturn("locId");
        when(location.getName()).thenReturn("LocName");
        when(locationLoader.getLocation("locId", Locale.UK)).thenReturn(location);
        when(locationLoader.getLocation("locId", null)).thenReturn(location);

        // Mock FlatLink
        FlatLink flatLink = new FlatLink("Discover", "/link", LinkType.INTERNAL);
        when(linkService.createSimpleLink(dest, null, Locale.UK)).thenReturn(flatLink);

        // Mock resource bundle
        when(bundle.getResourceBundle(anyString(), anyString(), any(Locale.class))).thenReturn("Discover");

        // Disambiguate ImageMapper overloaded methods
        when(imageMapper.createImage(ArgumentMatchers.<Image>any(), any(), any())).thenReturn(null);
        when(imageMapper.createImage(ArgumentMatchers.<InstagramImage>any(), any(), any())).thenReturn(null);

        // Mock hippoUtilsService
        when(hippoUtilsService.getValueFromList(anyString(), anyString())).thenReturn("placeId123");

        // Mock geometryViewportService
        when(geometryViewportService.extractViewportFromGeometry(any(JsonNode.class)))
                .thenReturn(om.createArrayNode());
        when(geometryViewportService.calculateCenterFromViewport(any(JsonNode.class)))
                .thenReturn(om.createObjectNode());

        // Mock DMSDataService with realistic nested geometry
        ObjectNode geometryNode = om.createObjectNode();
        ObjectNode innerGeometry = om.createObjectNode();
        innerGeometry.put("type", "bounds");
        ArrayNode coords = om.createArrayNode();
        coords.add(om.createArrayNode().add(-4.4165).add(57.37986));
        coords.add(om.createArrayNode().add(-2.80701).add(56.71657));
        innerGeometry.set("coordinates", coords);
        geometryNode.set("geometry", innerGeometry);

        // The buildPageNode expects top-level node with type & coordinates
        when(dmsData.getLocationBorders(anyString(), anyBoolean())).thenReturn(geometryNode.get("geometry"));

        // Create feature and category nodes
        ObjectNode feature = om.createObjectNode();
        ObjectNode category = om.createObjectNode().put("id", "cat").put("label", "Cat");

        // Call the method under test
        service.buildPageNode(Locale.UK, category, null, dest, feature);

        // Assertions
        assertTrue(feature.has("properties"), "Feature must have properties");
        assertTrue(feature.has("geometry"), "Feature must have geometry");

        // Optional: check placeId
        assertEquals("placeId123", feature.get("properties").get(MapService.PLACEID).asText(), "PlaceId must match mock");
    }

    @Test
    @DisplayName("VS-3996 - Create category node with CMS flag")
    void buildCategoryNodeCmsBased() {
        ObjectNode result = service.buildCategoryNode("acco", "Accommodation", true);

        assertNotNull(result);
        assertEquals("acco", result.get(ID).asText());
        assertEquals("Accommodation", result.get(MapService.LABEL).asText());
        assertTrue(result.get(MapService.CMS_DATA).asBoolean());
    }

    @Test
    @DisplayName("Create property node with image and link")
    void getPropertyNode_withImageAndLink() {
        FlatImage image = new FlatImage();
        image.setExternalImage("http://image.url");

        FlatLink link = new FlatLink("Discover", "/link", LinkType.EXTERNAL);

        ObjectNode categoryNode = mapper.createObjectNode().put("id", "testCat").put("label", "Test Cat");

        ObjectNode result = service.getPropertyNode("Title", "Desc", image, categoryNode, link, "123");

        assertNotNull(result);
        assertEquals("Title", result.get("title").asText());
        assertEquals("Desc", result.get("description").asText());
        assertEquals("123", result.get("id").asText());
        assertTrue(result.has("image"));
        assertTrue(result.has("link"));
    }

    @Test
    @DisplayName("Create geometry node")
    void getGeometryNode_test() {

        ArrayNode coords = mapper.createArrayNode();
        coords.add(1.0);
        coords.add(2.0);

        ObjectNode geoNode = service.getGeometryNode(coords, MapService.POINT);

        assertEquals(MapService.POINT, geoNode.get(MapService.TYPE).asText());
        assertEquals(coords, geoNode.get("coordinates"));
    }

    @Test
    @DisplayName("Create coordinates array")
    void getCoordinates_test() {
        ArrayNode result = service.getCoordinates(1.0, 2.0);

        assertEquals(1.0, result.get(0).asDouble());
        assertEquals(2.0, result.get(1).asDouble());
    }
    @Test
    @DisplayName("Add feature places node")
    void addFeaturePlacesNode_test() {
        MapsModule module = new MapsModule();
        MapCategory cat = mock(MapCategory.class);
        when(cat.getTitle()).thenReturn("Title");
        when(cat.getMapPins()).thenReturn(List.of());

        service.addFeaturePlacesNode(module, List.of(cat), Locale.UK, mapper.createArrayNode(), mapper.createArrayNode());

    }

}