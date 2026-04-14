package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.services.CommonUtilsService;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;


/**
 * REST endpoint for favourites service
 */
@Path("/favourites")
public class FavouritesRestService extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesRestService.class);

    private final FavouritesRepository favouritesRepository;
    private final CommonUtilsService utils;

    public FavouritesRestService(final FavouritesRepository favouritesRepository, final CommonUtilsService utils) {
        this.favouritesRepository = favouritesRepository;
        this.utils = utils;
    }

    @GET
    @Path("/")
    @Produces("text/plain")
    public Response health() {
        return Response.ok().entity("Favourites rest service - status OK!").build();
    }

    /**
     * Endpoint to return favourites cards based on UUIDs sent
     * @param request
     * @param locale
     * @param favouritesRequest
     * @return
     */
    @POST
    @Path("/get-favourites")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getFavouritesPostReq(@Context final HstRequest request,
                                  @DefaultValue("hst:root") @QueryParam("channel")
                                  final String locale, final FavouritesRequest favouritesRequest) {

        try {
            FavouritesCardResponse response = favouritesRepository.getFavouritesCards(favouritesRequest);
            if (response != null) {
                return Response.ok().entity(response).build();
            } else {
                return Response.serverError().entity("Could not process request.").build();
            }
        } catch (Exception e) {
            logger.error("Failed to get favourites cards", e);
            return Response.serverError().entity("Could not process request.").build();
        }

    }
}
