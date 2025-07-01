package com.visitscotland.brxm.obs;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.hippobeans.ObsFunction;
import com.visitscotland.brxm.hippobeans.ObsProvider;
import com.visitscotland.brxm.obs.model.ComparisonModule;
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
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparisonMapper {

    private static final Logger logger = LoggerFactory.getLogger(ComparisonMapper.class);

    public ComparisonModule map() {
        ComparisonModule module = new ComparisonModule();
        module.setFunctions(getFunctions());
        module.setProviders(getProviders());
        module.setFees(getFees());
        return module;
    }

    List<ContractFee> getFees(){
        List<ContractFee> entries = new ArrayList<>();
        Map <String, String> valueList = VsComponentManager.get(
                HippoUtilsService.class).getValueMap("obs-contract-fees");

        for (Map.Entry<String, String> entry : valueList.entrySet()) {
            entries.add(new ContractFee(entry.getKey(), entry.getValue()));
        }

        return entries;
    }


    List<Provider> getProviders(){
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
    private HippoBeanIterator findAllProviders() throws RepositoryException, QueryException {

        HstQueryManager hstQueryManager = getHstQueryManager();

        String mountContentPath = RequestContextProvider.get().getResolvedMount().getMount().getContentPath();
        Node mountContentNode = RequestContextProvider.get().getSession().getRootNode()
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

    List<Function> getFunctions(){
        List<Function> entries = new ArrayList<>();
        Map<String, String> valueList = VsComponentManager.get(
                HippoUtilsService.class).getValueMap("obs-features");
        Map<String, Function> functions = getFunctionsMap();

        for (Map.Entry<String, String> entry : valueList.entrySet()) {
            if (functions.containsKey(entry.getKey())) {
                entries.add(functions.get(entry.getKey()));
            } else {
                entries.add(new Function(entry.getKey(), entry.getValue()));
                logger.warn("Function {} not defined", entry.getKey());
            }
        }

        return entries;
    }

    Map<String, Function> getFunctionsMap(){
        Map<String, Function> map = new HashMap<>();

        try {
            HippoBeanIterator iterator = findAllFunctions();

            while (iterator.hasNext()){
                var document = (ObsFunction) iterator.nextHippoBean();
                map.put(document.getName(),new Function(document));
            }

        } catch (RepositoryException | QueryException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    private HippoBeanIterator findAllFunctions() throws RepositoryException, QueryException {

        HstQueryManager hstQueryManager = getHstQueryManager();

        String mountContentPath = RequestContextProvider.get().getResolvedMount().getMount().getContentPath();
        Node mountContentNode = RequestContextProvider.get().getSession().getRootNode()
                .getNode(PathUtils.normalizePath(mountContentPath));
        HstQuery hstQuery = hstQueryManager.createQuery(mountContentNode, ObsFunction.class);
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

    public HstQueryManager getHstQueryManager() throws RepositoryException {
        HstRequestContext requestContext = RequestContextProvider.get();
        return requestContext.getQueryManager(requestContext.getSession());
    }
}
