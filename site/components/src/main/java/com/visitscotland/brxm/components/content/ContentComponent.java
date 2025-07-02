package com.visitscotland.brxm.components.content;

import com.visitscotland.brxm.translation.TranslationFallbackProvider;
import com.visitscotland.brxm.config.VsComponentManager;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;

import org.onehippo.cms7.essentials.components.EssentialsContentComponent;

import java.util.Optional;

public class ContentComponent extends EssentialsContentComponent {
    private final TranslationFallbackProvider translationFallbackProvider;
    public static final String PAGE_PATH = "content";

    public ContentComponent() {
        translationFallbackProvider = VsComponentManager.get(TranslationFallbackProvider.class);
    }

    @Override
    public void setContentBeanWith404(HstRequest request, HstResponse response) {
        final Optional<HippoBean> contentBean = translationFallbackProvider.getContentBeanForRequest(request);

        if (contentBean.isPresent() && contentBean.get().getPath().endsWith("/" + PAGE_PATH)) {
            request.setModel("document", contentBean.get());
        } else {
            this.pageNotFound(response);
        }
    }
}
