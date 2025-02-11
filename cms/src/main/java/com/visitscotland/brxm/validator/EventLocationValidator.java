package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.exception.UnknownPropertyException;
import com.visitscotland.brxm.exception.ValidationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(EventLocationValidator.class);
    private static final String ONLINE_JCR_TYPE = "visitscotland:online";
    private static final String VENUE_JCR_TYPE = "visitscotland:venue";

    public EventLocationValidator() { }

    @Override
    public Optional<Violation> validate(ValidationContext validationContext, Node node) {
        try {
            if(isEventOnline(node) || !isVenueBlank(node)) {
                return Optional.empty();
            }

            return Optional.of(validationContext.createViolation());
        } catch (ValidationException | RepositoryException exception) {
            logger.error(exception.getMessage());
            return Optional.of(validationContext.createViolation("exception"));
        }
    }

    /**
     * Check if the venue is blank or not
     * @param node The event node
     * @return true if the venue is blank, false otherwise
     * @throws RepositoryException If there is an issue with the JCR repository
     * @throws ValidationException If the JCR property is not found for the node
     */
    private boolean isVenueBlank(final Node node) throws RepositoryException, ValidationException {
        if (node.hasProperty(VENUE_JCR_TYPE)) {
            return isJcrPropertyBlank(node, VENUE_JCR_TYPE);
        }

        throw new UnknownPropertyException(VENUE_JCR_TYPE);
    }

    /**
     * Check if the event is flagged as online or not
     * @param node The event node
     * @return true if the event is online, false otherwise
     * @throws RepositoryException If there is an issue with the JCR repository
     * @throws ValidationException If the JCR property is not found for the node
     */
    private boolean isEventOnline(final Node node) throws RepositoryException, ValidationException {
        if(node.hasProperty(ONLINE_JCR_TYPE)) {
            return node.getProperty(ONLINE_JCR_TYPE).getBoolean();
        }

        throw new UnknownPropertyException(ONLINE_JCR_TYPE);
    }
}