package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Optional;

/**
 * jcr:Name = visitscotland:google-maps-link-validator
 */
public class GoogleMapsLinkValidator implements Validator<Node>  {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleMapsLinkValidator.class);

    /**
     *     regex to extract coordinates from Google Maps place url using @ coordinates (.com and .co.uk)
     */
    private static final String URL_REGEX =
            "(?i)https://www\\.google\\.(?:com|co\\.uk)/maps/place/[^/]*/@(-?\\d{1,3}\\.\\d{1,20}),(-?\\d{1,3}\\.\\d{1,20}),\\d{1,2}z/data=.*";

    /**
     *     simple regex to check for Google urls (.com and .co.uk)
     */
    private static final String BASIC_URL_REGEX = "(?i)https://www\\.google\\.(?:com|co\\.uk)/maps/.*";

    static final String DAY = "visitscotland:Day";
    static final String MAP_LINK = "visitscotland:mapLink";
    static final String LINK_FIELD = "visitscotland:link";
    static final String EXTERNAL_LINK = "visitscotland:ExternalLink";
    static final String ERROR = "error";
    static final String EXCEPTION = "exception";
    static final String INVALID_LINK_DAYS = "invalidLinkDays";
    static final String INVALID_LINK_ITINERARIES = "invalidLinkItineraries";


    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {

        try {
            if (node.isNodeType(DAY)) {

                // validate 'place' link in Days
                final Node childNode = node.hasNode(MAP_LINK) ? node.getNode(MAP_LINK) : null;

                if (childNode == null) {
                    LOGGER.warn("Could not extract child node for map link");
                    return Optional.of(context.createViolation(ERROR));
                }

                if (childNode.hasProperty(LINK_FIELD)) {
                    final String mapLink = childNode.getProperty(LINK_FIELD).getString();
                    return validateDay(context, mapLink);
                } else {
                    LOGGER.warn("Unable to extract link from child node property {}", LINK_FIELD);
                    return Optional.of(context.createViolation(ERROR));
                }
            } else if (node.isNodeType(EXTERNAL_LINK)) {
                // currently used for mapLink in Itineraries
                return validateItinerary(context, node);
            } else {
                LOGGER.warn("Invalid node type: {}", node.getPrimaryNodeType());
                return Optional.of(context.createViolation(ERROR));
            }
        } catch (RepositoryException e) {
            LOGGER.error("A repository exception occurred.", e);
            return Optional.of(context.createViolation(EXCEPTION));
        }
    }

    /**
     * Validate mapLink from Days
     * @param context
     * @param mapLink
     * @return
     */
    private Optional<Violation> validateDay(final ValidationContext context, final String mapLink) {
        if (mapLink != null && mapLink.matches(URL_REGEX)) {
            return Optional.empty();
        } else {
            return Optional.of(context.createViolation(INVALID_LINK_DAYS));
        }
    }

    /**
     * Validate mapLink from Itineraries
     * @param context
     * @param node
     * @return
     */
    private Optional<Violation> validateItinerary(final ValidationContext context, final Node node) {
        try {
            if (node.getProperty(LINK_FIELD) == null) {
                LOGGER.warn("Link field not present in node.");
                return Optional.of(context.createViolation(ERROR));
            }
            final String mapLink = node.getProperty(LINK_FIELD).getString();
            if (mapLink != null && mapLink.matches(BASIC_URL_REGEX)) {
                return Optional.empty();
            } else {
                return Optional.of(context.createViolation(INVALID_LINK_ITINERARIES));
            }
        } catch (RepositoryException e) {
            LOGGER.error("A Repository Exception occurred when validating link field for Itinerary:", e);
            return Optional.of(context.createViolation(EXCEPTION));
        }
    }
}
