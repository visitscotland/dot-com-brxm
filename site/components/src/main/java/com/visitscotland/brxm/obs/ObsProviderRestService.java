package com.visitscotland.brxm.obs;

import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.obs.model.Provider;
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

/**
 * Note: These endpoint don't use Spring but JAX-RS for mapping to the main application
 *
 * https://en.wikipedia.org/wiki/Jakarta_RESTful_Web_Services#Implementations
 */
@Path("/obs/provider")
public class ObsProviderRestService extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(ObsProviderRestService.class);

    @GET
    @Path("/")
    @Produces()
    public Response health() {
        return Response.ok().entity("status OK!").build();
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public Response fragment(@Context HstRequest request,
                             @DefaultValue("hst:root") @QueryParam("channel") String locale) {
        try {
            return Response.ok().entity(getProviders()).build();
        } catch (VsException e){
            return Response.serverError().build();
        }
    }

    private List<Provider> getProviders(){
        List<Provider> entries = new ArrayList<>();
        try {
            HippoBeanIterator iterator = findAllProviders();

            while (iterator.hasNext()){
                ObsProvider hippoBean = (ObsProvider) iterator.nextHippoBean();
                entries.add(new Provider(hippoBean));
            }

        } catch (RepositoryException | QueryException e) {
            throw new RuntimeException(e);
        }

        return entries;
    }

    //TODO REfactor
    private HippoBeanIterator findAllProviders() throws RepositoryException, QueryException {
        HstRequestContext requestContext = RequestContextProvider.get();
        HstQueryManager hstQueryManager =
                getHstQueryManager(requestContext.getSession(),
                        requestContext);

        String mountContentPath = getMountforChannel("bsh").getContentPath();
        Node mountContentNode = requestContext.getSession().getRootNode()
                .getNode(PathUtils.normalizePath(mountContentPath));
        HstQuery hstQuery = hstQueryManager.createQuery(mountContentNode, ObsProvider.class);
        Filter filter = hstQuery.createFilter();
        //TODO Use Constants for these fields
        // Only documents of published type (green)
        filter.addEqualTo("hippostd:state", "published");
        // Only documents that are actually live and (opposing to those taken offline)
        filter.addEqualTo("hippostd:stateSummary", "live");
        hstQuery.setFilter(filter);

        HstQueryResult result = hstQuery.execute();

        return result.getHippoBeans();
    }

    /**
     * TODO Move to Utils
     * @param channel
     * @return
     */
    public Mount getMountforChannel(String channel){
        HstRequestContext context = RequestContextProvider.get();
        String currentHost = context.getVirtualHost().getHostGroupName();

        Mount rootMount = context.getResolvedMount().getMount().getParent();

        if (channel.equals("hst:root")){
            return rootMount;
        } else {
            Mount lang = rootMount.getChildMount(channel);
            if (lang == null) {
                logger.warn("The mount point for the channel {} was not located. Defaulting to English", channel);
                throw new VsException("The requested channel was not found");
            } else {
                return lang;
            }
        }
    }

}
