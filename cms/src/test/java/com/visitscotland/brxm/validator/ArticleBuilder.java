package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.hippobeans.Article;
import com.visitscotland.brxm.hippobeans.ArticleSection;
import com.visitscotland.brxm.hippobeans.ArticleStyledSection;
import com.visitscotland.brxm.test.SimpleNodeIterator;
import org.mockito.Answers;
import org.mockito.Mockito;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ArticleBuilder {

    private final Node node;
    private final List<Node> sections = new ArrayList<>();

    public ArticleBuilder() throws RepositoryException {
        node = Mockito.mock(Node.class, Mockito.RETURNS_DEEP_STUBS);
    }

    ArticleBuilder theme(String theme) throws RepositoryException {
        when(node.getProperty(Article.THEME).getValue().getString()).thenReturn(theme);
        return this;
    }

    /**
     * This method is going to be called from an iterator. In order to verify that the mock ignores the elements of the
     * list, we need to mock items on the list that won't be used.
     */
    private Node mockSectionNode(){
        return Mockito.mock(Node.class, withSettings().lenient().defaultAnswer(Answers.RETURNS_DEEP_STUBS));
    }

    ArticleBuilder section(boolean hasImage) throws RepositoryException {
        Node section = mockSectionNode();

        when(section.hasNode(ArticleSection.MEDIA)).thenReturn(true);
        when(section.getNode(ArticleSection.MEDIA).hasProperty(ArticleImageValidator.DEFAULT_HIPPO_LINK))
                .thenReturn(true);
        when(section.getNode(ArticleSection.MEDIA).getProperty(ArticleImageValidator.DEFAULT_HIPPO_LINK)
                .getString()).thenReturn(hasImage? "IMAGE-UUID": ImageValidator.EMPTY_IMAGE);

        sections.add(section);

        return this;
    }

    ArticleBuilder section(String title) throws RepositoryException {
        Node section = mockSectionNode();

        when(section.getProperty(ArticleStyledSection.HEADING).getString()).thenReturn(title);

        sections.add(section);

        return this;
    }

    Node build() throws RepositoryException {
        when(node.getNodes(Article.PARAGRAPH)).thenReturn(new SimpleNodeIterator(sections));
        return node;
    }
}
