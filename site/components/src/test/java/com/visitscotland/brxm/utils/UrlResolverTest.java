package com.visitscotland.brxm.utils;

import com.visitscotland.brxm.components.navigation.MenuItem;
import com.visitscotland.brxm.hippobeans.Page;

import org.hippoecm.hst.core.linking.HstLink;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UrlResolverTest {
    @Mock
    private MenuItem menuItem;

    private static final String HASHTAG = "#";

    @Test
    void When_GetUrl_WithPlainLink_ExpectPlainLinkReturned() {
        final Page page = Mockito.mock(Page.class);
        final String expected = "https://example.com";

        when(menuItem.getPage()).thenReturn(page);
        when(menuItem.getPlainLink()).thenReturn(expected);

        assertEquals(expected, UrlResolver.getUrl(menuItem));
        verify(menuItem, times(1)).getPlainLink();
    }

    @Test
    void When_GetUrl_WithHstLink_ExpectPageNotFoundReturned() {
        final HstLink hstLink = Mockito.mock(HstLink.class);
        final String expected = "pagenotfound";

        when(menuItem.getPage()).thenReturn(null);
        when(menuItem.getHstLink()).thenReturn(hstLink);

        assertEquals(expected, UrlResolver.getUrl(menuItem));

        verify(menuItem, times(1)).getPage();
        verify(menuItem, times(1)).getHstLink();
    }

    @Test
    void When_GetUrl_WithExternalLink_ExpectExternalLinkWithoutQuotesReturned() {
        final String input = "\"https://external.com\"";
        final String expected = "https://external.com";

        when(menuItem.getPage()).thenReturn(null);
        when(menuItem.getHstLink()).thenReturn(null);
        when(menuItem.getExternalLink()).thenReturn(input);

        assertEquals(expected, UrlResolver.getUrl(menuItem));

        verify(menuItem, times(1)).getPage();
        verify(menuItem, times(1)).getHstLink();
        verify(menuItem, times(2)).getExternalLink();
    }

    @Test
    void When_GetUrl_WithNoLinks_ExpectHashReturned() {
        when(menuItem.getPage()).thenReturn(null);
        when(menuItem.getHstLink()).thenReturn(null);
        when(menuItem.getExternalLink()).thenReturn(null);

        assertEquals(HASHTAG, UrlResolver.getUrl(menuItem));
    }

    @Test
    void When_GetUrl_WithNullItem_ExpectHashReturned() {
        assertEquals(HASHTAG, UrlResolver.getUrl(null));
    }
}