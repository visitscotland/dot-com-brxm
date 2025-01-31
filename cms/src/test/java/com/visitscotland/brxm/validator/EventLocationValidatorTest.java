package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.jcr.NodeUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventLocationValidatorTest {
    @Mock private Node node;
    @Mock private Property property;
    @Mock private ValidationContext validationContext;
    private EventLocationValidator eventLocationValidator;

    private static final String VENUE_JCR_TYPE = "visitscotland:venue";
    private static final String ONLINE_JCR_TYPE = "visitscotland:online";

    @BeforeEach
    void setUp() {
        this.eventLocationValidator = new EventLocationValidator();
    }

    @Test
    void validate_OnlineEvent_EmptyOptional() throws RepositoryException {
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(true);

        try(final MockedStatic<NodeUtility> mockedStatic = mockStatic(NodeUtility.class)) {
            mockedStatic.when(() -> NodeUtility.isJcrPropertyBlank(node, VENUE_JCR_TYPE)).thenReturn(false);

            final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

            assertTrue(result.isEmpty());
            verify(node, times(1)).getProperty(eq(ONLINE_JCR_TYPE));
            verify(property, times(1)).getBoolean();
        }
    }

    @Test
    void validate_OfflineEventWithVenue_EmptyOptional() throws RepositoryException {
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(false);

        try(final MockedStatic<NodeUtility> mockedStatic = mockStatic(NodeUtility.class)) {
            mockedStatic.when(() -> NodeUtility.isJcrPropertyBlank(node, VENUE_JCR_TYPE)).thenReturn(false);

            final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

            assertTrue(result.isEmpty());
            verify(node, times(1)).getProperty(eq(ONLINE_JCR_TYPE));
            verify(property, times(1)).getBoolean();
        }
    }

    @Test
    void validate_OfflineEventWithBlankVenue_OptionalOfViolation() throws RepositoryException {
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(false);
        when(validationContext.createViolation()).thenReturn(mock(Violation.class));

        try(final MockedStatic<NodeUtility> mockedStatic = mockStatic(NodeUtility.class)) {
            mockedStatic.when(() -> NodeUtility.isJcrPropertyBlank(node, VENUE_JCR_TYPE)).thenReturn(true);

            final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

            assertTrue(result.isPresent());
            verify(validationContext, times(1)).createViolation();
        }
    }

    @Test
    void validate_IsJcrPropertyBlankThrowsRepositoryException_OptionalOfViolation() {
        final RepositoryException repositoryException = new RepositoryException();
        final String exceptionTranslationVariant = "exception";

        when(validationContext.createViolation(exceptionTranslationVariant))
            .thenReturn(mock(Violation.class));

        try(final MockedStatic<NodeUtility> mockedStatic = mockStatic(NodeUtility.class)) {
            mockedStatic.when(() -> NodeUtility.isJcrPropertyBlank(node, VENUE_JCR_TYPE))
                .thenThrow(repositoryException);

            final Optional<Violation> result = eventLocationValidator.validate(validationContext, node);

            assertTrue(result.isPresent());
            verify(validationContext, times(1)).createViolation(eq(exceptionTranslationVariant));
        }
    }

    @Test
    void isEventOnline_OnlinePropertyIsFalse_False() throws RepositoryException {
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(false);

        boolean result = eventLocationValidator.isEventOnline(node);

        assertFalse(result);
        verify(node, times(1)).getProperty(eq(ONLINE_JCR_TYPE));
    }

    @Test
    void isEventOnline_OnlinePropertyIsTrue_True() throws RepositoryException {
        when(node.getProperty(ONLINE_JCR_TYPE)).thenReturn(property);
        when(property.getBoolean()).thenReturn(true);

        boolean result = eventLocationValidator.isEventOnline(node);

        assertTrue(result);
        verify(node, times(1)).getProperty(eq(ONLINE_JCR_TYPE));
    }

    @Test
    void isEventOnline_RepositoryException_DelegatesToCaller() throws RepositoryException {
        final RepositoryException exception = new RepositoryException();

        when(node.getProperty(ONLINE_JCR_TYPE)).thenThrow(exception);

        assertThrows(RepositoryException.class, () -> eventLocationValidator.isEventOnline(node));
        verify(node, times(1)).getProperty(eq(ONLINE_JCR_TYPE));
        verify(property, never()).getBoolean();
    }
}