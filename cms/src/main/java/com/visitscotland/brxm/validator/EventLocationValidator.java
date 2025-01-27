package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Node;

import java.util.Optional;

/**
 * jcr:Name = visitscotland:event-location-validator
 */
public class EventLocationValidator implements Validator<Node> {
    public EventLocationValidator() { }

    @Override
    public Optional<Violation> validate(ValidationContext validationContext, Node node) {
        try {
            if(isEventOnline(node) || !isVenueBlank(node)) {
                return Optional.empty();
            }

            return Optional.of(validationContext.createViolation());
        } catch (RepositoryException exception) {
            return Optional.of(validationContext.createViolation("exception"));
        }
    }

    private boolean isEventOnline(final Node node) throws RepositoryException {
        return node.getProperty("visitscotland:online").getBoolean();
    }

    private boolean isVenueBlank(final Node node) throws RepositoryException {
        final Optional<String> venue = Optional.ofNullable(node
            .getProperty("visitscotland:venue")
            .getString());

        return venue.map(String::isBlank).orElse(true);
    }
}