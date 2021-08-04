package com.visitscotland.brxm.validator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.ValidationContextException;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ensure that a list of links is unique. Must be configured on a hipposysedit:nodetype, with targetField property
 * specifying the field to be validated
 *
 * The configuration is
 *  <ul>
 *      <li>targetField - The name of the child node that contains the list of links</li>
 *      <li>linkIdField (optional) - The field or node that must be unique. If a node, then the hippo:docbase property is
 *                                   used for uniqueness. If not provided, the hippo:docbase on the targetField node is used</li>
 *  </ul>
 *
 */
public class UniqueLinksValidator implements Validator<Node> {

    private static final String TARGET_FIELD = "targetField";
    private static final String LINK_ID_FIELD = "linkIdField";
    private final String DEFAULT_HIPPO_LINK = "hippo:docbase";
    private final String targetField;
    private final String linkIdProperty;
    private static final Logger logger = LoggerFactory.getLogger(UniqueLinksValidator.class);

    public UniqueLinksValidator(final Node config) {
        try {
            if (!config.hasProperty(TARGET_FIELD)) {
                throw new ValidationContextException("A targetField must be provided for UniqueLinksValidator");
            }
            targetField = config.getProperty(TARGET_FIELD).getString();
            linkIdProperty = config.hasProperty(LINK_ID_FIELD) ? config.getProperty(LINK_ID_FIELD).getString() : DEFAULT_HIPPO_LINK;
        } catch (RepositoryException ex) {
            throw new ValidationContextException("Cannot read required properties for the UniqueLinksValidator. Verify the node ", ex);
        }
    }

    @Override
    public Optional<Violation> validate(ValidationContext validationContext, Node node) {
        try {
            if (node == null || !node.hasNode(targetField)) {
                throw new ValidationContextException(String.format("Can not validate as targetField `%s` does not exist on node", targetField));
            }
            NodeIterator linkNodes = node.getNodes(targetField);
            List<String> linkIds = new ArrayList<>();
            while (linkNodes.hasNext()) {
                Node nextNode = linkNodes.nextNode();
                String id;
                if (nextNode.hasNode(linkIdProperty) && nextNode.getNode(linkIdProperty).hasProperty(DEFAULT_HIPPO_LINK)) {
                    id = nextNode.getNode(linkIdProperty).getProperty(DEFAULT_HIPPO_LINK).getString();
                } else if (nextNode.hasProperty(linkIdProperty)) {
                    id = nextNode.getProperty(linkIdProperty).getString();
                } else {
                    logger.error("Link on node `{}` does not have a linkIdProperty `{}` or property with hippo:docbase",node.getPath(), linkIdProperty);
                    continue;
                }
                if (linkIds.contains(id)) {
                    return Optional.of(validationContext.createViolation());
                }
                linkIds.add(id);
            }
        } catch (RepositoryException ex) {
            logger.error("Repository error during unique links validator", ex);
        }
        return Optional.empty();
    }
}