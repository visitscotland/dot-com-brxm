package com.visitscotland.brxm.components.navigation;

import com.visitscotland.brxm.components.navigation.info.MenuComponentInfo;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.mapper.BannerMapper;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.utils.CMSProperties;
import com.visitscotland.brxm.utils.SiteProperties;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import java.util.Collections;

@ParametersInfo(
        type = MenuComponentInfo.class
)
public class MainMenuComponent extends MenuComponent {

    private final BannerMapper bannerMapper;
    private final DocumentUtilsService utils;
    private final CMSProperties properties;
    private final SiteProperties siteProperties;

    public MainMenuComponent() {
        bannerMapper = VsComponentManager.get(BannerMapper.class);
        utils = VsComponentManager.get(DocumentUtilsService.class);
        properties = VsComponentManager.get(CMSProperties.class);
        siteProperties = VsComponentManager.get(SiteProperties.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        //TODO to be moved to Page Assembler
        if (siteProperties.isMultiChannelSite()) {
            if (request.getPathInfo().startsWith(properties.getDmsInternalPath())) {
                request.setModel(LOCALIZED_URLS, Collections.emptyList());
            } else {
                request.setModel(LOCALIZED_URLS, utils.getLocalizedURLs(request));
            }
        }

        //TODO to be moved to Page Assembler
        request.setModel("banner", bannerMapper.getBannerModule(request));
    }

}
