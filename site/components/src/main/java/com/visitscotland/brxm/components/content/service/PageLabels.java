package com.visitscotland.brxm.components.content.service;

import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import org.springframework.stereotype.Component;

@Component
public class PageLabels {

    //Resource Bundle
    private static final String SOCIAL_SHARE_BUNDLE = "social.share";
    private static final String VIDEO_BUNDLE = "video";
    private static final String SKIP_TO_BUNDLE = "skip-to";
    private static final String SEO_BUNDLE = "seo";

    private static final String NAVIGATION_STATIC = "navigation.static";
    
    static final String CMS_MESSAGES_BUNDLE = "cms-messages";
    static final String NAVIGATION_SOCIAL_MEDIA = "navigation.social-media";

    /**
     * Adds labels that are necessary for type of pages. Please notice that there are two strategies for including properties
     * <br>
     * When all labels are required you should use {@code bundle.getAllLabels(...)}. However, in the case that only
     * some of them are needed we can create a new {@code Map} object and include them one by one. (i.e. global labels)
     * </ul>
     */
    public void includeGeneralLabels(PageCompositionHelper pageConfig, boolean isEditMode){
        setCommonGlobalLabels(pageConfig);

        addNavigationLabels(pageConfig);

        pageConfig.addAllSiteLabels(SEO_BUNDLE);
        pageConfig.addAllSiteLabels(SKIP_TO_BUNDLE);
        pageConfig.addAllSiteLabels(SOCIAL_SHARE_BUNDLE);

        //TODO: This labels should be conditional
        pageConfig.addAllSiteLabels(VIDEO_BUNDLE);

        if (isEditMode) {
            pageConfig.addAllSiteLabels(CMS_MESSAGES_BUNDLE);
        }
    }

    /**
     * Add global labels to the request. Global labels are those that are used in multiple places across the site and
     * are not specific to a page type or module.
     *
     * @param pageConfig PageCompositionHelper where the labels will be added to
     */
    private void setCommonGlobalLabels(PageCompositionHelper pageConfig) {
        pageConfig.addGlobalLabel("close");
        pageConfig.addGlobalLabel("cookie.link-message");
        pageConfig.addGlobalLabel("third-party-error");
        pageConfig.addGlobalLabel("default.alt-text");
        pageConfig.addGlobalLabel("image.title");
        pageConfig.addGlobalLabel("image.no.credit");
        pageConfig.addGlobalLabel("image.toggle.text");
        pageConfig.addGlobalLabel("home");
        pageConfig.addGlobalLabel("page.next");
        pageConfig.addGlobalLabel("page.previous");
        pageConfig.addGlobalLabel("back-to-top");
        pageConfig.addGlobalLabel("last-update");
    }

    private void addNavigationLabels(PageCompositionHelper pageConfig) {
        pageConfig.addAllSiteLabels(NAVIGATION_STATIC);
        pageConfig.addSiteSpecificLabels(NAVIGATION_SOCIAL_MEDIA);
    }
}
