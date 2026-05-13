package com.visitscotland.brxm.rest;

import com.visitscotland.brxm.services.CommonUtilsService;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.utils.Contract;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * REST service for retrieving events data
 */
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class VsEventsRestService {

    private final HippoUtilsService hippoUtilsService;
    private final CommonUtilsService utils;
    private final ResourceBundleService bundle;

    private static final String DEFAULT_SYNONYMS_INDEX = "vs-events-synonyms-en";
    private static final String LOCATIONS_INDEX = "vs-events-filters-locations";
    private static final Map<String, String> LOCALE_MAP = Map.of(
            "de", "vs-events-synonyms-de",
            "fr", "vs-events-synonyms-fr",
            "it", "vs-events-synonyms-it",
            "nl", "vs-events-synonyms-nl",
            "es", "vs-events-synonyms-es"
    );

    public VsEventsRestService(HippoUtilsService hippoUtilsService, CommonUtilsService utils, ResourceBundleService bundle) {
        this.hippoUtilsService = hippoUtilsService;
        this.utils = utils;
        this.bundle = bundle;
    }

    @GET
    @Path("/synonyms")
    public Map<String, Object> getSynonyms(
            @QueryParam("locale") @DefaultValue("en") String locale) {

        String synonymsIndex = getSynonymsIndex(locale);

        if (Contract.isEmpty(synonymsIndex)) {
            throw new NotFoundException("No synonyms index found for locale: " + locale);
        }

        Map<String, String> result = hippoUtilsService.getValueMap(synonymsIndex);

        if (result == null || result.isEmpty()) {
            throw new NotFoundException("No synonyms found for locale: " + locale);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("hash", utils.generateJsonVersion(result));
        response.put("data", result);

        return response;
    }

    private String getSynonymsIndex(String locale) {
        if (locale == null || locale.isBlank()) {
            return DEFAULT_SYNONYMS_INDEX;
        }

        return LOCALE_MAP.getOrDefault(locale, DEFAULT_SYNONYMS_INDEX);
    }

    @GET
    @Path("/locations")
    public Map<String, Object> getLocations( @QueryParam("locale") @DefaultValue("en") String locale){
        Map<String, String> result = hippoUtilsService.getValueMap(LOCATIONS_INDEX);

        if (result == null || result.isEmpty()) {
            throw new NotFoundException("No locations found for events");
        }

        Map<String, String> transformedData = new LinkedHashMap<>();

        for (Map.Entry<String, String> entry : result.entrySet()) {
            String code = entry.getValue();
            String label = bundle.getResourceBundle("search-events-locations", entry.getKey(), locale);
            transformedData.put(code, label != null ? label : entry.getKey());
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("hash", utils.generateJsonVersion(transformedData));
        response.put("data", transformedData);

        return response;
    }
}