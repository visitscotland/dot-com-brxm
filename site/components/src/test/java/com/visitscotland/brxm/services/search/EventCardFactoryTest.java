package com.visitscotland.brxm.services.search;

import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.hippobeans.EventBSH;
import com.visitscotland.brxm.hippobeans.Price;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class EventCardFactoryTest {
    @Mock private ResourceBundleService resourceBundleService;
    @Mock private EventBSH eventBSH;
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

        when(eventBSH.getPrice()).thenReturn(price);
        when(price.getPrice()).thenReturn(10.00D);
        when(price.getCurrency()).thenReturn(ISO_4217_UK_CURRENCY_CODE);

        final EventCard result = eventCardFactory.createEventCard(eventBSH);

        Assertions.assertEquals(expected, result.getPrice());
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

}
