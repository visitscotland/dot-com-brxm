package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.SkiCentre;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.SkiModule;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.utils.SiteProperties;
import org.springframework.stereotype.Component;

import java.util.MissingResourceException;

@Component
public class SkiCentreMapper extends ModuleMapper<SkiCentre, SkiModule> {

    static final String BUNDLE_FILE = "ski";

    private final SiteProperties properties;

    public SkiCentreMapper(SiteProperties properties) {
        this.properties = properties;
    }

    @Override
    void addLabels(PageCompositionHelper compositionHelper) throws MissingResourceException {
        compositionHelper.addAllSiteLabels(BUNDLE_FILE);
    }

    @Override
    SkiModule map(SkiCentre document, PageCompositionHelper compositionHelper) {
        return getModule(document);
    }

    SkiModule getModule(SkiCentre document) {
        SkiModule module = new SkiModule();

        module.setHippoBean(document);
        module.setTimeout(properties.getSkiTimeout());
        module.setTitle(document.getTitle());
        module.setIntroduction(document.getCopy());
        module.setFeedURL(document.getFeed());
        module.setPisteMap(document.getPisteMap());

        module.setPhone(document.getTelephone());
        module.setAddressLine(document.getAddress());
        module.setWebsite(new FlatLink(document.getWebsite(), document.getWebsite(), LinkType.EXTERNAL));
        module.setSocialChannelURLs(document.getSocialChannels());

        return module;
    }
}
