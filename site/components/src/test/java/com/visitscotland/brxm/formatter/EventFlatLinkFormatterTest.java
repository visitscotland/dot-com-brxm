package com.visitscotland.brxm.formatter;

import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.hippobeans.EventBSH;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventFlatLinkFormatterTest {
    private static final String EXTERNAL_LINK_LABEL = "External Link Label";
    private static final String CTA_LABEL = "Call To Action Label";
    private static final String CTA_LINK = "https://example.com";

    @Mock private ResourceBundleService resourceBundleService;

    @InjectMocks private EventFlatLinkFormatter eventFlatLinkFormatter;

    @Test
    void format_EventBSHValidCtaLink_CorrectlyFormatted() {
        final var eventBSH = mock(EventBSH.class);
        final var externalLink = mock(ExternalLink.class);
        final var expected = FlatLink.of(
            CTA_LABEL,
            CTA_LINK,
            LinkType.EXTERNAL
        );

        when(eventBSH.getCtaLink()).thenReturn(externalLink);
        when(externalLink.getLabel()).thenReturn(EXTERNAL_LINK_LABEL);
        when(resourceBundleService.getCtaLabel(EXTERNAL_LINK_LABEL, Locale.UK)).thenReturn(CTA_LABEL);
        when(externalLink.getLink()).thenReturn(CTA_LINK);

        final var result = eventFlatLinkFormatter.format(eventBSH);

        assertAll(() -> {
            Assertions.assertEquals(expected.getLink(), result.getLink());
            Assertions.assertEquals(expected.getLabel(), result.getLabel());
            Assertions.assertEquals(expected.getType(), result.getType());
        });

        verify(eventBSH, times(1)).getCtaLink();
        verify(externalLink, times(1)).getLabel();
        verify(resourceBundleService, times(1))
            .getCtaLabel(eq(EXTERNAL_LINK_LABEL), eq(Locale.UK));
        verify(externalLink, times(1)).getLink();
    }
}