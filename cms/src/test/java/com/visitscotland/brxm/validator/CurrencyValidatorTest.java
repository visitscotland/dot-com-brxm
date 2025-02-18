package com.visitscotland.brxm.validator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CurrencyValidatorTest {
    @Mock private ValidationContext validationContext;

    private Validator<String> currencyValidator;

    @BeforeEach
    void setUp() {
        currencyValidator = new CurrencyValidator();
    }

    @Test
    void validate_StringValidIso4217CurrencyCode_OptionalOfEmpty() {
        Assertions.fail();
    }

    @Test
    void validate_StringNull_OptionalOfViolation() {
        when(validationContext.createViolation()).thenReturn(mock(Violation.class));

        var result = currencyValidator.validate(validationContext, null);

        Assertions.assertTrue(result.isPresent());
        verify(validationContext, times(1)).createViolation();
    }

    @Test
    void validate_StringBlank_OptionalOfViolation() {
        final var blankString = "   ";

        when(validationContext.createViolation()).thenReturn(mock(Violation.class));

        var result = currencyValidator.validate(validationContext, blankString);

        Assertions.assertTrue(result.isPresent());
        verify(validationContext, times(1)).createViolation();
    }

    @Test
    void validate_StringNotValidIso4217CurrencyCode_OptionalOfViolation() {
        Assertions.fail();
    }
}