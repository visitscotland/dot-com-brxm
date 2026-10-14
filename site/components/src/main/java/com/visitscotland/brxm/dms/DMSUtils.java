package com.visitscotland.brxm.dms;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.dms.model.DataType;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.*;

@Component
public class DMSUtils {

    public List<DataType> getKeyFacilities(JsonNode product){
        List<DataType> facilities = new ArrayList<>();
        if (product.has(FACILITIES) && product.get(FACILITIES).isArray()){
            for (JsonNode facility : product.get(FACILITIES)) {
                DataType dataType = new DataType(facility.get(ID).asText(),facility.get(NAME).asText());
                facilities.add(dataType);
            }
        }
        return facilities;
    }
}
