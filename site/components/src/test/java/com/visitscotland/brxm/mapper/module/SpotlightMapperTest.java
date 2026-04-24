package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.SpotlightModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.pagebuilder.InvalidContentException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.utils.ContentLogger;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpotlightMapperTest {

    @InjectMocks
    SpotlightMapper mapper;

    @Mock
    LinkService linkService;

    @Mock
    AnchorFormatter anchorFormatter;

    @Mock
    ContentLogger contentLogger;

    @Mock
    PageCompositionHelper compositionHelper;

    @Test
    @DisplayName("Spotlight - Populates all fields")
    void createModule() throws Exception {
        Spotlight spotlight = mockBasicSpotlight();

        when(spotlight.getTitle()).thenReturn("Title");
        when(spotlight.getCopy()).thenReturn(mock(HippoHtml.class));
        when(spotlight.getLayout()).thenReturn("layout");
        when(spotlight.getAnchor()).thenReturn("dummy-anchor");
        when(spotlight.getImage()).thenReturn(mock(Image.class));

        when(anchorFormatter.getAnchorOrFallback(anyString(), any())).thenReturn("dummy-anchor");
        SpotlightModule module = mapper.createModule(spotlight, compositionHelper);

        verify(linkService, only()).createSimpleLink(any(), any(), any());
        verify(anchorFormatter, times(1)).getAnchorOrFallback(anyString(), any());

        assertEquals("Title", module.getTitle());
        assertEquals("layout", module.getLayout());
        assertEquals("dummy-anchor", module.getAnchor());
        assertNotNull(module.getCopy());
        assertNotNull(module.getCta());
        assertNotNull(module.getCta().getLink());
        assertNotNull(module.getCta().getLabel());
        assertEquals(spotlight, module.getHippoBean());
        assertNotNull(module.getImage());
        assertNotNull(module.getImage().getCmsImage());
    }

    @Test
    @DisplayName("Spotlight - Invalid CTA link throws exception")
    void createModuleInvalidCta() {
        Spotlight document = mock(Spotlight.class);
        CMSLink cta = mock(CMSLink.class);
        HippoBean linkable = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));

        when(document.getPath()).thenReturn("/content/spotlight");
        when(document.getCtaLink()).thenReturn(cta);
        when(cta.getLink()).thenReturn(linkable);

        when(linkService.createSimpleLink(any(), any(), any())).thenReturn(new FlatLink());

        assertThrows(
                InvalidContentException.class,
                () -> mapper.createModule(document, compositionHelper)
        );
    }

    @Test
    @DisplayName("Spotlight - Ambient video added when valid")
    void createModuleWithAmbientVideo() throws Exception {
        Spotlight spotlight = mockBasicSpotlight();
        VideoLink videoLink = mock(VideoLink.class);

        EnhancedLink enhancedLink = new EnhancedLink();
        enhancedLink.setLink("https://static/video.mp4");

        when(videoLink.getVideoLink()).thenReturn(mock(Video.class));
        when(spotlight.getVideo()).thenReturn(videoLink);
        when(compositionHelper.getLocale()).thenReturn(Locale.UK);

        when(linkService.createVideo(any(Video.class), any(), any())).thenReturn(enhancedLink);

        SpotlightModule module = mapper.createModule(spotlight, compositionHelper);

        verify(linkService).createVideo(any(Video.class), any(), any());
        verify(compositionHelper).addAllSiteLabels("ambient-video");
        assertEquals("https://static/video.mp4", module.getAmbientVideo());
    }

    @Test
    @DisplayName("Spotlight - Youtube video rejected")
    void createModuleWithYoutubeVideo() throws Exception {
        Spotlight spotlight = mockBasicSpotlight();
        VideoLink videoLink = mock(VideoLink.class);
        EnhancedLink enhancedLink = new EnhancedLink();
        enhancedLink.setYoutubeId("youtube-id");

        when(videoLink.getVideoLink()).thenReturn(mock(Video.class));
        when(spotlight.getVideo()).thenReturn(videoLink);
        when(spotlight.getPath()).thenReturn("/content/spotlight");

        when(linkService.createVideo(any(Video.class), any(), any())).thenReturn(enhancedLink);

        SpotlightModule module = mapper.createModule(spotlight, compositionHelper);

        assertNull(module.getAmbientVideo());
        assertTrue(
                module.getErrorMessages()
                        .contains("The video type is not compatible with this module")
        );

        verify(contentLogger).warn(
                contains("not compatible"),
                eq("/content/spotlight")
        );
    }

    private Spotlight mockBasicSpotlight() {
        Spotlight spotlight = mock(Spotlight.class);
        CMSLink cta = mock(CMSLink.class);

        when(spotlight.getCtaLink()).thenReturn(cta);
        when(cta.getLink()).thenReturn(mock(Video.class));
        when(cta.getLabel()).thenReturn("Click here!");

        when(linkService.createSimpleLink(any(), any(), any()))
                .thenReturn(new FlatLink("label", "http://localhost:8080/site", LinkType.INTERNAL));

        return spotlight;
    }
}