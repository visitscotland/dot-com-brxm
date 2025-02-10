package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.exception.UnknownPropertyException;
import com.visitscotland.brxm.exception.ValidationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.RepositoryException;
import javax.jcr.Node;

import java.util.Calendar;
import java.util.Optional;

public class EventDeadlineDateValidator implements Validator<Node> {
    private static final Logger logger = LogManager.getLogger(EventDateValidator.class);
    private static final String REGISTRATION_DEADLINE_PROPERTY = "visitscotland:deadline";
    private static final String START_DATE_PROPERTY = "visitscotland:startDate";

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {
        try {
            if(isRegistrationDeadlineBeforeStartDate(node)) {
                return Optional.empty();
            }

            return Optional.of(context.createViolation());
        } catch (RepositoryException | ValidationException exception) {
            logger.error(exception.getMessage(), exception);
            return Optional.of(context.createViolation("exception"));
        }
    }

    private boolean isRegistrationDeadlineBeforeStartDate(final Node node) throws RepositoryException {
        final Calendar startDate = getStartDate(node);
        final Calendar registrationDeadline = getRegistrationDeadline(node);

        return registrationDeadline.before(startDate);
    }

    private Calendar getStartDate(final Node node) throws RepositoryException, UnknownPropertyException {
        if(node.hasProperty(START_DATE_PROPERTY)) {
            return node.getProperty(START_DATE_PROPERTY).getDate();
        }

        throw new UnknownPropertyException(START_DATE_PROPERTY);
    }

    private Calendar getRegistrationDeadline(final Node node) throws RepositoryException, ValidationException {
        if(node.hasProperty(REGISTRATION_DEADLINE_PROPERTY)) {
            return node.getProperty(REGISTRATION_DEADLINE_PROPERTY).getDate();
        }

        throw new UnknownPropertyException(REGISTRATION_DEADLINE_PROPERTY);
    }
}