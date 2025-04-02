package com.visitscotland.brxm.translation;

import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import org.hippoecm.hst.core.request.ResolvedVirtualHost;

import org.hippoecm.hst.mock.core.request.MockHstRequestContext;
import org.hippoecm.hst.mock.core.component.MockHstRequest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Answers;
import org.mockito.Mock;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
class TranslationFallbackProviderTest {
    private static final String REQUEST_PATH = "/";
    private static final String ENGLISH_CONTENT_PATH = "/content/documents/visitscotland";
    private static final String RELATIVE_CONTENT_PATH = "sandbox/dummy-folder/dummy-folder/content";
    private static final String VIRTUALHOSTS_REQUEST_ATTR = "org.hippoecm.hst.configuration.hosting.VirtualHost.requestAttr";

    @Mock private MockHstRequest hstRequest;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private MockHstRequestContext hstRequestContext;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private ResolvedVirtualHost resolvedVirtualHost;

    private TranslationFallbackProvider translationFallbackProvider;

    @BeforeEach
    void setUp() {
        translationFallbackProvider = new TranslationFallbackProvider();
    }

    @Test
    void getContentBeanForRequest_ContentBeanPresent_OptionalOfHippoBean() {
        final var hippoBean = mock(HippoBean.class);

        when(hstRequest.getRequestContext()).thenReturn(hstRequestContext);
        when(hstRequestContext.getContentBean()).thenReturn(hippoBean);

        final var result = translationFallbackProvider.getContentBeanForRequest(hstRequest);

        Assertions.assertTrue(result.isPresent());
        verify(hstRequest).getRequestContext();
        verify(hstRequestContext).getContentBean();
    }

    @Test
    void getContentBeanForRequest_ContentBeanNotPresentAndFallbackPresent_OptionalOfHippoBean() throws ObjectBeanManagerException {
        final var hippoBean = mock(HippoBean.class);

        when(hstRequest.getRequestContext()).thenReturn(hstRequestContext);
        when(hstRequestContext.getContentBean()).thenReturn(null);
        when(hstRequest.getAttribute(VIRTUALHOSTS_REQUEST_ATTR)).thenReturn(resolvedVirtualHost);
        when(resolvedVirtualHost.matchMount(REQUEST_PATH).getMount().getContentPath()).thenReturn(ENGLISH_CONTENT_PATH);
        when(hstRequestContext.getResolvedSiteMapItem().getRelativeContentPath()).thenReturn(RELATIVE_CONTENT_PATH);
        when(hstRequestContext.getObjectBeanManager().getObject(anyString())).thenReturn(hippoBean);

        final var result = translationFallbackProvider.getContentBeanForRequest(hstRequest);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hippoBean, result.get());
        verify(hstRequest).getAttribute(eq(VIRTUALHOSTS_REQUEST_ATTR));
        verify(resolvedVirtualHost.matchMount(REQUEST_PATH).getMount()).getContentPath();
        verify(hstRequestContext.getResolvedSiteMapItem()).getRelativeContentPath();
        verify(hstRequestContext.getObjectBeanManager()).getObject(anyString());
    }

    @Test
    void getContentBeanForRequest_ContentBeanNotPresentAndNoVirtualHostRequestAttribute_EmptyOptional() {
        when(hstRequest.getRequestContext()).thenReturn(hstRequestContext);
        when(hstRequestContext.getContentBean()).thenReturn(null);
        when(hstRequest.getAttribute(VIRTUALHOSTS_REQUEST_ATTR)).thenReturn(null);

        final var result = translationFallbackProvider.getContentBeanForRequest(hstRequest);

        Assertions.assertFalse(result.isPresent());
        verify(hstRequest).getAttribute(eq(VIRTUALHOSTS_REQUEST_ATTR));
    }

    @Test
    void getContentBeanForRequest_ContentBeanNotPresentAndNoMatchedMount_EmptyOptional() {
        when(hstRequest.getRequestContext()).thenReturn(hstRequestContext);
        when(hstRequestContext.getContentBean()).thenReturn(null);
        when(hstRequest.getAttribute(VIRTUALHOSTS_REQUEST_ATTR)).thenReturn(resolvedVirtualHost);
        when(resolvedVirtualHost.matchMount(REQUEST_PATH)).thenReturn(null);

        final var result = translationFallbackProvider.getContentBeanForRequest(hstRequest);

        Assertions.assertFalse(result.isPresent());
        verify(resolvedVirtualHost).matchMount(eq(REQUEST_PATH));
    }

    @Test
    void getContentBeanForRequest_ContentBeanNotPresentAndRequestContextHasNoResolvedSiteMapItem_OptionalEmpty() {
        when(hstRequest.getRequestContext()).thenReturn(hstRequestContext);
        when(hstRequestContext.getContentBean()).thenReturn(null);
        when(hstRequest.getAttribute(VIRTUALHOSTS_REQUEST_ATTR)).thenReturn(resolvedVirtualHost);
        when(resolvedVirtualHost.matchMount(REQUEST_PATH).getMount().getContentPath()).thenReturn(ENGLISH_CONTENT_PATH);
        when(hstRequestContext.getResolvedSiteMapItem()).thenReturn(null);

        final var result = translationFallbackProvider.getContentBeanForRequest(hstRequest);

        Assertions.assertFalse(result.isPresent());
        verify(hstRequestContext).getResolvedSiteMapItem();
    }
}