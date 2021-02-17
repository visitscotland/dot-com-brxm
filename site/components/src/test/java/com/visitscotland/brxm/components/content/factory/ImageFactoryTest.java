package com.visitscotland.brxm.components.content.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brxm.beans.ExternalLink;
import com.visitscotland.brxm.beans.Image;
import com.visitscotland.brxm.beans.ImageData;
import com.visitscotland.brxm.beans.InstagramImage;
import com.visitscotland.brxm.beans.dms.LocationObject;
import com.visitscotland.brxm.beans.mapping.Coordinates;
import com.visitscotland.brxm.beans.mapping.FlatImage;
import com.visitscotland.brxm.beans.mapping.Module;
import com.visitscotland.brxm.dms.LocationLoader;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.CommonUtils;

import com.visitscotland.brxm.utils.Properties;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImageFactoryTest {

    @Resource
    @InjectMocks
    ImageFactory imageFactory;

    @Mock
    LocationLoader locationLoader;

    @Mock
    ResourceBundleService bundle;

    @Mock
    CommonUtils utils;

    @Mock
    Properties properties;

    final String INSTAGRAM_OBJECT = "{ " +
            "\"thumbnail_url\": \"http://instagram/visitscotland\"," +
            "\"author_name\": \"Mooe McCoo\"" +
            "}";

    @Test
    @DisplayName("Request information about the image to instagram. (Happy Path)")
    void getImageFromInstagram() throws IOException {
        LocationObject location = new LocationObject(null, null,"The Highlands",null, 1., -1., null,null);
        InstagramImage insta = mock(InstagramImage.class);

        when(insta.getCaption()).thenReturn("The magnificent coo");
        when(insta.getId()).thenReturn("007");
        when(insta.getLocation()).thenReturn("hl");
        when(utils.requestUrl(any())).thenReturn(INSTAGRAM_OBJECT);
        when(locationLoader.getLocation("hl", Locale.UK)).thenReturn(location);

        FlatImage image = imageFactory.getImage(insta, null, Locale.UK);

        Assertions.assertNotNull(image);
        Assertions.assertEquals("http://instagram/visitscotland", image.getExternalImage());
        Assertions.assertEquals("Mooe McCoo", image.getCredit());
        Assertions.assertEquals(FlatImage.Source.INSTAGRAM, image.getSource());
        Assertions.assertEquals("The magnificent coo", image.getAltText());
        Assertions.assertEquals("The magnificent coo", image.getDescription());
        Assertions.assertTrue(image.getPostUrl().contains("007"));
    }

    @Test
    @DisplayName("When the instagram object does not match with the spec, a null object is return and the issue is logged")
    void getImageFromInstagram_incorrect() throws IOException {
        InstagramImage image = mock(InstagramImage.class);
        Module module = new Module();

        when(utils.requestUrl(any())).thenReturn("{}");

        assertNull(imageFactory.getImage(image, module, Locale.UK));
        assertEquals(1, module.getErrorMessages().size());
    }

    @Test
    @DisplayName("The image retrieves the information from the CMS. (Happy Path)")
    void getImageFromCMS(){
        LocationObject location = new LocationObject(null, null,"The Highlands",null, 1., -1., null,null);
        Module module = new Module();
        Image cmsImage = mock(Image.class);

        when (cmsImage.getAltText()).thenReturn("Alt Text");
        when(cmsImage.getDescription()).thenReturn("Caption");
        when(cmsImage.getLocation()).thenReturn("hl");
        when(locationLoader.getLocation("hl", Locale.UK)).thenReturn(location);

        FlatImage image = imageFactory.getImage(cmsImage, module, Locale.UK);

        assertEquals(cmsImage, image.getCmsImage());
        assertEquals("Alt Text", image.getAltText());
        assertEquals("Caption", image.getDescription());
        Assertions.assertEquals("The Highlands", image.getLocation());
        Assertions.assertEquals(new Coordinates(1.,-1.), image.getCoordinates());
    }

    @Test
    @DisplayName("Localized images override alt text and description")
    void getImageFromCMS_localized(){
        Module module = new Module();
        Image cmsImage = mock(Image.class);
        ImageData data = mock(ImageData.class);
        when(cmsImage.getAltText()).thenReturn("English-at");
        when(cmsImage.getDescription()).thenReturn("English-d");
        when(cmsImage.getFr()).thenReturn(data);
        when(data.getAltText()).thenReturn("French-at");
        when(data.getCaption()).thenReturn("French-d");

        //English locale
        FlatImage english = imageFactory.getImage(cmsImage, module, Locale.ENGLISH);
        assertEquals("English-at", english.getAltText());
        assertEquals("English-d", english.getDescription());

        //Other Existing locale
        FlatImage french = imageFactory.createImage(cmsImage, module, Locale.FRENCH);
        assertEquals("French-at", french.getAltText());
        assertEquals("French-d", french.getDescription());

        //A non-existing locale
        FlatImage chinese = imageFactory.createImage(cmsImage, module, Locale.CHINESE);
        assertEquals("English-at", chinese.getAltText());
        assertEquals("English-d", chinese.getDescription());
    }

    @Test
    @DisplayName("A CMS image with no translation should generate 2 preview warnings")
    void getImageFromCMS_cmsWarning(){
        Module module = new Module();
        Image cmsImage = mock(Image.class);
        ImageData data = mock(ImageData.class);
        when(cmsImage.getFr()).thenReturn(data);
        when(data.getAltText()).thenReturn("");
        when(data.getCaption()).thenReturn(null);

        FlatImage image = imageFactory.getImage(cmsImage, module, Locale.FRENCH);

        verify(cmsImage, times(1)).getAltText();
        verify(cmsImage, times(1)).getDescription();

        assertEquals(2, module.getErrorMessages().size());
    }

    @Test
    @DisplayName("Request information about the image from the DMS")
    void getImageFromDMS() throws JsonProcessingException {
        final String DMS_OBJECT = "{ " +
                "\"name\": \"VisitScotland HeadQuarters\"," +
                "\"latitude\": 12," +
                "\"longitude\": -21," +
                "\"images\": [{"+
                "   \"mediaUrl\": \"http://www.visitscoland.com/VSHQ.jpeg\"," +
                "   \"copyright\": \"A guy\"," +
                "   \"altText\": \"Impressive building\"" +
                "}], " +
                "\"address\": {" +
                "   \"city\": \"Leith\"" +
                "}}";
        Module module = new Module();
        JsonNode dmsProduct = new ObjectMapper().readTree(DMS_OBJECT);

        FlatImage image = imageFactory.createImage(dmsProduct, module);

        Assertions.assertEquals("http://www.visitscoland.com/VSHQ.jpeg", image.getExternalImage());
        Assertions.assertEquals("A guy", image.getCredit());
        Assertions.assertEquals("Impressive building", image.getDescription());
        Assertions.assertEquals("Impressive building", image.getAltText());
        Assertions.assertEquals("Leith", image.getLocation());
        Assertions.assertEquals(12, image.getCoordinates().getLatitude());
        Assertions.assertEquals(-21, image.getCoordinates().getLongitude());
    }

    @Test
    @DisplayName("The description of the product populates the alt-text when the later is not defined in the DMS")
    void getImageFromDMS_noAltText() throws JsonProcessingException {
        final String DMS_OBJECT = "{ " +
                "\"name\": \"VisitScotland HeadQuarters\"," +
                "\"images\": [{"+
                "\"mediaUrl\": \"http://www.visitscoland.com/VSHQ.jpeg\"" +
                "}]}";
        Module module = new Module();
        JsonNode dmsProduct = new ObjectMapper().readTree(DMS_OBJECT);

        FlatImage image = imageFactory.createImage(dmsProduct, module);

        Assertions.assertEquals("VisitScotland HeadQuarters", image.getDescription());
        Assertions.assertEquals("VisitScotland HeadQuarters", image.getAltText());
    }

    @Test
    @DisplayName("When the image is not valid a null is returned")
    void getImageFromDMS_invalidImage() throws JsonProcessingException {
        final String DMS_OBJECT = "{ " +
                "\"name\": \"VisitScotland HeadQuarters\"," +
                "\"images\": [{}] }";
        Module module = new Module();
        assertNull(imageFactory.createImage( new ObjectMapper().readTree("{}"), module));
        assertNull(imageFactory.createImage( new ObjectMapper().readTree("{ " +
                "\"name\": \"VisitScotland HeadQuarters\"," +
                "\"image\": [{}] }"), module));
        assertEquals(2, module.getErrorMessages().size());
    }

    @Test
    @Disabled("The requirements are not completed")
    @DisplayName("Request an image from a Third Party system")
    void getImageFromExternalSource(){
        ExternalLink externalLink = mock(ExternalLink.class);

        when(externalLink.getLink()).thenReturn("http://third-party.com/image");

        FlatImage image = imageFactory.getImage(externalLink, null, Locale.UK);

        assertEquals("http://third-party.com/image", image.getExternalImage());
    }


}
