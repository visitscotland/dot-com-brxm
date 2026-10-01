package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.comparator.ComparatorMapper;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * jcr:Name = visitscotland:provider-name-validator
 */
public class ProviderNameValidator implements Validator<Node> {

    private static final Logger logger = LoggerFactory.getLogger(ProviderNameValidator.class);

    private static final String NAME_PROPERTY = "visitscotland:name";

    private static final List<String> ILLEGAL_CHARS = Arrays.asList("\\","?","*",":","[","]");

    @Override
    public Optional<Violation> validate(ValidationContext context, Node node) {

        try {
            if (node.hasProperty(NAME_PROPERTY)) {
                final Property property = node.getProperty(NAME_PROPERTY);
                final String name = property.getString();

                for (String illegalChar : ILLEGAL_CHARS) {
                    if (name.contains(illegalChar)) {
                        return Optional.of(context.createViolation("invalidCharacters"));
                    }
                }
                if (name.length() > 31) {
                    return Optional.of(context.createViolation("stringTooLong"));
                }
            }
        } catch (RepositoryException e) {
            logger.warn("An error occurred during validation.");
            return Optional.of(context.createViolation("exception"));
        }
        return Optional.empty();
    }
}
