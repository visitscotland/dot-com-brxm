package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.jcr.NodeUtility;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import org.mockito.Mock;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Property;
import javax.jcr.Node;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventLocationValidatorTest {
    private static final String EXCEPTION_TRANSLATION_VARIATION = "exception";
    private static final String ONLINE_JCR_TYPE = "visitscotland:online";
    private static final String VENUE_JCR_TYPE = "visitscotland:venue";

    @Mock private ValidationContext validationContext;
    @Mock private Property property;
    @Mock private Node node;

    private EventLocationValidator eventLocationValidator;

    @BeforeEach
    void setUp() {
        this.eventLocationValidator = new EventLocationValidator();
    }

    @Test
    void validate_EventNodeWithOnlinePropertyTrue_EmptyOptional() throws RepositoryException {
        when(node.hasProperty(ONLINE_JCR_TYPE)).thenReturn(true);
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(true);

        final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

        assertTrue(result.isEmpty());

        verify(node, times(1)).hasProperty(ONLINE_JCR_TYPE);
        verify(node, times(1)).getProperty(ONLINE_JCR_TYPE);
    }

    @Test
    void validate_EventNodeHasNoOnlineProperty_OptionalOfViolation() throws RepositoryException {
        when(node.hasProperty(ONLINE_JCR_TYPE)).thenReturn(false);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION)).thenReturn(mock(Violation.class));

        final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

        assertTrue(result.isPresent());

        verify(node, times(1)).hasProperty(eq(ONLINE_JCR_TYPE));
        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }

    @Test
    void validate_EventNodeWithOnlinePropertyFalseAndVenuePropertyPresent_EmptyOptional() throws RepositoryException {
        when(node.hasProperty(ONLINE_JCR_TYPE)).thenReturn(true);
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(false);

        try(final MockedStatic<NodeUtility> mockedStatic = mockStatic(NodeUtility.class)) {
            when(node.hasProperty(VENUE_JCR_TYPE)).thenReturn(true);
            mockedStatic.when(() -> NodeUtility.isJcrPropertyBlank(node, VENUE_JCR_TYPE)).thenReturn(false);

            final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

            assertTrue(result.isEmpty());

            verify(node, times(1)).hasProperty(eq(ONLINE_JCR_TYPE));
            verify(node, times(1)).getProperty(eq(ONLINE_JCR_TYPE));
            mockedStatic.verify(() -> NodeUtility.isJcrPropertyBlank(eq(node), eq(VENUE_JCR_TYPE)), times(1));
        }
    }

    @Test
    void validate_EventNodeWithOnlinePropertyFalseAndNoVenueProperty_EmptyOptional() throws RepositoryException {
        when(node.hasProperty(ONLINE_JCR_TYPE)).thenReturn(true);
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(false);
        when(node.hasProperty(VENUE_JCR_TYPE)).thenReturn(false);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION)).thenReturn(mock(Violation.class));

        final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

        assertTrue(result.isPresent());

        verify(node, times(1)).hasProperty(eq(VENUE_JCR_TYPE));
        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }

    @Test
    void validate_EventNodeWithOnlinePropertyFalseAndVenuePropertyIsBlank_OptionalOfViolation() throws RepositoryException {
        when(node.hasProperty(ONLINE_JCR_TYPE)).thenReturn(true);
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(false);

        try(final MockedStatic<NodeUtility> mockedStatic = mockStatic(NodeUtility.class)) {
            when(node.hasProperty(VENUE_JCR_TYPE)).thenReturn(true);
            mockedStatic.when(() -> NodeUtility.isJcrPropertyBlank(node, VENUE_JCR_TYPE)).thenReturn(true);
            when(validationContext.createViolation()).thenReturn(mock(Violation.class));

            final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

            assertTrue(result.isPresent());

            verify(validationContext, times(1)).createViolation();
        }
    }
}