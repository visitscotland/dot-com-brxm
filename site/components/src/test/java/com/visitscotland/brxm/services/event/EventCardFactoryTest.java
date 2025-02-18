package com.visitscotland.brxm.services.event;

import com.visitscotland.brxm.event.PriceFormatter;
import com.visitscotland.brxm.rest.event.EventCardFactory;
import com.visitscotland.brxm.services.ResourceBundleService;

import com.visitscotland.brxm.utils.ContentLogger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class EventCardFactoryTest {
    @Mock private ResourceBundleService resourceBundleService;
    @Mock private ContentLogger contentLogger;
    @Mock private PriceFormatter priceFormatter;

    @InjectMocks
    private EventCardFactory eventCardFactory;

    @BeforeEach
    void setUp() {
        this.eventCardFactory = new EventCardFactory(resourceBundleService, priceFormatter, contentLogger);
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
