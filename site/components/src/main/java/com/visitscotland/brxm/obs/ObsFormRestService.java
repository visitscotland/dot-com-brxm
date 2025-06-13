package com.visitscotland.brxm.obs;

import com.visitscotland.brxm.obs.form.Form;
import com.visitscotland.brxm.obs.model.FormBuilder;
import com.visitscotland.brxm.obs.model.Function;
import com.visitscotland.brxm.utils.VsException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Note: These endpoint don't use Spring but JAX-RS for mapping to the main application
 *
 * https://en.wikipedia.org/wiki/Jakarta_RESTful_Web_Services#Implementations
 */
@Path("/obs/")
public class ObsFormRestService extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(ObsFormRestService.class);

    @GET
    @Path("/")
    @Produces()
    public Response health() {
        return Response.ok().entity("status OK!").build();
    }

    @GET
    @Path("/form")
    @Produces("application/json")
    public Response list(@Context HstRequest request,
                             @DefaultValue("hst:root") @QueryParam("channel") String locale) {
        try {
            return Response.ok().entity(buildForm()).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    private Form buildForm() {
        FormBuilder builder = FormBuilder.create()
                .input("Business Name", true)
                .email("Email Address", true)
                .input("Website URL", false)
                .input("SubSector", false)
                .input("Location", false);

        for (Function f : new ComparisonMapper().getFunctions()) {
            builder.selectImportance(f.getDescription(), f.getId(), true);
        }

        return builder.build();
    }
}
