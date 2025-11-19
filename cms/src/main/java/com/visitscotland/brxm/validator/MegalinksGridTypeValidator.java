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

    private static final String GRID = "Grid";
    private static final String GRID_3 = "Grid 3";
    private static final String GRID_4 = "Grid 4";

    private static final String EMPTY_LINKS = "emptyLinks";
    private static final String EXCEPTION = "exception";
    private static final String NUMBER_OF_LINKS = "numberOfLinks";
    private static final String NUMBER_OF_LINKS_34 = "numberOfLinks34";
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

            if (layout.equals(GRID)) {
                return validateGrid(context, node);
            } else if (layout.equals(GRID_3) || layout.equals(GRID_4)) {
                // to be removed when grid 3 and 4 is deprecated
                return validateGrid34(context, node);
            } else {
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

    /**
     * normal grid links - can hold up to 4
     */
    private Optional<Violation> validateGrid(final ValidationContext context, final Node node) {

        try {
            final long nodeCount = node.getNodes("visitscotland:megalinkItems").getSize();
            if (nodeCount < 1 ) {
                return Optional.of(context.createViolation(EMPTY_LINKS));
            } else if (nodeCount > 4) {
                return Optional.of(context.createViolation(NUMBER_OF_LINKS));
            }
            return Optional.empty();
        } catch (RepositoryException exception) {
            exceptionLogger(exception);
            return Optional.of(context.createViolation(EXCEPTION));
        }
    }

    /**
     * to be removed when grid 3 and 4 is deprecated -
     * this ensures that no less than 3 or more than 4 links are in use
     */
    private Optional<Violation> validateGrid34(final ValidationContext context, final Node node) {

        try {
            final long nodeCount = node.getNodes("visitscotland:megalinkItems").getSize();
            if (nodeCount < 1 ) {
                return Optional.of(context.createViolation(EMPTY_LINKS));
            } else if (nodeCount < 3 || nodeCount > 4) {
                return Optional.of(context.createViolation(NUMBER_OF_LINKS_34));
            }
            return Optional.empty();
        } catch (RepositoryException exception) {
            exceptionLogger(exception);
            return Optional.of(context.createViolation(EXCEPTION));
        }

    }

    private void exceptionLogger(Exception exception) {
        logger.error(exception.getMessage(), exception);
    }
}
