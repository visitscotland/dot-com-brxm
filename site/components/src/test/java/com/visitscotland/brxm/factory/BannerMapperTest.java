package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Banner;
import com.visitscotland.brxm.mapper.BannerMapper;
import com.visitscotland.brxm.mock.BannerMockBuilder;
import com.visitscotland.brxm.model.BannerModule;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.core.component.HstRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BannerMapperTest {

    @Mock
    LinkService linkService;

    @Mock
    HippoUtilsService hippoUtilsService;

   @Mock
    SiteProperties siteProperties;

    @Mock
    ContentLogger logger;

    @InjectMocks
    BannerMapper factory;

    @DisplayName("Create banner module")
    @Test
    void bannerModule() throws Exception {
        HstRequest request = mock(HstRequest.class);
        Banner bannerBean = new BannerMockBuilder().copy("copy").build();
        FlatLink mockLink = mock(FlatLink.class);
        when(mockLink.getLink()).thenReturn("link");
        when(siteProperties.getSiteBanner()).thenReturn("banner");
        when(hippoUtilsService.getDocumentFromContent("banner")).thenReturn(bannerBean);
        when(linkService.createFindOutMoreLink(any(), any(), any())).thenReturn(mockLink);
        BannerModule banner = factory.getBannerModule(request);

        Assertions.assertEquals("copy", banner.getCopy().getContent());
        Assertions.assertNotNull(banner.getCtaLink());
    }

    @DisplayName("If banner does not exist, null is returned")
    @Test
    void bannerDoesNotExist() throws Exception {
        HstRequest request = mock(HstRequest.class);
        when(siteProperties.getSiteBanner()).thenReturn("banner");
        when(hippoUtilsService.getDocumentFromContent("banner")).thenReturn(null);
        Assertions.assertNull(factory.getBannerModule(request));
    }

    @DisplayName("VS-3221 - If link is not published, then don't create banner")
    @Test
    void linkDoesNotExist() throws Exception {
        HstRequest request = mock(HstRequest.class);
        Banner bannerBean = new BannerMockBuilder().build();
        when(hippoUtilsService.getDocumentFromContent("banner")).thenReturn(bannerBean);
        when(hippoUtilsService.getDocumentFromContent("banner")).thenReturn(bannerBean);
        when(siteProperties.getSiteBanner()).thenReturn("banner");

        BannerModule banner = factory.getBannerModule(request);

        Assertions.assertNull(banner.getCtaLink());
        Assertions.assertNull(banner.getCopy());
        verify(linkService).createFindOutMoreLink(any(), any(), any());
    }
}
