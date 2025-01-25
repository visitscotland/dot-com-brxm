package com.visitscotland.brxm.validator;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Answers;
import org.mockito.Mock;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Node;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventLocationValidatorTest {
    private static final String VISIT_SCOTLAND_ONLINE_JCR_TYPE="visitscotland:online";
    private static final String VISIT_SCOTLAND_VENUE_JCR_TYPE="visitscotland:venue";

    @Mock
    private ValidationContext validationContext;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Node node;

    private EventLocationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new EventLocationValidator();
    }

    @Test
    void validate_shouldPassValidationWhenEventIsOnline() throws RepositoryException {
        when(node.getProperty(VISIT_SCOTLAND_ONLINE_JCR_TYPE).getBoolean()).thenReturn(true);

        final Optional<Violation> result = validator.validate(validationContext, node);

        assertTrue(result.isEmpty());

        verify(node, never()).getProperty(eq(VISIT_SCOTLAND_VENUE_JCR_TYPE));
    }

    @Test
    void validate_shouldPassValidationWhenEventHasVenue() throws RepositoryException {
        final String venue = "Albert Hall";

        when(node.getProperty(VISIT_SCOTLAND_ONLINE_JCR_TYPE).getBoolean()).thenReturn(false);
        when(node.getProperty(VISIT_SCOTLAND_VENUE_JCR_TYPE).getString()).thenReturn(venue);

        final Optional<Violation> result = validator.validate(validationContext, node);

        assertTrue(result.isEmpty());

        verify(node, times(4)).getProperty(anyString());
    }

    @Test
    void validate_shouldFailValidationWhenOfflineEventHasNoVenue() throws RepositoryException {
        final Violation violation = mock(Violation.class);

        when(node.getProperty(VISIT_SCOTLAND_ONLINE_JCR_TYPE).getBoolean()).thenReturn(false);
        when(node.getProperty(VISIT_SCOTLAND_VENUE_JCR_TYPE).getString()).thenReturn(null);
        when(validationContext.createViolation()).thenReturn(violation);

        final Optional<Violation> result = validator.validate(validationContext, node);

        assertEquals(Optional.of(violation), result);

        verify(validationContext, times(1)).createViolation();
    }

    @Test
    void validate_shouldFailValidationWhenOfflineEventHasBlankVenue() throws RepositoryException {
        final Violation violation = mock(Violation.class);

        when(node.getProperty(VISIT_SCOTLAND_ONLINE_JCR_TYPE).getBoolean()).thenReturn(false);
        when(node.getProperty(VISIT_SCOTLAND_VENUE_JCR_TYPE).getString()).thenReturn("   ");
        when(validationContext.createViolation()).thenReturn(violation);

        final Optional<Violation> result = validator.validate(validationContext, node);

        assertEquals(Optional.of(violation), result);

        verify(validationContext, times(1)).createViolation();
    }

    @Test
    void validate_shouldFailValidationWhenRepositoryExceptionIsThrownAndViolationIsReturned() throws RepositoryException {
        final Violation violation = mock(Violation.class);
        final RepositoryException exception = new RepositoryException();

        when(node.getProperty(VISIT_SCOTLAND_ONLINE_JCR_TYPE).getBoolean())
            .thenThrow(exception);
        when(validationContext.createViolation("exception"))
            .thenReturn(violation);

        assertEquals(Optional.of(violation), validator.validate(validationContext, node));

        verify(validationContext, times(1)).createViolation(eq("exception"));
        verify(node, times(2)).getProperty(eq(VISIT_SCOTLAND_ONLINE_JCR_TYPE));
    }
}