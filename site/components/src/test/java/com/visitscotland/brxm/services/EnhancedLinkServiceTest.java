package com.visitscotland.brxm.services;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.utils.ContentLogger;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnhancedLinkServiceTest {

    @Mock
    private LinkService linkService;

    @Mock
    private ContentLogger contentLogger;

    @InjectMocks
    private EnhancedLinkService service;

    @Test
    @DisplayName("Convert MegalinkItem list to EnhancedLink list")
    void convert_megalinkItems() {
        Module<Megalinks> module = new Module<>();
        MegalinkItem item1 = mock(MegalinkItem.class);
        MegalinkItem item2 = mock(MegalinkItem.class);
        HippoBean linkable1 = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));
        HippoBean linkable2 = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));
        EnhancedLink link1 = new EnhancedLink();
        EnhancedLink link2 = new EnhancedLink();

        when(item1.getLinkItem()).thenReturn(linkable1);
        when(item2.getLinkItem()).thenReturn(linkable2);
        when(item1.getFeature()).thenReturn(true);
        when(item2.getFeature()).thenReturn(false);
        when(linkService.createEnhancedLink((Linkable) linkable1, module, Locale.UK, false))
                .thenReturn(Optional.of(link1));
        when(linkService.createEnhancedLink((Linkable) linkable2, module, Locale.UK, false))
                .thenReturn(Optional.of(link2));

        List<EnhancedLink> result = service.convert(module, Arrays.asList(item1, item2), Locale.UK, false);

        assertEquals(2, result.size());
        assertTrue(result.get(0).isFeatured());
        assertFalse(result.get(1).isFeatured());
    }

    @Test
    @DisplayName("Convert MegalinkItem list filters out null links")
    void convert_megalinkItems_withNullLinks() {
        Module<Megalinks> module = new Module<>();
        MegalinkItem item1 = mock(MegalinkItem.class);
        MegalinkItem item2 = mock(MegalinkItem.class);
        HippoBean linkable1 = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));
        HippoBean linkable2 = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));
        EnhancedLink link1 = new EnhancedLink();

        when(item1.getLinkItem()).thenReturn(linkable1);
        when(item2.getLinkItem()).thenReturn(linkable2);
        when(linkService.createEnhancedLink((Linkable) linkable1, module, Locale.UK, false))
                .thenReturn(Optional.of(link1));
        when(linkService.createEnhancedLink((Linkable) linkable2, module, Locale.UK, false))
                .thenReturn(Optional.empty());

        List<EnhancedLink> result = service.convert(module, Arrays.asList(item1, item2), Locale.UK, false);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Convert HippoBean list to EnhancedLink list")
    void convert_hippoBeans() {
        Module<?> module = new Module<>();
        HippoBean linkable = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));
        EnhancedLink link = new EnhancedLink();

        when(linkService.createEnhancedLink((Linkable) linkable, module, Locale.UK, false))
                .thenReturn(Optional.of(link));

        List<EnhancedLink> result = service.convert(module, Arrays.asList(linkable), Locale.UK, false, false);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Convert HippoBean list uses breadcrumb for Page items when enabled")
    void convert_hippoBeans_withBreadcrumb() {
        Module<?> module = new Module<>();
        Page page = mock(Page.class);
        EnhancedLink link = new EnhancedLink();
        link.setLabel("Original Label");

        when(page.getBreadcrumb()).thenReturn("Breadcrumb Label");
        when(linkService.createEnhancedLink(page, module, Locale.UK, false))
                .thenReturn(Optional.of(link));

        List<EnhancedLink> result = service.convert(module, Arrays.asList(page), Locale.UK, false, true);

        assertEquals(1, result.size());
        assertEquals("Breadcrumb Label", result.get(0).getLabel());
    }

    @Test
    @DisplayName("Convert HippoBean list does not use breadcrumb when disabled")
    void convert_hippoBeans_withoutBreadcrumb() {
        Module<?> module = new Module<>();
        Page page = mock(Page.class);
        EnhancedLink link = new EnhancedLink();
        link.setLabel("Original Label");

        when(linkService.createEnhancedLink(page, module, Locale.UK, false))
                .thenReturn(Optional.of(link));

        List<EnhancedLink> result = service.convert(module, Arrays.asList(page), Locale.UK, false, false);

        assertEquals(1, result.size());
        assertEquals("Original Label", result.get(0).getLabel());
        verify(page, never()).getBreadcrumb();
    }

    @Test
    @DisplayName("Convert VideoLink to EnhancedLink")
    void convert_videoLink() {
        Module<?> module = new Module<>();
        VideoLink videoLink = mock(VideoLink.class);
        Linkable linkable = mock(Video.class, withSettings().extraInterfaces(Linkable.class));
        EnhancedLink link = new EnhancedLink();

        when(videoLink.getVideoLink()).thenReturn((Video) linkable);
        when(linkService.createEnhancedLink(linkable, module, Locale.UK, false))
                .thenReturn(Optional.of(link));

        List<EnhancedLink> result = service.convert(module, Arrays.asList(videoLink), Locale.UK, false, false);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Convert unrecognized HippoBean type logs warning and returns empty list")
    void convert_unrecognizedType() {
        Module<?> module = new Module<>();
        HippoBean unrecognized = mock(HippoBean.class);

        when(unrecognized.getPath()).thenReturn("/path/to/bean");

        List<EnhancedLink> result = service.convert(module, Arrays.asList(unrecognized), Locale.UK, false, false);

        assertEquals(0, result.size());
        verify(contentLogger).warn(anyString(), anyString(), anyString());
        assertTrue(module.getErrorMessages().size() > 0);
    }

    @Test
    @DisplayName("Convert null HippoBean logs warning and returns empty list")
    void convert_nullBean() {
        Module<?> module = new Module<>();

        List<EnhancedLink> result = service.convert(module, Arrays.asList((HippoBean) null), Locale.UK, false, false);

        assertEquals(0, result.size());
        verify(contentLogger).warn(anyString());
    }

    @Test
    @DisplayName("Convert handles unpublished documents")
    void convert_unpublishedDocument() {
        Module<?> module = new Module<>();
        HippoBean linkable = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));

        when(linkable.getPath()).thenReturn("/path/to/unpublished");
        when(linkService.createEnhancedLink((Linkable) linkable, module, Locale.UK, false))
                .thenReturn(Optional.empty());

        List<EnhancedLink> result = service.convert(module, Arrays.asList(linkable), Locale.UK, false, false);

        assertEquals(0, result.size());
        verify(contentLogger).warn(anyString(), anyString());
    }

    @Test
    @DisplayName("Convert adds error to null module without throwing exception")
    void convert_nullModule() {
        HippoBean linkable = mock(HippoBean.class, withSettings().extraInterfaces(Linkable.class));
        EnhancedLink link = new EnhancedLink();

        when(linkService.createEnhancedLink((Linkable) linkable, null, Locale.UK, false))
                .thenReturn(Optional.of(link));

        List<EnhancedLink> result = service.convert(null, Arrays.asList(linkable), Locale.UK, false, false);

        assertEquals(1, result.size());
    }
}
