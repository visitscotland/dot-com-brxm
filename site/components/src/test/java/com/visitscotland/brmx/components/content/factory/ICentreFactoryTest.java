package com.visitscotland.brmx.components.content.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brmx.beans.ICentre;
import com.visitscotland.brmx.beans.Image;
import com.visitscotland.brmx.beans.mapping.FlatImage;
import com.visitscotland.brmx.beans.mapping.ICentreModule;
import com.visitscotland.brmx.beans.mapping.megalinks.EnhancedLink;
import com.visitscotland.brmx.dms.DMSDataService;
import com.visitscotland.brmx.dms.ProductSearchBuilder;
import com.visitscotland.brmx.mock.TouristInformationMockBuilder;
import com.visitscotland.brmx.services.ResourceBundleService;
import com.visitscotland.brmx.utils.HippoUtilsService;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jcr.RepositoryException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ICentreFactoryTest {

    @Mock
    HippoUtilsService utils;

    @Mock
    DMSDataService dmsData;

    @Mock
    LinkModulesFactory linkFactory;

    @Mock
    ResourceBundleService bundle;

    ICentreFactory factory;

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

    @BeforeEach
    void init() {
        factory = new ICentreFactory(utils, dmsData, linkFactory, bundle);
        mockBuilder = new TouristInformationMockBuilder().addICentre();
    }

//    private ICentre iCentreMock(){
//        return mock(ICentre.class);
//    }

    @Test
    @DisplayName("iCentre Module for General pages")
    void getNoModule() {
        //Gets a module with a link to a  when  no location defined (General Pages)
        //Verifies that a link to the iCentres page is defined
        //Verifies that no request to the dms is performed

        when(bundle.getResourceBundle(ICentreFactory.BUNDLE_ID, "icentre.description.link", Locale.UK))
                .thenReturn("url");
        when(bundle.getResourceBundle(ICentreFactory.BUNDLE_ID, "icentre.description.link.text", Locale.UK))
                .thenReturn("link text");

        ICentreModule module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, "");

        verify(dmsData, never()).legacyMapSearch(any());
        assertEquals(1, module.getLinks().size());
        assertEquals("url", module.getLinks().get(0).getLink());
        assertEquals("link text", module.getLinks().get(0).getLabel());
    }

    @Test
    @DisplayName("iCentre Module for Destination pages")
    void getModule() throws JsonProcessingException {
        //Returns a basic module when the location is provided (Destination Pages)
        //Also verifies that the list of iCentre match with the location
        String location = "Edinburgh";
        JsonNode node = new ObjectMapper().readTree(MOCK_JSON);

        ArgumentCaptor<ProductSearchBuilder> captor = ArgumentCaptor.forClass(ProductSearchBuilder.class);
        doReturn(node).when(dmsData).legacyMapSearch(captor.capture());

        ICentreModule module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, location);

        String dmsUrl = captor.getValue().build();

        assertTrue(dmsUrl.contains(location), "The queryString " + dmsUrl + "does not contain " + location);
        assertNotNull(module);
        assertNotNull(module.getLinks().get(0).getLink());
    }

    /**
     * I'm not sure about whether this test is useful or not
     */
    @Test
    @DisplayName("ICentre Module - All fields are mapped correctly")
    void getModule_mapping() {
        // Verifies that all data coming from the document gets correctly mapped in the module
        // The Product product doesn't get directly mapped.

        ICentre ti = mockBuilder.addICentreTitle("title").addICentreImage()
                .addQuoteText().addQuoteImage().addQuoteAuthor("Moo McCoo").addQuoteRole("Grass QA")
                .build().getICentre();

        ICentreModule module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, null);

        assertEquals("title", module.getTitle());
        assertEquals(ti.getImage(), module.getImage().getCmsImage());
        assertEquals(ti.getQuote().getQuote(), module.getQuote());
        assertEquals(ti.getQuote().getImage(), module.getQuoteImage().getCmsImage());
        assertEquals("Moo McCoo", module.getQuoteAuthorName());
        assertEquals("Grass QA", module.getQuoteAuthorTitle());
    }

    @Test
    @DisplayName("VS-1507 - Explicit and Default Title")
    void getModule_defaultTitle() {
        // Verifies that the default title is used when a title is not defined the document
        ICentreModule module;
        when(bundle.getResourceBundle(eq(ICentreFactory.BUNDLE_ID), any(), eq(Locale.UK))).thenReturn(null);

        //Default title
        when(bundle.getResourceBundle(ICentreFactory.BUNDLE_ID, "icentre.title.default", Locale.UK))
                .thenReturn("title");
        module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, null);
        assertEquals("title", module.getTitle());

        //Title from the document
        module = factory.getModule(mockBuilder.addICentreTitle("Document title").build().getICentre(), Locale.UK, null);
        assertEquals("Document title", module.getTitle());
    }

    @Test
    @DisplayName("VS-1507 - Image logic")
    void getModule_getImageFromTheProduct() throws JsonProcessingException, RepositoryException, QueryException, ObjectBeanManagerException {
        // Verifies the following requirement:
        // - Default to generic image of from any iCentre
        // - Option to pull an image from DMS (to match a location mentioned within quote, using CTA link to DMS listing page)
        ICentreModule module;
        Image defaultImage = mock(Image.class);
        when(bundle.getResourceBundle(eq(ICentreFactory.BUNDLE_ID), any(), eq(Locale.UK))).thenReturn(null);

        //No image Defined
        when(utils.getDocumentFromNode((String) null)).thenReturn(defaultImage);
        module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, null);
        assertNull(module.getImage().getExternalImage());
        assertEquals(defaultImage, module.getImage().getCmsImage());

        //TODO Verify CMS from this case
        //No image Defined but DMS ID provided
        JsonNode node = new ObjectMapper().readTree(MOCK_JSON);
        EnhancedLink link = new EnhancedLink();
        link.setImage(new FlatImage());
        link.getImage().setExternalImage("dms-image.jpg");
        when(dmsData.legacyMapSearch(any())).thenReturn(node);
        when(linkFactory.createEnhancedLink(any(), any(),false)).thenReturn(link);
        module = factory.getModule(mockBuilder.addQuoteProduct().build().getICentre(), Locale.CANADA_FRENCH, "St. Kilda");
        assertEquals("dms-image.jpg", module.getImage().getExternalImage());
        assertNull(module.getImage().getCmsImage());

        //Image defined in the document
        module = factory.getModule(mockBuilder.addICentreImage().build().getICentre(), Locale.CANADA_FRENCH, "St. Kilda");
        assertNotEquals(defaultImage, module.getImage().getCmsImage());
        assertNull(module.getImage().getExternalImage());
    }

    @Test
    @DisplayName("VS-1507 - The module disappears when there is not iCentres in the location")
    void getModule_disappear() throws JsonProcessingException {
        //Verifies that the module disappears when there is no ICentre
        when(dmsData.legacyMapSearch(any())).thenReturn(new ObjectMapper().readTree("[{}]"));
        ICentreModule module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, "St. Kilda");

        assertNull(module);
    }

    @Test
    @DisplayName("VS-1507 - Different labels for the description depending on the number of iCentres found")
    void getModule_description() throws JsonProcessingException {
        ICentreModule module;
        when(bundle.getResourceBundle(eq(ICentreFactory.BUNDLE_ID), any(), eq(Locale.UK))).thenReturn(null);

        //Single VIC
        when(dmsData.legacyMapSearch(any())).thenReturn(new ObjectMapper().readTree(MOCK_JSON));
        when(bundle.getResourceBundle(eq(ICentreFactory.BUNDLE_ID), eq("icentre.description.singleVic"), eq(Locale.UK)))
                .thenReturn("Single VIC");
        module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, "Skye");
        assertEquals("Single VIC", module.getDescription());

        //Multiple VIC
        when(dmsData.legacyMapSearch(any())).thenReturn(new ObjectMapper().readTree(MOCK_JSON_MULTIPLE));
        when(bundle.getResourceBundle(eq(ICentreFactory.BUNDLE_ID), eq("icentre.description.multipleVic"), eq(Locale.UK)))
                .thenReturn("Multiple VICs");
        module = factory.getModule(mockBuilder.build().getICentre(), Locale.UK, "Highlands");
        assertEquals("Multiple VICs", module.getDescription());
    }

}
