package com.visitscotland.brxm.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.ListicleModule;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.mock.ListicleItemMockBuilder;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.utils.VsException;
import com.visitscotland.dataobjects.DataType;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.LATITUDE;
import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.LONGITUDE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListicleFactoryTest {

    @Mock
    LinkService linksService;
    @Mock
    DMSDataService dmsData;
    @Mock
    ImageFactory imageFactory;
    @Mock
    DMSUtils dmsUtils;
    @Mock
    DocumentUtilsService documentUtils;

    @Resource
    @InjectMocks
    ListicleFactory factory;

    @Mock
    Listicle page;

    @BeforeEach
    void initRequest() {
        when(page.getDescOrder()).thenReturn(Boolean.FALSE);
    }


    @Test
    @DisplayName("Create a listicle page")
    void createListiclePage() {
        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.emptyList());

        Assertions.assertNotNull(factory.generateItems(Locale.UK, page));
    }

    @Test
    @DisplayName("ListicleItem - Basic Item with no main product")
    void listicle_basic() {

        ListicleItem item = new ListicleItemMockBuilder().title("Title").subtitle("Edinburgh").addDescription().extraLink().build();
        FlatLink link = new FlatLink();

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(linksService.createFindOutMoreLink(any(), any(), any())).thenReturn(link);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        Assertions.assertEquals(1, items.get(0).getIndex());
        Assertions.assertEquals(item, items.get(0).getHippoBean());
        Assertions.assertEquals("Title", items.get(0).getTitle());
        Assertions.assertEquals("Edinburgh", items.get(0).getSubtitle());
        Assertions.assertNotNull(items.get(0).getDescription());
        Assertions.assertEquals(1, items.get(0).getLinks().size());
        Assertions.assertEquals(link, items.get(0).getLinks().get(0));
    }

    @Test
    @DisplayName("ListicleItem from CMSLink")
    void listicle_cmsLink() {
        ListicleItem item = new ListicleItemMockBuilder().addImage().cmsLink().build();
        EnhancedLink link = new EnhancedLink();
        FlatImage moduleImage = new FlatImage();
        link.setLink("www.google.com");

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.getImage(eq(item.getListicleItemImage()), any(), any())).thenReturn(moduleImage);
        when(linksService.createEnhancedLink(any(), any(), any(),anyBoolean())).thenReturn(link);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        Assertions.assertEquals(1, items.get(0).getLinks().size());
        Assertions.assertEquals(moduleImage, items.get(0).getImage());
        Assertions.assertEquals(link, items.get(0).getLinks().get(0));
    }

    @Test
    @DisplayName("ListicleItem from CMSLink - When the image is not set on the CMS")
    void listicle_cmsLink_fallbackImage() {
        ListicleItem item = new ListicleItemMockBuilder().cmsLink().build();
        EnhancedLink link = new EnhancedLink();
        link.setImage(new FlatImage());
        link.setLink("www.google.com");

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(linksService.createEnhancedLink(any(), any(), any(),anyBoolean())).thenReturn(link);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        Assertions.assertEquals(1, items.get(0).getLinks().size());
        Assertions.assertEquals("www.google.com", items.get(0).getLinks().get(0).getLink());

        Assertions.assertNull(item.getListicleItemImage());
        Assertions.assertEquals(link.getImage(), items.get(0).getImage());
    }

    @Test
    @DisplayName("ListicleItem from DMSLink")
    void listicle_dmsLink() {
        ListicleItem item = new ListicleItemMockBuilder().addImage().subtitle("Subtitle").dmsLink("1234").build();
        FlatLink link = new FlatLink();
        FlatImage moduleImage = new FlatImage();
        JsonNode node = mock(JsonNode.class);
        List<DataType> facilities = Collections.emptyList();

        when(node.has(LATITUDE)).thenReturn(false);

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.getImage(any(Image.class), any(), any())).thenReturn(moduleImage);
        when(dmsData.productCard("1234", Locale.UK)).thenReturn(node);
        when(linksService.createDmsLink(any(), any(), any())).thenReturn(link);
        when(dmsUtils.getKeyFacilities(node)).thenReturn(facilities);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        ListicleModule module = items.get(0);

        Assertions.assertEquals("Subtitle", module.getSubtitle());
        Assertions.assertEquals(moduleImage, module.getImage());
        Assertions.assertEquals(1, module.getLinks().size());
        Assertions.assertSame(facilities, module.getFacilities());

        Assertions.assertEquals(link, module.getLinks().get(0));
        verify(dmsData, times(1)).productCard("1234", Locale.UK);
    }

    @Test
    @DisplayName("VS-3086 ListicleItem from ExternalLink")
    void listicle_externalLink() {
        ListicleItem item = new ListicleItemMockBuilder().addImage().subtitle("Subtitle").externalLink().build();
        FlatLink link = new FlatLink("Find out more", "www.visitscotland.com", LinkType.EXTERNAL);
        FlatImage moduleImage = new FlatImage();

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.getImage(any(Image.class), any(), any())).thenReturn(moduleImage);
        when(linksService.createFindOutMoreLink(any(), any(), any())).thenReturn(link);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        ListicleModule module = items.get(0);

        Assertions.assertEquals("Subtitle", module.getSubtitle());
        Assertions.assertEquals(moduleImage, module.getImage());
        Assertions.assertEquals(link, module.getLinks().get(0));
    }

    @Test
    @DisplayName("VS-3206 ListicleItem extra links from shared link ")
    void listicle_extraSharedlLink() {
        ListicleItem item = new ListicleItemMockBuilder().addImage().subtitle("Subtitle").extraSharedLink("").build();

        FlatLink link = new FlatLink("Discover Spain", "www.visitspain.com", LinkType.EXTERNAL);
        FlatImage moduleImage = new FlatImage();

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.getImage(any(Image.class), any(), any())).thenReturn(moduleImage);
        when(linksService.createSimpleLink(any(), any(), any())).thenReturn(link);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        ListicleModule module = items.get(0);
        FlatLink extraLink = module.getLinks().get(0);

        Assertions.assertEquals("www.visitspain.com", extraLink.getLink());
        Assertions.assertEquals("Discover Spain", extraLink.getLabel());
        assertEquals(LinkType.EXTERNAL, extraLink.getType());
    }

    @Test
    @DisplayName("VS-3206 ListicleItem extra links from shared link override label")
    void listicle_extraSharedlLinkOverride() {
        ListicleItem item = new ListicleItemMockBuilder().addImage().subtitle("Subtitle").extraSharedLink("Override text").build();

        FlatLink link = new FlatLink("Discover Spain", "www.visitspain.com", LinkType.EXTERNAL);
        FlatImage moduleImage = new FlatImage();

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.getImage(any(Image.class), any(), any())).thenReturn(moduleImage);
        when(linksService.createSimpleLink(any(), any(), any())).thenReturn(link);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        ListicleModule module = items.get(0);
        FlatLink extraLink = module.getLinks().get(0);

        Assertions.assertEquals("www.visitspain.com", extraLink.getLink());
        Assertions.assertEquals("Override text", extraLink.getLabel());
        assertEquals(LinkType.EXTERNAL, extraLink.getType());
    }


    @Test
    @DisplayName("VS-3086 ListicleItem from Product Search Results")
    void listicle_psrLink() {
        ListicleItem item = new ListicleItemMockBuilder().addImage().subtitle("Subtitle").productSearchLink().build();
        FlatLink link = new FlatLink("Find out more", "www.visitscotland.com", LinkType.INTERNAL);
        FlatImage moduleImage = new FlatImage();

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.getImage(any(Image.class), any(), any())).thenReturn(moduleImage);
        when(linksService.createFindOutMoreLink(any(), any(), any())).thenReturn(link);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(1, items.size());
        ListicleModule module = items.get(0);

        Assertions.assertEquals("Subtitle", module.getSubtitle());
        Assertions.assertEquals(moduleImage, module.getImage());
        Assertions.assertEquals(link, module.getLinks().get(0));
    }

    @Test
    @DisplayName("ListicleItem from DMSLink - Subtitle & Image take fallback values from the DMS")
    void listicle_dmsLink_fallback() {
        ListicleItem item = new ListicleItemMockBuilder().dmsLink("1234").build();
        FlatImage dmsImage = new FlatImage();
        dmsImage.setLocation("Location");
        JsonNode node = mock(JsonNode.class, withSettings().defaultAnswer(Answers.RETURNS_DEEP_STUBS));

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.createImage(any(JsonNode.class), any())).thenReturn(dmsImage);
        when(dmsData.productCard("1234", Locale.UK)).thenReturn(node);

        ListicleModule module = factory.generateItems(Locale.UK, page).get(0);

        Assertions.assertEquals("Location", module.getSubtitle());
        Assertions.assertEquals(dmsImage, module.getImage());
    }

    @Test
    @DisplayName("ListicleItem from DMSLink - Coordinates take fallback values from the DMS")
    void listicle_dmsLink_coordinates() {
        ListicleItem item = new ListicleItemMockBuilder().addImage().dmsLink("1234").build();
        FlatImage moduleImage = new FlatImage();
        moduleImage.setCmsImage((Image) item.getListicleItemImage());
        JsonNode node = mock(JsonNode.class, withSettings().defaultAnswer(Answers.RETURNS_DEEP_STUBS));

        when(node.has(LATITUDE)).thenReturn(true);
        when(node.get(LATITUDE).asDouble()).thenReturn(12.);
        when(node.get(LONGITUDE).asDouble()).thenReturn(-2.1);

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(imageFactory.getImage(any(Image.class), any(), any())).thenReturn(moduleImage);
        when(dmsData.productCard("1234", Locale.UK)).thenReturn(node);

        ListicleModule module = factory.generateItems(Locale.UK, page).get(0);

        Assertions.assertEquals(moduleImage, module.getImage());
        Assertions.assertEquals(12., moduleImage.getCoordinates().getLatitude());
        Assertions.assertEquals(-2.1, moduleImage.getCoordinates().getLongitude());
    }

    @Test
    @DisplayName("ListicleItem from DMSLink - No DMS id generates a CMS warning")
    void listicle_dmsLink_noMatch() {
        ListicleItem item = new ListicleItemMockBuilder().dmsLink("1234").build();

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(Collections.singletonList(item));
        when(dmsData.productCard("1234", Locale.UK)).thenReturn(null);

        ListicleModule module = factory.generateItems(Locale.UK, page).get(0);

        Assertions.assertEquals(1, module.getErrorMessages().size());
    }


    @Test
    @DisplayName("The items will be shown with descendent order")
    void descendentOrder() {
        List<ListicleItem> listicleItems = Arrays.asList(
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().build()
        );

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(listicleItems);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(listicleItems.size(), items.size());
        for (int i = items.size() - 1; i > 0; i--) {
            Assertions.assertEquals(i + 1, items.get(i).getIndex());
        }
    }

    @Test
    @DisplayName("The items will be shown with ascendant order")
    void ascendantOrder() {
        List<ListicleItem> listicleItems = Arrays.asList(
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().build()
        );

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(listicleItems);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(listicleItems.size(), items.size());
        for (int i = 0; i < items.size(); i++) {
            Assertions.assertEquals(i + 1, items.get(i).getIndex());
        }
    }

    @Test
    @DisplayName("VS-3221 - Listicle item with no published link should be skipped")
    void noPublishedLink() {
        List<ListicleItem> listicleItems = Arrays.asList(
                new ListicleItemMockBuilder().cmsLink(null).build()
        );
        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(listicleItems);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(0, items.size());
    }

    @Test
    @DisplayName("VS-3221 - When item is skipped due to not containing a published link, numbering of other items should still be correct - desc")
    void noPublishedLink_numberingStillCorrect_desc() {
        List<ListicleItem> listicleItems = Arrays.asList(
                new ListicleItemMockBuilder().cmsLink(null).build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().cmsLink(null).build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().cmsLink(null).build()
        );

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(listicleItems);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(3, items.size());
        for (int i = items.size() - 1; i > 0; i--) {
            Assertions.assertEquals(i + 1, items.get(i).getIndex());
        }
    }

    @Test
    @DisplayName("VS-3221 - When item is skipped due to not containing a published link, numbering of other items should still be correct - asc")
    void noPublishedLink_numberingStillCorrect_asc() {
        List<ListicleItem> listicleItems = Arrays.asList(
                new ListicleItemMockBuilder().cmsLink(null).build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().cmsLink(null).build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().build(),
                new ListicleItemMockBuilder().cmsLink(null).build()
        );

        when(documentUtils.getAllowedDocuments(page, ListicleItem.class)).thenReturn(listicleItems);

        List<ListicleModule> items = factory.generateItems(Locale.UK, page);

        Assertions.assertEquals(3, items.size());
        for (int i = 1; i <= items.size(); i++) {
            Assertions.assertEquals(i, items.get(i-1).getIndex());
        }
    }


    @Test
    @DisplayName("Precedence order for images sources")
    @Disabled("To be confirmed")
    void imageOrder() {
        /**
         *  TODO
         1 (most important): Location(Subtitle) field
         2. DMS
         3. Location from the image
         */
        throw new VsException("Not implemented yet");

    }


}
