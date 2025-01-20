package com.visitscotland.brxm.jcr;

import com.visitscotland.brxm.utils.HippoUtilsService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import org.springframework.stereotype.Component;

import javax.jcr.RepositoryException;
import javax.jcr.NodeIterator;
import javax.jcr.Node;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

@Component
public class SiblingDocumentProcessor {
    private static final Logger logger = LogManager.getLogger(SiblingDocumentProcessor.class);
    private static final String PRIMARY_JCR_TYPE = "jcr:primaryType";
    private static final String HIPPO_PAGE_JCR_TYPE = "visitscotland:page";
    private static final String HIPPO_FOLDER_JCR_TYPE = "hippostd:folder";
    private static final String HIPPO_HANDLE_JCR_TYPE = "hippo:handle";

    private final HippoUtilsService hippoUtilsService;

    protected SiblingDocumentProcessor(HippoUtilsService hippoUtilsService) {
        this.hippoUtilsService = hippoUtilsService;
    }

    private Set<HippoDocument> getSiblingDocuments(final HippoBean hippoBean) throws RepositoryException {
        return getNodeIterator(hippoBean)
            .map(this::getNodesFromNodeIterator)
            .orElse(Collections.emptySet())
            .stream()
            .parallel()
            .filter(this::isNodeAuthorised)
            .map(this::getHippoDocumentFromNode)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
    }

    private Optional<NodeIterator> getNodeIterator(final HippoBean hippoBean) throws RepositoryException {
        final NodeIterator nodeIterator = hippoBean
            .getNode()
            .getParent()
            .getParent()
            .getNodes();

        return Optional.ofNullable(nodeIterator);
    }

    private Set<Node> getNodesFromNodeIterator(final NodeIterator nodeIterator) {
        final Set<Node> nodes = new HashSet<>();

        while (nodeIterator.hasNext()) {
            nodes.add(nodeIterator.nextNode());
        }

        return nodes;
    }

    private boolean isNodeAuthorised(final Node node) {
        try {
            final String primaryJcrType = node.getProperty(PRIMARY_JCR_TYPE).getString();

            return !(primaryJcrType.equals(HIPPO_HANDLE_JCR_TYPE))
                && !(primaryJcrType.equals(HIPPO_FOLDER_JCR_TYPE))
                && !node.isNodeType(HIPPO_PAGE_JCR_TYPE);
        } catch (RepositoryException exception) {
            logger.error("An exception occurred while checking if a JCR Node was authorised", exception);
            return false;
        }
    }

    private Optional<HippoDocument> getHippoDocumentFromNode(final Node node) {
        try {
            return Optional.of(hippoUtilsService.getDocumentFromNode(node));
        } catch (QueryException | ObjectBeanManagerException exception) {
            logger.error("Error trying to get the document from the node", exception);
            return Optional.empty();
        }
    }
}