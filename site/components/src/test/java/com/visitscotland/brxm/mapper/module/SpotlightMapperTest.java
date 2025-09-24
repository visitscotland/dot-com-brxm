package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.CTABanner;
import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.hippobeans.SharedLink;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.AnchorFormatter;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.brxm.pagebuilder.InvalidContentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpotlightMapperTest {

    private static final String BUNDLE_ID = "newsletter-signpost";

    @Mock
    ResourceBundleService bundle;
    @Mock
    HippoUtilsService utils;
    @Mock
    SiteProperties properties;
    @Mock
    LinkService linkService;
    @Mock
    AnchorFormatter anchorFormatter;
    @Mock
    ContentLogger contentLogger;

    @InjectMocks
    SpotlightMapper spotlightMapper;

    @Test
    @DisplayName("Should return ErrorModule when CTA link is not available")
    void when_ctaLinkIsNull_then_ErrorModuleIsReturned(){
        CTABanner ctaBanner = mock(CTABanner.class, RETURNS_DEEP_STUBS);
        FlatLink cta = new FlatLink(); // no link set

        when(ctaBanner.getCtaLink().getLink()).thenReturn(mock(SharedLink.class));
        when(ctaBanner.getPath()).thenReturn("path");
        when(linkService.createSimpleLink(any(), any(), any())).thenReturn(cta);

        Assertions.assertThrows(InvalidContentException.class, () ->spotlightMapper.createModule(ctaBanner));
    }


    @Test
    @DisplayName("Should set label when label is not empty")
    void when_overrideLabelIsProvided_then_thatLabelShouldBeUsed() throws InvalidContentException {
        CTABanner ctaBanner = mock(CTABanner.class, RETURNS_DEEP_STUBS);
        SharedLink linkable = mock(SharedLink.class);

        FlatLink flatLink = new FlatLink();
        flatLink.setLink("https://example.com");

        when(ctaBanner.getCtaLink().getLink()).thenReturn(linkable);
        when(linkService.createSimpleLink(eq(linkable), any(), isNull())).thenReturn(flatLink);

        when(ctaBanner.getCtaLink().getLabel()).thenReturn("Read More");

        Module<?> result = spotlightMapper.createModule(ctaBanner);
        Assertions.assertInstanceOf(SignpostModule.class, result);

        Assertions.assertEquals("Read More", ((SignpostModule) result).getCta().getLabel());
    }

    @Test
    @DisplayName("Should set image using CTA banner image")
    void when_imageIsProvided_then_thatImageShouldBeUsed() throws InvalidContentException {
        CTABanner ctaBanner = mock(CTABanner.class, RETURNS_DEEP_STUBS);
        SharedLink linkable = mock(SharedLink.class);

        FlatLink flatLink = new FlatLink();
        flatLink.setLink("https://image.com");

        when(ctaBanner.getCtaLink().getLink()).thenReturn(linkable);
        when(linkService.createSimpleLink(eq(linkable), any(), any())).thenReturn(flatLink);
        when(anchorFormatter.getAnchorOrFallback(any(), any())).thenReturn("anchor");

        when(ctaBanner.getImage()).thenReturn(mock(Image.class));

        Module<?> result = spotlightMapper.createModule(ctaBanner);
        Assertions.assertInstanceOf(SignpostModule.class, result);
        SignpostModule signpostModule = (SignpostModule) result;

        Assertions.assertNotNull(signpostModule.getImage());
        Assertions.assertInstanceOf(Image.class, signpostModule.getImage().getCmsImage());
    }

}
