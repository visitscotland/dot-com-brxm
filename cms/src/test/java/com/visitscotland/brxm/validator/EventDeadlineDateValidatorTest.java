package com.visitscotland.brxm.validator;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Property;
import javax.jcr.Node;

import java.util.Calendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventDeadlineDateValidatorTest {
    private static final String REGISTRATION_DEADLINE_PROPERTY = "visitscotland:deadline";
    private static final String START_DATE_PROPERTY = "visitscotland:startDate";
    private static final String EXCEPTION_TRANSLATION_VARIATION = "exception";

    @Mock private ValidationContext validationContext;
    @Mock private Node node;
    @Mock private Property property;

    private Validator<Node> eventDeadlineDateValidator;

    @BeforeEach
    void setUp() {
        this.eventDeadlineDateValidator = new EventDeadlineDateValidator();
    }

    @Test
    void validate_EventNodeWithDeadlinePropertyBeforeStartDate_EmptyOptional() throws RepositoryException {
        final Calendar startDate = mock(Calendar.class);
        final Calendar registrationDeadline = mock(Calendar.class);

        stubNodeProperty(START_DATE_PROPERTY, startDate);
        stubNodeProperty(REGISTRATION_DEADLINE_PROPERTY, registrationDeadline);

        when(registrationDeadline.before(any(Calendar.class))).thenReturn(true);

        final var result = eventDeadlineDateValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isEmpty());

        verify(node, times(1)).hasProperty(eq(START_DATE_PROPERTY));
        verify(node, times(1)).hasProperty(eq(REGISTRATION_DEADLINE_PROPERTY));
        verify(node, times(1)).getProperty(eq(START_DATE_PROPERTY));
        verify(node, times(1)).getProperty(eq(REGISTRATION_DEADLINE_PROPERTY));
        verify(registrationDeadline, times(1)).before(any(Calendar.class));
    }

    @Test
    void validate_EventNodeWithDeadlinePropertyAfterStartDate_OptionalOfViolation() throws RepositoryException {
        final Calendar startDate = mock(Calendar.class);
        final Calendar registrationDeadline = mock(Calendar.class);

        stubNodeProperty(START_DATE_PROPERTY, startDate);
        stubNodeProperty(REGISTRATION_DEADLINE_PROPERTY, registrationDeadline);
        when(registrationDeadline.before(any(Calendar.class))).thenReturn(false);
        when(validationContext.createViolation()).thenReturn(mock(Violation.class));

        final var result = eventDeadlineDateValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation();
    }

    @Test
    void validate_EventNodeWithNonExistingStartDateProperty_OptionalOfExceptionViolation() throws RepositoryException {
        when(node.hasProperty(START_DATE_PROPERTY)).thenReturn(false);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION))
            .thenReturn(mock(Violation.class));

        final var result = eventDeadlineDateValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }

    @Test
    void validate_EventNodeWithNonExistingRegistrationDeadlineProperty_OptionalOfExceptionViolation() throws RepositoryException {
        stubNodeProperty(START_DATE_PROPERTY, mock(Calendar.class));
        when(node.hasProperty(REGISTRATION_DEADLINE_PROPERTY)).thenReturn(false);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION))
            .thenReturn(mock(Violation.class));

        final var result = eventDeadlineDateValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }

    private void stubNodeProperty(String propertyName, Calendar calendar) throws RepositoryException {
        when(node.hasProperty(propertyName)).thenReturn(true);
        when(node.getProperty(propertyName)).thenReturn(property);
        when(property.getDate()).thenReturn(calendar);
    }
}