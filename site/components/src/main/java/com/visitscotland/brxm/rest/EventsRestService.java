package com.visitscotland.brxm.rest;

import com.visitscotland.brxm.services.CommonUtilsService;
import com.visitscotland.brxm.services.HippoUtilsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * REST service for retrieving events data
 */
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventsRestService {

    private final HippoUtilsService hippoUtilsService;
    private final CommonUtilsService utils;

    private static final String DEFAULT_INDEX = "vs-events-synonyms-en";
    private static final Map<String, String> LOCALE_MAP = Map.of(
            "de", "vs-events-synonyms-de",
            "fr", "vs-events-synonyms-fr",
            "it", "vs-events-synonyms-it",
            "nl", "vs-events-synonyms-nl",
            "es", "vs-events-synonyms-es"
    );

    public EventsRestService(HippoUtilsService hippoUtilsService, CommonUtilsService utils) {
        this.hippoUtilsService = hippoUtilsService;
        this.utils = utils;
    }

    @GET
    @Path("/synonyms")
    public Object getSynonyms(
            @QueryParam("locale") @DefaultValue("en") String locale) {

        String synonymsIndex = getSynonymsIndex(locale);

        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> result = hippoUtilsService.getValueMap(synonymsIndex);

        response.put("hash", utils.generateJsonVersion(result));
        response.put("data", result);

        return response;
    }

    private String getSynonymsIndex(String locale) {
        if (locale == null || locale.isBlank()) {
            return DEFAULT_INDEX;
        }

        return LOCALE_MAP.getOrDefault(locale, DEFAULT_INDEX);
    }
}