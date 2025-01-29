package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Node;

import java.util.Optional;

import static com.visitscotland.brxm.jcr.NodeUtility.isJcrPropertyBlank;

/**
 * jcr:Name = visitscotland:event-location-region-validator
 */
public class EventLocationRegionValidator extends EventLocationValidator {
    public EventLocationRegionValidator() { }

    @Override
    public Optional<Violation> validate(ValidationContext validationContext, Node node) {
        try {
            final boolean isRegionBlank = isJcrPropertyBlank(node, "visitscotland:region");

            if(isRegionBlank) {
                return super.validate(validationContext, node);
            }

            return Optional.empty();
        } catch (RepositoryException exception) {
            return Optional.of(validationContext.createViolation("exception"));
        }
    }
}