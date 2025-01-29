package com.visitscotland.brxm.rest;

import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.model.bsh.EventCard;
import com.visitscotland.brxm.services.search.EventRepository;
import com.visitscotland.brxm.utils.VsException;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
//    "/industry"
//    "/travel-trade"
//    "/training"
    @Path("training")
    @Produces("application/json")
    public Response training(@Context HstRequest request) {
        try {
            return Response.ok().entity(eventRepository.findAllTrainingEvents()).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    private EventCard createEvent1(){
        EventCard ec = new EventCard();
        FlatLink fl = new FlatLink("Find out more", "http://localhost:3000/site/sandbox", LinkType.EXTERNAL);
        ec.setTitle("Event 1");
        ec.setSummary(new HippoHtml());
        ec.setDates("28 Feb - 03 Mar, 2025");
        ec.setTimes("12:00 PST");
        ec.setPrice("Free");
        ec.setLocation("Glasgow");
        ec.setOrganizer("Software Engineering Team :)");
        ec.setCta(fl);

        return ec;
    }

    private EventCard createEvent2(){
        EventCard ec = new EventCard();
        FlatLink fl = new FlatLink("Find out more", "http://localhost:3000/site/sandbox", LinkType.EXTERNAL);
        ec.setTitle("Event 2");
//        ec.setDescription(new HippoHtmlWrapper(new HippoHtml (), "<p> Vivamus bibendum elit ac magna vestibulum tristique. Proin imperdiet sit amet augue egestas venenatis. Donec et nunc in turpis tincidunt molestie a bibendum mi. Vestibulum auctor ut lectus eget aliquet. Vestibulum rhoncus enim blandit sapien finibus congue. Donec et nunc in turpis tincidunt molestie a bibendum mi. Vestibulum auctor ut lectus eget aliquet. Vestibulum rhoncus enim blandit sapien finibus congue. honcus enim blandit sapien finbus co. </p>"));
        ec.setDates("2 Mar 2025");
        ec.setTimes("Check the organizer for more details");
        ec.setPrice("400 GBP + VAT");
        ec.setLocation("Online");
        ec.setCta(fl);

        return ec;
    }

    private EventCard createEvent3(){
        EventCard ec = new EventCard();
        FlatLink fl = new FlatLink("Find out more", "http://localhost:3000/site/sandbox", LinkType.EXTERNAL);
        ec.setTitle("This is the Super event of 2025. Make sure you don't miss it out or else");
//        ec.setDescription(new HippoHtmlWrapper(new HippoHtml (), "<p> Vivamus bibendum elit ac magna vestibulum tristique. Proin imperdiet sit amet augue egestas venenatis. Donec et nunc in turpis tincidunt molestie a bibendum mi. Vestibulum auctor ut lectus eget aliquet. Vestibulum rhoncus enim blandit sapien finibus congue. Donec et nunc in turpis tincidunt molestie a bibendum mi. Vestibulum auctor ut lectus eget aliquet. Vestibulum rhoncus enim blandit sapien finibus congue. honcus enim blandit sapien finbus co. </p>"));
        ec.setDates("31 Mar 2025 - 20 Apr 2026");
        ec.setTimes("Check the organizer for more details");
        ec.setLocation("Kingdom of Fife");
        ec.setCta(fl);

        return ec;
    }
}
