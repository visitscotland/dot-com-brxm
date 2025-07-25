package com.visitscotland.brxm.comparator;

import com.visitscotland.brxm.comparator.model.ComparisonModule;
import com.visitscotland.brxm.comparator.model.Feature;
import com.visitscotland.brxm.comparator.model.Provider;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.hippobeans.ComparatorFeature;
import com.visitscotland.brxm.hippobeans.ComparatorSystem;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.services.HippoUtilsService;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ComparisonMapper {

    private static final Logger logger = LoggerFactory.getLogger(ComparisonMapper.class);

    private final HippoQueryService queryService;

    public ComparisonMapper(HippoQueryService queryService) {
        this.queryService = queryService;
    }

    public ComparisonModule map(DevModule document) throws VsContractException, BrxmWrapperException {
        ComparisonModule module = new ComparisonModule(document);
        module.setFunctions(getFunctions());
        module.setProviders(getProviders());
        return module;
    }

    List<Provider> getProviders() throws BrxmWrapperException, VsContractException {
        List<Provider> entries = new ArrayList<>();
        try {
            HippoBeanIterator iterator = queryService.findAll(ComparatorSystem.class);

            while (iterator.hasNext()){
                ComparatorSystem hippoBean = (ComparatorSystem) iterator.nextHippoBean();
                entries.add(new Provider(hippoBean));
            }

            return entries;
        } catch (RepositoryException | QueryException e) {
            logger.error(e.getMessage(), e);
            throw new BrxmWrapperException(e);
        }
    }

    List<Feature> getFunctions() throws BrxmWrapperException, VsContractException {
        List<Feature> entries = new ArrayList<>();
        Map<String, String> valueList = VsComponentManager.get(
                HippoUtilsService.class).getValueMap("bsh-obs-features");
        Map<String, Feature> functions = getFunctionsMap();

        for (Map.Entry<String, String> entry : valueList.entrySet()) {
            if (functions.containsKey(entry.getKey())) {
                entries.add(functions.get(entry.getKey()));
            } else {
                entries.add(new Feature(entry.getKey(), entry.getValue()));
                logger.warn("Function {} is not defined", entry.getKey());
            }
        }

        return entries;
    }

    Map<String, Feature> getFunctionsMap() throws BrxmWrapperException, VsContractException {
        Map<String, Feature> map = new HashMap<>();

        Map<String, String> featureList = VsComponentManager.get(
                HippoUtilsService.class).getValueMap("bsh-obs-features");

        Map<String, String> groupList = VsComponentManager.get(
                HippoUtilsService.class).getValueMap("bsh-obs-groups");

        try {
            HippoBeanIterator iterator = queryService.findAll(ComparatorFeature.class);

            while (iterator.hasNext()){
                var document = (ComparatorFeature) iterator.nextHippoBean();
                map.put(document.getName(),new Feature(document, featureList.get(document.getId()), groupList.get(document.getCategory())));
            }

            return map;

        } catch (RepositoryException | QueryException e) {
            throw new BrxmWrapperException(e);
        }
    }
}
