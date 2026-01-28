package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.components.navigation.info.GeneralPageComponentInfo;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.LocalizationComponent;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.EssentialsContentComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Implements the translation fallback functionality. If the content can not be found on a non-english mount, then
 * check the english mount for content instead
 */
// TODO: Rename to VsContentComponent
public abstract class ContentComponent extends EssentialsContentComponent {
    private final HippoUtilsService hippoUtilsService;
    private final LocalizationComponent localizationComponent;
    public static final String PAGE_PATH = "content";
    private static final String ERROR_CODE = "errorCode";

    protected ContentComponent() {
        hippoUtilsService = VsComponentManager.get(HippoUtilsService.class);
        localizationComponent = VsComponentManager.get(LocalizationComponent.class);
    }

    @Override
    public void setContentBeanWith404(HstRequest request, HstResponse response) {
        Optional<HippoBean> contentBean = hippoUtilsService.getContentBeanWithTranslationFallback(request);
        if (contentBean.isPresent() && contentBean.get().getPath().endsWith("/" + PAGE_PATH)) {
            //TODO: Evaluate Moving the Constant from PageContentComponents
            request.setModel("document", contentBean.get());
        } else {
            this.pageNotFound(response);
        }
    }

    @Override
    public void prepareBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
        super.prepareBeforeRender(request, response);

        setStatusCode(request, response);
        localizationComponent.setLocale();
    }

    /**
     * Sets the status code for the Page request.
     *
     * @param request HstRequest
     * @param response HstResponse
     */
    private void setStatusCode(HstRequest request, HstResponse response){
        GeneralPageComponentInfo pageInfo = getComponentParametersInfo(request);
        if (pageInfo != null) {
            int pageStatus = Integer.parseInt(pageInfo.getStatus());
            response.setStatus(pageStatus);
            if (pageStatus >= 400) {
                request.setModel(ERROR_CODE, pageStatus);
            }
        }
    }
}
