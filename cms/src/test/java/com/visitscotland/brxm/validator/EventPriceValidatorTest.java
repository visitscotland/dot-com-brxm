package com.visitscotland.brxm.validator;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventPriceValidatorTest {
    private static final String ISO_4217_UK_CURRENCY_CODE = "GBP";

    void validate_PriceNodeWithValidCurrencyPositivePriceAndVatTrue_OptionalEmpty() { }
    void validate_PriceNodeWithInvalidCurrencyPositivePriceAndVatTrue_OptionalOfViolation() { }
    void validate_PriceNodeWithValidCurrencyAndInvalidPrice_OptionalOfViolation() { }
    void validate_nullPriceNode_OptionalOfExceptionalViolation() { }
    void validate_nullCurrencyProperty_OptionalOfExceptionalViolation() { }
    void validate_nullPriceProperty_OptionalOfExceptionalViolation() { }
    void validate_PriceNodeWithValidCurrencyZeroPriceAndVatTrue_OptionalEmpty() { }
    void validate_PriceNodeWithValidCurrencyBlankPriceAndVatTrue_OptionalEmpty() { }
    void validate_PriceNodeWithValidCurrencyPositivePriceAndInvalidVat_OptionalOfViolation() { }
    void validate_PriceNodeWithNonIso4217CurrencyPositivePriceAndVatTrue_OptionalOfViolation() { }
    void validate_PriceNodeWithEmptyCurrencyPositivePriceAndVatTrue_OptionalOfViolation() { }
}