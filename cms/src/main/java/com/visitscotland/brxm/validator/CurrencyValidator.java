package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import java.util.Optional;

public class CurrencyValidator implements Validator<String> {
    @Override
    public Optional<Violation> validate(ValidationContext context, String s) {
        return Optional.empty();
    }
}