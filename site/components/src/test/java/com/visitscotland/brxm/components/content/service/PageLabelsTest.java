package com.visitscotland.brxm.components.content.service;

import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

/**
 * This unit test has been generated with AI and hasn't been reviewed
 *
 * TODO: Review Unit test
 */
@ExtendWith(MockitoExtension.class)
class PageLabelsTest {

    @Mock
    private PageCompositionHelper pageConfig;

    @InjectMocks
    private PageLabels pageLabels;

    @Test
    @DisplayName("Should include cms-messages bundle when in edit mode")
    void includeGeneralLabels_edit_mode() {
        // Behavior: In edit mode, CMS_MESSAGES bundle should be added along with all others
        pageLabels.includeGeneralLabels(pageConfig, true);

        // Verify all site bundles including cms-messages
        verify(pageConfig).addAllSiteLabels(PageLabels.CMS_MESSAGES_BUNDLE);
    }

    @Test
    @DisplayName("Should exclude cms-messages bundle when outside edit mode")
    void excludeGeneralLabels_edit_mode() {
        // Behavior: In edit mode, CMS_MESSAGES bundle should be added along with all others
        pageLabels.includeGeneralLabels(pageConfig, false);

        // Verify all site bundles including cms-messages
        verify(pageConfig, never()).addAllSiteLabels(PageLabels.CMS_MESSAGES_BUNDLE);
    }

    @Test
    @DisplayName("Should register global labels")
    void includeGeneralLabels_verifies_all_global_label_registrations() {
        pageLabels.includeGeneralLabels(pageConfig, false);

        verify(pageConfig, atLeast(2)).addGlobalLabel(anyString());
    }

    @Test
    @DisplayName("Social media labels do not fall back to the default file.")
    void includeGeneralLabels_navigation_labels_strategy() {
        pageLabels.includeGeneralLabels(pageConfig, false);

        verify(pageConfig).addSiteSpecificLabels(PageLabels.NAVIGATION_SOCIAL_MEDIA);
        verify(pageConfig, never()).addAllSiteLabels(PageLabels.NAVIGATION_SOCIAL_MEDIA);
    }
}