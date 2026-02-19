package com.visitscotland.brxm.favourites;

import com.visitscotland.brxm.services.CommonUtilsService;
import com.visitscotland.brxm.utils.VsException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*; // TODO - individual imports
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * TODO - Complete this
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
    @Produces()
    public Response health() {
        return Response.ok().entity("Favourites rest service - status OK!").build();
    }

    // test with GET first and then check POST
    @GET
    @Path("/favourites-list-get")
    @Produces(/*"application/json"*/)
    public Response getFavouritesGet(@Context final HstRequest request,
                             @DefaultValue("hst:root") @QueryParam("channel") final String locale/*, List<String> uuids*/) {
        try {
            //List<FavouritesCard> favouritesCards = favouritesRepository.getFavouritesCards(uuids);
            return Response.ok().build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    /*@POST
    @Path("/favourites-list-post")
    @Consumes("application/json")
    //@Produces("application/json")
    public Response getFavouritesPost(@Context final HstRequest request,
                                  @DefaultValue("hst:root") @QueryParam("channel") final String locale) {
        return Response.ok().entity("Favourites rest service - my-favs").build();
    }*/
}
