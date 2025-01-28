package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Node;

import java.util.Optional;

import static com.visitscotland.brxm.jcr.NodeUtility.isJcrPropertyBlank;

public class EventLocationRegionValidator extends EventLocationValidator {
    public EventLocationRegionValidator() { }

    @Override
    public Optional<Violation> validate(ValidationContext validationContext, Node node) {
        try {
            final boolean isVenueBlank = isJcrPropertyBlank(node, "visitscotland:venue");
            final boolean isRegionBlank = isJcrPropertyBlank(node, "visitscotland:region");

            if(isEventOnline(node) || !isRegionBlank || !isVenueBlank) {
                return Optional.empty();
            }

            return Optional.of(validationContext.createViolation());
        } catch (RepositoryException exception) {
            return Optional.of(validationContext.createViolation("exception"));
        }
    }
}