package com.visitscotland.brxm.components.content.factory;

import com.visitscotland.brxm.beans.IKnow;
import com.visitscotland.brxm.beans.mapping.IKnowModule;
import com.visitscotland.brxm.cfg.SpringContext;
import com.visitscotland.brxm.dms.DMSConstants;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.mock.TouristInformationMockBuilder;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.HippoUtilsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IKnowFactoryTest {

    static ProductSearchBuilder psBuilder;

    @Mock
    HippoUtilsService utils;

    @Mock
    ResourceBundleService bundle;

    IKnowFactory factory;

    TouristInformationMockBuilder mockBuilder;

    @BeforeAll
    public static void initContext(){
        ApplicationContext context = mock(ApplicationContext.class, withSettings().lenient());
        psBuilder = mock(ProductSearchBuilder.class, Answers.RETURNS_SELF);
        when(psBuilder.build()).thenReturn("URL");
        when(context.getBean(ProductSearchBuilder.class)).thenReturn(psBuilder);

        new SpringContext().setApplicationContext(context);
    }

    @BeforeEach
    void init() {
        factory = new IKnowFactory(utils, bundle);
        mockBuilder = new TouristInformationMockBuilder().addIKnow();

    }

    @Test
    @DisplayName("VS-1661 - iKnow Module General Definition")
    void getModule()  throws UnsupportedEncodingException {
        //Gets a null when there is no location defined
        //Verifies default values
        String location = "Edinburgh";
        when(bundle.getResourceBundle(ICentreFactory.BUNDLE_ID,"iknow.title.default", Locale.UK))
                .thenReturn("default title");
        when(bundle.getResourceBundle(ICentreFactory.BUNDLE_ID,"iknow.description.default", Locale.UK))
                .thenReturn("default description");
        when(bundle.getResourceBundle(ICentreFactory.BUNDLE_ID,"iknow.link.label", Locale.UK))
                .thenReturn("link text");

        IKnowModule module = factory.getIKnowModule(mockBuilder.build().getIKnow(), location, Locale.UK);

        assertEquals("default title", module.getTitle());
        assertEquals("default description", module.getDescription());
        assertEquals("link text", module.getLink().getLabel());

        ArgumentCaptor<String> locationCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> awardCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeCaptor = ArgumentCaptor.forClass(String.class);

        verify(psBuilder).location(locationCaptor.capture());
        verify(psBuilder).award(awardCaptor.capture());
        verify(psBuilder).productTypes(typeCaptor.capture());


        assertEquals(location, locationCaptor.getValue());
        assertEquals(DMSConstants.AWARD_IKNOW, awardCaptor.getValue(), location);
        assertEquals(DMSConstants.TYPE_SEE_DO, typeCaptor.getValue(), location);
    }


    @Test
    @DisplayName("VS-1661 - iKnow Module General Definition")
    void getModule_checkMapping() {
        // Verifies that the default title is used when a title is not defined the document
        IKnow iknow = mockBuilder.addIKnowTitle("idunno").addIKnowDescription("description").build().getIKnow();
        IKnowModule module = factory.getIKnowModule(iknow, "Edinburgh", Locale.UK);

        assertEquals("idunno", module.getTitle());
        assertEquals("description", module.getDescription());
        assertEquals(iknow.getDescription(), module.getDescription());
    }

}
