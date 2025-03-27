package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.hippobeans.SharedLink;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.util.Optional;

public class MegaLinksBSHValidator implements Validator<Node> {
    private static final Logger logger = LoggerFactory.getLogger(MultiValueNumberSelectedValidator.class);
    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {
        try {
            if(node.hasNode("visitscotland:singleImageLinks")) {
                return Optional.of(context.createViolation("singleImage"));
            }
            if(!validDownload(node)) {
                return Optional.of(context.createViolation());
            }
        } catch (RepositoryException exception) {
            return Optional.of(context.createViolation("exception"));
        }

        return Optional.empty();
    }

    /**
     * Checks if there is only 1 link attached for the Download layout
     * @return true if the price is invalid, false otherwise
     * @throws RepositoryException if there is an error accessing the JCR node
     */
    private boolean validDownload(final Node node) throws RepositoryException {
        if(node.getProperty("visitscotland:layout").getValue().getString().equals("Download")) {
            if(node.hasNode("visitscotland:megalinkItems")) {
                if (node.getNodes("visitscotland:megalinkItems").getSize() > 1) {
                    return false;
                }
            }
        }

        return true;
    }

}