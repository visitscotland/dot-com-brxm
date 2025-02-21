package com.visitscotland.brxm.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class ContentListHeadingValidatorTest {

    private static final String CONTENT_LIST = "content-list";
    private static final String CONTENT_LIST_2 = "content-list-2";
    private static final String NO_CONTENT_LIST = "other";

    @Mock
    ValidationContext context;

    private Node getDefaultConfig() throws RepositoryException {
        Node node = Mockito.mock(Node.class, Mockito.RETURNS_DEEP_STUBS);
        List<Value> values = new ArrayList<>();
        for (String theme : new String[] {CONTENT_LIST, CONTENT_LIST_2}){
            Value value = Mockito.mock(Value.class, Mockito.RETURNS_DEEP_STUBS);
            Mockito.when(value.getString()).thenReturn(theme);
            values.add(value);
        }
        when(node.hasProperty(ContentListHeadingValidator.MANDATORY_HEADING)).thenReturn(true);
        when(node.getProperty(ContentListHeadingValidator.MANDATORY_HEADING).getValues())
                .thenReturn(values.toArray(new Value[]{}));
        return node;
    }

    @ParameterizedTest
    @ValueSource(strings = {CONTENT_LIST, "other"})
    @DisplayName("An Empty List is Valid")
    void validate_emptyList(String theme) throws RepositoryException {
        ContentListHeadingValidator validator = new ContentListHeadingValidator(getDefaultConfig());

        Optional<Violation> violation = validator.validate(null, new ArticleBuilder()
                .theme(theme).build());

        Assertions.assertTrue(violation.isEmpty());
    }

    @Test
    @DisplayName("Headings are not required for some layouts")
    void validate_imageNotRequired() throws RepositoryException {
        ContentListHeadingValidator validator = new ContentListHeadingValidator(getDefaultConfig());

        Optional<Violation> violation = validator.validate(null, new ArticleBuilder()
                .theme(NO_CONTENT_LIST).section("heading").section(true).build());

        Assertions.assertTrue(violation.isEmpty());
    }

    @Test
    @DisplayName("Headings are required for all elements for some layouts")
    void validate_imageRequiredAndProvided() throws RepositoryException {
        ContentListHeadingValidator validator = new ContentListHeadingValidator(getDefaultConfig());

        Optional<Violation> violation = validator.validate(context, new ArticleBuilder()
                .theme(CONTENT_LIST).section("heading").section("heading").section("heading").build());

        Assertions.assertTrue(violation.isEmpty());
    }

    @Test
    @DisplayName("Images are required for all elements for some layouts")
    void validate_imageRequiredNotProvided() throws RepositoryException {
        ContentListHeadingValidator validator = new ContentListHeadingValidator(getDefaultConfig());
        ValidationContext context = Mockito.mock(ValidationContext.class);
        when((context.createViolation())).thenReturn(Mockito.mock(Violation.class));


        Assertions.assertFalse(validator.validate(context, new ArticleBuilder()
                .theme(CONTENT_LIST).section("").build()).isEmpty());
        Assertions.assertFalse(validator.validate(context, new ArticleBuilder()
                .theme(CONTENT_LIST).section(null).build()).isEmpty());
        Assertions.assertFalse(validator.validate(context, new ArticleBuilder()
                .theme(CONTENT_LIST).section("heading").section("").build()).isEmpty());
    }

    @Test
    @DisplayName("Images validation is ignored for some layouts")
    void validate_imageIgnored() throws RepositoryException {
        ContentListHeadingValidator validator = new ContentListHeadingValidator(getDefaultConfig());
        ValidationContext context = Mockito.mock(ValidationContext.class);
        when((context.createViolation())).thenReturn(Mockito.mock(Violation.class));

        Assertions.assertTrue(validator.validate(context, new ArticleBuilder()
                .theme(NO_CONTENT_LIST).section("").build()).isEmpty());
        Assertions.assertTrue(validator.validate(context, new ArticleBuilder()
                .theme(NO_CONTENT_LIST).section(null).build()).isEmpty());
        Assertions.assertTrue(validator.validate(context, new ArticleBuilder()
                .theme(NO_CONTENT_LIST).section("heading").section("").build()).isEmpty());
    }
}
