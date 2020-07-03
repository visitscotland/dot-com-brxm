package com.visitscotland.brmx.translation.plugin;

import org.hippoecm.frontend.translation.ILocaleProvider;
import org.hippoecm.repository.api.HippoNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeSetAddDocumentTest {
    private ChangeSet changeSet;
    @Mock
    private ILocaleProvider.HippoLocale mockLocale;

    @BeforeEach
    public void beforeEach() {
        changeSet = new ChangeSet(mockLocale);
    }

    @Test
    public void addDocument() throws Exception {
        HippoNode mockHandle = mock(HippoNode.class);
        when(mockHandle.getName()).thenReturn("docUrl");
        when(mockHandle.getDisplayName()).thenReturn("docName");
        JcrDocument mockDocument = mock(JcrDocument.class);
        when(mockDocument.getHandle()).thenReturn(mockHandle);

        when(mockLocale.getName()).thenReturn("locale");

        changeSet.addDocument(mockDocument);

        assertFalse(changeSet.getDocuments().isEmpty());
        FolderTranslation translation = changeSet.getDocuments().get(0);
        assertEquals("docUrl", translation.getUrl());
        assertEquals("docUrl", translation.getUrlfr());
        assertEquals("docName", translation.getName());
        assertEquals("docName (locale)", translation.getNamefr());
    }
}
