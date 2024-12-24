package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.hippobeans.Destination;
import com.visitscotland.brxm.hippobeans.General;
import com.visitscotland.brxm.hippobeans.MapModule;
import com.visitscotland.brxm.utils.HippoUtilsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;
import org.onehippo.taxonomy.api.Category;
import org.onehippo.taxonomy.api.Taxonomy;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapPageValidatorTest {

    private MapPageValidator validator;

    @Mock
    private HippoUtilsService utilsService;

    @BeforeEach
    void setUp() {
        // Mock getUtilsService to return the mocked service
        validator = spy(new MapPageValidator());
        doReturn(utilsService).when(validator).getUtilsService();
    }

    @Test
    @DisplayName("General Pages can only define one Taxonomy")
    void validate_GeneralPageWithMultipleTaxonomyKeys() throws Exception {
        // Setup the mock nodes
        Node node = mock(Node.class, RETURNS_DEEP_STUBS);
        Property property = mock(Property.class);
        ValidationContext context = mock(ValidationContext.class);
        Violation violation = mock(Violation.class);

        when(node.getParent().getParent().getNode("content")).thenReturn(Mockito.mock(Node.class));

        // Mock the utils service to recognize as GeneralPage
        when(utilsService.getDocumentFromNode(any(Node.class), eq(true))).thenReturn(mock(General.class));

        // Mock node properties
        when(node.hasProperty(MapModule.MAP_KEYS)).thenReturn(true);
        when(node.getProperty(MapModule.MAP_KEYS)).thenReturn(property);
        when(property.getValues()).thenReturn(new Value[]{Mockito.mock(Value.class), Mockito.mock(Value.class)}); // More than one key

        // Mock violation creation
        when(context.createViolation(MapPageValidator.VIOLATION_GENERAL_PAGE)).thenReturn(violation);

        // Execute validation
        Optional<Violation> result = validator.validate(context, node);

        // Verify
        assertTrue(result.isPresent());
        assertEquals(violation, result.get());
    }

    @Test
    @DisplayName("General Pages can only refer to parent taxonomy items")
    void validate_GeneralPageWithSingleEmptyChildTaxonomyKey() throws Exception {
        // Setup the mock nodes
        Node node = mock(Node.class, RETURNS_DEEP_STUBS);
        Property property = mock(Property.class);
        Value value = Mockito.mock(Value.class);
        Taxonomy taxonomy = Mockito.mock(Taxonomy.class);
        ValidationContext context = mock(ValidationContext.class);
        Violation violation = mock(Violation.class);

        when(node.getParent().getParent().getNode("content")).thenReturn(Mockito.mock(Node.class));

        // Mock the utils service to recognize as GeneralPage
        when(utilsService.getDocumentFromNode(any(Node.class), eq(true))).thenReturn(mock(General.class));

        // Mock node properties
        when(node.hasProperty(MapModule.MAP_KEYS)).thenReturn(true);
        when(node.getProperty(MapModule.MAP_KEYS)).thenReturn(property);
        when(property.getValues()).thenReturn(new Value[]{value});
        when(value.getString()).thenReturn("taxonomyKey");

        // Mock taxonomy behavior
        when(utilsService.getTaxonomy()).thenReturn(taxonomy);
        when(taxonomy.getCategoryByKey("taxonomyKey")).thenReturn(mock(Category.class));
        when(taxonomy.getCategoryByKey("taxonomyKey").getChildren()).thenReturn(Collections.emptyList());

        // Mock violation creation
        when(context.createViolation(MapPageValidator.VIOLATION_GENERAL_PAGE)).thenReturn(violation);

        // Execute validation
        Optional<Violation> result = validator.validate(context, node);

        // Verify
        assertTrue(result.isPresent());
        assertEquals(violation, result.get());
    }

    //TODO: Should we remove the taxonomy field from destination?
    @Test
    @DisplayName("Taxonomies are not mandatory")
    void validate_NoViolation() throws Exception {
        // Setup the mock nodes
        Node node = mock(Node.class, RETURNS_DEEP_STUBS);
        Property property = mock(Property.class);
        Value value = Mockito.mock(Value.class);
        Taxonomy taxonomy = Mockito.mock(Taxonomy.class);

        when(node.getParent().getParent().getNode("content")).thenReturn(Mockito.mock(Node.class));

        // Mock the utils service to recognize as GeneralPage
        when(utilsService.getDocumentFromNode(any(Node.class), eq(true))).thenReturn(mock(General.class));

        // Mock node properties
        when(node.hasProperty(MapModule.MAP_KEYS)).thenReturn(true);
        when(node.getProperty(MapModule.MAP_KEYS)).thenReturn(property);
        when(property.getValues()).thenReturn(new Value[]{value});
        when(value.getString()).thenReturn("taxonomyKey");

        // Mock taxonomy behavior
        when(utilsService.getTaxonomy()).thenReturn(taxonomy);
        List children = Collections.singletonList(new Object());
        when(taxonomy.getCategoryByKey("taxonomyKey")).thenReturn(Mockito.mock(Category.class));
        when(taxonomy.getCategoryByKey("taxonomyKey").getChildren()).thenReturn(children);

        // Execute validation
        Optional<Violation> result = validator.validate(null, node);

        // Verify
        assertFalse(result.isPresent());
    }

    //TODO: Should we remove the taxonomy field from destination?
    @Test
    @DisplayName("Destination Pages do not allow Taxonomies??")
    void validate_DestinationPageWithTaxonomyKeys() throws Exception {
        // Setup the mock nodes
        Node node = mock(Node.class, RETURNS_DEEP_STUBS);
        Property property = mock(Property.class);
        Value value = Mockito.mock(Value.class);
        ValidationContext context = mock(ValidationContext.class);
        Violation violation = mock(Violation.class);

        when(node.getParent().getParent().getNode("content")).thenReturn(Mockito.mock(Node.class));

        // Mock the utils service to recognize as GeneralPage
        when(utilsService.getDocumentFromNode(any(Node.class), eq(true))).thenReturn(mock(Destination.class));

        // Mock node properties
        when(node.hasProperty(MapModule.MAP_KEYS)).thenReturn(true);
        when(node.getProperty(MapModule.MAP_KEYS)).thenReturn(property);
        when(property.getValues()).thenReturn(new Value[]{value});

        // Mock violation creation
        when(context.createViolation("destinationPage")).thenReturn(violation);

        // Execute validation
        Optional<Violation> result = validator.validate(context, node);

        // Verify
        assertTrue(result.isPresent());
        assertEquals(violation, result.get());
    }

    //TODO: Should we remove the taxonomy field from destination?
    @Test
    @DisplayName("Destination Pages do not allow Taxonomies??")
    void validate_DestinationPageWithTaxonomyKeys_pass() throws Exception {
        // Setup the mock nodes
        Node node = mock(Node.class, RETURNS_DEEP_STUBS);
        Property property = mock(Property.class);

        when(node.getParent().getParent().getNode("content")).thenReturn(Mockito.mock(Node.class));

        // Mock the utils service to recognize as GeneralPage
        when(utilsService.getDocumentFromNode(any(Node.class), eq(true))).thenReturn(mock(Destination.class));

        // Mock node properties
        when(node.hasProperty(MapModule.MAP_KEYS)).thenReturn(true);
        when(node.getProperty(MapModule.MAP_KEYS)).thenReturn(property);
        when(property.getValues()).thenReturn(new Value[]{});

        // Execute validation
        Optional<Violation> result = validator.validate(null, node);

        // Verify
        assertTrue(result.isEmpty());
    }
}
