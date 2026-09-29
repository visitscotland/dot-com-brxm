package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.hippobeans.SkiCentre;
import com.visitscotland.brxm.model.SkiModule;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkiCentreMapperTest {

    @Mock
    SiteProperties properties;

    @InjectMocks
    SkiCentreMapper skiCentreMapper;

    @DisplayName("VS-4378 - SkiCentre - Create Basic Module")
    @Test
    void createModule(){
        SkiCentre document = mock(SkiCentre.class);

        Assertions.assertNotNull(skiCentreMapper.getModule(document));
    }

    @DisplayName("VS-4378 - SkiCentre - Create Module with all Fields")
    @Test
    void allFields(){
        SkiCentre document = mock(SkiCentre.class);
        HippoHtml description = mock(HippoHtml.class);

        when(document.getTitle()).thenReturn("Title");
        when(document.getCopy()).thenReturn(description);
        when(document.getFeed()).thenReturn("http://www.ski.scot/cairngorm/feed");
        when(document.getPisteMap()).thenReturn("map.pdf");
        when(document.getWebsite()).thenReturn("http://www.ski.scot/cairngorm");
        when(document.getTelephone()).thenReturn("+44 0131 123456");
        when(document.getAddress()).thenReturn("Address");

        SkiModule module = skiCentreMapper.getModule(document);

        Assertions.assertEquals("Title", module.getTitle());
        Assertions.assertEquals("http://www.ski.scot/cairngorm/feed", module.getFeedURL());
        Assertions.assertEquals(description, document.getCopy());
        Assertions.assertEquals("map.pdf", module.getPisteMap());
        Assertions.assertEquals("http://www.ski.scot/cairngorm", module.getWebsite().getLink());
        Assertions.assertEquals("+44 0131 123456", document.getTelephone());
        Assertions.assertEquals("Address", document.getAddress());
    }

    @Test
    @DisplayName("Resource Bundle are included in the template")
    void testIncludeResourceBundle() throws PageCompositionException {
        SkiCentre document = mock(SkiCentre.class);
        PageCompositionHelper compositionHelper = mock(PageCompositionHelper.class);

        skiCentreMapper.include(document, compositionHelper);

        verify(compositionHelper).addAllSiteLabels("ski");
    }
}
