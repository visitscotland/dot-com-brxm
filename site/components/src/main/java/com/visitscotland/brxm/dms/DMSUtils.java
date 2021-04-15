package com.visitscotland.brxm.dms;

import com.fasterxml.jackson.databind.JsonNode;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.dataobjects.DataType;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.visitscotland.brxm.dms.DMSConstants.DMSProduct.*;

@Component
public class DMSUtils {

    public static final String ITINERARY_BUNDLE = "itinerary";

    private ResourceBundleService bundle;

    public DMSUtils(ResourceBundleService bundle) {
        this.bundle = bundle;
    }

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

    /**
     * TODO this method returns the current open state, and it could be affected by the cache, ask WEBOPS and move it to front end if needed
     * TODO This method is only intended for Itineraries? Should be part of itineraries factory? Otherwise, the bundle keys should be more general
     *
     * TODO to be removed after the refactoring of itineraries
     *
     * @deprecated
     */
    @Deprecated
    public String currentOpenStatus(String starTime, String endTime, Locale locale) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
        LocalTime starts = LocalTime.parse(starTime, formatter);
        LocalTime ends = LocalTime.parse(endTime, formatter);
        LocalTime currentTime = LocalTime.now(ZoneId.of("+1"));

        if (currentTime.isAfter(starts) && currentTime.isBefore(ends)) {
            if (currentTime.plusMinutes(30).isAfter(ends)) {
                return bundle.getResourceBundle(ITINERARY_BUNDLE, "stop.close.soon", locale);
            } else {
                return bundle.getResourceBundle(ITINERARY_BUNDLE, "stop.open", locale);
            }
        } else {
            return bundle.getResourceBundle(ITINERARY_BUNDLE, "stop.closed", locale);
        }
    }

    /**
     * Returns the Opening times text depending on the information returned by the product card or null if that data
     * cannot be inferred
     *
     * TODO: UNIT TEST
     */
    public String setOpeningTimes(JsonNode product, Locale locale){
        if (product.has(OPENING)) {
            JsonNode opening = product.get(OPENING);

            //TODO adjust the message to designs when ready
            if ((opening.has(OPENING_STATE)) && (!opening.get(OPENING_STATE).asText().equalsIgnoreCase("unknown"))) {
                String openingMessage = bundle.getResourceBundle(ITINERARY_BUNDLE, !opening.get(OPENING_PROVISIONAL).asBoolean() ? "usually" : "provisionally",locale)
                        + " " + opening.get(OPENING_STATE).asText() + " " + opening.get(OPENING_DAY).asText();
                if ((opening.has(START_TIME)) && (opening.has(END_TIME))) {
                    return openingMessage + ": " + opening.get(START_TIME).asText() + "-" + opening.get(END_TIME).asText();
                } else {
                    return openingMessage;
                }
            }
        }
        return null;
    }
}
