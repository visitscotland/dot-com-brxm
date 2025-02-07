package com.visitscotland.brxm.rest;

import com.visitscotland.brxm.services.event.EventRepository;
import com.visitscotland.brxm.utils.VsException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Note: These endpoint don't use Spring but JAX-RS for mapping to the main application
 */
@Path("/bsh/events-search/")
public class EventsSearchService extends AbstractResource {
    private final EventRepository eventRepository;

    public EventsSearchService(final EventRepository eventService) {
        this.eventRepository = eventService;
    }

    @GET
    @Path("/")
    public Response health() {
        return Response.ok().entity("status OK!").build();
    }

    @GET
    @Path("training")
    @Produces("application/json")
    public Response trainingEvents(
            @Context HstRequest request) {
        try {
            return Response.ok().entity(eventRepository.findAllTrainingEvents()).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("industry")
    @Produces("application/json")
    public Response industryEvents(@Context HstRequest request) {
        try {
            //TODO: BSHUB-583
            return null;
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("travel-trade")
    @Produces("application/json")
    public Response travelTradeEvents(@Context HstRequest request) {
        try {
            //TODO: BSHUB-584
            return null;
        } catch (VsException e){
            return Response.serverError().build();
        }
    }
}
