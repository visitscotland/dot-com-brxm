package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.beans.BaseDocument;
import com.visitscotland.brxm.beans.Page;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton
 */
@Component
public class DocumentUtils {

    private static final Logger logger = LoggerFactory.getLogger(DocumentUtils.class.getName());

    public static final String DOCUMENT_TYPE = "jcr:primaryType";

    private HippoUtilsService utils;

    public DocumentUtils() {
        this(new HippoUtilsService());
    }

    DocumentUtils(HippoUtilsService utils) {
        this.utils = utils;
    }

    private static DocumentUtils instance;

    public static DocumentUtils getInstance() {
        if (instance == null) {
            instance = new DocumentUtils();
        }
        return instance;
    }

    /**
     * Returns a list of allowed documents defined by its main document (@{code Page})
     *
     * @param page Main document that represents a document and has some children associated
     */
    public List<BaseDocument> getAllowedDocuments(Page page) {
        return getSiblingDocuments(page, BaseDocument.class, page.getChildJcrTypes());
    }

    /**
     * Returns a list of allowed documents
     *
     * @param document      Main document that link all children
     * @param expectedClass A List of this type will be returned
     * @param allowedTypes  jcr:primaryTypes that are allowed to be discovered
     * @param <T>           Type that will be returned
     */
    public <T> List<T> getSiblingDocuments(HippoBean document, Class<T> expectedClass, String... allowedTypes) {
        //Get the list of sibling nodes
        final NodeIterator it;
        final List<T> documents = new ArrayList<>();

        try {
            it = document.getNode().getParent().getParent().getNodes();
        } catch (RepositoryException e) {
            logError("Error trying to connect to the repository", document.getNode(), e);
            return documents;
        }

        while (it.hasNext()) {
            javax.jcr.Node jcrNode = it.nextNode();
            try {
                if (jcrNode.getNodes().getSize() > 0) {
                    String primaryType = jcrNode.getNodes().nextNode().getProperty(DOCUMENT_TYPE).getString();
                    for (String type : allowedTypes) {
                        if (type.equals(primaryType)) {
                            Object hippoBean = utils.getDocumentFromNode(jcrNode);

                            //The document is added if the type matches
                            if (hippoBean != null) {
                                if (expectedClass.isAssignableFrom(hippoBean.getClass())) {
                                    documents.add((T) hippoBean);
                                } else {
                                    logError("The following node might be corrupted and cannot be resolved as a BaseDocument", jcrNode, null);
                                }
                            }
                            break;
                        }
                    }
                }
            } catch (QueryException | RepositoryException | NullPointerException | ObjectBeanManagerException e) {
                logError("The following node is corrupted", document.getNode(), e);
            }
        }
        return documents;
    }

    private void logError(String message, Node node, Exception e) {
        try {
            logger.error(message + node.getPath(), e);
        } catch (RepositoryException e1) {
            logger.error(message + ". A nested exception happened while trying to access to the node ", e1);
        }
    }
}
