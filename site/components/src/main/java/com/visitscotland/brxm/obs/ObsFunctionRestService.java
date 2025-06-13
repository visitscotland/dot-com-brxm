package com.visitscotland.brxm.obs;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.hippobeans.ObsProvider;
import com.visitscotland.brxm.obs.model.ContractFee;
import com.visitscotland.brxm.obs.model.Function;
import com.visitscotland.brxm.obs.model.Provider;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.utils.VsException;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.hippoecm.hst.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
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
@Path("/obs/function")
public class ObsFunctionRestService extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(ObsFunctionRestService.class);

    @GET
    @Path("/")
    @Produces()
    public Response health() {
        return Response.ok().entity("status OK!").build();
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public Response list(@Context HstRequest request,
                             @DefaultValue("hst:root") @QueryParam("channel") String locale) {
        try {
            return Response.ok().entity(getFunctions()).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/fees")
    @Produces("application/json")
    public Response fees(@Context HstRequest request,
                         @DefaultValue("hst:root") @QueryParam("channel") String locale) {
        try {
            return Response.ok().entity(getFees()).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    private List<Function> getFunctions(){
        List<Function> entries = new ArrayList<>();
        Map <String, String> valueList = VsComponentManager.get(
                    HippoUtilsService.class).getValueMap("obs-features");

        for (Map.Entry<String, String> entry : valueList.entrySet()) {
            entries.add(new Function(entry.getKey(), entry.getValue()));
        }

        return entries;
    }

    private List<ContractFee> getFees(){
        List<ContractFee> entries = new ArrayList<>();
        Map <String, String> valueList = VsComponentManager.get(
                HippoUtilsService.class).getValueMap("obs-contract-fees");

        for (Map.Entry<String, String> entry : valueList.entrySet()) {
            entries.add(new ContractFee(entry.getKey(), entry.getValue()));
        }

        return entries;
    }
}
