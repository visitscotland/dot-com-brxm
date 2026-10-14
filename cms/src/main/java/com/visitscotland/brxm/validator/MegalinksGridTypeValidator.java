package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.translation.SessionFactory;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.PathNotFoundException;
import java.util.Optional;

/**
 * jcrType = visitscotland:megalinks-grid-type-validator
 */
public class MegalinksGridTypeValidator implements Validator<Node> {

    private SessionFactory sessionFactory;

    private static final String LAYOUT_PROPERTY = "visitscotland:layout";

    private static final String EXCEPTION = "exception";
    private static final String NULL_LAYOUT = "nullLayout";
    private static final String TRANSLATION = "translation";

    private static final Logger logger = LoggerFactory.getLogger(MegalinksGridTypeValidator.class);

    public MegalinksGridTypeValidator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MegalinksGridTypeValidator() {}

    @Override
    public Optional<Violation> validate(final ValidationContext context, final Node node) {

        try {
            final String layout = node.hasProperty(LAYOUT_PROPERTY) ? node.getProperty(LAYOUT_PROPERTY).getString() : null;

            if (layout == null) {
                return Optional.of(context.createViolation(NULL_LAYOUT));
            }
            else {
                // we can ignore any other layout types
                return Optional.empty();
            }

        } catch (PathNotFoundException exception) {
            exceptionLogger(exception);
            return Optional.of(context.createViolation(TRANSLATION));
        } catch (RepositoryException exception) {
            exceptionLogger(exception);
            return Optional.of(context.createViolation(EXCEPTION));
        }
    }

    private void exceptionLogger(Exception exception) {
        logger.error(exception.getMessage(), exception);
    }
}
