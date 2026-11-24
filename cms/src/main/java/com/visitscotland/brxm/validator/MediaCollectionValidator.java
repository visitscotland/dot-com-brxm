package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Optional;

import static org.hippoecm.repository.api.HippoNodeType.HIPPO_DOCBASE;

/**
 * checks if Image links in a mediaCollection are empty
 * Video links are already handled in LinkValidator
 *
 * jcr:Name = visitscotland:media-collection-validator
 */
public class MediaCollectionValidator implements Validator<Node> {

    private static final Logger logger = LoggerFactory.getLogger(MediaCollectionValidator.class);

    private static final String IMAGE_LINK = "hippogallerypicker:imagelink";

    @Override
    public Optional<Violation> validate(final ValidationContext context, final Node document) {

        try {
            if (document.hasProperty(HIPPO_DOCBASE)) {
                final String nodeId = document.getProperty(HIPPO_DOCBASE).getValue().getString();
                if (document.isNodeType(IMAGE_LINK) && nodeId.equals(ImageValidator.EMPTY_IMAGE)) {
                    return Optional.of(context.createViolation());
                }
            } else {
                logger.info("Property {} not found in document.", HIPPO_DOCBASE);
            }


        } catch (RepositoryException e) {
            logger.warn("An error occurred during validation: ", e);
        }
        return Optional.empty();
    }
}
