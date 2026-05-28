package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.ValidationContextException;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Optional;

/**
 *
 * jcr:Name = visitscotland:max-two-checkbox-validator
 * These nodes can be found under:
 * /hippo:configuration/hippo:modules/validation/hippo:moduleconfig/
 */
public class MaxSelectionValidator implements Validator<Node> {

    static final String MAX_SELECTIONS = "maxSelections";
    private static final String propertyName = "visitscotland:transports";
    private final long maxSelections;

    public MaxSelectionValidator(final Node config) {

        try {

            maxSelections = config.getProperty(MAX_SELECTIONS).getLong();

        } catch (RepositoryException e) {
            throw new ValidationContextException("Cannot read validator configuration",e );
        }
    }

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {

        try {

            if (!node.hasProperty(propertyName)) {
                return Optional.empty();
            }

            var property = node.getProperty(propertyName);

            if (!property.isMultiple()) {
                return Optional.empty();
            }

            var values = property.getValues();

            return values.length > maxSelections
                    ? Optional.of(context.createViolation())
                    : Optional.empty();

        } catch (Exception e) {
            throw new ValidationContextException("Validation error", e);
        }
    }
}