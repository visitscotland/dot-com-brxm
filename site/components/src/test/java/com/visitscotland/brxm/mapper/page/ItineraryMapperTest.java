package com.visitscotland.brxm.mapper.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.DMSDataService;
import com.visitscotland.brxm.dms.DMSUtils;
import com.visitscotland.brxm.dms.ProductSearchBuilder;
import com.visitscotland.brxm.factory.hippo.ValueList;
import com.visitscotland.brxm.hippobeans.DMSLink;
import com.visitscotland.brxm.hippobeans.Day;
import com.visitscotland.brxm.hippobeans.Itinerary;
import com.visitscotland.brxm.mapper.EntryMapper;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.mock.ItineraryDayMockBuilder;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.services.DocumentUtilsService;
import com.visitscotland.brxm.services.LinkService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.ContentLogger;
import org.hippoecm.hst.core.container.ComponentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//TODO This unit test needs to be broken down
@Disabled
@ExtendWith(MockitoExtension.class)
class ItineraryMapperTest {

    @Mock
    Itinerary itinerary;
    @Mock
    ResourceBundleService bundle;
    @Mock
    DMSDataService dmsData;
    @Mock
    ImageMapper imageMapper;
    @Mock
    DMSUtils utils;
    @Mock
    DocumentUtilsService documentUtils;
    @Mock
    LinkService linkService;
    @Mock
    EntryMapper entryMapper;
    @Mock
    ContentLogger logger;

    @Mock(answer = Answers.RETURNS_SELF)
    private ProductSearchBuilder builder;

    @InjectMocks
    ItineraryMapper factory;

    @BeforeEach
    void setUp() {
        initProductSearchBuilder();
    }

    private void initProductSearchBuilder() {
        ComponentManager context = mock(ComponentManager.class, withSettings().lenient());
        when(context.getComponent(ProductSearchBuilder.class)).thenReturn(builder);
        VsComponentManager.setComponentManager(context);
    }

    @Test
    @DisplayName("Create an itinerary page")
    void createSimpleItinerary() {
        when(documentUtils.getAllowedDocuments(itinerary, Day.class)).thenReturn(Collections.emptyList());

        ItineraryPage iti = factory.buildItinerary(itinerary, Locale.UK);
        assertNotNull(iti);
        assertEquals(BigDecimal.ZERO, iti.getDistance());

    }

    @Test
    @DisplayName("When no cta provided for DMS link, default cta is set")
    void dmsCtaSet() throws Exception {
        final String JSON = "{" +
                " \"dmsLink\": {\"link\": \"/info/fake-product-p123\"}," +
                " \"name\":\"dms title\", " +
                " \"images\":[{" +
                "    \"mediaUrl\":\"https://img.visitscotland.com/fake-product.jpg\"" +
                "}]}";
        JsonNode node = new ObjectMapper().readTree(JSON);
        List<Day> days = new ItineraryDayMockBuilder().addDmsStop("123").title("module title").buildAsList();
        when(documentUtils.getAllowedDocuments(itinerary, Day.class)).thenReturn(days);
        when(dmsData.productCard("123", Locale.UK)).thenReturn(node);
        when(bundle.getFindOutMoreAboutCta("module title", Locale.UK)).thenReturn("Find out more about module title");

        factory.buildItinerary(itinerary, Locale.UK);

        verify(linkService).createDmsLink(Locale.UK, (DMSLink) days.get(0).getStops().get(0).getStopItem(), node, "Find out more about module title");
    }

    @Test
    @DisplayName("When no cta provided for external link, default cta is set")
    void externalCtaSet() {
        List<Day> days = new ItineraryDayMockBuilder().addExternalStop("https://example.com",false).title("title").buildAsList();
        when(documentUtils.getAllowedDocuments(itinerary, Day.class)).thenReturn(days);
        when(bundle.getFindOutMoreAboutCta("title", Locale.UK)).thenReturn("Find out more about title");

        factory.buildItinerary(itinerary, Locale.UK);

        verify(linkService).createExternalLink(Locale.UK, "https://example.com", "Find out more about title", null);
    }

    @Test
    @DisplayName("The transports are populated")
    void transportsArePopulated() {
        when(itinerary.getTransports()).thenReturn(new String[]{"n", "s"});
        when(entryMapper.getEntry("n", ValueList.VS_ITINERARY_TRANSPORT)).thenReturn(new Entry("n","Car"));
        when(entryMapper.getEntry("s", ValueList.VS_ITINERARY_TRANSPORT)).thenReturn(new Entry("s","Ship"));

        ItineraryPage page = factory.buildItinerary(itinerary, Locale.UK);

        assertEquals(2, page.getTransports().size());
        assertEquals("Car", page.getTransports().get(0).getDisplayName());
        assertEquals("Ship", page.getTransports().get(1).getDisplayName());
    }

}
