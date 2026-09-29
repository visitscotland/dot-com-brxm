package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.favourites.dto.FavouritesResponse;
import com.visitscotland.brxm.favourites.dto.FavouritesRequest;
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
     * @param favouritesRequest
     * @return
     */
    @POST
    @Path("/get-favourites")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavourites(final FavouritesRequest favouritesRequest) {

        try {
            FavouritesResponse response = favouritesRepository.getFavouritesCards(favouritesRequest);
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
