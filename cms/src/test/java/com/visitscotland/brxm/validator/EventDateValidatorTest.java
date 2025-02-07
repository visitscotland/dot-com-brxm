package com.visitscotland.brxm.validator;

import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventDateValidatorTest {
    private static final String START_DATE_PROPERTY = "visitscotland:startDate";
    private static final String EXCEPTION_TRANSLATION_VARIATION = "exception";
    private static final String END_DATE_PROPERTY = "visitscotland:endDate";

    @Mock private ValidationContext validationContext;
    @Mock private Property property;
    @Mock private Node node;

    private Validator<Node> eventDateValidator;

    @BeforeEach
    void setUp() {
        this.eventDateValidator = new EventDateValidator();
    }

    @ParameterizedTest
    @CsvSource({ "true, true, true, false", "true, true, false, true" })
    void validate_EventNode_EndDateComparison(boolean hasStartDate,
                                              boolean hasEndDate,
                                              boolean endDateAfterStart,
                                              boolean expectViolation) throws RepositoryException {
        final var startDateCalendar = mock(Calendar.class);
        final var endDateCalendar = mock(Calendar.class);

        stubNodeProperty(START_DATE_PROPERTY, hasStartDate, startDateCalendar);
        stubNodeProperty(END_DATE_PROPERTY, hasEndDate, endDateCalendar);

        if (hasStartDate && hasEndDate) {
            when(endDateCalendar.after(any(Calendar.class))).thenReturn(endDateAfterStart);
        }

        if (expectViolation) {
            when(validationContext.createViolation()).thenReturn(mock(Violation.class));
        }

        var result = eventDateValidator.validate(validationContext, node);

        Assertions.assertEquals(expectViolation, result.isPresent());
        verify(endDateCalendar, hasStartDate && hasEndDate ? times(1) : never()).after(any(Calendar.class));
        verify(validationContext, expectViolation ? times(1) : never()).createViolation();
    }

    @Test
    void validate_EventNodeWithNoStartDateProperty_OptionalOfExceptionViolation() throws RepositoryException {
        when(node.hasProperty(START_DATE_PROPERTY)).thenReturn(false);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION)).thenReturn(mock(Violation.class));

        final var result = eventDateValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }

    @Test
    void validate_EventNodeWithNoEndDateProperty_OptionalOfExceptionViolation() throws RepositoryException {
        stubNodeProperty(START_DATE_PROPERTY, true, mock(Calendar.class));
        when(node.hasProperty(END_DATE_PROPERTY)).thenReturn(false);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION)).thenReturn(mock(Violation.class));

        final var result = eventDateValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }

    private void stubNodeProperty(String propertyName, boolean hasProperty, Calendar calendar) throws RepositoryException {
        when(node.hasProperty(propertyName)).thenReturn(hasProperty);
        if (hasProperty) {
            when(node.getProperty(propertyName)).thenReturn(property);
            when(property.getDate()).thenReturn(calendar);
        }
    }
}