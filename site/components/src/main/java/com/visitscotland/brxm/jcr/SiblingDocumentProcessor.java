package com.visitscotland.brxm.jcr;

import com.visitscotland.brxm.utils.HippoUtilsService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import org.springframework.stereotype.Component;

import javax.jcr.RepositoryException;
import javax.jcr.NodeIterator;
import javax.jcr.Node;

import java.util.stream.Collectors;
import java.util.function.Supplier;
import java.util.Collections;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

@Component
public class SiblingDocumentProcessor {
    private static final Logger logger = LogManager.getLogger(SiblingDocumentProcessor.class);
    private final HippoUtilsService hippoUtilsService;

    protected SiblingDocumentProcessor(HippoUtilsService hippoUtilsService) {
        this.hippoUtilsService = hippoUtilsService;
    }

    public Set<HippoDocument> getSiblingDocuments(final HippoBean hippoBean, Supplier<Set<String>> allowedJcrTypes) throws RepositoryException {
        return getSiblingHippoDocuments(hippoBean);
    }

    private Set<HippoDocument> getSiblingHippoDocuments(final HippoBean hippoBean) throws RepositoryException {
        return getNodeIterator(hippoBean)
            .map(this::getNodesFromNodeIterator)
            .orElse(Collections.emptySet())
            .stream()
            .filter(this::isNodeAuthorised)
            .map(this::getHippoDocumentFromNode)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
    }

    private Optional<NodeIterator> getNodeIterator(final HippoBean hippoBean) throws RepositoryException {
        return Optional.ofNullable(hippoBean
            .getNode()
            .getParent()
            .getParent()
            .getNodes()
        );
    }

    private Set<Node> getNodesFromNodeIterator(final NodeIterator nodeIterator) {
        final Set<Node> nodes = new HashSet<>();

        while (nodeIterator.hasNext()) {
            nodes.add(nodeIterator.nextNode());
        }

        return nodes;
    }

    private boolean isNodeAuthorised(final Node node) {
        return false;
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