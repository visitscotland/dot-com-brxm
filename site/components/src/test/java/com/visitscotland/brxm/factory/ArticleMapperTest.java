package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Article;
import com.visitscotland.brxm.hippobeans.ArticleSection;
import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.hippobeans.Quote;
import com.visitscotland.brxm.hippobeans.Video;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.mapper.ArticleMapper;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.mapper.QuoteMapper;
import com.visitscotland.brxm.model.ArticleModule;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleMapperTest {

    @InjectMocks
    ArticleMapper factory;

    @Mock
    ImageMapper imageFactory;

    @Mock
    LinkService linkService;

    @Mock
    AnchorFormatter anchorFormatter;

    @Mock
    QuoteMapper embedder;

    private static final String DUMMY_ANCHOR = "dummy-anchor";

    @Test
    @DisplayName("Main Article - Populates all fields")
    void getModule() {
        Article article = mock(Article.class);
        when(article.getMediaItem()).thenReturn(mock(Image.class));
        when(article.getTitle()).thenReturn("Title");
        when(article.getAnchor()).thenReturn(DUMMY_ANCHOR);
        when(article.getCopy()).thenReturn(mock(HippoHtml.class));
        when(anchorFormatter.getAnchorOrFallback(anyString(), any())).thenReturn(DUMMY_ANCHOR);

        ArticleModule module = factory.getModule(article, null, false);

        verify(imageFactory, only()).getImage(any(Image.class), any(), any());
        verify(anchorFormatter, times(1)).getAnchorOrFallback(anyString(), any());
        assertEquals("Title", module.getTitle());
        assertEquals(DUMMY_ANCHOR, module.getAnchor());
        assertEquals(HippoHtml.class, module.getIntroduction().getClass());
        assertEquals(article, module.getHippoBean());
    }

    @Test
    @DisplayName("VS-4069 Main Article - Populates all fields with video")
    void getModuleWithMainVideo() {
        Article article = mock(Article.class);
        VideoLink videoLink = mock(VideoLink.class);

        when(videoLink.getVideoLink()).thenReturn(mock(Video.class));
        when(article.getMediaItem()).thenReturn(videoLink);
        when(article.getTitle()).thenReturn("Title");
        when(article.getAnchor()).thenReturn(DUMMY_ANCHOR);
        when(article.getCopy()).thenReturn(mock(HippoHtml.class));
        when(anchorFormatter.getAnchorOrFallback(anyString(), any())).thenReturn(DUMMY_ANCHOR);

        ArticleModule module =factory.getModule(article, null, false);

        verify(linkService, only()).createVideo(any(Video.class), any(), any());
        verify(anchorFormatter, times(1)).getAnchorOrFallback(anyString(), any());
        assertEquals("Title", module.getTitle());
        assertEquals(DUMMY_ANCHOR, module.getAnchor());
        assertEquals(HippoHtml.class, module.getIntroduction().getClass());
        assertEquals(article, module.getHippoBean());
    }


    @Test
    @DisplayName("Article Sections - Populates all fields")
    void getModuleSections() {
        ArticleSection section = mock(ArticleSection.class);
        Article article = mock(Article.class);

        when(section.getCopy()).thenReturn(mock(HippoHtml.class));
        when(section.getMediaItem()).thenReturn(mock(Image.class));
        when(section.getQuote()).thenReturn(mock(Quote.class));
        when(article.getParagraph()).thenReturn(Arrays.asList(section, section));
        when(article.getAnchor()).thenReturn(DUMMY_ANCHOR);
        when(anchorFormatter.getAnchorOrFallback(anyString(), any())).thenReturn(DUMMY_ANCHOR);

        ArticleModule module = factory.getModule(article, null, false);

        verify(imageFactory, times(2)).getImage(any(Image.class), any(), any());
        verify(embedder, times(2)).getQuote(any(Quote.class), any(), any());
        verify(anchorFormatter, times(1)).getAnchorOrFallback(anyString(), any());
        assertEquals(2, module.getSections().size());
    }

    @Test
    @DisplayName("VS-4069 Article Sections - Populates all fields with video")
    void getModuleSectionsWithVideo() {
        ArticleSection section = mock(ArticleSection.class);
        Article article = mock(Article.class);

        VideoLink videoLink = mock(VideoLink.class);
        when(videoLink.getVideoLink()).thenReturn(mock(Video.class));
        when(section.getMediaItem()).thenReturn(videoLink);
        when(section.getCopy()).thenReturn(mock(HippoHtml.class));
        when(section.getQuote()).thenReturn(mock(Quote.class));
        when(article.getParagraph()).thenReturn(Arrays.asList(section, section));
        when(article.getAnchor()).thenReturn(DUMMY_ANCHOR);
        when(anchorFormatter.getAnchorOrFallback(anyString(), any())).thenReturn(DUMMY_ANCHOR);

        ArticleModule module = factory.getModule(article, null, false);

        verify(linkService, times(2)).createVideo(any(Video.class), any(), any());
        verify(embedder, times(2)).getQuote(any(Quote.class), any(), any());
        verify(anchorFormatter, times(1)).getAnchorOrFallback(anyString(), any());
        assertEquals(2, module.getSections().size());
    }
}
