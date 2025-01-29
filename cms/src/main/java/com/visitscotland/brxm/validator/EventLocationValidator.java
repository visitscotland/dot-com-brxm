package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Node;

import java.util.Optional;

import static com.visitscotland.brxm.jcr.NodeUtility.isJcrPropertyBlank;

/**
 * jcr:Name = visitscotland:event-location-validator
 */
public class EventLocationValidator implements Validator<Node> {
    public EventLocationValidator() { }

    @Override
    public Optional<Violation> validate(ValidationContext validationContext, Node node) {
        try {
            final boolean isVenueBlank = isJcrPropertyBlank(node, "visitscotland:venue");

            if(isEventOnline(node) || !isVenueBlank) {
                return Optional.empty();
            }

            return Optional.of(validationContext.createViolation());
        } catch (RepositoryException exception) {
            return Optional.of(validationContext.createViolation("exception"));
        }
    }

    protected boolean isEventOnline(final Node node) throws RepositoryException {
        return node.getProperty("visitscotland:online").getBoolean();
    }
}