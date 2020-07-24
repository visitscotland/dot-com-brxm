package com.visitscotland.brmx.translation.plugin;

import com.visitscotland.brmx.beans.Itinerary;
import com.visitscotland.brmx.beans.Page;
import com.visitscotland.brmx.beans.TranslationParent;
import org.hippoecm.frontend.translation.ILocaleProvider;
import org.hippoecm.repository.api.HippoNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.VarargMatcher;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentTranslatorBuildChangeSetListTest {
    public static final String JCR_TYPE = "visitscotland:Page";
    private DocumentTranslator documentTranslator;
    @Mock
    private HippoNode mockSourceNode;
    @Mock
    private JcrDocument mockSourceDocument;
    @Mock
    private ILocaleProvider.HippoLocale mockItalianLocale;
    private ChangeSet mockItalianChangeSet;
    @Mock
    private ILocaleProvider.HippoLocale mockSpanishLocale;
    private ChangeSet mockSpanishChangeSet;
    @Mock
    private ILocaleProvider.HippoLocale mockFrenchLocale;
    private ChangeSet mockFrenchChangeSet;

    private List<ILocaleProvider.HippoLocale> targetLocaleList;
    @BeforeEach
    public void beforeEach() throws Exception {
        targetLocaleList = new ArrayList<>();
        targetLocaleList.add(mockItalianLocale);
        targetLocaleList.add(mockSpanishLocale);
        targetLocaleList.add(mockFrenchLocale);

        documentTranslator = spy(new DocumentTranslator());
        mockItalianChangeSet = createMockChangeSet();
        lenient().doReturn(mockItalianChangeSet).when(documentTranslator).createChangeSet(same(mockItalianLocale));

        mockSpanishChangeSet = createMockChangeSet();
        lenient().doReturn(mockSpanishChangeSet).when(documentTranslator).createChangeSet(same(mockSpanishLocale));

        mockFrenchChangeSet = createMockChangeSet();
        lenient().doReturn(mockFrenchChangeSet).when(documentTranslator).createChangeSet(same(mockFrenchLocale));

        lenient().doReturn(mockSourceDocument).when(documentTranslator).createJcrDocument(same(mockSourceNode));
    }

    @Test
    public void buildChangeSetList_emptyTargetLocaleList() throws Exception {
        // When the target locale list passed to the method is empty
        // it should return an empty ChangeSet not cause an exception
        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, Collections.emptyList());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void buildChangeSetList_sourceDocumentTranslated_notPageType() throws Exception {
        // The source document is already translated into every target locale and is not a Page type
        // Should return an empty ChangeSet list
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(true);

        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(false);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    @Test
    public void buildChangeSetList_sourceDocumentHasMissingLocales_notPageType() throws Exception {
        // The source document has missing locale translations, is not a Page type,
        // Should return a ChangeSet for each missing locale
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(false);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(false);

        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(false);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(mockItalianChangeSet, never()).addDocument(any(JcrDocument.class));
        verify(mockFrenchChangeSet).addDocument(same(mockSourceDocument));
        verify(mockFrenchChangeSet, atMostOnce()).addDocument(any(JcrDocument.class));
        verify(mockSpanishChangeSet).addDocument(same(mockSourceDocument));
        verify(mockSpanishChangeSet, atMostOnce()).addDocument(any(JcrDocument.class));

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    @Test
    public void buildChangeSetList_sourceDocumentTranslated_PageType_notTranslationParent() throws Exception {
        // The source document is already translated into every target locale and is a Page type,
        // but not a TranslationParent
        // Should return an empty ChangeSet list
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(true);

        Page mockHippoBean = mock(Page.class);
        when(mockSourceDocument.asHippoBean()).thenReturn(mockHippoBean);
        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(true);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    @Test
    public void buildChangeSetList_sourceDocumentHasMissingLocales_PageType_notTranslationParent() throws Exception {
        // The source document has missing locale translations, is a Page type,
        // but not a TranslationParent
        // Should return a ChangeSet for each missing locale
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(false);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(false);

        Page mockHippoBean = mock(Page.class);
        when(mockSourceDocument.asHippoBean()).thenReturn(mockHippoBean);
        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(true);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(mockItalianChangeSet, never()).addDocument(any(JcrDocument.class));
        verify(mockFrenchChangeSet).addDocument(same(mockSourceDocument));
        verify(mockFrenchChangeSet, atMostOnce()).addDocument(any(JcrDocument.class));
        verify(mockSpanishChangeSet).addDocument(same(mockSourceDocument));
        verify(mockSpanishChangeSet, atMostOnce()).addDocument(any(JcrDocument.class));

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    @Test
    public void buildChangeSetList_sourceDocumentTranslated_PageType_TranslationParent_noChildren() throws Exception {
        // The source document is already translated into every target locale, is a Page type and a TranslationParent
        // The source document has no siblings
        // Should return an empty ChangeSet list
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(true);

        Itinerary mockHippoBean = mock(Itinerary.class);
        when(mockHippoBean.getChildJcrTypes()).thenReturn(new String[] {"type1", "type2"});

        Node mockContainingFolder = mock(Node.class);
        NodeIterator mockNodeIterator = mock(NodeIterator.class);
        when(mockContainingFolder.getNodes()).thenReturn(mockNodeIterator);
        when(mockNodeIterator.hasNext()).thenReturn(false);

        when(mockSourceDocument.getContainingFolder()).thenReturn(mockContainingFolder);
        when(mockSourceDocument.asHippoBean()).thenReturn(mockHippoBean);
        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(true);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    @Test
    public void buildChangeSetList_sourceDocumentTranslated_PageType_TranslationParent_withNonMatchingChildren() throws Exception {
        // The source document is already translated into every target locale, is a Page type and a TranslationParent
        // The source document has siblings but they do not match any of the JCR types of the TranslationParent
        // The siblings also contain folders and other types that are not hippo:handle or hippo:translated
        // Should return an empty ChangeSet list
        // The source document is already translated into every target locale, is a Page type and a TranslationParent
        // The source document has no siblings that match the TranslationParent child types
        // Should return an empty ChangeSet list
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(true);

        Itinerary mockHippoBean = mock(Itinerary.class);
        when(mockHippoBean.getChildJcrTypes()).thenReturn(new String[] {"type1", "type2"});

        HippoNode mockFolderType = createMockSibling("hippostd:folder");
        HippoNode mockOtherType = createMockSibling("some:other");
        HippoNode mockHandleType = createMockSibling(JcrDocument.HIPPO_HANDLE);
        HippoNode mockTranslatedType = createMockSibling(JcrDocument.HIPPO_TRANSLATED);

        JcrDocument mockHandleDocument = mock(JcrDocument.class);
        doReturn(mockHandleDocument).when(documentTranslator).createJcrDocument(same(mockHandleType));
        when(mockHandleDocument.isNodeType(ArgumentMatchers.<String>any())).thenReturn(false);

        JcrDocument mockTranslatedDocument = mock(JcrDocument.class);
        doReturn(mockTranslatedDocument).when(documentTranslator).createJcrDocument(same(mockTranslatedType));
        when(mockTranslatedDocument.isNodeType(ArgumentMatchers.<String>any())).thenReturn(false);

        HippoNode mockContainingFolder = mock(HippoNode.class);
        when(mockContainingFolder.getNodes()).thenAnswer(new Answer<NodeIterator>() {
            @Override
            public NodeIterator answer(InvocationOnMock invocation) throws Throwable {
                return createNodeIterator(mockFolderType, mockOtherType, mockHandleType,
                        mockTranslatedType);
            }
        });

        when(mockSourceDocument.getContainingFolder()).thenReturn(mockContainingFolder);
        when(mockSourceDocument.asHippoBean()).thenReturn(mockHippoBean);
        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(true);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    @Test
    public void buildChangeSetList_sourceDocumentTranslated_PageType_TranslationParent_withMatchingChildren_allTranslated() throws Exception {
        // The source document is already translated into every target locale, is a Page type and a TranslationParent
        // The source document has siblings they match one of the JCR types of the TranslationParent
        // and are already translated.
        // The siblings also contain folders and other types that are not hippo:handle or hippo:translated
        // Should return an empty ChangeSet list
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(true);

        Itinerary mockHippoBean = mock(Itinerary.class);
        when(mockHippoBean.getChildJcrTypes()).thenReturn(new String[] {"type1", "type2"});

        HippoNode mockFolderType = createMockSibling("hippostd:folder");
        HippoNode mockOtherType = createMockSibling("some:other");
        HippoNode mockHandleType = createMockSibling(JcrDocument.HIPPO_HANDLE);
        HippoNode mockTranslatedType = createMockSibling(JcrDocument.HIPPO_TRANSLATED);
        HippoNode mockTranslationSibling = createMockSibling(JcrDocument.HIPPO_TRANSLATED);

        JcrDocument mockHandleDocument = mock(JcrDocument.class);
        doReturn(mockHandleDocument).when(documentTranslator).createJcrDocument(same(mockHandleType));
        when(mockHandleDocument.isNodeType(ArgumentMatchers.<String>any())).thenReturn(false);

        JcrDocument mockTranslatedDocument = mock(JcrDocument.class);
        doReturn(mockTranslatedDocument).when(documentTranslator).createJcrDocument(same(mockTranslatedType));
        when(mockTranslatedDocument.isNodeType(ArgumentMatchers.<String>any())).thenReturn(false);

        JcrDocument mockTranslationSiblingDocument = mock(JcrDocument.class);
        doReturn(mockTranslationSiblingDocument)
                .when(documentTranslator).createJcrDocument(same(mockTranslationSibling));
        when(mockTranslationSiblingDocument.isNodeType(eq("type1"), eq("type2"))).thenReturn(true);
        when(mockTranslationSiblingDocument.hasTranslation(any(ILocaleProvider.HippoLocale.class))).thenReturn(true);

        HippoNode mockContainingFolder = mock(HippoNode.class);
        when(mockContainingFolder.getNodes()).thenAnswer(new Answer<NodeIterator>() {
            @Override
            public NodeIterator answer(InvocationOnMock invocation) throws Throwable {
                return createNodeIterator(mockFolderType, mockOtherType, mockHandleType,
                        mockTranslatedType, mockTranslationSibling);
            }
        });

        when(mockSourceDocument.getContainingFolder()).thenReturn(mockContainingFolder);
        when(mockSourceDocument.asHippoBean()).thenReturn(mockHippoBean);
        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(true);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    @Test
    public void buildChangeSetList_sourceDocumentTranslated_PageType_TranslationParent_withMatchingChildren_missingLocales() throws Exception {
        // The source document is already translated into every target locale, is a Page type and a TranslationParent.
        // The source document has siblings they match one of the JCR types of the TranslationParent
        // and have missing locales.
        // The siblings also contain folders and other types that are not hippo:handle or hippo:translated
        // Should return a ChangeSet list for each sibling missing locale and the sibling
        // documents should be in the relevant ChangeSet
        when(mockSourceDocument.hasTranslation(same(mockItalianLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(true);
        when(mockSourceDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(true);

        Itinerary mockHippoBean = mock(Itinerary.class);
        when(mockHippoBean.getChildJcrTypes()).thenReturn(new String[] {"type1", "type2"});

        HippoNode mockFolderType = createMockSibling("hippostd:folder");
        HippoNode mockOtherType = createMockSibling("some:other");
        HippoNode mockHandleType = createMockSibling(JcrDocument.HIPPO_HANDLE);
        HippoNode mockTranslatedType = createMockSibling(JcrDocument.HIPPO_TRANSLATED);
        HippoNode mockTranslationSibling = createMockSibling(JcrDocument.HIPPO_TRANSLATED);

        JcrDocument mockHandleDocument = mock(JcrDocument.class);
        doReturn(mockHandleDocument).when(documentTranslator).createJcrDocument(same(mockHandleType));
        when(mockHandleDocument.isNodeType(ArgumentMatchers.<String>any())).thenReturn(false);

        JcrDocument mockTranslatedDocument = mock(JcrDocument.class);
        doReturn(mockTranslatedDocument).when(documentTranslator).createJcrDocument(same(mockTranslatedType));
        when(mockTranslatedDocument.isNodeType(ArgumentMatchers.<String>any())).thenReturn(false);

        JcrDocument mockTranslationSiblingDocument = mock(JcrDocument.class);
        doReturn(mockTranslationSiblingDocument)
                .when(documentTranslator).createJcrDocument(same(mockTranslationSibling));
        when(mockTranslationSiblingDocument.isNodeType(eq("type1"), eq("type2"))).thenReturn(true);
        when(mockTranslationSiblingDocument.hasTranslation(same(mockItalianLocale))).thenReturn(false);
        when(mockTranslationSiblingDocument.hasTranslation(same(mockFrenchLocale))).thenReturn(false);
        when(mockTranslationSiblingDocument.hasTranslation(same(mockSpanishLocale))).thenReturn(true);

        HippoNode mockContainingFolder = mock(HippoNode.class);
        when(mockContainingFolder.getNodes()).thenAnswer(new Answer<NodeIterator>() {
            @Override
            public NodeIterator answer(InvocationOnMock invocation) throws Throwable {
                return createNodeIterator(mockFolderType, mockOtherType, mockHandleType,
                        mockTranslatedType, mockTranslationSibling);
            }
        });

        when(mockSourceDocument.getContainingFolder()).thenReturn(mockContainingFolder);
        when(mockSourceDocument.asHippoBean()).thenReturn(mockHippoBean);
        when(mockSourceDocument.isNodeType(eq(JCR_TYPE))).thenReturn(true);

        List<ChangeSet> result = documentTranslator.buildChangeSetList(mockSourceNode, targetLocaleList);

        assertNotNull(result);
        //assertEquals(2, result.size());

        verify(mockSpanishChangeSet, never()).addDocument(any(JcrDocument.class));
        verify(mockFrenchChangeSet).addDocument(same(mockTranslationSiblingDocument));
        verify(mockFrenchChangeSet, atMostOnce()).addDocument(any(JcrDocument.class));
        verify(mockItalianChangeSet).addDocument(same(mockTranslationSiblingDocument));
        verify(mockItalianChangeSet, atMostOnce()).addDocument(any(JcrDocument.class));

        verify(mockItalianChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockFrenchChangeSet).populateFolders(same(mockSourceDocument));
        verify(mockSpanishChangeSet).populateFolders(same(mockSourceDocument));
    }

    private ChangeSet createMockChangeSet() throws Exception {
        ChangeSet mockChangeSet = mock(ChangeSet.class);
        final List<JcrDocument> documentList = new ArrayList<>();
        lenient().doAnswer(new Answer<List<JcrDocument>>() {
            @Override
            public List<JcrDocument> answer(InvocationOnMock invocation) throws Throwable {
                return documentList;
            }
        }).when(mockChangeSet).getDocuments();
        lenient().doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                documentList.add(invocation.getArgument(0));
                return null;
            }
        }).when(mockChangeSet).addDocument(any(JcrDocument.class));
        return mockChangeSet;
    }

    private NodeIterator createNodeIterator(Node... nodes) {
        List<Node> nodeList = Arrays.asList(nodes);
        final Iterator<Node> nodeIterator = nodeList.iterator();
        NodeIterator mockNodeIterator = mock(NodeIterator.class);
        when(mockNodeIterator.hasNext()).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return nodeIterator.hasNext();
            }
        });
        when(mockNodeIterator.nextNode()).thenAnswer(new Answer<Node>() {
            @Override
            public Node answer(InvocationOnMock invocation) throws Throwable {
                return nodeIterator.next();
            }
        });
        return mockNodeIterator;
    }

    private HippoNode createMockSibling(String... matchingTypes) throws Exception {
        HippoNode mockNode = mock(HippoNode.class);
        lenient().when(mockNode.isNodeType(anyString())).thenReturn(false);
        for (String matchingType : matchingTypes) {
            lenient().when(mockNode.isNodeType(eq(matchingType))).thenReturn(true);
        }
        return mockNode;
    }
}
