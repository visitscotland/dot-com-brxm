package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Optional;

/**
 * jcr:Name = visitscotland:asset-link-validator
 */
public class AssetLinkValidator implements Validator<Node> {

    public Optional<Violation> validate(final ValidationContext context, final Node document) {

        try {
            boolean asset = hasValidLink(document, "visitscotland:asset");

            if (!asset) {
                return Optional.of(context.createViolation());
            } else {
                return Optional.empty();
            }
        } catch (RepositoryException e) {
            return Optional.of(context.createViolation());
        }
    }

    private boolean hasValidLink (Node document, String fieldName) throws RepositoryException {
        return document.hasNode(fieldName)
                && document.getNode(fieldName).hasProperty("hippo:docbase")
                && !document.getNode(fieldName).getProperty("hippo:docbase").getString().startsWith("cafebabe-");
    }
}

