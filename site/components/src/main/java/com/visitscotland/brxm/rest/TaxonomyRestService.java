package com.visitscotland.brxm.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.onehippo.taxonomy.api.Category;
import org.onehippo.taxonomy.api.CategoryInfo;
import org.onehippo.taxonomy.api.Taxonomy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * REST service to retrieve the taxonomy for a specific section.
 */
@Path("/taxonomy")
@Produces(MediaType.APPLICATION_JSON)
public class TaxonomyRestService {

    private final HippoUtilsService hippoUtilsService;

    public TaxonomyRestService(HippoUtilsService hippoUtilsService) {
        this.hippoUtilsService = hippoUtilsService;
    }

    //TODO combine both endpoints
    @GET
    @Path("/category")
    public Object getTaxonomyCategoryGroups(
            @QueryParam("taxonomy")  String taxonomyId,
            @QueryParam("node") String node,
            @QueryParam("locale") @DefaultValue("en") String locale) {

        if (taxonomyId == null || taxonomyId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Query param 'taxonomy' is required")
                    .build();
        }

        if (node == null || node.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Query param 'node' is required")
                    .build();
        }


        Locale loc = Locale.forLanguageTag(locale);
        Taxonomy taxonomy =  hippoUtilsService.getTaxonomy(taxonomyId);
        if (taxonomy == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Taxonomy not found: " + taxonomyId)
                    .build();
        }
        Category parentCategory = taxonomy.getCategory(node);
        if (parentCategory == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Category not found: " + node)
                    .build();
        }


        List<? extends Category> childrenCategory = parentCategory.getChildren();

        List<Map<String, Object>> result = new ArrayList<>();
        for (Category cat : childrenCategory) {
            result.add(mapNodeSynonyms(cat, loc));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("version", getTaxonomyVersion(result));
        response.put("data", result);

        return response;
    }

    @GET
    @Path("/synonyms")
    public Object getTaxonomySynonyms(
            @QueryParam("taxonomy")  String taxonomyId,
            @QueryParam("node") String node,
            @QueryParam("locale") @DefaultValue("en") String locale) {

        if (taxonomyId == null || taxonomyId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Query param 'taxonomy' is required")
                    .build();
        }

        if (node == null || node.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Query param 'category' is required")
                    .build();
        }


        Locale loc = Locale.forLanguageTag(locale);
        Taxonomy taxonomy =  hippoUtilsService.getTaxonomy(taxonomyId);
        if (taxonomy == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Taxonomy not found: " + taxonomyId)
                    .build();
        }
        Category parentCategory = taxonomy.getCategory(node);
        if (parentCategory == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Category not found: " + node)
                    .build();
        }

        List<Map<String, Object>> result = new ArrayList<>();
        result.add(mapNodeSynonyms(parentCategory, loc));

        Map<String, Object> response = new HashMap<>();
        response.put("version", getTaxonomyVersion(result));
        response.put("data", result);

        return response;
    }



    /**
     * Maps a category to a JSON object with key first, then displayName, and children (if any).
     *
     * @param category Category to map.
     * @param locale   Locale to get the correct name.
     * @return LinkedHashMap representing the category and its children.
     */
    private Map<String, Object> mapNodeSynonyms(Category category, Locale locale) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        CategoryInfo categoryInfo = category.getInfo(locale);
        map.put("name", categoryInfo.getName());
        map.put("synonyms", categoryInfo.getSynonyms());

        return map;
    }

    /**
     * Generates a version identifier for a taxonomy dataset.
     *
     * <p>This method serializes the given data structure into a JSON string,
     * computes its MD5 hash, and encodes the result using Base64. The resulting
     * string can be used as a version fingerprint for the provided taxonomy data.</p>
     *
     * @param data a list of maps representing the taxonomy data to version;
     *             each map contains string keys and arbitrary object values
     * @return a Base64-encoded MD5 hash representing the version of the input data
     * @throws RuntimeException if the JSON serialization or hash generation fails
     */
    private String getTaxonomyVersion(List<Map<String, Object>> data) {
        try {
            String json = new ObjectMapper().writeValueAsString(data);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(json.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(digest);

        } catch (Exception e) {
            throw new RuntimeException("Cannot generate taxonomy version", e);
        }
    }

}