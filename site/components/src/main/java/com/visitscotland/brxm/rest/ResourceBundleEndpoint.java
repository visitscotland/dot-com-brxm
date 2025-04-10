package com.visitscotland.brxm.rest;

import com.visitscotland.brxm.services.ResourceBundleService;

import org.hippoecm.hst.jaxrs.services.AbstractResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Locale;

@Path("/bundle")
public class ResourceBundleEndpoint extends AbstractResource {
    private final ResourceBundleService resourceBundleService;

    protected ResourceBundleEndpoint(ResourceBundleService resourceBundleService) {
        this.resourceBundleService = resourceBundleService;
    }

    @GET
    @Path("/{bundleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBundle(final @PathParam("bundleId") String bundleId,
                              final @QueryParam("locale") String locale) {
        final var parsedLocale = Locale.forLanguageTag(locale);
        final var result = resourceBundleService.getAllLabels(bundleId, parsedLocale);

        return Response.ok(result).build();
    }
}