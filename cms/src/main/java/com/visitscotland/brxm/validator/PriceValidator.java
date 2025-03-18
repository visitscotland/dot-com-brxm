package com.visitscotland.brxm.validator;

import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import java.util.Optional;

/**
 * jcr:Name = visitscotland:event-price-validator
 */
public class PriceValidator implements Validator<Node> {
    private static final String AMOUNT_JCR_PROPERTY = "visitscotland:price";

    public PriceValidator() { }

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {
        try {
            if(isPriceInvalid(node)) {
                return Optional.of(context.createViolation());
            }
        } catch (RepositoryException exception) {
            return Optional.of(context.createViolation("exception"));
        }

        return Optional.empty();
    }

    /**
     * Checks if a price is valid
     * @param node The JCR node to check
     * @return true if the price is invalid, false otherwise
     * @throws RepositoryException if there is an error accessing the JCR node
     */
    private boolean isPriceInvalid(final Node node) throws RepositoryException {
        if(node.hasProperty(AMOUNT_JCR_PROPERTY)) {
            final double price = node.getProperty(AMOUNT_JCR_PROPERTY).getDouble();
            return Double.isNaN(price) || price < 0;
        }

       return true;
    }
}