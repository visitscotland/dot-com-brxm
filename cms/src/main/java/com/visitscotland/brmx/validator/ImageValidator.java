package com.visitscotland.brmx.validator;

import com.visitscotland.brmx.beans.Image;
import com.visitscotland.brmx.translation.SessionFactory;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Optional;

import static org.hippoecm.repository.api.HippoNodeType.HIPPO_DOCBASE;

/**
 * jcr:Name = visitscotland:image-validator

 */
//TODO investigate if we can create unit test for this validator
public class ImageValidator implements Validator<Node> {

    private static final String EMPTY_IMAGE = "cafebabe-cafe-babe-cafe-babecafebabe";
    private SessionFactory sessionFactory;

    public ImageValidator() {
        this.sessionFactory = new SessionFactory();
    }

    public Optional<Violation> validate(final ValidationContext context, final Node document) {
        try {
             if(!document.getProperty(HIPPO_DOCBASE).getValue().getString().equals(EMPTY_IMAGE)) {
                 Node galleryNode = sessionFactory.getJcrSession().getNodeByIdentifier(document.getProperty(HIPPO_DOCBASE).getString());
                 Node childNode = galleryNode.getNode(galleryNode.getName());
                 if (!childNode.hasProperty(Image.CREDIT) || !childNode.hasProperty(Image.ALT_TEXT)){
                     return Optional.of(context.createViolation());
                 }
               }
        } catch (RepositoryException e) {
            return Optional.of(context.createViolation());
        }

        return Optional.empty();
    }
}

