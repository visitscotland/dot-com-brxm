package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.hippobeans.SharedLink;
import com.visitscotland.brxm.hippobeans.SharedLinkBSH;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SharedLinkExternalValidatorTest {

    private SharedLinkExternalValidator validator;

    @Mock
    private ValidationContext context;

    @Mock
    private Node node;

    @BeforeEach
    void setUp() {
        validator = new SharedLinkExternalValidator();
    }

    @ParameterizedTest
    @CsvSource({",false", "partner-site,true"})
    @DisplayName("Website type Property is mandatory for External Links")
    void testValidate_ExternalLinkWithEmptyWebsiteType(String value, Boolean isValid) throws Exception {
        NodeIterator nodeIterator = mock(NodeIterator.class);
        Node link = mock(Node.class);
        Property websiteTypeProperty = mock(Property.class);

        // Setup: Mock node with an external link and empty `WEBSITE_TYPE`
        when(node.getNodes(SharedLink.LINK_TYPES)).thenReturn(nodeIterator);
        when(nodeIterator.hasNext()).thenReturn(true, false); // One node in the iterator
        when(nodeIterator.nextNode()).thenReturn(link);

        // Mock link node type
        when(link.isNodeType(ExternalLink.PRIMARY_TYPE)).thenReturn(true);

        // Mock the main node to have Website Type property with an empty string
        when(node.hasProperty(SharedLinkBSH.WEBSITE_TYPE)).thenReturn(true);
        when(node.getProperty(SharedLinkBSH.WEBSITE_TYPE)).thenReturn(websiteTypeProperty);
        when(websiteTypeProperty.getString()).thenReturn(value==null ? "" : value);

        // Mock violation creation
        if (!isValid){
            when(context.createViolation()).thenReturn(mock(Violation.class));
        }

        // Execute
        Optional<Violation> result = validator.validate(context, node);

        // Verify
        assertEquals(isValid, result.isEmpty());
    }

    @Test
    @DisplayName("Website type is only mandatory for External Links")
    void testValidate_NoExternalLinks() throws Exception {
        NodeIterator nodeIterator = mock(NodeIterator.class);
        Node link = mock(Node.class);

        // Setup: Mock node with non-external link
        when(node.getNodes(SharedLink.LINK_TYPES)).thenReturn(nodeIterator);
        when(nodeIterator.hasNext()).thenReturn(true, false); // One node in the iterator
        when(nodeIterator.nextNode()).thenReturn(link);

        // Mock link node type
        when(link.isNodeType(ExternalLink.PRIMARY_TYPE)).thenReturn(false);

        // Execute
        Optional<Violation> result = validator.validate(context, node);

        // Verify
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Return violation when RepositoryException is thrown")
    void testValidate_RepositoryException() throws Exception {
        Violation violation = mock(Violation.class);

        // Setup: Throw exception during iteration
        when(node.getNodes(SharedLink.LINK_TYPES)).thenThrow(new RepositoryException());

        // Mock violation creation
        when(context.createViolation()).thenReturn(violation);

        // Execute
        Optional<Violation> result = validator.validate(context, node);

        // Verify
        assertTrue(result.isPresent());
        assertEquals(violation, result.get());
    }
}
