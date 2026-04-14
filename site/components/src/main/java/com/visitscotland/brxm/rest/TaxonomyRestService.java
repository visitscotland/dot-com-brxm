package com.visitscotland.brxm.rest;

import com.visitscotland.brxm.services.CommonUtilsService;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.onehippo.taxonomy.api.Category;
import org.onehippo.taxonomy.api.CategoryInfo;
import org.onehippo.taxonomy.api.Taxonomy;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

/**
 * REST service for retrieving taxonomy categories and their synonyms.
 * This endpoint allows clients to retrieve a taxonomy node along with its synonyms,
 * and optionally traverse its children up to a given depth.
 */
@Path("/taxonomy")
@Produces(MediaType.APPLICATION_JSON)
public class TaxonomyRestService {

    private final HippoUtilsService hippoUtilsService;
    private final CommonUtilsService utils;
    private static final int MAX_DEPTH = 3;

    public TaxonomyRestService(HippoUtilsService hippoUtilsService, CommonUtilsService utils) {
        this.hippoUtilsService = hippoUtilsService;
        this.utils = utils;
    }
     /* Retrieves a taxonomy node and its synonyms, including its descendants
     * up to a maximum depth of 3 levels.
     *
     * @param taxonomyId the taxonomy identifier (path parameter)
     * @param node       the category key (path parameter)
     * @param locale     the locale used to resolve category names (default: "en")
     * @return a JSON response
     */
    @GET
    @Path("{taxonomyId}/{node}")
    public Object getTaxonomy(
            @PathParam("taxonomyId") String taxonomyId,
            @PathParam("node") String node,
            @QueryParam("locale") @DefaultValue("en") String locale) {

        if (taxonomyId == null || taxonomyId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Path param 'taxonomyId' is required")
                    .build();
        }

        if (node == null || node.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Path param 'node' is required")
                    .build();
        }

        Locale loc = Locale.forLanguageTag(locale);
        Taxonomy taxonomy = hippoUtilsService.getTaxonomy(taxonomyId);

        if (taxonomy == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Taxonomy not found: " + taxonomyId)
                    .build();
        }

        Category category = taxonomy.getCategory(node);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Category not found: " + node)
                    .build();
        }

        List<Map<String, Object>> result = new ArrayList<>();
        result.add(mapCategoryRecursive(category, loc, MAX_DEPTH));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("hash", utils.generateJsonVersion(result));
        response.put("data", result);

        return response;
    }
    /**
     * Recursively maps a category and its children into a JSON-friendly structure.
     *
     * @param category the category to map
     * @param locale   the locale used to resolve display values
     * @param depth    recursion depth control

     * @return a map representing the category, including its synonyms and optional children
     */
    private Map<String, Object> mapCategoryRecursive(Category category, Locale locale, int depth) {
        Map<String, Object> map = mapNodeSynonyms(category, locale);

        if (depth == 0) {
            return map;
        }

        List<Map<String, Object>> categories = new ArrayList<>();
        for (Category child : category.getChildren()) {
            int nextDepth = (depth == -1) ? -1 : depth - 1;
            categories.add(mapCategoryRecursive(child, locale, nextDepth));
        }

        if (!categories.isEmpty()) {
            map.put("categories", categories);
        }

        return map;
    }

    /**
     * Maps a category into a simple structure containing its name and synonyms.
     *
     * @param category the category to map
     * @param locale   the locale used to resolve the category display name
     * @return a map with name and synonyms
     */
    private Map<String, Object> mapNodeSynonyms(Category category, Locale locale) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        CategoryInfo categoryInfo = category.getInfo(locale);

        if (categoryInfo == null) {
            map.put("name", category.getKey());
            map.put("synonyms", Collections.emptyList());
            return map;
        }
        map.put("name", categoryInfo.getName());
        map.put("synonyms", categoryInfo.getSynonyms());

        return map;
    }
}