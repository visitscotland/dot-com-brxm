package com.visitscotland.brxm.formatter;

import org.springframework.stereotype.Component;

import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.FlatLink;

import javax.annotation.Nonnull;

import java.util.Locale;

@Component
public class EventFlatLinkFormatter implements HippoBeanFormatter<EventBSH, FlatLink> {
    private final ResourceBundleService resourceBundleService;

    protected EventFlatLinkFormatter(ResourceBundleService resourceBundleService) {
        this.resourceBundleService = resourceBundleService;
    }

    @Override
    public FlatLink format(@Nonnull EventBSH hippoBean) {
        final ExternalLink externalLink = hippoBean.getCtaLink();
        final String label = resourceBundleService.getCtaLabel(externalLink.getLabel(), Locale.UK);
        final String link = externalLink.getLink();

        return FlatLink.of(label, link, LinkType.EXTERNAL);
    }
}