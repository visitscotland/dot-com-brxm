package com.visitscotland.brxm.components.navigation;


import com.visitscotland.brxm.components.navigation.info.MenuComponentInfo;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.factory.NavigationFactory;
import com.visitscotland.brxm.utils.HippoUtilsService;
import com.visitscotland.brxm.utils.InternalParameterProcessor;
import com.visitscotland.brxm.utils.Language;
import com.visitscotland.brxm.utils.Properties;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.internal.HstMutableRequestContext;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.onehippo.cms7.essentials.components.EssentialsMenuComponent;

import java.util.Locale;

@ParametersInfo(
        type = MenuComponentInfo.class
)
public class MenuComponent extends EssentialsMenuComponent {

    private static final String PREVIEW_QUERY_PARAMETER = "preview-token";

    public static final String MENU = "menu";
    public static final String LOCALIZED_URLS = "localizedURLs";

    private final NavigationFactory factory;
    private final HippoUtilsService utils;
    private final Properties properties;


    public MenuComponent() {
        this(VsComponentManager.get(NavigationFactory.class),
                VsComponentManager.get(HippoUtilsService.class),
                VsComponentManager.get(Properties.class));
    }

    public MenuComponent(NavigationFactory factory, HippoUtilsService utils, Properties properties) {
        this.factory = factory;
        this.utils = utils;
        this.properties = properties;
    }

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        if (request.getRequestURI().startsWith(properties.getDmsInternalPath())) {
            String vsLocale = utils.getParameterFromUrl(request, InternalParameterProcessor.PARAM_LOCALE);
            if (vsLocale != null) {
                Language language = Language.getLanguageForLocale(Locale.forLanguageTag(vsLocale));
                HstMutableRequestContext requestContext = (HstMutableRequestContext) RequestContextProvider.get();
                requestContext.setResolvedMount(utils.getMount(request, language.getCmsMount()));
                requestContext.setPreferredLocale(language.getLocale());
            }
        }

        boolean editMode = Boolean.TRUE.equals(request.getAttribute("editMode"));
        boolean cacheable;

        if (editMode) {
            cacheable = Boolean.TRUE.equals(properties.getNavigationCache());
        } else {
            cacheable = properties.isSnippetCacheEnabled();
        }

        String id = (editMode?"1-":"0-") + getAnyParameter(request, PREVIEW_QUERY_PARAMETER);

        RootMenuItem rootMenuItem = factory.buildMenu(request, request.getModel(MENU), id, cacheable);
        rootMenuItem.setCmsCached(cacheable && editMode);

        request.setModel(MENU, rootMenuItem);
    }
}
