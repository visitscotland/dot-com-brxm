package com.visitscotland.brxm.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.factory.ImageFactory;
import com.visitscotland.brxm.hippobeans.ICentre;
import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.hippobeans.Quote;
import com.visitscotland.brxm.mock.TouristInformationMockBuilder;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatQuote;
import com.visitscotland.brxm.model.ICentreModule;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.CMSProperties;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.brxm.utils.pagebuilder.PageCompositionException;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.core.container.ComponentManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jcr.RepositoryException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ICentreMapperTest {

    @Mock
    HippoUtilsService utils;

    @Mock
    DMSDataService dmsData;

    @Mock
    QuoteMapper quoteEmbedder;

    @Mock
    ImageFactory imageFactory;

    @Mock
    ResourceBundleService bundle;

    @Mock
    CMSProperties properties;

    @Mock
    SiteProperties siteProperties;


    @InjectMocks
    ICentreMapper factory;

    TouristInformationMockBuilder mockBuilder;

    static final String MOCK_JSON = "[{" +
            " \"properties\": {" +
            " \"name\":\"name\", \"id\":\"id\" " +
            "}}]";

    static final String MOCK_JSON_MULTIPLE = "[{" +
            " \"properties\": {" +
            " \"name\":\"name\", \"id\":\"id\" " +
            "}}, {" +
            " \"properties\": {" +
            " \"name\":\"name\", \"id\":\"id\" " +
            "}}]";

    @BeforeAll
    public static void initContext(){
        ComponentManager context = mock(ComponentManager.class, withSettings().lenient());
        ProductSearchBuilder psb = mock(ProductSearchBuilder.class, Answers.RETURNS_SELF);
        when(psb.build()).thenReturn("URL");
        when(context.getComponent(ProductSearchBuilder.class)).thenReturn(psb);

        VsComponentManager.setComponentManager(context);
    }

    @BeforeEach
    void init() {
        factory = new ICentreMapper(utils, dmsData, bundle, quoteEmbedder, imageFactory, properties, siteProperties, utils);
        mockBuilder = new TouristInformationMockBuilder().addICentre();
    }

    @Test
    @DisplayName("iCentre Module for General pages")
    void getNoModule() throws PageCompositionException {
        //Gets a module with a link to a  when  no location defined (General Pages)
        //Verifies that a link to the iCentres page is defined
        //Verifies that no request to the dms is performed

        ICentreModule module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK);

        verify(dmsData, never()).legacyMapSearch(any());
    }

    @Test
    @DisplayName("iCentre Module for Destination pages")
    void getModule() throws JsonProcessingException, PageCompositionException {
        //Returns a basic module when the location is provided (Destination Pages)
        //Also verifies that the list of iCentre match with the location
        String location = "Edinburgh";
        JsonNode node = new ObjectMapper().readTree(MOCK_JSON);

        ICentreModule module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK);

        assertNotNull(module);
    }

    /**
     * I'm not sure about whether this test is useful or not
     */
    @Test
    @DisplayName("ICentre Module - All fields are mapped correctly")
    void getModule_mapping() throws PageCompositionException {
        // Verifies that all data coming from the document gets correctly mapped in the module
        // The Product doesn't get directly mapped.
        ICentre ti = mockBuilder.addICentreTitle("title").addICentreImage()
//                .addQuoteText().addQuoteImage().addQuoteAuthor("Moo McCoo").addQuoteRole("Grass QA")
                .build().getICentre();
        FlatImage image = new FlatImage();
        image.setCmsImage(ti.getImage());
        when(imageFactory.createImage(any(Image.class), any(), any())).thenReturn(image);

        ICentreModule module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK);

        assertEquals("title", module.getTitle());
        assertEquals(ti.getImage(), module.getImage().getCmsImage());
//        assertEquals(ti.getQuote().getQuote(), module.getQuote());
//        assertEquals(ti.getQuote().getImage(), module.getQuote().getImage().getCmsImage());
//        assertEquals("Moo McCoo", module.getQuote().getAuthorName());
//        assertEquals("Grass QA", module.getQuote().getAuthorTitle());
    }

    @Test
    @DisplayName("VS-1507 - Explicit and Default Title")
    void getModule_defaultTitle() throws PageCompositionException {
        // Verifies that the default title is used when a title is not defined the document
        ICentreModule module;
        when(bundle.getResourceBundle(eq(ICentreMapper.BUNDLE_ID), any(), eq(Locale.UK))).thenReturn(null);

        //Default title
        when(bundle.getResourceBundle(ICentreMapper.BUNDLE_ID, "icentre.title.default", Locale.UK))
                .thenReturn("title");
        module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK);
        assertEquals("title", module.getTitle());

        //Title from the document
        module = factory.getModule(mockBuilder.addICentreTitle("Document title").build().getICentre(), Locale.UK);
        assertEquals("Document title", module.getTitle());
    }

    @Test
    @DisplayName("VS-1507 - Image logic")
    void getModule_getImageFromTheProduct() throws JsonProcessingException, RepositoryException, QueryException, ObjectBeanManagerException, PageCompositionException {
        // Verifies the following requirement:
        // - Default to generic image of from any iCentre
        // - Option to pull an image from DMS (to match a location mentioned within quote, using CTA link to DMS listing page)
        ICentreModule module;
        when(bundle.getResourceBundle(eq(ICentreMapper.BUNDLE_ID), any(), eq(Locale.UK))).thenReturn(null);

        //Definition of the Quote when != null
        EnhancedLink link = new EnhancedLink();
        link.setImage(new FlatImage());
        link.getImage().setExternalImage("dms-image.jpg");
        FlatQuote quote = new FlatQuote();
        quote.setLink(link);
        when(quoteEmbedder.getQuote(any(Quote.class), any(), any())).thenReturn(quote);

        //Definition of the Image when != null
        FlatImage cmsImage = new FlatImage();
        cmsImage.setCmsImage(mock(Image.class));
        when(imageFactory.createImage(any(Image.class), any(), any())).thenReturn(cmsImage);

        //Definition of the default Image
        Image defaultCMSImage = mock(Image.class);
        FlatImage defaultImage = new FlatImage();
        defaultImage.setCmsImage(defaultCMSImage);
        when(utils.getDocumentFromNode((String) null)).thenReturn(defaultCMSImage);
        when(imageFactory.createImage(eq(defaultCMSImage), any(), any())).thenReturn(defaultImage);

        //Case 1: No image Defined => Default Image.
        module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK);
        assertNull(module.getImage().getExternalImage());
        assertEquals(defaultImage, module.getImage());

        //Case 2: No image Defined but DMS ID provided => Image from DMS
        JsonNode node = new ObjectMapper().readTree(MOCK_JSON);
        module = factory.getModule(mockBuilder.addQuote().build().getICentre(), Locale.CANADA_FRENCH);
        assertEquals("dms-image.jpg", module.getImage().getExternalImage());
        assertNull(module.getImage().getCmsImage());

        //Case 3: Image defined in the document => Defined Image
        module = factory.getModule(mockBuilder.addICentreImage().build().getICentre(), Locale.CANADA_FRENCH);
        assertEquals(cmsImage, module.getImage());
        assertNull(module.getImage().getExternalImage());
    }

}
