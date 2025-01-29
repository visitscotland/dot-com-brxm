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

    private static final String JCR_PROPERTY_NAME = "jcr:primaryType";

    @Test
    void isJcrPropertyBlank_ValidNodeAndPresentStringProperty_False() throws RepositoryException {
        final String jcrPropertyValue = "Property Value";

        when(node.getProperty(JCR_PROPERTY_NAME)).thenReturn(property);
        when(property.getString()).thenReturn(jcrPropertyValue);

        final boolean result = NodeUtility.isJcrPropertyBlank(node, JCR_PROPERTY_NAME);

        assertFalse(result);

        verify(node, times(1)).getProperty(eq(JCR_PROPERTY_NAME));
        verify(property, times(1)).getString();
    }

    @Test
    void isJcrPropertyBlank_NullProperty_True() throws RepositoryException {
        when(node.getProperty(JCR_PROPERTY_NAME)).thenReturn(property);
        when(property.getString()).thenReturn(null);

        final boolean result = NodeUtility.isJcrPropertyBlank(node, JCR_PROPERTY_NAME);

        assertTrue(result);
    }

    @Test
    void isJcrPropertyBlank_NullNodeParameter_True() throws RepositoryException {
        final boolean result = NodeUtility.isJcrPropertyBlank(null, JCR_PROPERTY_NAME);
        assertTrue(result);
    }

    @Test
    void isJcrPropertyBlank_NullPropertyNameParameter_True() throws RepositoryException {
        final boolean result = NodeUtility.isJcrPropertyBlank(node, null);
        assertTrue(result);
    }

    @Test
    void isJcrPropertyBlank_GetPropertyThrowsRepositoryException_DelegateToCaller() throws RepositoryException {
        final RepositoryException exception = new RepositoryException();

        when(node.getProperty(JCR_PROPERTY_NAME)).thenThrow(exception);

        assertThrows(RepositoryException.class, () -> NodeUtility.isJcrPropertyBlank(node, JCR_PROPERTY_NAME));
        verify(node, times(1)).getProperty(eq(JCR_PROPERTY_NAME));
    }

    @Test
    void isJcrPropertyBlank_GetStringThrowsRepositoryException_DelegateToCaller() throws RepositoryException {
        final RepositoryException exception = new RepositoryException();

        when(node.getProperty(JCR_PROPERTY_NAME)).thenReturn(property);
        when(property.getString()).thenThrow(exception);

        assertThrows(RepositoryException.class, () -> NodeUtility.isJcrPropertyBlank(node, JCR_PROPERTY_NAME));
        verify(node, times(1)).getProperty(eq(JCR_PROPERTY_NAME));
        verify(property, times(1)).getString();
    }
}