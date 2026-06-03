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
 * Prevents selecting an exclusive value together with other values.
 * jcr:Name = visitscotland:season-exclusive-validator
 * These nodes can be found under:
 * /hippo:configuration/hippo:modules/validation/hippo:moduleconfig/
 */
public class ExclusiveSelectionValidator implements Validator<Node> {

    private static final String EXCLUSIVE_VALUE = "exclusiveValue";
    private static final String VISITSCOTLAND_SEASONS = "visitscotland:seasons";

    private final String exclusiveValue;

    public ExclusiveSelectionValidator(final Node config) {

        try {

            exclusiveValue = config.getProperty(EXCLUSIVE_VALUE).getString();

        } catch (RepositoryException e) {
            throw new ValidationContextException("Cannot read validator configuration",e );
        }
    }

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {

        try {

            if (!node.hasProperty(VISITSCOTLAND_SEASONS)) {
                return Optional.empty();
            }

            var property = node.getProperty(VISITSCOTLAND_SEASONS);

            if (!property.isMultiple()) {
                return Optional.empty();
            }

            var values = property.getValues();

            boolean hasAll = false;

            for (var v : values) {
                if (exclusiveValue.equals(v.getString())) {
                    hasAll = true;
                    break;
                }
            }

            return (hasAll && values.length > 1)
                    ? Optional.of(context.createViolation())
                    : Optional.empty();

        } catch (Exception e) {
            throw new ValidationContextException("Validation error", e);
        }
    }
}