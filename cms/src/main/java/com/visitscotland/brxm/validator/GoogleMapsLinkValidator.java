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

    private static final Logger logger = LoggerFactory.getLogger(GoogleMapsLinkValidator.class);

    // regex to extract coordinates from url using @ coordinates
    private static final String URL_REGEX =
            "(?i)https://www\\.google\\.com/maps/place/[^/]*/@(-?\\d{1,3}\\.\\d{1,20}),(-?\\d{1,3}\\.\\d{1,20}),.*";

    static final String MAP_LINK = "visitscotland:mapLink";
    static final String LINK_FIELD = "visitscotland:link";
    static final String ERROR = "error";
    static final String EXCEPTION = "exception";
    static final String INVALID_LINK = "invalidLink";

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {

        try {
            // validate 'place' link
            final Node childNode =  node.hasNode(MAP_LINK) ? node.getNode(MAP_LINK) : null;

            if (childNode == null ) {
                logger.warn("Could not extract child node for map link");
                return Optional.of(context.createViolation(ERROR));
            }
            if (childNode.hasProperty(LINK_FIELD)) {
                final String mapLink = childNode.getProperty(LINK_FIELD).getString();
                if (mapLink != null && mapLink.matches(URL_REGEX)){
                    return Optional.empty();
                } else {
                    return Optional.of(context.createViolation(INVALID_LINK));
                }
            } else {
                logger.warn("Unable to extract link from child node property {}", LINK_FIELD);
                return Optional.of(context.createViolation(ERROR));
            }
        } catch (RepositoryException e) {
            return Optional.of(context.createViolation(EXCEPTION));
        }
    }
}
