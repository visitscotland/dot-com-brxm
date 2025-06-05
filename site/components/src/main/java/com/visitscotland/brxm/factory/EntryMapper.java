package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.factory.hippo.ValueList;
import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper {

    private static final Logger logger = LoggerFactory.getLogger(EntryMapper.class);

    private final HippoUtilsService hippoUtils;

    public EntryMapper(HippoUtilsService hippoUtils) {
        this.hippoUtils = hippoUtils;
    }

    public Entry getEntry(String key, ValueList valueList) {
        String displayText = hippoUtils.getValueMap(valueList.id()).get(key);
        if (displayText == null) {
            logger.warn("No display text found for the key '{}' in the Value List '{}'", key, valueList.id());
            displayText = key; // Fallback to using the key as display text
        }
        return new Entry(key, displayText);
    }

}
