package com.visitscotland.brxm.event;

import com.visitscotland.brxm.utils.VsException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/bsh/events-search/")
class EventsRestResource extends AbstractResource {
    private static final Logger logger = LoggerFactory.getLogger(EventsRestResource.class);

    private final EventRepository eventRepository;

    public EventsRestResource(final EventRepository eventService) {
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
    public Response trainingEvents(@Context HstRequest request) {
        try {
            return Response.ok().entity(eventRepository.findTrainingEvents()).build();
        } catch (VsException e) {
            logger.error("Error while fetching training events", e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("industry")
    @Produces("application/json")
    public Response industryEvents(@Context HstRequest request) {
        try {
            return Response.ok().entity(eventRepository.findIndustryEvents()).build();
        } catch (VsException e) {
            logger.error("Error while fetching industry events", e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("travel-trade")
    @Produces("application/json")
    public Response travelTradeEvents(@Context HstRequest request) {
        try {
            return Response.ok().entity(eventRepository.findTravelTradeEvents()).build();
        } catch (VsException e) {
            logger.error("Error while fetching travel trade events", e);
            return Response.serverError().build();
        }
    }
}
