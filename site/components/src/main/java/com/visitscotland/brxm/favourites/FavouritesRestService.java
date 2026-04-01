package com.visitscotland.brxm.favourites;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * REST endpoint for favourites service
 */
@Path("/favourites")
public class FavouritesRestService extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesRestService.class);

    private final FavouritesRepository favouritesRepository;

    public FavouritesRestService(final FavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
