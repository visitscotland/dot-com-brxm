package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.hippobeans.MediaCollection;
import com.visitscotland.brxm.hippobeans.Video;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.MediaSection;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.services.LinkService;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MediaSectionMapperTest {

    @Mock
    private ImageMapper imageMapper;

    @Mock
    private LinkService linkService;

    @InjectMocks
    private MediaSectionMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new MediaSectionMapper(imageMapper, linkService);
    }

    @Test
    @DisplayName("Creates a basic media section containing a single image")
    void shouldCreateBasicMediaSection() {
        MediaCollection mediaCollection = mock(MediaCollection.class);

        Image image = mock(Image.class);
        FlatImage flatImage = mock(FlatImage.class);

        when(mediaCollection.getMedia()).thenReturn(List.of(image));
        when(imageMapper.getImage(eq(image), any(Module.class), eq(Locale.UK))).thenReturn(flatImage);

        MediaSection result = mapper.map(mediaCollection, new Module<>(), Locale.UK);

        assertNotNull(result);
        assertEquals("image", result.getType());
        assertEquals(1, result.getItems().size());
        assertSame(flatImage, result.getItems().get(0));
    }

    @Test
    @DisplayName("Creates a media section containing a wide image")
    void shouldCreateMediaSectionWithWideImage() {
        MediaCollection mediaCollection = mock(MediaCollection.class);
        Image wideImage = mock(Image.class);
        FlatImage mappedWideImage = mock(FlatImage.class);

        when(mediaCollection.getMedia()).thenReturn(List.of(wideImage));
        when(imageMapper.getImage(eq(wideImage), any(Module.class), eq(Locale.UK))).thenReturn(mappedWideImage);

        MediaSection result = mapper.map(mediaCollection, new Module<>(), Locale.UK);

        assertNotNull(result);
        assertEquals("image", result.getType());
        assertEquals(List.of(mappedWideImage), result.getItems());

        verify(imageMapper).getImage(eq(wideImage), any(Module.class), eq(Locale.UK));
    }

    @Test
    @DisplayName("Creates a media section containing a single video")
    void shouldCreateMediaSectionWithVideo() {
        MediaCollection mediaCollection = mock(MediaCollection.class);
        VideoLink videoLink = mock(VideoLink.class);

        when(videoLink.getVideoLink()).thenReturn(mock(Video.class));
        when(mediaCollection.getMedia()).thenReturn(List.of(videoLink));
        when(linkService.createVideo(any(Video.class), any(Module.class), eq(Locale.UK))).thenReturn(new EnhancedLink());

        MediaSection result = mapper.map(mediaCollection, new Module<>(), Locale.UK);

        assertNotNull(result);
        assertEquals("video", result.getType());
        assertEquals(1, result.getItems().size());
    }

    @Test
    @DisplayName("Creates a carousel media section when images and videos are combined")
    void shouldCreateMediaCarousel() {
        MediaCollection mediaCollection = mock(MediaCollection.class);
        Image image = mock(Image.class);
        VideoLink video = mock(VideoLink.class);

        FlatImage flatImage = new FlatImage();
        EnhancedLink enhancedLink = new EnhancedLink();

        when(video.getVideoLink()).thenReturn(mock(Video.class));
        when(mediaCollection.getMedia()).thenReturn(List.of(video, image));

        when(imageMapper.getImage(eq(image), any(Module.class), eq(Locale.UK))).thenReturn(flatImage);
        when(linkService.createVideo(any(Video.class), any(Module.class), eq(Locale.UK)))
                .thenReturn(enhancedLink);

        MediaSection result = mapper.map(mediaCollection, new Module<>(), Locale.UK);

        assertNotNull(result);
        assertEquals("carousel", result.getType());
        assertEquals(2, result.getItems().size());

        assertTrue(result.getItems().contains(flatImage));
        assertTrue(result.getItems().contains(enhancedLink));
    }

    @Test
    @DisplayName("Creates a carousel media section when multiple images are provided")
    void shouldCreateGalleryOfImages() {
        MediaCollection mediaCollection = mock(MediaCollection.class);

        when(mediaCollection.getMedia()).thenReturn(List.of(mock(Image.class), mock(Image.class)));
        when(imageMapper.getImage(any(Image.class), any(Module.class), eq(Locale.UK))).thenReturn(new FlatImage());

        MediaSection result = mapper.map(mediaCollection, new Module<>(), Locale.UK);

        assertNotNull(result);

        // Current implementation returns "carousel" for any collection > 1 item
        assertEquals("carousel", result.getType());
        assertEquals(2, result.getItems().size()); 
        verify(imageMapper, times(2))
                .getImage(any(Image.class), any(Module.class), eq(Locale.UK));
    }

    @Test
    @DisplayName("Returns null when compound media collection is null")
    void shouldReturnNullWhenCompoundIsNull() {

        MediaSection result = mapper.map(null, new Module<>(), Locale.UK);

        assertNull(result);

        verifyNoInteractions(imageMapper);
        verifyNoInteractions(linkService);
    }

    @Test
    @DisplayName("Returns an empty media section when no media items exist")
    void shouldHandleEmptyMediaCollection() {
        MediaCollection mediaCollection = mock(MediaCollection.class);

        when(mediaCollection.getMedia()).thenReturn(Collections.emptyList());

        MediaSection result = mapper.map(mediaCollection, new Module<>(), Locale.UK);

        assertNotNull(result);
        assertEquals("unknown", result.getType());
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    @DisplayName("Reports unsupported media types as new Module<>() errors")
    void shouldReportUnsupportedMediaType() {
        MediaCollection mediaCollection = mock(MediaCollection.class);

        HippoBean unsupported = mock(HippoBean.class);
        Module<?> module = spy(new Module<>());

        when(mediaCollection.getMedia()).thenReturn(List.of(unsupported));
        when(unsupported.getDisplayName()).thenReturn("Unsupported Asset");

        MediaSection result = mapper.map(mediaCollection, module, Locale.UK);

        verify(module).addErrorMessage("Unsupported media type: Unsupported Asset");

        assertNotNull(result);
        assertTrue(result.getItems().isEmpty());
    }
}