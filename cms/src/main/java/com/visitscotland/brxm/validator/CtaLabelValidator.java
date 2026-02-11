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
 * jcr:Name = visitscotland:cta-label-validator
 */
public class CtaLabelValidator implements Validator<Node> {

    private static final Logger logger = LoggerFactory.getLogger(CtaLabelValidator.class);

    static final String CTA_LINK = "visitscotland:ctaLink";
    static final String LABEL_FIELD = "visitscotland:label";
    static final String ERROR = "error";
    static final String EXCEPTION = "exception";
    static final String EMPTY_LABEL = "emptyLabel";

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {

        try {
            // validate label has text
            final Node childNode =  node.hasNode(CTA_LINK) ? node.getNode(CTA_LINK) : null;

            if (childNode == null ) {
                logger.warn("Could not extract child node for CTA link");
                return Optional.of(context.createViolation(ERROR));
            }
            if (childNode.hasProperty(LABEL_FIELD)) {
                final String label = childNode.getProperty(LABEL_FIELD).getString();

                if (label == null || label.isEmpty()) {
                    return Optional.of(context.createViolation(EMPTY_LABEL));
                } else {
                    return Optional.empty();
                }
            } else {
                logger.warn("Unable to extract label for CTA from child node property {}", LABEL_FIELD);
                return Optional.of(context.createViolation(ERROR));
            }
        } catch (RepositoryException e) {
            logger.error("A repository exception occurred.", e);
            return Optional.of(context.createViolation(EXCEPTION));
        }

    }
}