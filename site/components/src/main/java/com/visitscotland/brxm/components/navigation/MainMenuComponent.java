package com.visitscotland.brxm.components.navigation;

import com.visitscotland.brxm.components.navigation.info.MenuComponentInfo;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.BannerFactory;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.utils.CMSProperties;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import java.util.Collections;

@ParametersInfo(
        type = MenuComponentInfo.class
)
public class MainMenuComponent extends MenuComponent {

    private final BannerFactory bannerFactory;
    private final DocumentUtilsService utils;
    private final CMSProperties properties;

    public MainMenuComponent() {
        bannerFactory = VsComponentManager.get(BannerFactory.class);
        utils = VsComponentManager.get(DocumentUtilsService.class);
        properties = VsComponentManager.get(CMSProperties.class);
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        if (request.getPathInfo().startsWith(properties.getDmsInternalPath())) {
            request.setModel(LOCALIZED_URLS, Collections.emptyList());
        } else {
            request.setModel(LOCALIZED_URLS, utils.getLocalizedURLs(request));
        }
        request.setModel("banner", bannerFactory.getBannerModule(request));
    }

}
