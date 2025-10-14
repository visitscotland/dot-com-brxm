package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.factory.hippo.ValueList;
import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EntryMapperTest {

    @Mock
    private HippoUtilsService hippoUtils;

    @InjectMocks
    private EntryMapper entryMapper;

    private static final String KEY = "train";
    private static final String EXPECTED_DISPLAY_TEXT = "Locomotive";
    private static final ValueList VALUELIST_ITEM = ValueList.VS_ITINERARY_TRANSPORT;


    @Test
    @DisplayName("When the key exists, the display name is printed")
    void when_KeyExistsInValueMap_getEntry_shouldReturnEntryWithDisplayText() {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put(KEY, EXPECTED_DISPLAY_TEXT);

        when(hippoUtils.getValueMap(VALUELIST_ITEM.id())).thenReturn(valueMap);

        Entry result = entryMapper.getEntry(KEY, VALUELIST_ITEM);

        assertNotNull(result);
        assertEquals(KEY, result.getKey());
        assertEquals(EXPECTED_DISPLAY_TEXT, result.getDisplayName());
    }

    @Test
    @DisplayName("When the key is not found, the display name is the same as the key")
    void whenKeyDoesNotExistInValueMap_getEntry_shouldReturnEntryWithKeyAsDisplayText() {
        Map<String, String> valueMap = new HashMap<>();

        when(hippoUtils.getValueMap(VALUELIST_ITEM.id())).thenReturn(valueMap);

        Entry result = entryMapper.getEntry(KEY, VALUELIST_ITEM);

        assertNotNull(result);
        assertEquals(KEY, result.getKey());
        assertEquals(KEY, result.getDisplayName());
    }
}