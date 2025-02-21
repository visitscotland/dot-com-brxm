package com.visitscotland.brxm.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberValidatorTest {

    private NumberValidator validator;

    @BeforeEach
    void setUp() {
        validator = new NumberValidator();
    }

    @ParameterizedTest
    @CsvSource({"0,true", "123, true", "-123,true", "10.5,false", "aaa,false"})
    @DisplayName("Should return empty when the value is a valid integer")
    void validate(String value, boolean isValid) throws Exception {
        ValidationContext context = mock(ValidationContext.class);

        // Mock violation creation
        if (!isValid){
            when(context.createViolation()).thenReturn(mock(Violation.class));
        }

        // Execute
        Optional<Violation> result = validator.validate(context, value);

        // Verify
        assertEquals(isValid, result.isEmpty());
    }

    @Test
    @DisplayName("Should return a violation when the value is empty")
    void testValidate_EmptyValue() {
        // Setup
        ValidationContext context = mock(ValidationContext.class);
        Violation violation = mock(Violation.class);
        when(context.createViolation()).thenReturn(violation);

        // Execute
        Optional<Violation> result = validator.validate(context, "");

        // Verify
        assertTrue(result.isPresent());
        assertEquals(violation, result.get());
    }
}
