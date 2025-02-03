package com.visitscotland.brxm.validator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventPriceValidatorTest {
    private static final String PRICE_JCR_PROPERTY_NAME = "visitscotland:price";
    private static final String EXCEPTION_TRANSLATION_VARIATION = "exception";
    private static final double NEGATIVE_DOUBLE_VALUE = -17.99D;
    private static final double POSITIVE_DOUBLE_VALUE = 17.99D;
    private static final double ZERO_DOUBLE_VALUE = 0.00D;

    @Mock private Node node;
    @Mock private Property property;
    @Mock private ValidationContext validationContext;

    private EventPriceValidator eventPriceValidator;

    @BeforeEach
    void setUp() {
        this.eventPriceValidator = new EventPriceValidator();
    }

    @ValueSource(doubles = { POSITIVE_DOUBLE_VALUE, ZERO_DOUBLE_VALUE })
    @ParameterizedTest
    void validate_PriceNodeWithPositivePrice_OptionalEmpty(double value) throws RepositoryException {
        when(node.hasProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(true);
        when(node.getProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(property);
        when(property.getDouble()).thenReturn(value);

        final Optional<Violation> result = eventPriceValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isEmpty());

        verify(node, times(1)).hasProperty(eq(PRICE_JCR_PROPERTY_NAME));
        verify(node, times(1)).getProperty(eq(PRICE_JCR_PROPERTY_NAME));
        verify(property, times(1)).getDouble();
    }

    @Test
    void validate_PriceNodeWithNegativePrice_OptionalOfViolation() throws RepositoryException {
        when(node.hasProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(true);
        when(node.getProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(property);
        when(property.getDouble()).thenReturn(NEGATIVE_DOUBLE_VALUE);
        when(validationContext.createViolation()).thenReturn(mock(Violation.class));

        final Optional<Violation> result = eventPriceValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation();
    }

    @Test
    void validate_PriceNodeGetPricePropertyThrowsRepositoryException_OptionalOfExceptionalViolation() throws RepositoryException {
        final Exception repositoryException = new RepositoryException();

        when(node.hasProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(true);
        when(node.getProperty(PRICE_JCR_PROPERTY_NAME)).thenThrow(repositoryException);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION)).thenReturn(mock(Violation.class));

        final Optional<Violation> result = eventPriceValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }
}