package com.visitscotland.brxm.validator;

import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Property;
import javax.jcr.Node;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PriceValidatorTest {
    private static final String PRICE_JCR_PROPERTY_NAME = "visitscotland:price";
    private static final String EXCEPTION_TRANSLATION_VARIATION = "exception";
    private static final double NEGATIVE_DOUBLE_VALUE = -17.99D;
    private static final double POSITIVE_DOUBLE_VALUE = 17.99D;
    private static final double ZERO_DOUBLE_VALUE = 0.00D;

    @Mock private Node node;
    @Mock private Property property;
    @Mock private ValidationContext validationContext;

    private PriceValidator priceValidator;

    @BeforeEach
    void setUp() {
        this.priceValidator = new PriceValidator();
    }

    @ValueSource(doubles = { POSITIVE_DOUBLE_VALUE, ZERO_DOUBLE_VALUE })
    @ParameterizedTest
    void validate_PriceNodeWithPositivePrice_OptionalEmpty(double value) throws RepositoryException {
        when(node.hasProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(true);
        when(node.getProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(property);
        when(property.getDouble()).thenReturn(value);

        final var result = priceValidator.validate(validationContext, node);

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

        final var result = priceValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation();
    }

    @Test
    void validate_PriceNodeGetPricePropertyThrowsRepositoryException_OptionalOfExceptionalViolation() throws RepositoryException {
        final Exception repositoryException = new RepositoryException();

        when(node.hasProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(true);
        when(node.getProperty(PRICE_JCR_PROPERTY_NAME)).thenThrow(repositoryException);
        when(validationContext.createViolation(EXCEPTION_TRANSLATION_VARIATION)).thenReturn(mock(Violation.class));

        final var result = priceValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isPresent());

        verify(validationContext, times(1)).createViolation(eq(EXCEPTION_TRANSLATION_VARIATION));
    }

    @Test
    void validate_PriceNodeHasPropertyForPriceReturnsFalse_OptionalEmpty() throws RepositoryException {
        when(node.hasProperty(PRICE_JCR_PROPERTY_NAME)).thenReturn(false);

        final var result = priceValidator.validate(validationContext, node);

        Assertions.assertTrue(result.isEmpty());

        verify(node, times(1)).hasProperty(eq(PRICE_JCR_PROPERTY_NAME));
    }
}