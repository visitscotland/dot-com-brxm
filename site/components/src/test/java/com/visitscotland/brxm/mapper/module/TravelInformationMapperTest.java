package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.hippobeans.TravelInformation;
import com.visitscotland.brxm.hippobeans.TravelInformationTab;
import com.visitscotland.brxm.hippobeans.TravelInformationTransportRow;
import com.visitscotland.brxm.mock.TravelInformationMockBuilder;
import com.visitscotland.brxm.mock.TravelInformationTabMockBuilder;
import com.visitscotland.brxm.mock.TravelInformationTransportRowMockBuilder;
import com.visitscotland.brxm.model.TravelInformationModule;
import com.visitscotland.brxm.model.TravelInformationTransportModule;
import com.visitscotland.brxm.model.TravelInformationTransportRowModule;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TravelInformationMapperTest {

    @Mock
    ResourceBundleService bundle;

    @InjectMocks
    TravelInformationMapper mapper;

    @DisplayName("Travel information module created correctly")
    @Test
    void travelInformationModuleCreated() {

        doReturn("Train").when(bundle).getResourceBundle(
                TravelInformationMapper.TRAVEL_INFO_TRANSPORTS_OPTIONS,
                "train",
                Locale.UK
        );

        doReturn("Cycling").when(bundle).getResourceBundle(
                TravelInformationMapper.TRAVEL_INFO_TRANSPORTS_OPTIONS,
                "cycling",
                Locale.UK
        );

        TravelInformationTransportRow trainRow =
                new TravelInformationTransportRowMockBuilder()
                        .copy("train row copy")
                        .transport("train")
                        .build();

        TravelInformationTransportRow cyclingRow =
                new TravelInformationTransportRowMockBuilder()
                        .copy("cycling row copy")
                        .transport("cycling")
                        .build();

        TravelInformationTab gettingAround =
                new TravelInformationTabMockBuilder()
                        .title("getting around tab title")
                        .addTransportRow(trainRow)
                        .build();

        TravelInformationTab gettingTo =
                new TravelInformationTabMockBuilder()
                        .title("getting to tab title")
                        .addTransportRow(trainRow)
                        .addTransportRow(cyclingRow)
                        .build();

        TravelInformation travelInformation =
                new TravelInformationMockBuilder()
                        .title("travel information title")
                        .copy("travel information copy")
                        .practicalInformation(gettingAround, gettingTo)
                        .build();

        TravelInformationModule module =
                mapper.getTravelInformation(
                        travelInformation,
                        Locale.UK
                );

        assertNotNull(module);

        // Main module
        assertEquals(
                "travel information title",
                module.getTitle()
        );

        assertEquals(
                "travel information copy",
                module.getCopy().getContent()
        );

        // Practical information
        assertNotNull(module.getPracticalInformation());

        assertEquals(
                2,
                module.getPracticalInformation().size()
        );

        // Getting around
        assertInstanceOf(
                TravelInformationTransportModule.class,
                module.getPracticalInformation().get(0)
        );

        TravelInformationTransportModule gettingAroundModule =
                (TravelInformationTransportModule)
                        module.getPracticalInformation().get(0);

        assertEquals(
                "getting around tab title",
                gettingAroundModule.getTitle()
        );

        assertEquals(
                1,
                gettingAroundModule
                        .getPracticalInformationContent()
                        .size()
        );

        TravelInformationTransportRowModule gettingAroundRow =
                gettingAroundModule
                        .getPracticalInformationContent()
                        .get(0);

        assertEquals(
                "train row copy",
                gettingAroundRow.getCopy().getContent()
        );

        assertEquals(
                "train",
                gettingAroundRow.getTransport().getKey()
        );

        assertEquals(
                "Train",
                gettingAroundRow.getTransport().getLabel()
        );

        // Getting to
        assertInstanceOf(
                TravelInformationTransportModule.class,
                module.getPracticalInformation().get(1)
        );

        TravelInformationTransportModule gettingToModule =
                (TravelInformationTransportModule)
                        module.getPracticalInformation().get(1);

        assertEquals(
                "getting to tab title",
                gettingToModule.getTitle()
        );

        assertEquals(
                2,
                gettingToModule
                        .getPracticalInformationContent()
                        .size()
        );

        TravelInformationTransportRowModule trainModule =
                gettingToModule
                        .getPracticalInformationContent()
                        .get(0);

        assertEquals(
                "train row copy",
                trainModule.getCopy().getContent()
        );

        assertEquals(
                "train",
                trainModule.getTransport().getKey()
        );

        assertEquals(
                "Train",
                trainModule.getTransport().getLabel()
        );

        TravelInformationTransportRowModule cyclingModule =
                gettingToModule
                        .getPracticalInformationContent()
                        .get(1);

        assertEquals(
                "cycling row copy",
                cyclingModule.getCopy().getContent()
        );

        assertEquals(
                "cycling",
                cyclingModule.getTransport().getKey()
        );

        assertEquals(
                "Cycling",
                cyclingModule.getTransport().getLabel()
        );
    }

    @DisplayName("When transport item does not exist in options, then key is used as label")
    @Test
    void travelItemKeyDoesNotExistInOptions() {

        TravelInformationTransportRow trainRow =
                new TravelInformationTransportRowMockBuilder()
                        .transport("key does not exist")
                        .build();

        TravelInformationTab gettingAround =
                new TravelInformationTabMockBuilder()
                        .addTransportRow(trainRow)
                        .build();

        TravelInformationTab gettingTo =
                new TravelInformationTabMockBuilder()
                        .build();

        TravelInformation travelInformation =
                new TravelInformationMockBuilder()
                        .practicalInformation(gettingAround, gettingTo)
                        .build();

        TravelInformationModule module =
                mapper.getTravelInformation(
                        travelInformation,
                        Locale.UK
                );

        TravelInformationTransportModule gettingAroundModule =
                (TravelInformationTransportModule)
                        module.getPracticalInformation().get(0);

        TravelInformationTransportRowModule row =
                gettingAroundModule
                        .getPracticalInformationContent()
                        .get(0);

        assertEquals(
                "key does not exist",
                row.getTransport().getKey()
        );

        assertEquals(
                "key does not exist",
                row.getTransport().getLabel()
        );
    }
}

