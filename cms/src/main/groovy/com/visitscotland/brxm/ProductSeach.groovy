package com.visitscotland.brxm

import org.hippoecm.repository.api.HippoWorkspace
import org.onehippo.repository.update.BaseNodeUpdateVisitor

import javax.jcr.Node
import javax.jcr.NodeIterator
import javax.jcr.RepositoryException
import javax.jcr.Session
import javax.jcr.Value
import javax.jcr.query.Query
import javax.jcr.query.QueryResult

/**
 * Groovy Script created for searching documents that use ProductSearch Objects
 *
 * Use the following XPATH /jcr:root
 *
 * @author jcalcines
 * @since February 2026
 * @version 3.1.x
 */
class ProductSearchRetirement extends BaseNodeUpdateVisitor {

    @Override
    boolean doUpdate(Node node) {
        searchLiveDocuments(node.session, "ProductsSearch")
        return true;
    }

    List<Node> searchLiveDocuments(Session session, String targetDocument) {
        NodeIterator it = query(session,"//content/documents//element(*, visitscotland:${targetDocument})")
        List<Node> documents = new ArrayList<>();

        while (it.hasNext()){
            Node node = it.next()
            Node mainDocument = node;
            while (mainDocument.getName() != "visitscotland" && !mainDocument.hasProperty("hippo:availability")) {
                mainDocument = mainDocument.getParent()
            }

            for (Value value : mainDocument.getProperty("hippo:availability").getValues()){
                if (value.getString() == "live"){
                    log.debug ("Live Document ${mainDocument.getPath()}: ")
                    documents.add(mainDocument)
                }
            }
        }

        return documents;
    }

    /**
     * Executes a query and log when no results are returned
     * @param session
     * @param query
     * @return
     */
    NodeIterator query(def session, def query){
        QueryResult results = ((HippoWorkspace) session.getWorkspace()).getQueryManager().createQuery(query, Query.XPATH).execute();
        if (!results.getNodes().hasNext()){
            log.warn "No query results for ${query}"
        } else {
            // Note that the method size. Moves the pointer for the iterator at the end. That is why we need to
            // invoke .getNodes() for getting the size
            log.info "Matches = ${results.getNodes().size()}";
        }
        return results.getNodes();
    }

    @Override
    boolean undoUpdate(Node node) throws RepositoryException, UnsupportedOperationException {
        return false
    }
}
