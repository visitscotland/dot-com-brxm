package com.visitscotland.brxm.jcr;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NodeUtilityTest {
    @Mock
    private Node node;

    @Mock
    private Property property;

    @Test
    void isJcrPropertyBlank_ValidNodeAndPresentStringProperty_False() throws RepositoryException {
        final String jcrPropertyName = "jcr:primaryType";
        final String jcrPropertyValue = "Property Value";

        when(node.getProperty(jcrPropertyName)).thenReturn(property);
        when(property.getString()).thenReturn(jcrPropertyValue);

        final boolean result = NodeUtility.isJcrPropertyBlank(node, jcrPropertyName);

        assertFalse(result);

        verify(node, times(1)).getProperty(eq(jcrPropertyName));
        verify(property, times(1)).getString();
    }

    @Test
    void isJcrPropertyBlank_NullProperty_True() throws RepositoryException {
        final String jcrPropertyName = "jcr:primaryType";

        when(node.getProperty(jcrPropertyName)).thenReturn(property);
        when(property.getString()).thenReturn(null);

        final boolean result = NodeUtility.isJcrPropertyBlank(node, jcrPropertyName);

        assertTrue(result);
    }

    @Test
    void isJcrPropertyBlank_NullNodeParameter_True() throws RepositoryException {
        final boolean result = NodeUtility.isJcrPropertyBlank(null, "jcr:primaryType");
        assertTrue(result);
    }

    @Test
    void isJcrPropertyBlank_NullPropertyNameParameter_True() throws RepositoryException {
        final boolean result = NodeUtility.isJcrPropertyBlank(node, null);
        assertTrue(result);
    }

    @Test
    void isJcrPropertyBlank_GetPropertyThrowsRepositoryException_DelegateToCaller() throws RepositoryException {
        final String jcrPropertyName = "jcr:primaryType";
        final RepositoryException exception = new RepositoryException();

        when(node.getProperty(jcrPropertyName)).thenThrow(exception);

        assertThrows(RepositoryException.class, () -> NodeUtility.isJcrPropertyBlank(node, jcrPropertyName));
        verify(node, times(1)).getProperty(eq(jcrPropertyName));
    }

    @Test
    void isJcrPropertyBlank_GetStringThrowsRepositoryException_DelegateToCaller() throws RepositoryException {
        final String jcrPropertyName = "jcr:primaryType";
        final RepositoryException exception = new RepositoryException();

        when(node.getProperty(jcrPropertyName)).thenReturn(property);
        when(property.getString()).thenThrow(exception);

        assertThrows(RepositoryException.class, () -> NodeUtility.isJcrPropertyBlank(node, jcrPropertyName));
        verify(node, times(1)).getProperty(eq(jcrPropertyName));
        verify(property, times(1)).getString();
    }
}