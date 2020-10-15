package com.visitscotland.brmx.translation;

import com.visitscotland.brmx.translation.plugin.JcrDocument;
import com.visitscotland.brmx.translation.plugin.JcrDocumentFactory;
import org.hippoecm.repository.api.Workflow;
import org.hippoecm.repository.api.WorkflowException;
import org.hippoecm.repository.standardworkflow.EditableWorkflow;
import org.hippoecm.repository.translation.HippoTranslationNodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class TranslationService {
    private static final Logger log = LoggerFactory.getLogger(TranslationService.class);
    private JcrDocumentFactory jcrDocumentFactory;
    private SessionFactory sessionFactory;

    @Autowired
    public TranslationService(JcrDocumentFactory jcrDocumentFactory,
                              SessionFactory sessionFactory) {
        this.jcrDocumentFactory = jcrDocumentFactory;
        this.sessionFactory = sessionFactory;
    }

    public JcrDocument getDocument(String nodeId) throws RepositoryException {
        Node jcrNode = sessionFactory.getJcrSession().getNodeByIdentifier(nodeId);
        return jcrDocumentFactory.createFromNode(jcrNode);
    }

    public boolean hasPendingTranslations(JcrDocument document) throws RepositoryException {
        Set<JcrDocument> translations = document.getTranslations();
        for (JcrDocument translation : translations) {
            Node unpublishedNode = translation.getVariantNode(JcrDocument.VARIANT_UNPUBLISHED);
            if ( unpublishedNode.hasProperty(JcrDocument.VS_TRANSLATION_FLAG) &&
                    unpublishedNode.getProperty(JcrDocument.VS_TRANSLATION_FLAG).getBoolean()) {
                return true;
            }
        }
        return false;
    }

    public boolean getTranslationFlag(JcrDocument document) throws RepositoryException {
        Node unpublishedNode = document.getVariantNode(JcrDocument.VARIANT_UNPUBLISHED);
        if ( unpublishedNode.hasProperty(JcrDocument.VS_TRANSLATION_FLAG) ) {
            return unpublishedNode.getProperty(JcrDocument.VS_TRANSLATION_FLAG).getBoolean();
        }
        return false;
    }

    // Returns HTML escaped with encodeURIComponent in the javascript
    public String getDocumentDifference(String nodeId) throws RepositoryException, IOException {
        JcrDocument document = getDocument(nodeId);
        Node unpublishedNode = document.getVariantNode(JcrDocument.VARIANT_UNPUBLISHED);
        if ( unpublishedNode.hasProperty(JcrDocument.VS_TRANSLATION_DIFF) ) {
            return unpublishedNode.getProperty(JcrDocument.VS_TRANSLATION_DIFF).getString();
        }
        return null;
    }

    public void clearTranslationFlag(JcrDocument jcrDocument)
            throws RepositoryException, WorkflowException, RemoteException {
        if (jcrDocument.isDraftBeingEdited()) {
            throw new WorkflowException("Document being edited");
        }

        Session jcrSession = sessionFactory.getJcrSession();

        Node unpublishedNode = jcrDocument.getVariantNode(JcrDocument.VARIANT_UNPUBLISHED);
        Property localeProperty = unpublishedNode.getProperty(HippoTranslationNodeType.LOCALE);
        if (Locale.ENGLISH.getLanguage().equals(localeProperty.getString())) {
            throw new IllegalArgumentException("Unable to clear translation flag for English document");
        }

        Workflow editing = sessionFactory.getUserSession().getWorkflowManager().getWorkflow("editing", jcrDocument.getHandle());
        if (editing instanceof EditableWorkflow) {
            EditableWorkflow editableWorkflow = (EditableWorkflow) editing;
            editableWorkflow.obtainEditableInstance();
            try {
                unpublishedNode.setProperty(JcrDocument.VS_TRANSLATION_FLAG, false);
                if (unpublishedNode.hasProperty(JcrDocument.VS_TRANSLATION_DIFF)) {
                    Property diffProperty = unpublishedNode.getProperty(JcrDocument.VS_TRANSLATION_DIFF);
                    diffProperty.remove();
                }
            } finally {
                editableWorkflow.disposeEditableInstance();
            }

            jcrSession.save();
            jcrSession.refresh(true);
        } else {
            throw new IllegalStateException("Unable to get EditableWorkflow");
        }
    }

    public List<JcrDocument> setTranslationContent(JcrDocument jcrDocument, String translationContent) throws WorkflowException, RepositoryException, RemoteException {
        Session jcrSession = sessionFactory.getJcrSession();

        if (jcrDocument.isNodeType(JcrDocument.VS_TRANSLATABLE_TYPE)) {
            // The Hippo CMS uses the handle of the document variants to perform checkout, commit and discard
            // operations. But the lockEditableNode returns the unpublished Node so we need to keep hold of the
            // handle so we can perform the commit, or discard operations.

            // Can only use an English document to lookup the translations
            if (!Locale.ENGLISH.getLanguage().equals(jcrDocument.getLocaleName())) {
                throw new IllegalArgumentException("Must use an English document to set the translations");
            }

            // HashMap<Handle, Editable>
            HashMap<Node, Node> editableNodes = new HashMap<>();
            List<JcrDocument> nodesBeingEdited = new ArrayList<>();

            // Need to check if the root (English) document is being edited by another user. Don't want to send for
            // translation if it is
            if (jcrDocument.isDraftBeingEdited()) {
                nodesBeingEdited.add(jcrDocument);
            }

            Set<JcrDocument> jcrTranslations = jcrDocument.getTranslations();
            for (JcrDocument translatedDocument : jcrTranslations) {
                if (translatedDocument.isDraftBeingEdited()) {
                    nodesBeingEdited.add(translatedDocument);
                    log.debug("Document already checked out for edit, unable to send for translation");
                    continue;
                }
                try {
                    Node handle = translatedDocument.getHandle();
                    // The editable node returned is the draft variant of the document. If we apply changes to the
                    // draft, and then commit changes to the node it also flags the document as changed.
                    // We do not want that, we want the editor to choose if the document has changed.
                    // Getting the editable node still ensures that nobody else is editing the document, but if the
                    // changes are applied to the unpublished variant and the draft is discarded it should have the
                    // desired result, changes applied without flagging the document as changed.
                    lockEditableNode(handle);

                    Node unpublishedNode = translatedDocument.getVariantNode(JcrDocument.VARIANT_UNPUBLISHED);
                    editableNodes.put(handle, unpublishedNode);
                } catch (WorkflowException ex) {
                    // If we get a workflow exception we have not been able to check the document out
                    // add the Node to a list of failed documents
                    log.debug("Document already checked out for edit, unable to send for translation", ex);
                    nodesBeingEdited.add(translatedDocument);
                }
            }

            if (nodesBeingEdited.isEmpty()) {
                if (!editableNodes.isEmpty()) {
                    for (HashMap.Entry<Node, Node> editableNodeEntry : editableNodes.entrySet()) {
                        Node editableUnpublishedVariant = editableNodeEntry.getValue();
                        editableUnpublishedVariant.setProperty("visitscotland:translationFlag", true);
                        editableUnpublishedVariant.setProperty("visitscotland:diff", translationContent);
                        // If this was the draft node we would want to commit the changes
                        discardEditableNode(editableNodeEntry.getKey());
                    }
                    jcrSession.save();
                }
            } else {
                for (Node handle : editableNodes.keySet()) {
                    discardEditableNode(handle);
                }
            }
            jcrSession.refresh(false);
            return nodesBeingEdited;
        }
        return Collections.emptyList();
    }

    protected void lockEditableNode(Node handle) throws RemoteException, WorkflowException, RepositoryException {
        final Workflow editing = sessionFactory.getUserSession().getWorkflowManager().getWorkflow("editing", handle);
        if (editing instanceof EditableWorkflow) {
            EditableWorkflow editableWorkflow = (EditableWorkflow) editing;
            editableWorkflow.obtainEditableInstance();
        } else {
            throw new WorkflowException("Unable to obtain an EditableWorkflow to perform translation");
        }
    }

    protected void discardEditableNode(Node handle) throws RemoteException, WorkflowException, RepositoryException {
        final Workflow editing = sessionFactory.getUserSession().getWorkflowManager().getWorkflow("editing", handle);
        if (editing instanceof EditableWorkflow) {
            EditableWorkflow editableWorkflow = (EditableWorkflow) editing;
            editableWorkflow.disposeEditableInstance();
        } else {
            throw new WorkflowException("Unable to obtain an EditableWorkflow to discard checkout");
        }
    }
}
