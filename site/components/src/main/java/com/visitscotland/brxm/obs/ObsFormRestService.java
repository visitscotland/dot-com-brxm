package com.visitscotland.brxm.obs;

import com.visitscotland.brxm.obs.form.Form;
import com.visitscotland.brxm.obs.model.FormBuilder;
import com.visitscotland.brxm.obs.model.Function;
import com.visitscotland.brxm.obs.model.Provider;
import com.visitscotland.brxm.obs.model.SpreadSheetRequest;
import com.visitscotland.brxm.utils.VsException;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Note: These endpoint don't use Spring but JAX-RS for mapping to the main application
 *
 * https://en.wikipedia.org/wiki/Jakarta_RESTful_Web_Services#Implementations
 */
@Path("/obs/form")
public class ObsFormRestService extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(ObsFormRestService.class);

    @GET
    @Path("/")
    @Produces()
    public Response health() {
        return Response.ok().entity("status OK!").build();
    }

    @GET
    @Path("/json-config")
    @Produces("application/json")
    public Response list(@Context HstRequest request,
                         @DefaultValue("false") @QueryParam("checkboxes") Boolean checkboxes,
                         @DefaultValue("") @QueryParam("category") String category) {
        try {
            return Response.ok().entity(buildForm(category, checkboxes)).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    private Form buildForm(String category, Boolean checkboxes) {
        FormBuilder builder = FormBuilder.create()
                .input("Business Name", true)
                .email("Email Address", true)
                .input("Website URL", false)
                .input("SubSector", false)
                .input("Location", false);

        for (Function f : new ComparisonMapper().getFunctions()) {
            if (Boolean.TRUE.equals(checkboxes)) {
                builder.checkbox(f.getDescription(), f.getId());
            } else if (Contract.isEmpty(category) || in(category.toLowerCase(), f.getCategory())){
                builder.selectImportance(f.getDescription(), f.getId(), true);
            }
        }

        return builder.build();
    }

    private boolean in(String value, String[] list){
        if (list != null) {
            for (String elm : list) {
                if (value.trim().equalsIgnoreCase(elm)) {
                    return true;
                }
            }
        }
        return false;
    }

    @POST
    @Path("/shortlist")
    @Produces("application/json")
    public Response shortlist(@Context HstRequest request,
                              @RequestBody Map<String, String> body
    ) {
        try {
            return Response.ok().entity(generateRequestBody(body)).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }


    private SpreadSheetRequest generateRequestBody(Map<String, String> body){
        SpreadSheetRequest requestBody = new SpreadSheetRequest();
        requestBody.setFunctions(new ComparisonMapper().getFunctions());
        requestBody.setProviders(shortlist(body));

        return requestBody;
    }

    private List<Provider> shortlist(Map<String, String> body){
        List<Provider> provider = new ComparisonMapper().getProviders();
        String[] allFunctions = getAllFunctions();
        List<Provider> shortlist = new ArrayList<>();

        for (Provider p : provider) {
            boolean include = true;
            for (Map.Entry<String, String> entry: body.entrySet()){
                if (entry.getValue().equals("true") &&
                        !in(entry.getKey(), p.getFunctions()) &&
                        in(entry.getKey(), allFunctions)){
                    include = false;
                    break;
                }
            }
            if (include) {
                shortlist.add(p);
            }
        }
        return shortlist;

    }

    private String[] getAllFunctions(){
        return new ComparisonMapper().getFunctions().stream().map(
                function -> function.id
        ).toArray(String[]::new);
    }


}
