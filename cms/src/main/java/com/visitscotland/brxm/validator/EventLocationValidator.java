package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Optional;

final class EventLocationValidator implements Validator<Node> {
    private static final String EVENT_VENUE_JCR_TYPE = "visitscotland:venue";
    private static final String EVENT_ONLINE_JCR_TYPE = "visitscotland:online";

    @Override
    public Optional<Violation> validate(ValidationContext validationContext, Node node) {
        final boolean isValid = isNodeValid(node);
        return isValid ? Optional.empty() : Optional.of(validationContext.createViolation());
    }

    private boolean isNodeValid(final Node node) {
        try {
            return isEventOnline(node) || !isVenueBlank(node);
        } catch (RepositoryException e) {
            return false;
        }
    }

    private boolean isEventOnline(final Node node) throws RepositoryException {
        return node.getProperty(EVENT_ONLINE_JCR_TYPE).getBoolean();
    }

    private boolean isVenueBlank(final Node node) throws RepositoryException {
        return node.getProperty(EVENT_VENUE_JCR_TYPE).getString().isBlank();
    }
}