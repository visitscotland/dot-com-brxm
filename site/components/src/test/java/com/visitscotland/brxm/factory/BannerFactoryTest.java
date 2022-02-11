package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Banner;
import com.visitscotland.brxm.mock.BannerMockBuilder;
import com.visitscotland.brxm.model.BannerModule;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.utils.HippoUtilsService;
import com.visitscotland.brxm.utils.Properties;
import org.hippoecm.hst.core.component.HstRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BannerFactoryTest {

    @Mock
    LinkService linkService;

    @Mock
    HippoUtilsService hippoUtilsService;

    @Mock
    Properties properties;

    @InjectMocks
    @Resource
    BannerFactory factory;

    @DisplayName("Create banner module")
    @Test
    public void bannerModule() throws Exception {
        HstRequest request = mock(HstRequest.class);
        Banner bannerBean = new BannerMockBuilder().title("title").copy("copy").build();
        FlatLink mockLink = mock(FlatLink.class);
        when(mockLink.getLink()).thenReturn("link");
        when(properties.getBannerContentPath()).thenReturn("banner");
        when(hippoUtilsService.getDocumentFromContent("banner")).thenReturn(bannerBean);
        when(linkService.createFindOutMoreLink(any(), any(), any())).thenReturn(mockLink);
        BannerModule banner = factory.getBannerModule(request);

        Assertions.assertEquals("title", banner.getTitle());
        Assertions.assertEquals("copy", banner.getCopy().getContent());
        Assertions.assertNotNull(banner.getCtaLink());
    }

    @DisplayName("If banner does not exist, null is returned")
    @Test
    public void bannerDoesNotExist() throws Exception {
        HstRequest request = mock(HstRequest.class);
        when(properties.getBannerContentPath()).thenReturn("banner");
        when(hippoUtilsService.getDocumentFromContent("banner")).thenReturn(null);
        Assertions.assertNull(factory.getBannerModule(request));
    }

    @DisplayName("VS-3221 - If link is not published, then don't create banner")
    @Test
    public void linkDoesNotExist() throws Exception {
        HstRequest request = mock(HstRequest.class);
        Banner bannerBean = new BannerMockBuilder().build();
        when(properties.getBannerContentPath()).thenReturn("banner");
        when(hippoUtilsService.getDocumentFromContent("banner")).thenReturn(bannerBean);

        BannerModule banner = factory.getBannerModule(request);

        Assertions.assertNull(banner);
        verify(linkService).createCTALink(any(), any(), any());
    }
}
