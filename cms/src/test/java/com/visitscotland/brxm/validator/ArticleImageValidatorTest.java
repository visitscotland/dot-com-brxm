package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.hippobeans.Article;
import com.visitscotland.brxm.hippobeans.ArticleSection;
import com.visitscotland.brxm.test.SimpleNodeIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleImageValidatorTest {

    private static final String IMAGE_LIST = "image-list";
    private static final String IMAGE_LIST_2 = "image-list-2";

    @Mock
    ValidationContext context;

    private Node getDefaultConfig() throws RepositoryException {
        Node node = Mockito.mock(Node.class, Mockito.RETURNS_DEEP_STUBS);
        List<Value> values = new ArrayList<>();
        for (String theme : new String[] {IMAGE_LIST, IMAGE_LIST_2}){
            Value value = Mockito.mock(Value.class, Mockito.RETURNS_DEEP_STUBS);
            Mockito.when(value.getString()).thenReturn(theme);
            values.add(value);
        }
        when(node.hasProperty(ArticleImageValidator.THEMES)).thenReturn(true);
        when(node.getProperty(ArticleImageValidator.THEMES).getValues()).thenReturn(values.toArray(new Value[]{}));
        return node;
    }

    @ParameterizedTest
    @ValueSource(strings = {IMAGE_LIST, "other"})
    @DisplayName("An Empty List is Valid")
    void validate_emptyList(String theme) throws RepositoryException {
        ArticleImageValidator validator = new ArticleImageValidator(getDefaultConfig());

        Optional<Violation> violation = validator.validate(null, new ArticleBuilder()
                .theme(theme).build());

        Assertions.assertTrue(violation.isEmpty());
    }

    @Test
    @DisplayName("Images are not required for some layouts")
    void validate_imageNotRequired() throws RepositoryException {
        ArticleImageValidator validator = new ArticleImageValidator(getDefaultConfig());

        Optional<Violation> violation = validator.validate(null, new ArticleBuilder()
                .theme("other").section(false).section(true).build());

        Assertions.assertTrue(violation.isEmpty());
    }

    @Test
    @DisplayName("Images are required for all elements for some layouts")
    void validate_imageRequiredAndProvided() throws RepositoryException {
        ArticleImageValidator validator = new ArticleImageValidator(getDefaultConfig());

        Optional<Violation> violation = validator.validate(context, new ArticleBuilder()
                .theme(IMAGE_LIST).section(true).section(true).section(true).build());

        Assertions.assertTrue(violation.isEmpty());
    }

    @Test
    @DisplayName("Images are required for all elements for some layouts")
    void validate_imageRequiredNotProvided() throws RepositoryException {
        ArticleImageValidator validator = new ArticleImageValidator(getDefaultConfig());
        ValidationContext context = Mockito.mock(ValidationContext.class);
        when((context.createViolation())).thenReturn(Mockito.mock(Violation.class));

        Optional<Violation> violation = validator.validate(context, new ArticleBuilder()
                .theme(IMAGE_LIST).section(true).section(true).section(false).build());

        Assertions.assertFalse(violation.isEmpty());
    }

    @Test
    @DisplayName("Images validation is ignored for some layouts")
    void validate_imageIgnored() throws RepositoryException {
        ArticleImageValidator validator = new ArticleImageValidator(getDefaultConfig());
        ValidationContext context = Mockito.mock(ValidationContext.class);
        when((context.createViolation())).thenReturn(Mockito.mock(Violation.class));

        Optional<Violation> violation = validator.validate(context, new ArticleBuilder()
                .theme(IMAGE_LIST).section(false).build());

        Assertions.assertFalse(violation.isEmpty());
    }

    private static class ArticleBuilder {

        private final Node node;
        private final List<Node> sections = new ArrayList<>();

        ArticleBuilder() throws RepositoryException {
            node = Mockito.mock(Node.class, Mockito.RETURNS_DEEP_STUBS);

        }

        ArticleBuilder theme(String theme) throws RepositoryException {
            when(node.getProperty(Article.THEME).getValue().getString()).thenReturn(theme);
            return this;
        }

        ArticleBuilder section(boolean hasImage) throws RepositoryException {

            Node section = Mockito.mock(Node.class, Mockito.RETURNS_DEEP_STUBS);
            lenient().when(section.hasNode(ArticleSection.MEDIA)).thenReturn(true);
            lenient().when(section.getNode(ArticleSection.MEDIA).hasProperty(ArticleImageValidator.DEFAULT_HIPPO_LINK))
                    .thenReturn(true);
            lenient().when(section.getNode(ArticleSection.MEDIA).getProperty(ArticleImageValidator.DEFAULT_HIPPO_LINK)
                    .getString()).thenReturn(hasImage? "IMAGE-UUID": ImageValidator.EMPTY_IMAGE);

            sections.add(section);

            return this;
        }

        Node build() throws RepositoryException {
            when(node.getNodes(Article.PARAGRAPH)).thenReturn(new SimpleNodeIterator(sections));
            return node;
        }
    }
}
