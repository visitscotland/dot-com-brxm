package com.visitscotland.brxm.validator;

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

    private static final String MAX_LENGTH = "maxLength";
    private static final String TARGET_FIELD = "targetField";

    private Long maxLength;
    private String targetField;

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {
        try {
            if(!validDownload(node)) {
                return Optional.of(context.createViolation());
            }
        } catch (RepositoryException exception) {
            return Optional.of(context.createViolation("exception"));
        }

        return Optional.empty();
    }

    /**
     * Checks if a price is valid
     * @param node The JCR node to check
     * @return true if the price is invalid, false otherwise
     * @throws RepositoryException if there is an error accessing the JCR node
     */
    private boolean validDownload(final Node node) throws RepositoryException {
        if(node.getProperty("visitscotland:layout").getValue().getString().equals("Download")) {
            return countNumberOfItems(node) < 2;
        }

        return true;
    }

    private Integer countNumberOfItems(Node node) {
        try {
            if (node.hasNode("visitscotland:megalinkItems")) {
                int count = 0;
                NodeIterator childNodes = node.getNodes("visitscotland:megalinkItems");
                while (childNodes.hasNext()) {
                    count++;
                    childNodes.nextNode();
                }
                return count;
            }
            logger.error("Can not run MultiValueNumberSelectedValidator as node {} has no property or child {}", node.getPath(), targetField);
        } catch (RepositoryException ex) {
            logger.error("Repository error when running multi value validator", ex);
        }
        return 0;
    }
}