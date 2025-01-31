package com.visitscotland.brxm.services.search;

import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.hippobeans.Price;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class EventCardFactoryTest {
    @Mock private ResourceBundleService resourceBundleService;
    @Mock private EventBSH document;
    @Mock private Price price;

    private EventCardFactory eventCardFactory;

    private static final String ISO_4217_UK_CURRENCY_CODE = "GBP";

    @BeforeEach
    void setUp() {
        this.eventCardFactory = new EventCardFactory(resourceBundleService);
    }

    @Test
    void formatPrice_ValidPriceNoVat_ExpectedFormat() {
        final String expected = "10.00 GBP";

        when(document.getPrice()).thenReturn(price);
        when(price.getPrice()).thenReturn(10.00D);
        when(price.getCurrency()).thenReturn(ISO_4217_UK_CURRENCY_CODE);

        final EventCard result = eventCardFactory.createEventCard(document);

        Assertions.assertEquals(expected, result.getPrice());

        verify(document, times(1)).getPrice();
        verify(price, times(1)).getPrice();
        verify(price, times(1)).getCurrency();
    }

    @Test
    void formatPrice_ValidPriceWithVat_ExpectedFormat() {
        final String vatLabel = "+VAT";
        final String expected = "10.00 GBP " + vatLabel;

        when(document.getPrice()).thenReturn(price);
        when(price.getPrice()).thenReturn(10.00D);
        when(price.getCurrency()).thenReturn(ISO_4217_UK_CURRENCY_CODE);
        when(price.getVat()).thenReturn(true);
        when(resourceBundleService.getResourceBundle("event-listings", "vat", Locale.UK))
            .thenReturn(vatLabel);

        final EventCard result = eventCardFactory.createEventCard(document);

        Assertions.assertEquals(expected, result.getPrice());

        verify(resourceBundleService, times(1))
            .getResourceBundle(anyString(), anyString(), any(Locale.class));
    }

    @Test
    void formatPrice_PriceNull_ExpectNull() {
        when(document.getPrice()).thenReturn(null);
        final EventCard result = eventCardFactory.createEventCard(document);

        Assertions.assertNull(result.getPrice());

        verify(document, times(1)).getPrice();
        verify(price, never()).getCurrency();
    }

    @Test
    @DisplayName("Time - Display Start time only")
    void formatTimes_startTime(){

    }

    @Test
    @DisplayName("Time -  Display Start and End time")
    void formatTimes_bothTimes(){

    }
    @Test
    @DisplayName("Time - No time provided")
    void formatTimes_noTime(){

    }

    @Test
    @DisplayName("Time - Include timezone")
    void formatTimes_timezone(){

    }

    @Test
    @DisplayName("Time - Timezone provided but no actual time")
    void formatTimes_timezone_noTime(){

    }

    @Test
    @DisplayName("Date - No start Date provided")
    void formatDates_noStartDate(){

    }

    @Test
    @DisplayName("Date - No end Date provided")
    void formatDates_noEndDate(){

    }

    @Test
    @DisplayName("Date - Same year")
    void formatDates_sameYear(){

    }

    @Test
    @DisplayName("Date - Same month")
    void formatDates_sameMonth(){

    }

    @Test
    @DisplayName("Date - Same start and end date")
    void formatDates_sameDates(){

    }

}
