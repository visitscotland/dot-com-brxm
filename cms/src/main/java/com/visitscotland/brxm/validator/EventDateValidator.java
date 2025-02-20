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
import java.util.TimeZone;

/**
 * jcrType = visitscotland:event-date-validator
 */
public class EventDateValidator implements Validator<Node> {
    private static final Logger logger = LogManager.getLogger(EventDateValidator.class);
    private static final String START_DATE_PROPERTY = "visitscotland:startDate";
    private static final String END_DATE_PROPERTY = "visitscotland:endDate";

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {
        try {
            if(isEventDatesValid(node)) {
                return Optional.empty();
            }

            return Optional.of(context.createViolation());
        } catch (RepositoryException | ValidationException exception) {
            logger.error(exception.getMessage(), exception);
            return Optional.of(context.createViolation("exception"));
        }
    }

    private boolean isEventDatesValid(final Node node) throws RepositoryException {
        final Calendar startDate = getStartDate(node);
        final Calendar endDate = getEndDate(node);

        return isCalendarDefaultDate(endDate) || endDate.after(startDate);
    }

    private static boolean isCalendarDefaultDate(final Calendar calendar) {
        final Calendar defaultDate = new Calendar.Builder()
            .setCalendarType("gregory")
            .setDate(1, 1, 1)
            .setTimeOfDay(12, 0, 0)
            .setTimeZone(TimeZone.getTimeZone("UTC"))
            .build();

        return calendar.equals(defaultDate);
    }

    private Calendar getStartDate(final Node node) throws RepositoryException, UnknownPropertyException {
        if(node.hasProperty(START_DATE_PROPERTY)) {
            return node.getProperty(START_DATE_PROPERTY).getDate();
        }

        throw new UnknownPropertyException(START_DATE_PROPERTY);
    }

    private Calendar getEndDate(final Node node) throws RepositoryException, UnknownPropertyException {
        if(node.hasProperty(END_DATE_PROPERTY)) {
            return node.getProperty(END_DATE_PROPERTY).getDate();
        }

        throw new UnknownPropertyException(END_DATE_PROPERTY);
    }
}