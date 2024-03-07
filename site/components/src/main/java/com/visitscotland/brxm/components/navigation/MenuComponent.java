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
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.onehippo.cms7.essentials.components.EssentialsMenuComponent;

import java.util.Locale;

@ParametersInfo(
        type = MenuComponentInfo.class
)
public class MenuComponent extends EssentialsMenuComponent {

    private static final String VS_PREFIX = "navigation.";
    private static final String BE_PREFIX = "be.navigation.";

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

        request.setModel(MENU, getRootMenuItem(request));
    }

    /**
     * Generates the root menu item considering if it needs to be cached.
     * <br>
     * There are three different navigation environments to be cached.
     * <ul>
     * <li>Live: General purpose Navigation served to final users</li>
     * <li>CMS: Navigation inside the CMS. It would use URLs containing <i>/_cmsinternal/</i></li>
     * <lI>Preview: Navigation for users that use the preview query string ({@code preview-token}) </li>
     * </ul>
     *
     * @param request HstRequest object
     *
     * @return Resulting RootMenuItem
     */
    private RootMenuItem getRootMenuItem(HstRequest request){
        boolean editMode = Boolean.TRUE.equals(request.getAttribute("editMode"));
        boolean cacheable = editMode ? Boolean.TRUE.equals(properties.getNavigationCache()) : properties.isSnippetCacheEnabled();

        String id = (editMode?"1-":"0-") + getAnyParameter(request, PREVIEW_QUERY_PARAMETER);

        RootMenuItem rootMenuItem = factory.buildMenu(request, getResourceBundle(request), id, cacheable);
        rootMenuItem.setCmsCached(cacheable);

        return rootMenuItem;
    }

    private String getResourceBundle(HstRequest request){
        String prefix;
        if (utils.isBusinessEventsSite(request)){
            prefix = BE_PREFIX;
        } else {
            prefix = VS_PREFIX;
        }

        return prefix +  ((HstSiteMenu) request.getModel(MENU)).getName();
    }
}
